<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>积分</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ssjCreditController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${ssjCreditPage.id }">
					<input id="createName" name="createName" type="hidden" value="${ssjCreditPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${ssjCreditPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${ssjCreditPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${ssjCreditPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${ssjCreditPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${ssjCreditPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${ssjCreditPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${ssjCreditPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${ssjCreditPage.bpmStatus }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								积分:
							</label>
						</td>
						<td class="value">
						     	 <input id="credit" name="credit" type="text" style="width: 150px" class="inputxt"  value='${ssjCreditPage.credit}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">积分</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/lyq/ssj/ssjCredit.js"></script>		
