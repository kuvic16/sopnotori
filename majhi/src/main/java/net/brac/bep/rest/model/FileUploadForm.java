/**
 * 
 */
package net.brac.bep.rest.model;

import javax.ws.rs.FormParam;

import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 * @File FileUploadForm.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate May 3, 2016
 */
public class FileUploadForm {
	public FileUploadForm() {
	}
	
	private byte[] data;
	private String fileName;
	private String type;
	private int size;
	private String ext;

	public byte[] getData() {
		return data;
	}
	
	public String getFileName() {
		return fileName;
	}

	public String getType() {
		return type;
	}

	public int getSize() {
		return size;
	}
	
	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@FormParam("file")
	@PartType("application/octet-stream")
	public void setData(byte[] data) {
		this.data = data;
	}
	
	@FormParam("fileName")
	@PartType("application/octet-stream")
	public void setFileName(String fileName) {
		this.fileName = fileName;
		if(StringUtils.isNotBlank(fileName)){
			String[] fNames = getFileName().split("\\.");
			if(fNames.length > 1){
				setExt(fNames[1]);
			}
		}
	}
	
	@FormParam("type")
	@PartType("application/octet-stream")
	public void setType(String type) {
		this.type = type;
	}
	
	@FormParam("size")
	@PartType("application/octet-stream")
	public void setSize(int size) {
		this.size = size;
	}
	
	
	public static void main(String[] args){
		String a = "aaa.txt";
		System.out.println(a.split("\\.")[1]);
	}
}
