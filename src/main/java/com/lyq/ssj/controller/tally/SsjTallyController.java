package com.lyq.ssj.controller.tally;

import com.lyq.ssj.entity.account.SsjAccountEntity;
import com.lyq.ssj.entity.tally.SsjTallyEntity;
import com.lyq.ssj.service.account.SsjAccountServiceI;
import com.lyq.ssj.service.tally.SsjTallyServiceI;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;

import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 记账
 * @date 2017-03-01 23:04:33
 */
@Controller
@RequestMapping("/ssjTallyController")
public class SsjTallyController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(SsjTallyController.class);

    @Autowired
    private SsjTallyServiceI ssjTallyService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;


    /**
     * 记账列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/lyq/ssj/tally/ssjTallyList");
    }

    /**
     * easyui AJAX请求数据
     *
     * @param request
     * @param response
     * @param dataGrid
     * @param user
     */

    @RequestMapping(params = "datagrid")
    public void datagrid(SsjTallyEntity ssjTally, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(SsjTallyEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ssjTally, request.getParameterMap());
        try {
            //自定义追加查询条件
            String query_time_begin = request.getParameter("time_begin");
            String query_time_end = request.getParameter("time_end");
            if (StringUtil.isNotEmpty(query_time_begin)) {
                cq.ge("time", new SimpleDateFormat("yyyy-MM-dd").parse(query_time_begin));
            }
            if (StringUtil.isNotEmpty(query_time_end)) {
                cq.le("time", new SimpleDateFormat("yyyy-MM-dd").parse(query_time_end));
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.ssjTallyService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除记账
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(SsjTallyEntity ssjTally, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        ssjTally = systemService.getEntity(SsjTallyEntity.class, ssjTally.getId());
        message = "记账删除成功";
        try {
            ssjTallyService.delete(ssjTally);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "记账删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除记账
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "记账删除成功";
        try {
            for (String id : ids.split(",")) {
                SsjTallyEntity ssjTally = systemService.getEntity(SsjTallyEntity.class,
                        id
                );
                ssjTallyService.delete(ssjTally);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "记账删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 添加记账
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(SsjTallyEntity ssjTally, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "记账添加成功";
        try {
            ssjTallyService.save(ssjTally);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "记账添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 更新记账
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(SsjTallyEntity ssjTally, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "记账更新成功";
        SsjTallyEntity t = ssjTallyService.get(SsjTallyEntity.class, ssjTally.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(ssjTally, t);
            ssjTallyService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "记账更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 记账新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(SsjTallyEntity ssjTally, HttpServletRequest req) {
        List<SsjAccountEntity> accountList = systemService.getList(SsjAccountEntity.class);
        req.setAttribute("accountList", accountList);
        if (StringUtil.isNotEmpty(ssjTally.getId())) {
            ssjTally = ssjTallyService.getEntity(SsjTallyEntity.class, ssjTally.getId());
            req.setAttribute("ssjTallyPage", ssjTally);
        }
        return new ModelAndView("com/lyq/ssj/tally/ssjTally-add");
    }

    /**
     * 记账编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(SsjTallyEntity ssjTally, HttpServletRequest req) {
        List<SsjAccountEntity> accountList = systemService.getList(SsjAccountEntity.class);
        req.setAttribute("accountList", accountList);
        if (StringUtil.isNotEmpty(ssjTally.getId())) {
            ssjTally = ssjTallyService.getEntity(SsjTallyEntity.class, ssjTally.getId());
            req.setAttribute("ssjTallyPage", ssjTally);
        }
        return new ModelAndView("com/lyq/ssj/tally/ssjTally-update");
    }

    /**
     * 导入功能跳转
     *
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "ssjTallyController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public String exportXls(SsjTallyEntity ssjTally, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(SsjTallyEntity.class, dataGrid);
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ssjTally, request.getParameterMap());
        List<SsjTallyEntity> ssjTallys = this.ssjTallyService.getListByCriteriaQuery(cq, false);
        modelMap.put(NormalExcelConstants.FILE_NAME, "记账");
        modelMap.put(NormalExcelConstants.CLASS, SsjTallyEntity.class);
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("记账列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                "导出信息"));
        modelMap.put(NormalExcelConstants.DATA_LIST, ssjTallys);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    /**
     * 导出excel 使模板
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(SsjTallyEntity ssjTally, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put(NormalExcelConstants.FILE_NAME, "记账");
        modelMap.put(NormalExcelConstants.CLASS, SsjTallyEntity.class);
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("记账列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                "导出信息"));
        modelMap.put(NormalExcelConstants.DATA_LIST, new ArrayList());
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(params = "importExcel", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<SsjTallyEntity> listSsjTallyEntitys = ExcelImportUtil.importExcel(file.getInputStream(), SsjTallyEntity.class, params);
                for (SsjTallyEntity ssjTally : listSsjTallyEntitys) {
                    ssjTallyService.save(ssjTally);
                }
                j.setMsg("文件导入成功！");
            } catch (Exception e) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(e));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return j;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<SsjTallyEntity> list() {
        List<SsjTallyEntity> listSsjTallys = ssjTallyService.getList(SsjTallyEntity.class);
        return listSsjTallys;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        SsjTallyEntity task = ssjTallyService.get(SsjTallyEntity.class, id);
        if (task == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody SsjTallyEntity ssjTally, UriComponentsBuilder uriBuilder) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<SsjTallyEntity>> failures = validator.validate(ssjTally);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }

        //保存
        try {
            ssjTallyService.save(ssjTally);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        //按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
        String id = ssjTally.getId();
        URI uri = uriBuilder.path("/rest/ssjTallyController/" + id).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody SsjTallyEntity ssjTally) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<SsjTallyEntity>> failures = validator.validate(ssjTally);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }

        //保存
        try {
            ssjTallyService.saveOrUpdate(ssjTally);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        //按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        ssjTallyService.deleteEntityById(SsjTallyEntity.class, id);
    }
}
