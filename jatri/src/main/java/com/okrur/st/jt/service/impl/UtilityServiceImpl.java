/**
 * 
 */
package com.okrur.st.jt.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.okrur.st.jt.rest.model.FileUploadForm;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.service.UtilityService;
import com.okrur.st.jt.util.IConstant;
import com.okrur.st.jt.util.IdGenerator;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File UtilityServiceImpl.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate May 3, 2016
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class UtilityServiceImpl implements UtilityService{

	/* (non-Javadoc)
	 * @see net.brac.bep.service.UtilityService#uploadFile(net.brac.bep.rest.model.FileUploadForm, java.lang.String)
	 */
	@Override
	public ResponseModel uploadFile(FileUploadForm fileUploadForm, String directory) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EXPENDITURE, RestUtil.REQUEST_TYPE_FILE_UPLOAD);
		try{
			String fileName = IdGenerator.generate(IConstant.ATTACHMENT_TEMP) + "." + fileUploadForm.getExt();
			directory = directory + fileName;
			writeFile(fileUploadForm.getData(), directory);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, fileName);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	private void writeFile(byte[] content, String filename) throws IOException {
		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.UtilityService#renameFile()
	 */
	@Override
	public boolean renameFile(String oldFilePath, String newFilePath) {
		File oldName = new File(oldFilePath);
	    File newName = new File(newFilePath);
	    if(oldName.renameTo(newName)) {
	         return true;
	    }
		return false;
	}

}
