# k-copy 文件复制demo项目
### 1. k-copy项目简介

本项目旨在制作一个可以把A文件夹中的文件复制到指定的B文件夹当中，并对其中要复制的文件，做一定操作的项目，用来学习和应用JAVA IO 文件流读写、File相关类API操作等等

### 2. 项目基本操作和使用

#### 2.1 基本使用

```java
// 通过CopyHandlerFactory获取一个FileHandler操作类
AbstractFileHandler abstractFileHandler = CopyHandlerFactory.getFileHandler(CopyHandlerFactory.NORMAL);
// 调用copyAllFile方法，将A文件夹中所有文件和子目录中的文件复制到B文件夹中
abstractFileHandler.copyAllFile(new File("A文件夹路径"), new File("B文件夹路径"));
```

#### 2.2 CopyHandlerFactory可用常量

```java
1. CopyHandlerFactory.NORMAL // 通过普通的java io 流进行复制
2. CopyHandlerFactory.CHANNEL // 通过java nio 文件通道的方式进行复制,速度会比普通的方式快
3. CopyHandlerFactory.THREAD // 通过创建多个线程的方式进行复制，一般用于复制单个超大文件
```

#### 2.3 copyAllFile 相关参数讲解

```java
abstractFileHandler.copyAllFile(oldPath, 
newPath, 
newSuffix, 
newPrefix, 
newName, 
fileType, 
needCopyNames);
```

copyAllFile 除了最开始的A文件夹和B文件夹两个参数外，还可以自定义5个参数，这5个参数分别有如下作用,我们以复制a.txt和b.txt文件来举例

##### 2.3.1 newSuffix 新的后缀

如果newSuffix不为空的话，则会将复制过去的文件的文件名名的末尾新增上newSuffix

```java
AbstractFileHandler abstractFileHandler = CopyHandlerFactory.getFileHandler(CopyHandlerFactory.NORMAL);
abstractFileHandler.copyAllFile(oldPath, 
newPath, 
"test", 
newPrefix, 
newName, 
fileType, 
needCopyNames);
// a.txt ---> atest.txt
// b.txt ---> btest.txt
```

##### 2.3.2 newPrefix 新的前缀

如果newPrefix不为空的话，则会将复制过去的文件的文件名名的前面新增上newPrefix

```java
AbstractFileHandler abstractFileHandler = CopyHandlerFactory.getFileHandler(CopyHandlerFactory.NORMAL);
abstractFileHandler.copyAllFile(oldPath, 
newPath, 
newSuffix, 
"test", 
newName, 
fileType, 
needCopyNames);
// a.txt ---> testa.txt
// b.txt ---> testb.txt
```

##### 2.3.3 newName 新的名称

如果newName不为空的话，会将文件夹下的所有文件重命名为newName，并且newSuffix和newPrefix将不在起作用,并且会在文件的名称后面累加上数字标记，以1开始，如果文件只有一个，则不加数字标记

```java
AbstractFileHandler abstractFileHandler = CopyHandlerFactory.getFileHandler(CopyHandlerFactory.NORMAL);
abstractFileHandler.copyAllFile(oldPath, 
newPath, 
newSuffix, 
newPrefix, 
"test", 
fileType, 
needCopyNames);
// a.txt ---> test1.txt
// b.txt ---> test2.txt
```

##### 2.3.4 fileType 文件类型

如果fileType 不为空的话，会将文件夹下的所有文件的类型重置为fileType声明的类型,注意文件类型要以.开头，不能包含文件后缀不允许的字符

```java
AbstractFileHandler abstractFileHandler = CopyHandlerFactory.getFileHandler(CopyHandlerFactory.NORMAL);
abstractFileHandler.copyAllFile(oldPath, 
newPath, 
newSuffix, 
newPrefix, 
newName, 
".java", 
needCopyNames);
// a.txt ---> a.java
// b.txt ---> b.java
```

##### 2.3.5 needCopyNames 需要复制的文件

如果needCopyNames 不为空的话，复制文件时会匹配needCopyNames 中的字符，如果匹配通过，该文件才会复制

```java
AbstractFileHandler abstractFileHandler = CopyHandlerFactory.getFileHandler(CopyHandlerFactory.NORMAL);
List<String> needCopyNamesArray = new ArrayList();
needCopyNamesArray.add("(.*)txt");
abstractFileHandler.copyAllFile(oldPath, 
newPath, 
newSuffix, 
newPrefix, 
newName, 
fileType, 
needCopyNamesArray);
// a.txt ---> a.txt
// b.txt ---> b.txt
```

这里(.*)txt会匹配所有以txt结尾的文件名称，a.txt和b.txt都是以txt结尾，所以可以被复制，新增到needCopyNamesArray可以是正则表达式，也可以是单纯的文件名称，所有添加进列表中的匹配字符都会依次与文件名进行匹配，匹配通过的文件将会被复制

### 3. 使用配置文件

项目提供CopyConfig类来自动加载配置文件的对象,可以将copyFileAll所需要的参数统一加载进程序中，使用方式如下:

1. 编写如下代码，加载配置到CopyModel对象中，并传给CopyAllFile方法使用

```java
// 加载配置到CopyModel类中
CopyModel copyModel = CopyConfig.loadCopyModel();
AbstractFileHandler abstractFileHandler = CopyHandlerFactory.getFileHandler(CopyHandlerFactory.CHANNEL);
abstractFileHandler.copyAllFile(
new File(copyModel.getSourcePath()),
new File(copyModel.getTargetPath()), 
copyModel.getNewSuffix(), 
copyModel.getNewPrefix(), 
copyModel.getNewName(), 
copyModel.getNewFileType(), 
copyModel.getNeedCopyNames() == null ? null : Arrays.stream(copyModel.getNeedCopyNames().split(",")).collect(Collectors.toList()));
// 这里需要注意的是needCopyNamesArray,由于配置文件中我们配置的是字符串，以”，“分隔，这里我们需要以”，“分隔将字符串分隔为列表
// 也可以直接将CopyModel作为参数传递
abstractFileHandler.copyAllFile(copyModel);
```

2. 在项目的resources目录中，新建config.properties文件, CopyConfig.loadCopyModel()会加载config.propties文件,如果配置文件不存在，则会抛出配置文件不存在异常
3. config.properties编写,和上面的基本配置一样，needCopyNames的值用"，"分隔

```
sourcePath=E://test//a
targetPath=E://test//b
newSuffix=新的前缀
newPrefix=新的后缀
newName=新的名称
newFileType=.javatxt
needCopyNames=(.*)java,(.*)txt
```

