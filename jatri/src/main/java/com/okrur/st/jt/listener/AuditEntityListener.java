package com.okrur.st.jt.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.okrur.st.jt.data.domain.AuditableEntity;
import com.okrur.st.jt.rest.model.UserModel;
import com.okrur.st.jt.security.SecurityUtil;
import com.okrur.st.jt.util.APPDate;
import com.okrur.st.jt.util.IConstant;

/**
 * @File AuditEntityListener.java
 * @author shaiful islam palash | kuvic16@gmail.com
 */
public class AuditEntityListener {
	private static final Logger log = (Logger) LoggerFactory.getLogger(AuditEntityListener.class);

	/**
	 * Before create the entity this method will call
	 * @param AuditableEntity
	 */
	@PrePersist
	public void prePersist(AuditableEntity e) {
		try {
			UserModel uModel = SecurityUtil.getCurrentLoggedUser();
			if (uModel != null) {
				e.setCreatedByID(uModel.getId());
				e.setCreatedByName(uModel.getUsername());
			}
		} catch (Exception ex) {
			log.error("Exception in Auditable Entity Listener", ex);
		} finally {
			if (StringUtils.isBlank(e.getCreatedByName())) {
				e.setCreatedByName(IConstant.DEFAULT_CREATOR_UPADTER);
			}
			if (e.getCreatedByDate() == null) {
				e.setCreatedByDate(APPDate.now());
			}
		}
	}

	
	/**
	 * Before update the entity this method will call
	 * @param AuditableEntity
	 */
	
	@PreUpdate
	public void preUpdate(AuditableEntity e) {
		try {
			UserModel uModel = SecurityUtil.getCurrentLoggedUser();
			if (uModel != null) {
				e.setLastModifiedByID(uModel.getId());
				e.setLastModifiedByName(uModel.getUsername());
			}
		} catch (Exception ex) {
			log.error("Exception in Auditable Entity Listener", ex);
		} finally {
			if (StringUtils.isBlank(e.getLastModifiedByName())) {
				e.setLastModifiedByName(IConstant.DEFAULT_CREATOR_UPADTER);
			}
			if (e.getLastModifiedByDate() == null) {
				e.setLastModifiedByDate(APPDate.now());
			}
		}
	}
}
