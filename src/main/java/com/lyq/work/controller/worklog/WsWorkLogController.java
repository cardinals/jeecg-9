package com.lyq.work.controller.worklog;

import com.lyq.work.entity.worklog.WorkLogEntity;
import com.lyq.work.service.worklog.WorkLogServiceI;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.util.SignatureUtil;
import org.jeecgframework.web.cgform.util.TableJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: Controller
 * @Description: 工作日志对外接口
 * @author onlineGenerator
 * @date 2017-02-17 08:54:27
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/wsWorkLogController")
public class WsWorkLogController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WsWorkLogController.class);

	@Autowired
	private WorkLogServiceI workLogService;

	@Autowired
	private CgFormFieldServiceI cgFormFieldService;

	/**
	 * 签名密钥key
	 */
	private static final String SIGN_KEY = "26F72780372E84B6CFAED6F7B19139CC47B1912B6CAED753";


	/**
	 * online表单对外接口：getFormInfo 获取表单数据
	 * 注意：
	 * @return
	 */
	@RequestMapping(params = "list")
	@ResponseBody
	public TableJson list(String tableName,
								 String id,
								 String sign) {
		TableJson j = new TableJson();
		try {
			//参数校验
			if(StringUtil.isEmpty(tableName)){
				throw new BusinessException("tableName不能为空");
			}
			if(StringUtil.isEmpty(id)){
				throw new BusinessException("id不能为空");
			}
			if(StringUtil.isEmpty(sign)){
				throw new BusinessException("sign不能为空");
			}
			//签名验证
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("tableName", tableName);
			paramMap.put("id", id);
			paramMap.put("method", "getFormInfo");
			if(!SignatureUtil.checkSign(paramMap, SIGN_KEY, sign)){
				throw new BusinessException("签名验证失败");
			}
			//校验该表是否是online表单
			CgFormHeadEntity head = cgFormFieldService.getCgFormHeadByTableName(tableName);
			if(head==null){
				throw new BusinessException("该表单不是online表单");
			}
			List<WorkLogEntity> workList = workLogService.findHql("FROM WorkLogEntity w ORDER BY w.date DESC");
			j.setTableData(workList);
		} catch (BusinessException e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("系统异常");
		}
		return j;
	}

	public static void main(String[] args) {
//		tableName=ssj_tally&id=402847ed5d4a5138015d4a5138fd0000&sign=735CE07A2AB9C1872983B09C85A770D9
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("tableName", "ssj_tally");
		paramMap.put("id", "402847ed5d4a5138015d4a5138fd0000");
		paramMap.put("method", "getFormInfo");
		System.out.println(SignatureUtil.sign(paramMap, SIGN_KEY));
	}

}
