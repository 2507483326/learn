package com.epat.annotation.config;

import java.io.File;

/**
 * @author 李涛
 * @date : 2021/7/8 19:53
 */
public interface ConfigLoader {

    public ConfigModel loadConfig (File file) throws Exception;

}
