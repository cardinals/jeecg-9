<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>工作日志</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript">
        //编写自定义JS代码
    </script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="workLogController.do?doUpdate"
             tiptype="1">
    <input id="id" name="id" type="hidden" value="${workLogPage.id }">
    <input id="createName" name="createName" type="hidden" value="${workLogPage.createName }">
    <input id="createBy" name="createBy" type="hidden" value="${workLogPage.createBy }">
    <input id="createDate" name="createDate" type="hidden" value="${workLogPage.createDate }">
    <input id="updateName" name="updateName" type="hidden" value="${workLogPage.updateName }">
    <input id="updateBy" name="updateBy" type="hidden" value="${workLogPage.updateBy }">
    <input id="updateDate" name="updateDate" type="hidden" value="${workLogPage.updateDate }">
    <input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${workLogPage.sysOrgCode }">
    <input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${workLogPage.sysCompanyCode }">
    <input id="bpmStatus" name="bpmStatus" type="hidden" value="${workLogPage.bpmStatus }">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right">
                <label class="Validform_label">
                    标题:
                </label>
            </td>
            <td class="value">
                <input id="title" name="title" type="text" style="width:600px" class="inputxt"
                       value='${workLogPage.title}'>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">标题</label>
            </td>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    内容:
                </label>
            </td>
            <td class="value">
                <textarea id="content" style="width:600px;height: 100px;" class="inputxt" rows="6"
                          name="content">${workLogPage.content}</textarea>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">内容</label>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    日期:
                </label>
            </td>
            <td class="value">
                <input id="date" name="date" type="text" style="width: 150px" class="Wdate" onchange="changeDate(this)" onClick="WdatePicker()"
                       value='<fmt:formatDate value='${workLogPage.date}' type="date" pattern="yyyy-MM-dd"/>'>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">日期</label>
            </td>
        </tr>
    </table>
</t:formvalid>
</body>
<script src="webpage/com/lyq/work/worklog/workLog.js"></script>
