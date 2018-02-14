package com.my.framework.exception;

public class Paging {

	/**
	 * Paging paging = new Paging(param.getPageNum(), param.getPageSize(), totCnt);
	 * param.setOffset(paging.getOffset());
	 * result.setList();
	 * result.setPage(paging);
	 */
	private int totCnt = 0;
	private int pageNum = 1;
	private int pageSize = 10;
	private int offset = 0;
	
	public Paging(int pageNum, int pageSize, int totCnt) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totCnt = totCnt;
	}
	
	public void initialize() {
		this.offset = (pageNum -1) * pageSize;
	}

	public int getTotCnt() {
		return totCnt;
	}

	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}
