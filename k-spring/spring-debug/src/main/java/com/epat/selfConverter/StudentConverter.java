package com.epat.selfConverter;

import org.springframework.core.convert.converter.Converter;

/**
 * @author 李涛
 * @date : 2021/7/4 10:24
 */
public class StudentConverter<S, T> implements Converter<S, T> {

	@Override
	public T convert(S source) {
		return null;
	}

}
