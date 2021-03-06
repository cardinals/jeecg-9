<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
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
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table"
             action="ssjAccountController.do?doUpdate" tiptype="1">
    <input id="id" name="id" type="hidden" value="${ssjAccountPage.id }">
    <input id="createName" name="createName" type="hidden" value="${ssjAccountPage.createName }">
    <input id="createBy" name="createBy" type="hidden" value="${ssjAccountPage.createBy }">
    <input id="createDate" name="createDate" type="hidden" value="${ssjAccountPage.createDate }">
    <input id="updateName" name="updateName" type="hidden" value="${ssjAccountPage.updateName }">
    <input id="updateBy" name="updateBy" type="hidden" value="${ssjAccountPage.updateBy }">
    <input id="updateDate" name="updateDate" type="hidden" value="${ssjAccountPage.updateDate }">
    <input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${ssjAccountPage.sysOrgCode }">
    <input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${ssjAccountPage.sysCompanyCode }">
    <input id="bpmStatus" name="bpmStatus" type="hidden" value="${ssjAccountPage.bpmStatus }">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right">
                <label class="Validform_label">
                    账户名:
                </label>
            </td>
            <td class="value">
                <input id="account" name="account" type="text" style="width: 150px" class="inputxt"
                       value='${ssjAccountPage.account}'>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">账户名</label>
            </td>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    备注:
                </label>
            </td>
            <td class="value">
                <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"
                       value='${ssjAccountPage.remark}'>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">备注</label>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 账户类型: </label></td>
            <td class="value"><select id="accountTypeId" name="accountTypeId" datatype="*">
                <c:forEach items="${accountTypeList}" var="accountType">
                    <option value="${accountType.id }" ${accountType.id==ssjAccountPage.accountTypeId?'selected':''}>${accountType.accountType}</option>
                </c:forEach>
            </select> <span class="Validform_checktip">请选择账户类型</span></td>
        </tr>
    </table>
</t:formvalid>
</body>
<script src="webpage/com/lyq/ssj/account/ssjAccount.js"></script>
