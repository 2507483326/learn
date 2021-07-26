package com.epat.decorators;

/**
 * @author 李涛
 * @date : 2021/7/14 17:49
 */
public interface DataSource {

    void writeData(String data);

    String readData();

}
