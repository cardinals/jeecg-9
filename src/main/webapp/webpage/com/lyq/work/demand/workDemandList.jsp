<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="workDemandList" checkbox="true" fitColumns="false" title="需求单" actionUrl="workDemandController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当前状态"  field="bpmStatus" defaultVal="1" query="true" queryMode="single" dictionary="demandType" width="120"></t:dgCol>
   <t:dgCol title="标题"  field="title" query="true" queryMode="single"  width="300"></t:dgCol>
   <t:dgCol title="需求内容"  field="content"   query="true" queryMode="single"  width="300"></t:dgCol>
   <t:dgCol title="提出人"  field="proposeMan"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提出日期"  field="proposeDate" formatter="yyyy-MM-dd"  query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="工程师"  field="programmer"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="完成时间"  field="finishTime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="完成备注"  field="finishRemark"   query="true" queryMode="single"  width="300"></t:dgCol>
   <t:dgCol title="附件"  field="attachment"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="级别"  field="level"   query="true" queryMode="single" dictionary="level" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="workDemandController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="workDemandController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="workDemandController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="workDemandController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="workDemandController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/lyq/work/demand/workDemandList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#workDemandListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#workDemandListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#workDemandListtb").find("input[name='proposeDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#workDemandListtb").find("input[name='proposeDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#workDemandListtb").find("input[name='finishTime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });



//导入
function ImportXls() {
	openuploadwin('Excel导入', 'workDemandController.do?upload', "workDemandList");
}

//导出
function ExportXls() {
	JeecgExcelExport("workDemandController.do?exportXls","workDemandList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("workDemandController.do?exportXlsByT","workDemandList");
}
 </script>