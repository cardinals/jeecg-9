package com.lyq.work.service.worklog;
import com.lyq.work.entity.worklog.WorkLogEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface WorkLogServiceI extends CommonService{
	
 	public void delete(WorkLogEntity entity) throws Exception;
 	
 	public Serializable save(WorkLogEntity entity) throws Exception;
 	
 	public void saveOrUpdate(WorkLogEntity entity) throws Exception;
 	
}
