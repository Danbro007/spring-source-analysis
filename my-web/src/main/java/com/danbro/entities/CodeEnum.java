package com.danbro.entities;

/**
 * @Classname CodeEnum
 * @Description TODO
 * @Date 2020/6/26 11:55
 * @Author Danrbo
 */
public enum CodeEnum {
	/**
	 * 状态码
	 */
	SUCCESS(200,"成功！"),
	FAILURE(400,null);


	private Integer code;
	private String message;

	CodeEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
