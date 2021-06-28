package com.epat.selfPropertyEditor;

import java.beans.PropertyEditorSupport;

/**
 * @author 李涛
 * @date : 2021/6/24 9:32
 */
public class AddressPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String [] s = text.split("_");
		Address address = new Address();
		address.setProvince(s[0]);
		address.setCity(s[1]);
		address.setTown(s[2]);
		setValue(address);
	}
}
