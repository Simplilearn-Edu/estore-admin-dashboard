package com.example.estorebackend.model;

import java.util.List;

public class Response<T> {
	
	Integer code;
	String message;
	List<T> data;
	
	public Response() {
		// TODO Auto-generated constructor stub
	}

	public Response(Integer code, String message, List<T> data) {
		this.code = code;
		this.message = message;
		this.data = data;
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

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
	
}
