package com.okrur.st.jt.data.repository;

import java.util.List;

import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.jt.data.domain.Udv;

/**
 * @File LinkedMapRepository.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Stateless
public class UdvRepository extends AbstractRepository<Udv>{
	public UdvRepository() {
		super(Udv.class);
	}
	
	public boolean isExist(String category, String value){
		//HINT add more parameters if needed
		try{
			if(StringUtils.isNotBlank(value)){
				String clause = "t.category=?1 and t.value = ?2";
				Object[] params = {category, value};
				
				List<Udv> list = this.loadByClause(clause, params);
				if(list != null && list.size()>0){
					return true;
				}
			}
		}catch(Throwable t){
			//TODO log exception
		}
		return false;
	}
}