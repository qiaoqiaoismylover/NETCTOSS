package com.tarena.entity.page;

public abstract class Page {

	// 分页时输入的参数
	private int currentPage = 1; // 当前页
	private int pageSize = 5; // 每页显示最大行数

	// 写分页SQL时需要的条件，由输入参数计算而来
	private int begin; // 当前页的起始行
	private int end; // 当前页的终止行

	// 用于计算总页数
	private int rows; // 总行数

	// 总页数，由rows和pageSize计算而来
	private int totalPage;

	public int getBegin() {
		begin = (currentPage - 1) * pageSize;
		return begin;
	}

	public int getEnd() {
		end = currentPage * pageSize + 1;
		return end;
	}

	public int getTotalPage() {
		if (rows % pageSize == 0) {
			totalPage = rows / pageSize;
		} else {
			totalPage = rows / pageSize + 1;
		}
		
		return totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
		
		/*
		 * 总行数发生改变时,可能引起总页数的变化,
		 * 从而可能导致当前页大于总页数,在此处理一下这个情况.
		 * */
		if (currentPage > getTotalPage()) {
			currentPage = getTotalPage();
		}
	}
	
}
