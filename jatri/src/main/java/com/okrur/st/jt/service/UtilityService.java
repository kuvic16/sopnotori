package com.okrur.st.jt.service;


import com.okrur.st.jt.rest.model.FileUploadForm;
import com.okrur.st.jt.rest.model.ResponseModel;

/**
 * @File UtilityService.java
 * @author Md. Shaiful Islam | kuvic16@gmail.com
 * @CreationDate 02 May, 2016
 */
public interface UtilityService{
	public ResponseModel uploadFile(FileUploadForm fileUploadForm, String directory);
	public boolean renameFile(String oldFilePath, String newFilePath);
}
