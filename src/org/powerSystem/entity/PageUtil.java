package org.powerSystem.entity;

import java.io.Serializable;
import java.util.List;

public class PageUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	private List rows;//行的集合
	private int total;//总条数

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public PageUtil(List rows, int total) {
		this.rows = rows;
		this.total = total;
	}

	public PageUtil(){}
}
