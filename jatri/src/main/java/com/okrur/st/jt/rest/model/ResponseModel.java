package com.okrur.st.jt.rest.model;

public class ResponseModel {
	private String module;
	private String request_type;
	private String success;
	private String message;
	private String total;
	private Object model;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}
	
	public String getRequest_type() {
		return request_type;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}
	
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ResponseModel(){
		
	}
	
	public ResponseModel(String _module, String _request_type){
		this.setModule(_module);
		this.setRequest_type(_request_type);
	}
	
	public ResponseModel(String _success, String _message, Object _model){
		this.setSuccess(_success);
		this.setMessage(_message);
		this.setModel(_model);
	}
	
	public void setResponse(String _success, String _message, Object _model){
		this.setSuccess(_success);
		this.setMessage(_message);
		this.setModel(_model);
	}
}
