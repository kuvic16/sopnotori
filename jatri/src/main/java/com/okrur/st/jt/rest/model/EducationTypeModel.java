package com.okrur.st.jt.rest.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.okrur.st.jt.data.domain.EducationType;
import com.okrur.st.jt.data.domain.Grade;

/**
 * @File EducationTypeModel.java: EducationTypeModel Model for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 03, 2015
 */
public class EducationTypeModel {
	
	@JsonIgnore
	private String id;
	private String name;
	private String description;
	private float scale;
	private String code;
	
	@JsonIgnore
	private String totalGradingPoint;
	
	@JsonIgnore
	private String totalGrade;
	
	@JsonIgnore
	private List<GradeModel> gradeModelList;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public List<GradeModel> getGradeModelList() {
		return gradeModelList;
	}

	public void setGradeModelList(List<GradeModel> gradeModelList) {
		this.gradeModelList = gradeModelList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTotalGradingPoint() {
		return totalGradingPoint;
	}

	public void setTotalGradingPoint(String totalGradingPoint) {
		this.totalGradingPoint = totalGradingPoint;
	}

	public String getTotalGrade() {
		return totalGrade;
	}

	public void setTotalGrade(String totalGrade) {
		this.totalGrade = totalGrade;
	}

	public EducationTypeModel(){}
	
	public EducationTypeModel(EducationType entity){
		this.setId(entity.getId());
		this.setName(entity.getName());
		this.setDescription(entity.getDescription());
		this.setScale(entity.getScale());
		this.setCode(entity.getCode());
	}
	
	public EducationTypeModel(EducationType entity, List<Grade> gradeList){
		new EducationTypeModel(entity);
		List<GradeModel> gradeModels = new ArrayList<GradeModel>();
		for(Grade grade : gradeList){
			gradeModels.add(new GradeModel(grade));
		}
		this.setGradeModelList(gradeModels);
	}
}
