package com.my.framework.exception;

public interface IResult {

	public static final int OK = 0;
	public static final int ERROR = -1;
	public static final int TIMEOUT = -2;
	public static final int INVALID_TOKEN = -3;
	public static final int ACCESS_DENIED = -4;
	public static final int INVALID_PARAM = -5;
	public static final int VALIDATE_ERROR = -6;
	public static final int CUD_ERROR = -7;
}
