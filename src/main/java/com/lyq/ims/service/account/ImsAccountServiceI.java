package com.lyq.ims.service.account;
import com.lyq.ims.entity.account.ImsAccountEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ImsAccountServiceI extends CommonService{
	
 	public void delete(ImsAccountEntity entity) throws Exception;
 	
 	public Serializable save(ImsAccountEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ImsAccountEntity entity) throws Exception;
 	
}
