package com.epat.decorators;

/**
 * @author 李涛
 * @date : 2021/7/14 17:50
 */
public class DataSourceDecorator  implements DataSource{

    private DataSource wrappee;

    DataSourceDecorator(DataSource source) {
        this.wrappee = source;
    }

    @Override
    public void writeData(String data) {
        wrappee.writeData(data);
    }

    @Override
    public String readData() {
        return wrappee.readData();
    }

}
