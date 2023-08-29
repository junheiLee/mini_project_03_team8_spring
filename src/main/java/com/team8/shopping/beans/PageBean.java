package com.team8.shopping.beans;

public class PageBean {

	private int min;
	private int max;
	private int prevPage;
	private int nextPage;
	private int pageCnt;
	private int currentPage;
	
	public PageBean(int productCnt, int currentPage, int listCnt, int paginationCnt) {
		this.currentPage = currentPage;
		pageCnt = productCnt / listCnt;
		if (productCnt % listCnt > 0) {
			pageCnt++;
		}
		min = ((currentPage - 1) / paginationCnt) * paginationCnt + 1;
		max = min + paginationCnt - 1;
		if (max > pageCnt) {
			max = pageCnt;
		}
		prevPage = min - 1;
		nextPage = max + 1;
	}
	
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
