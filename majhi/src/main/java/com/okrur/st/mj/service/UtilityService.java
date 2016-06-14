package com.okrur.st.mj.service;


import com.okrur.st.mj.rest.model.FileUploadForm;
import com.okrur.st.mj.rest.model.ResponseModel;

/**
 * @File UtilityService.java
 * @author Md. Shaiful Islam | kuvic16@gmail.com
 * @CreationDate 02 May, 2016
 */
public interface UtilityService{
	public ResponseModel uploadFile(FileUploadForm fileUploadForm, String directory);
	public boolean renameFile(String oldFilePath, String newFilePath);
}
