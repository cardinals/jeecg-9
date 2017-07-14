<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>记账</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript">
        //编写自定义JS代码
    </script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ssjTallyController.do?doAdd"
             tiptype="1">
    <input id="id" name="id" type="hidden" value="${ssjTallyPage.id }">
    <input id="createName" name="createName" type="hidden" value="${ssjTallyPage.createName }">
    <input id="createBy" name="createBy" type="hidden" value="${ssjTallyPage.createBy }">
    <input id="createDate" name="createDate" type="hidden" value="${ssjTallyPage.createDate }">
    <input id="updateName" name="updateName" type="hidden" value="${ssjTallyPage.updateName }">
    <input id="updateBy" name="updateBy" type="hidden" value="${ssjTallyPage.updateBy }">
    <input id="updateDate" name="updateDate" type="hidden" value="${ssjTallyPage.updateDate }">
    <input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${ssjTallyPage.sysOrgCode }">
    <input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${ssjTallyPage.sysCompanyCode }">
    <input id="bpmStatus" name="bpmStatus" type="hidden" value="${ssjTallyPage.bpmStatus }">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right">
                <label class="Validform_label">
                    时间:
                </label>
            </td>
            <td class="value">
                <input id="time" name="time" type="text" style="width: 150px" class="Wdate"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                       value='<fmt:formatDate value='${ssjTallyPage.time}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">时间</label>
            </td>
        </tr>

        <tr>
            <td align="right">
                <label class="Validform_label">
                    账户:
                </label>
            </td>
            <td class="value"><select style="width: 150px" name="account" id="account">
                <option value="">--- 请选择账户 ---</option>
                <c:forEach var="fun" items="${accountList}" varStatus="status">
                    <option value="${fun.id }"
                            <c:if test="${ssjTallyPage.account == fun.id }">selected="selected"</c:if>><t:mutiLang
                            langKey="${fun.account}"/></option>
                </c:forEach>
            </select></td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    金额:
                </label>
            </td>
            <td class="value">
                <input id="price" name="price" type="text" style="width: 150px" class="inputxt"
                       value='${ssjTallyPage.price}'>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">金额</label>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    备注:
                </label>
            </td>
            <td class="value">
                <textarea id="memo" style="width:300px;height: 100px;" class="inputxt" rows="6"
                          name="memo">${ssjTallyPage.memo}</textarea>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">备注</label>
            </td>
        </tr>
    </table>
</t:formvalid>
</body>
<script src="webpage/com/lyq/ssj/tally/ssjTally.js"></script>
