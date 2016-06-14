package net.brac.bep.rest.model;

import net.brac.bep.util.IUtil;
import org.codehaus.jackson.annotate.JsonIgnore;

import net.brac.bep.data.domain.GradePoint;

/**
 * @File GradePointModel.java: Grade point Model for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 23, 2015
 */
public class GradePointModel {

	@JsonIgnore
	private String id;
	private String letterGrade;
	private String minMark;
	private String maxMark;
	private String point;
	@JsonIgnore
	private String educationTypeId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLetterGrade() {
		return letterGrade;
	}

	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}

	public String getMaxMark() {
		return maxMark;
	}

	public void setMaxMark(String maxMark) {
		this.maxMark = maxMark;
	}

	public String getMinMark() {
		return minMark;
	}

	public void setMinMark(String minMark) {
		this.minMark = minMark;
	}

	public String getEducationTypeId() {
		return educationTypeId;
	}

	public void setEducationTypeId(String educationTypeId) {
		this.educationTypeId = educationTypeId;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public GradePointModel() {
	}

	public GradePointModel(GradePoint entity) {
		this.setId(entity.getId());
		this.setLetterGrade(entity.getLetterGrade());
		this.setMinMark(IUtil.toString(entity.getMinMark()));
		this.setMaxMark(IUtil.toString(entity.getMaxMark()));
		this.setPoint(IUtil.toString(entity.getPoint()));
		this.setEducationTypeId(entity.getEducationTypeId());
	}
}
