package com.lyq.work.controller.demand;

import com.lyq.work.entity.demand.WorkDemandEntity;
import com.lyq.work.service.demand.WorkDemandServiceI;

import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.*;
import org.jeecgframework.tag.vo.datatable.SortDirection;
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
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;

import java.io.OutputStream;

import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 需求单
 * @date 2017-07-28 08:33:57
 */
@Controller
@RequestMapping("/workDemandController")
public class WorkDemandController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(WorkDemandController.class);

    @Autowired
    private WorkDemandServiceI workDemandService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;
    @Autowired
    private CgFormFieldServiceI cgFormFieldService;


    /**
     * 需求单列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/lyq/work/demand/workDemandList");
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
    public void datagrid(WorkDemandEntity workDemand, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WorkDemandEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, workDemand, request.getParameterMap());
        try {
            //自定义追加查询条件
            String query_proposeDate_begin = request.getParameter("proposeDate_begin");
            String query_proposeDate_end = request.getParameter("proposeDate_end");
            if (StringUtil.isNotEmpty(query_proposeDate_begin)) {
                cq.ge("proposeDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_proposeDate_begin));
            }
            if (StringUtil.isNotEmpty(query_proposeDate_end)) {
                cq.le("proposeDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_proposeDate_end));
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        // 添加排序
        cq.addOrder("level", SortDirection.asc);
        cq.addOrder("proposeDate", SortDirection.asc);
        this.workDemandService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除需求单
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WorkDemandEntity workDemand, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        workDemand = systemService.getEntity(WorkDemandEntity.class, workDemand.getId());
        message = "需求单删除成功";
        try {
            workDemandService.delete(workDemand);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "需求单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除需求单
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "需求单删除成功";
        try {
            for (String id : ids.split(",")) {
                WorkDemandEntity workDemand = systemService.getEntity(WorkDemandEntity.class,
                        id
                );
                workDemandService.delete(workDemand);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "需求单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 添加需求单
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(WorkDemandEntity workDemand, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "需求单添加成功";
        try {
            workDemandService.save(workDemand);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "需求单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        j.setObj(workDemand);
        return j;
    }

    /**
     * 更新需求单
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(WorkDemandEntity workDemand, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "需求单更新成功";
        WorkDemandEntity t = workDemandService.get(WorkDemandEntity.class, workDemand.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(workDemand, t);
            workDemandService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "需求单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 需求单新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(WorkDemandEntity workDemand, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(workDemand.getId())) {
            workDemand = workDemandService.getEntity(WorkDemandEntity.class, workDemand.getId());
        } else {
            workDemand.setBpmStatus("1");// 默认为解决
            workDemand.setProposeDate(new Date());// 设置默认提出日期
            workDemand.setProposeMan(ResourceUtil.getSessionUserName().getRealName());// 设置提出人
        }
        req.setAttribute("workDemandPage", workDemand);
        return new ModelAndView("com/lyq/work/demand/workDemand-add");
    }

    /**
     * 需求单编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(WorkDemandEntity workDemand, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(workDemand.getId())) {
            workDemand = workDemandService.getEntity(WorkDemandEntity.class, workDemand.getId());
            req.setAttribute("workDemandPage", workDemand);
        }
        return new ModelAndView("com/lyq/work/demand/workDemand-update");
    }

    /**
     * 导入功能跳转
     *
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "workDemandController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public String exportXls(WorkDemandEntity workDemand, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(WorkDemandEntity.class, dataGrid);
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, workDemand, request.getParameterMap());
        List<WorkDemandEntity> workDemands = this.workDemandService.getListByCriteriaQuery(cq, false);
        modelMap.put(NormalExcelConstants.FILE_NAME, "需求单");
        modelMap.put(NormalExcelConstants.CLASS, WorkDemandEntity.class);
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("需求单列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                "导出信息"));
        modelMap.put(NormalExcelConstants.DATA_LIST, workDemands);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    /**
     * 导出excel 使模板
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(WorkDemandEntity workDemand, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put(NormalExcelConstants.FILE_NAME, "需求单");
        modelMap.put(NormalExcelConstants.CLASS, WorkDemandEntity.class);
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("需求单列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
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
                List<WorkDemandEntity> listWorkDemandEntitys = ExcelImportUtil.importExcel(file.getInputStream(), WorkDemandEntity.class, params);
                for (WorkDemandEntity workDemand : listWorkDemandEntitys) {
                    workDemandService.save(workDemand);
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

    /**
     * 获取文件附件信息
     *
     * @param id workDemand主键id
     */
    @RequestMapping(params = "getFiles")
    @ResponseBody
    public AjaxJson getFiles(String id) {
        List<CgUploadEntity> uploadBeans = cgFormFieldService.findByProperty(CgUploadEntity.class, "cgformId", id);
        List<Map<String, Object>> files = new ArrayList<Map<String, Object>>(0);
        for (CgUploadEntity b : uploadBeans) {
            String title = b.getAttachmenttitle();//附件名
            String fileKey = b.getId();//附件主键
            String path = b.getRealpath();//附件路径
            String field = b.getCgformField();//表单中作为附件控件的字段
            Map<String, Object> file = new HashMap<String, Object>();
            file.put("title", title);
            file.put("fileKey", fileKey);
            file.put("path", path);
            file.put("field", field == null ? "" : field);
            files.add(file);
        }
        AjaxJson j = new AjaxJson();
        j.setObj(files);
        return j;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<WorkDemandEntity> list() {
        List<WorkDemandEntity> listWorkDemands = workDemandService.getList(WorkDemandEntity.class);
        return listWorkDemands;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        WorkDemandEntity task = workDemandService.get(WorkDemandEntity.class, id);
        if (task == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody WorkDemandEntity workDemand, UriComponentsBuilder uriBuilder) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<WorkDemandEntity>> failures = validator.validate(workDemand);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }

        //保存
        try {
            workDemandService.save(workDemand);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        //按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
        String id = workDemand.getId();
        URI uri = uriBuilder.path("/rest/workDemandController/" + id).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody WorkDemandEntity workDemand) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        Set<ConstraintViolation<WorkDemandEntity>> failures = validator.validate(workDemand);
        if (!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        }

        //保存
        try {
            workDemandService.saveOrUpdate(workDemand);
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
        workDemandService.deleteEntityById(WorkDemandEntity.class, id);
    }
}
