package com.wn.loanapp.form;

import org.springframework.web.multipart.MultipartFile;

public class CSVForm {

	private MultipartFile csvFile;

	/**
	 * @return the csvFile
	 */
	public MultipartFile getCsvFile() {
		return csvFile;
	}

	/**
	 * @param csvFile the csvFile to set
	 */
	public void setCsvFile(MultipartFile csvFile) {
		this.csvFile = csvFile;
	}
	
}
