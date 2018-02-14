package com.my.framework.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.my.framework.mypage.MyPageVo;

@Service("result")
public class Result implements IResult {

	private String status;
	private String code;
	private String message;
	private String developerMessage;
	private Object dhx;

	private String result;

	private Object data;

	private Object param;

	private Object error;

	private Object list;

	private Paging page;

	private Object subList;
	private ArrayList<HashMap<String, Object>> gridList;
	private MyPageVo userVo;

	public Result() {
		this.status = "200";
		this.code = String.valueOf(IResult.OK);
		this.message = "Success";
	}

	public Result(String status, String code, String message, String developerMessage) {
		this.status = StringUtils.isNotEmpty(status) ? status : "200";
		this.code = StringUtils.isNotEmpty(code) ? code : String.valueOf(IResult.OK);
		this.message = StringUtils.isNotEmpty(message) ? message : "Success";
		this.developerMessage = StringUtils.isNotEmpty(developerMessage) ? developerMessage : "";
	}

	public Map<String, Object> getMap() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", this.status);
		resultMap.put("code", this.code);
		resultMap.put("message", this.message);
		resultMap.put("developerMessage", this.developerMessage);
		return resultMap;
	}

	public String getResult() {
		if ("0".equals(this.code)) {
			return "ok";
		} else {
			return "error";
		}
	}

	public void setResult(Map<String, Object> obj) {
		this.status = (String) obj.get("status");
		this.code = (String) obj.get("errorNumber");
		this.message = (String) obj.get("message");
		this.developerMessage = (String) obj.get("developerMessage");
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public Object getDhx() {
		return dhx;
	}

	public void setDhx(Object dhx) {
		this.dhx = dhx;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}

	public Paging getPage() {
		return page;
	}

	public void setPage(Paging page) {
		this.page = page;
	}

	public Object getSubList() {
		return subList;
	}

	public void setSubList(Object subList) {
		this.subList = subList;
	}

	public ArrayList<HashMap<String, Object>> getGridList() {
		return gridList;
	}

	public void setGridList(ArrayList<HashMap<String, Object>> gridList) {
		this.gridList = gridList;
	}

	public MyPageVo getUserVo() {
		return userVo;
	}

	public void setUserVo(MyPageVo userVo) {
		this.userVo = userVo;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
