package com.dream.auth.controller;

public class Auth {
	private String id = null;
	private String name = null;
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
	public boolean equals(Object that){
		Auth other = (Auth) that;
		return this.getId() == null ? that == null : this.getId().equals(other.getId())
				&& this.getName() == null ? that == null : this.getName().equals(other.getName());
	}
}
