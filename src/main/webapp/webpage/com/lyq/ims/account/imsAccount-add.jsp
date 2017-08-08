<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>账户</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="imsAccountController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${imsAccountPage.id }">
					<input id="createName" name="createName" type="hidden" value="${imsAccountPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${imsAccountPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${imsAccountPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${imsAccountPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${imsAccountPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${imsAccountPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${imsAccountPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${imsAccountPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${imsAccountPage.bpmStatus }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							账户名:
						</label>
					</td>
					<td class="value">
					     	 <input id="account" name="account" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">账户名</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							密码:
						</label>
					</td>
					<td class="value">
					     	 <input id="password" name="password" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">密码</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							网址:
						</label>
					</td>
					<td class="value">
					     	 <input id="url" name="url" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">网址</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="remark" name="remark"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
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
  <script src = "webpage/com/lyq/ims/account/imsAccount.js"></script>		
