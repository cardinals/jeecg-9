package com.lyq.ims.controller.account;

import com.lyq.ims.entity.account.ImsAccountEntity;
import com.lyq.ims.service.account.ImsAccountServiceI;
import com.lyq.work.entity.worklog.WorkLogEntity;
import com.lyq.work.service.worklog.WorkLogServiceI;
import org.apache.log4j.Logger;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: Controller
 * @Description: 账户对外接口
 * @author onlineGenerator
 * @date 2017-08-08 16:49:11
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/wsImsAccountController")
public class WsImsAccountController {

	@Autowired
	private ImsAccountServiceI imsAccountServiceI;

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
	public TableJson list(String sign) {
		TableJson j = new TableJson();
		try {
			//参数校验
			if(StringUtil.isEmpty(sign)){
				throw new BusinessException("sign不能为空");
			}
			//签名验证
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("method", "list");
			if(!SignatureUtil.checkSign(paramMap, SIGN_KEY, sign)){
				throw new BusinessException("签名验证失败");
			}
			List<ImsAccountEntity> accountEntities = imsAccountServiceI.findHql("FROM ImsAccountEntity w ORDER BY w.account DESC");
			j.setTableData(accountEntities);
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
//		sign=61CB81BAEE8E9134BF7AA846634C3CED
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("method", "list");
		System.out.println(SignatureUtil.sign(paramMap, SIGN_KEY));
	}
}
