package com.epat.kLog.appender;

import com.epat.kLog.constant.LogConstant;
import com.epat.kLog.converter.ColorConverter;
import com.epat.kLog.converter.DateConverter;
import com.epat.kLog.converter.LevelConverter;
import com.epat.kLog.converter.ThreadConverter;
import java.util.Date;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/6/8 9:49
 */
public abstract class LogAppender {

    private List<String> whiteClassList;

    private Integer logLevel = LogConstant.ALL;

    private String pattern = "";


    public Boolean isNeedLog (Class clazz, Integer level) {
        if (!isCanLevelLog(level)) {
            return false;
        }
        return isCanClassLog(clazz);
    }

    public void log (Thread thread, Class clazz, Integer level, Object message) {
        if (!isNeedLog(clazz, level)) {
            return;
        }
        out(messageHandler(thread, clazz, level, message));
    };

    protected String messageHandler (Thread thread, Class clazz, Integer level, Object message) {
        return DateConverter.transform(new Date()) + " " + ColorConverter.transform(ColorConverter.BLUE_FG, clazz.getClass().getName())  + LevelConverter.transform(level) + ThreadConverter.transform(thread)
 +       " >>> " + message.toString();
    }

    /**
     * 输出方法
     * @param message
     */
    public abstract void out(String message);

    /**
     * 是否可以打印当前等级日志
     * @param level
     * @return
     */
    protected Boolean isCanLevelLog (Integer level) {
        return logLevel.intValue() >  level.intValue();
    }

    protected Boolean isCanClassLog (Class clazz) {
        String clazzName = clazz.getName().replaceAll(".", "/");
        if (whiteClassList == null || whiteClassList.size() == 0) {
            return true;
        }
        for (String s : whiteClassList) {
            String pattern = s.replaceAll(".", "/").replaceAll("\\*", ".*");
            if (clazzName.matches(pattern)) {
                return true;
            }
        }
        return false;
    }

}
