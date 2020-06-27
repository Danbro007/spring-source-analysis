package com.danbro.entities;

import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @Classname Result
 * @Description TODO
 * @Date 2020/6/26 11:52
 * @Author Danrbo
 */

public class Result {

	private Integer code;
	private String message;
	private Object data;
	private List<ObjectError> errorMessage;


	public Result(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Result(CodeEnum codeEnum, String message, Object data) {
		this.code = codeEnum.getCode();
		this.message = message;
		this.data = data;
	}

	public Result(CodeEnum codeEnum, Object data) {
		this.code = codeEnum.getCode();
		this.message = codeEnum.getMessage();
		this.data = data;
	}

	public Result(CodeEnum codeEnum, List<ObjectError> errorMessage) {
		this.code = codeEnum.getCode();
		this.errorMessage = errorMessage;
	}

	public static Result resultOfSuccess(CodeEnum codeEnum, Object data){
		return new Result(codeEnum,data);
	}

	public static Result resultOfSuccess(CodeEnum codeEnum){
		return new Result(codeEnum,null);
	}

	public static Result resultOfFailure(CodeEnum codeEnum,List<ObjectError> errorMessage){
		return new Result(codeEnum,errorMessage);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<ObjectError> getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(List<ObjectError> errorMessage) {
		this.errorMessage = errorMessage;
	}
}
