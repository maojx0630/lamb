package com.github.maojx0630.swb.entity;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-24 15:01
 * @description:
 */
public class UserInfo {

	private String id;

	private String name;

	public UserInfo() {
	}

	public UserInfo(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
