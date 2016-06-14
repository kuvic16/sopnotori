package com.okrur.st.mj.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@MappedSuperclass
public abstract class PersistableEntity implements Serializable{	
	private static final long serialVersionUID = -583909837667738686L;
	
	@Id
	@GeneratedValue(generator = "uids")
	@GenericGenerator(name = "uids", strategy = "net.brac.bep.util.IdGenerator", parameters = { @Parameter(name = "uuid_gen_strategy_class",  value = "com.brac.ehealth.util.IdGenerator") })
	@Column(name = "id", nullable = false, length = 100, unique = true)
	private String id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		if(!StringUtils.isNotBlank(this.id)){
			this.id = id;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersistableEntity other = (PersistableEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public abstract String getObjCode();

}
