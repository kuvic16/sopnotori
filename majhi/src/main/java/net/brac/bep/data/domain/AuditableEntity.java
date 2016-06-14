package net.brac.bep.data.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.brac.bep.listener.AuditEntityListener;
import net.brac.bep.util.APPDate;

@MappedSuperclass
@EntityListeners(value = { AuditEntityListener.class })
public abstract class AuditableEntity extends PersistableEntity {
	
	private static final long serialVersionUID = 6708428237893630481L;
	
	@Column(name = "created_by_id", length = 36)
	private String createdByID;
	
	@Column(name = "created_by_name", length = 150)
	private String createdByName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_by_date")
	private Calendar createdByDate;

	
	@Column(name = "last_modified_by_id", length = 36)
	private String lastModifiedByID;
	
	@Column(name = "last_modified_by_name", length = 150)
	private String lastModifiedByName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified_by_date")
	private Calendar lastModifiedByDate;

	public APPDate getCreatedByDate() {
		return APPDate.nullableDate(createdByDate);
	}

	public void setCreatedByDate(APPDate createdByDate) {
		this.createdByDate = createdByDate;
	}

	public APPDate getLastModifiedByDate() {
		return APPDate.nullableDate(lastModifiedByDate);
	}

	public void setLastModifiedByDate(APPDate lastModifiedByDate) {
		this.lastModifiedByDate = lastModifiedByDate;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(final String createdBy) {
		this.createdByName = createdBy;
	}

	public String getLastModifiedByName() {
		return lastModifiedByName;
	}

	public void setLastModifiedByName(final String lastModifiedBy) {
		this.lastModifiedByName = lastModifiedBy;
	}

	public String getCreatedByID() {
		return createdByID;
	}

	public void setCreatedByID(String createdByID) {
		this.createdByID = createdByID;
	}

	public String getLastModifiedByID() {
		return lastModifiedByID;
	}

	public void setLastModifiedByID(String lastModifiedByID) {
		this.lastModifiedByID = lastModifiedByID;
	}	
}
