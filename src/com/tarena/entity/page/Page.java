package com.tarena.entity.page;

public abstract class Page {

	// ��ҳʱ����Ĳ���
	private int currentPage = 1; // ��ǰҳ
	private int pageSize = 5; // ÿҳ��ʾ�������

	// д��ҳSQLʱ��Ҫ������������������������
	private int begin; // ��ǰҳ����ʼ��
	private int end; // ��ǰҳ����ֹ��

	// ���ڼ�����ҳ��
	private int rows; // ������

	// ��ҳ������rows��pageSize�������
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
		 * �����������ı�ʱ,����������ҳ���ı仯,
		 * �Ӷ����ܵ��µ�ǰҳ������ҳ��,�ڴ˴���һ��������.
		 * */
		if (currentPage > getTotalPage()) {
			currentPage = getTotalPage();
		}
	}
	
}
