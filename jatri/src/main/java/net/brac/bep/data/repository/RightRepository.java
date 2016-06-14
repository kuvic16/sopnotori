package net.brac.bep.data.repository;

import java.util.List;

import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import net.brac.bep.data.domain.Right;

@Stateless
public class RightRepository extends AbstractRepository<Right> {

	public RightRepository() {
		super(Right.class);
	}
	
	public boolean isExist(String name){
		//HINT add more parameters if needed
		try{
			if(StringUtils.isNotBlank(name)){
				String clause = "t.name = ?1";
				Object[] params = {name};
				
				List<Right> list = this.loadByClause(clause, params);
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
