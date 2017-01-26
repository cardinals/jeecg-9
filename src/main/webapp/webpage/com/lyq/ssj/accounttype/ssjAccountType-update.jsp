<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>随手记账户类型</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript">
        //编写自定义JS代码
    </script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table"
             action="ssjAccountTypeController.do?doUpdate" tiptype="1">
    <input id="id" name="id" type="hidden" value="${ssjAccountTypePage.id }">
    <input id="createName" name="createName" type="hidden" value="${ssjAccountTypePage.createName }">
    <input id="createBy" name="createBy" type="hidden" value="${ssjAccountTypePage.createBy }">
    <input id="createDate" name="createDate" type="hidden" value="${ssjAccountTypePage.createDate }">
    <input id="updateName" name="updateName" type="hidden" value="${ssjAccountTypePage.updateName }">
    <input id="updateBy" name="updateBy" type="hidden" value="${ssjAccountTypePage.updateBy }">
    <input id="updateDate" name="updateDate" type="hidden" value="${ssjAccountTypePage.updateDate }">
    <input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${ssjAccountTypePage.sysOrgCode }">
    <input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${ssjAccountTypePage.sysCompanyCode }">
    <input id="bpmStatus" name="bpmStatus" type="hidden" value="${ssjAccountTypePage.bpmStatus }">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right">
                <label class="Validform_label">
                    账户类型:
                </label>
            </td>
            <td class="value">
                <input id="accountType" name="accountType" type="text" style="width: 150px" class="inputxt"
                       value='${ssjAccountTypePage.accountType}'>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">账户类型</label>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="Validform_label"> 性别: </label></td>
            <td class="value"><t:dictSelect field="sex" typeGroupCode="sex" hasLabel="false"
                                            datatype="*"></t:dictSelect> <span class="Validform_checktip"></span></td>
        </tr>
    </table>
</t:formvalid>
</body>
<script src="webpage/com/lyq/ssj/accounttype/ssjAccountType.js"></script>
