package com.taotao.common.pojo;

public class EUTreeNode {
	private long id;
	
	private String text;
	private String state;
	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String string) {
		this.state = string;
	}
	public String toString() {
		return "EUTreeNode [id=" + id + ", text=" + text + ", state=" + state + "]";
	}

}
