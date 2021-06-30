package com.epat.http.tool;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author 李涛
 * @date : 2021/6/30 10:44
 */
public class ByteTool {


    public static byte[] byteExpansion(byte[] origin, int num) {
        return Optional.ofNullable(origin).map(o -> {
            byte[] temp = new byte[o.length + num];
            IntStream.range(0, o.length).forEach(i -> temp[i] = o[i]);
            return temp;
        }).orElseGet(() -> new byte[num]);
    }

}
