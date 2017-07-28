package com.lyq.work.service.demand;
import com.lyq.work.entity.demand.WorkDemandEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface WorkDemandServiceI extends CommonService{
	
 	public void delete(WorkDemandEntity entity) throws Exception;
 	
 	public Serializable save(WorkDemandEntity entity) throws Exception;
 	
 	public void saveOrUpdate(WorkDemandEntity entity) throws Exception;
 	
}
