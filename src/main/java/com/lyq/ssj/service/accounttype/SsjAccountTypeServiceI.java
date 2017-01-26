package com.lyq.ssj.service.accounttype;
import com.lyq.ssj.entity.accounttype.SsjAccountTypeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface SsjAccountTypeServiceI extends CommonService{
	
 	public void delete(SsjAccountTypeEntity entity) throws Exception;
 	
 	public Serializable save(SsjAccountTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(SsjAccountTypeEntity entity) throws Exception;
 	
}
