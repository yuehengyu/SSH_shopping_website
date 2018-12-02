package pers.yhy.shop.utils;

import java.util.List;

/**
 * Pagination class function
 * 
 * @author yuehe
 *
 */
public class PageBean<T> {
	private int page;// current page
	private int totalCount;// total records
	private int totalPage;
	private int limit;// records in each page
	private List<T> list;// data set in each page

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
