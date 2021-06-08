package com.epat.kLog.converter;
/**
 * @author 李涛
 * @date : 2021/6/8 9:49
 */
public class ColorConverter {

    public final static String ESC_START = "\033[";
    public final static String ESC_END = "m";
    public final static String BLACK_FG = "30";
    public final static String RED_FG = "31";
    public final static String GREEN_FG = "32";
    public final static String YELLOW_FG = "33";
    public final static String BLUE_FG = "34";
    public final static String MAGENTA_FG = "35";
    public final static String CYAN_FG = "36";
    public final static String WHITE_FG = "37";
    public final static String DEFAULT_FG = "39";

    final private static String SET_DEFAULT_COLOR = ESC_START + "0;" + DEFAULT_FG + ESC_END;

    public static String transform(String color, Object event) {
        StringBuilder sb = new StringBuilder();
        sb.append(ESC_START);
        sb.append(color);
        sb.append(ESC_END);
        sb.append(event);
        sb.append(SET_DEFAULT_COLOR);
        return BracketsConverter.transform(sb.toString());
    }

}
