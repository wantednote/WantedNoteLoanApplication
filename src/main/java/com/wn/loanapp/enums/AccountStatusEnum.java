package com.wn.loanapp.enums;

public enum AccountStatusEnum {
	
	Pending(0),Active(1),Blocked(2),Deleted(3);

	private Integer value;
	
	AccountStatusEnum(){}
	
	AccountStatusEnum(Integer value){
		this.value= value;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}
}
