package com.lyq.ssj.service.tally;
import com.lyq.ssj.entity.tally.SsjTallyEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface SsjTallyServiceI extends CommonService{
	
 	public void delete(SsjTallyEntity entity) throws Exception;
 	
 	public Serializable save(SsjTallyEntity entity) throws Exception;
 	
 	public void saveOrUpdate(SsjTallyEntity entity) throws Exception;
 	
}
