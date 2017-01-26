package com.lyq.ssj.service;
import com.lyq.ssj.entity.SsjCreditEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface SsjCreditServiceI extends CommonService{
	
 	public void delete(SsjCreditEntity entity) throws Exception;
 	
 	public Serializable save(SsjCreditEntity entity) throws Exception;
 	
 	public void saveOrUpdate(SsjCreditEntity entity) throws Exception;
 	
}
