package com.okrur.st.mj.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.mj.rest.model.GradePointModel;
import com.okrur.st.mj.util.IUtil;

/**
 * @File GradePoint.java: GradePoint database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 23, 2015
 */
@Entity
@XmlRootElement
@Table(name = "grade_point")
public class GradePoint extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;

	

	@Size(min = 0, max = 5)
	@Column(name = "letter_grade", length = 5)
	private String letterGrade;
	
	@Column(name = "min_mark")
	private float minMark;
	
	@Column(name = "max_mark")
	private float maxMark;
	
	@Column(name = "point")
	private float point;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "education_type_id", length = DomainConstant.ID_LENGTH)
	private String educationTypeId;

	
	public String getLetterGrade() {
		return letterGrade;
	}

	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}

	public float getMinMark() {
		return minMark;
	}

	public void setMinMark(float minMark) {
		this.minMark = minMark;
	}

	public float getMaxMark() {
		return maxMark;
	}

	public void setMaxMark(float maxMark) {
		this.maxMark = maxMark;
	}

	public float getPoint() {
		return point;
	}

	public void setPoint(float point) {
		this.point = point;
	}
	
	public String getEducationTypeId() {
		return educationTypeId;
	}

	public void setEducationTypeId(String educationTypeId) {
		this.educationTypeId = educationTypeId;
	}

	@Override
	public String getObjCode() {
		return "grade_point";
	}
	
	public GradePoint(){}
	public GradePoint setGradePoint(GradePointModel model){
		this.setId(model.getId());
		this.setLetterGrade(model.getLetterGrade());
		this.setMinMark(IUtil.toFloat(model.getMinMark()));
		this.setMaxMark(IUtil.toFloat(model.getMaxMark()));
		this.setPoint(IUtil.toFloat(model.getPoint()));
		this.setEducationTypeId(model.getEducationTypeId());
		return this;
	}
}
