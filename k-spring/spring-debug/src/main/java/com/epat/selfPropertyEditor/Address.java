package com.epat.selfPropertyEditor;

/**
 * @author 李涛
 * @date : 2021/6/24 9:30
 */
public class Address {

	private String province;

	private String city;

	private String town;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Override
	public String toString() {
		return "Address{" +
				"province='" + province + '\'' +
				", city='" + city + '\'' +
				", town='" + town + '\'' +
				'}';
	}
}
