package com.ake.nbems.common.core.enums;

public enum LinkageEnum {
	
	OFF(0,"主机开"),
	ON(1,"主机关"),
	NOT_LINKAGE(2,"未联动");
	
	
	private int value;
	
	private String text;
	
	LinkageEnum(int value,String text){
		this.value = value;
		this.text = text;
	}
	
	public static String getText(int value){
		for(LinkageEnum l : LinkageEnum.values()){
			if(l.getValue() == value){
				return l.getText();
			}
		}
		return null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
