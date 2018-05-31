package com.wn.loanapp.dto;

import java.util.List;

public class DatatableJsonResponse {

	@SuppressWarnings("rawtypes")
	private List data;
	
	private Integer recordsTotal;
	
	private Integer draw;
	
	private String error;
	
	private Integer recordsFiltered;
	
	/**
	 * @return the recordsFiltered
	 */
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	/**
	 * @param recordsFiltered the recordsFiltered to set
	 */
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	/**
	 * @return the data
	 */
	@SuppressWarnings("rawtypes")
	public List getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	@SuppressWarnings("rawtypes")
	public void setData(List data) {
		this.data = data;
	}

	/**
	 * @return the recordsTotal
	 */
	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	/**
	 * @param recordsTotal the recordsTotal to set
	 */
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	/**
	 * @return the draw
	 */
	public Integer getDraw() {
		return draw;
	}

	/**
	 * @param draw the draw to set
	 */
	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
}
