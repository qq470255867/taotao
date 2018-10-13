package com.taotao.portal.pojo;

import java.util.List;

public class SearchResult {
	private List<Item> list;
	private long pageCount;
	private long curPage;
	private long recordCount;

	public List<Item> getList() {
		return list;
	}

	public void setList(List<Item> list) {
		this.list = list;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getCurPage() {
		return curPage;
	}

	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

}
