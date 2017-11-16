package com.lyq.work.controller.demand;

import com.lyq.work.entity.demand.WorkDemandEntity;
import com.lyq.work.service.demand.WorkDemandServiceI;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.StringUtil;
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
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 需求单对外接口
 * @date 2017-08-08 16:49:11
 */
@Controller
@RequestMapping("/wsWorkDemandController")
public class WsWorkDemandController {

    @Autowired
    private WorkDemandServiceI workDemandServiceI;

    /**
     * 签名密钥key
     */
    private static final String SIGN_KEY = "26F72780372E84B6CFAED6F7B19139CC47B1912B6CAED753";


    /**
     * online表单对外接口：getFormInfo 获取表单数据
     * 注意：
     *
     * @return
     */
    @RequestMapping(params = "list")
    @ResponseBody
    public TableJson list(String sign) {
        TableJson j = new TableJson();
        try {
            //参数校验
            if (StringUtil.isEmpty(sign)) {
                throw new BusinessException("sign不能为空");
            }
            //签名验证
            Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("method", "list");
            if (!SignatureUtil.checkSign(paramMap, SIGN_KEY, sign)) {
                throw new BusinessException("签名验证失败");
            }
            List<WorkDemandEntity> workDemandEntities = workDemandServiceI.findHql("FROM WorkDemandEntity w ORDER BY w.proposeDate DESC");
            j.setTableData(workDemandEntities);
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
//		paramMap.put("method", "list");
        System.out.println(SignatureUtil.sign(paramMap, SIGN_KEY));
    }
}
