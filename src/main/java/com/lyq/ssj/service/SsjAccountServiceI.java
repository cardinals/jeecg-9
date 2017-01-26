package com.lyq.ssj.service;
import com.lyq.ssj.entity.SsjAccountEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface SsjAccountServiceI extends CommonService{
	
 	public void delete(SsjAccountEntity entity) throws Exception;
 	
 	public Serializable save(SsjAccountEntity entity) throws Exception;
 	
 	public void saveOrUpdate(SsjAccountEntity entity) throws Exception;
 	
}
