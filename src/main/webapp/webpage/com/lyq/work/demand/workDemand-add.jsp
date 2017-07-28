<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>需求单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
		<link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css" />
		<script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="workDemandController.do?doAdd" tiptype="1" callback="jeecgFormFileCallBack@Override">
					<input id="id" name="id" type="hidden" value="${workDemandPage.id }">
					<input id="createName" name="createName" type="hidden" value="${workDemandPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${workDemandPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${workDemandPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${workDemandPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${workDemandPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${workDemandPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${workDemandPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${workDemandPage.sysCompanyCode }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							当前状态:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="bpmStatus" type="list"
									typeGroupCode="demandType" defaultVal="${workDemandPage.bpmStatus}" hasLabel="false"  title="流程状态"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">流程状态</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 600px" class="inputxt"  datatype="*">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							需求内容:
						</label>
					</td>
					<td class="value">
						  	 <textarea style="width:600px;height: 100px;" class="inputxt" rows="6" id="content" name="content"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">需求内容</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							提出人:
						</label>
					</td>
					<td class="value">
					     	 <input id="proposeMan" name="proposeMan" type="text" style="width: 150px" class="inputxt"  value='${workDemandPage.proposeMan}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提出人</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							提出日期:
						</label>
					</td>
					<td class="value">
							   <input id="proposeDate" name="proposeDate" type="text" style="width: 150px"
					      						class="Wdate" value='<fmt:formatDate value='${workDemandPage.proposeDate}' type="date" pattern="yyyy-MM-dd"/>' onClick="WdatePicker()"
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提出日期</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							工程师:
						</label>
					</td>
					<td class="value">
					     	 <input id="programmer" name="programmer" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工程师</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							完成时间:
						</label>
					</td>
					<td class="value">
							   <input id="finishTime" name="finishTime" type="text" style="width: 150px"
					      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">完成时间</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							完成备注:
						</label>
					</td>
					<td class="value">
						  	 <textarea style="width:600px;height: 100px;" class="inputxt" rows="6" id="finishRemark" name="finishRemark"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">完成备注</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							附件:
						</label>
					</td>
					<td class="value">
								<table></table>
								<div class="form jeecgDetail">
									<script type="text/javascript">
										var serverMsg="";
										$(function(){
											$('#attachment').uploadify({
												buttonText:'添加文件',
												auto:false,
												progressData:'speed',
												multi:true,
												height:25,
												overrideEvents:['onDialogClose'],
												fileTypeDesc:'文件格式:',
												queueID:'filediv_file',
												fileTypeExts:'*.rar;*.zip;*.doc;*.docx;*.txt;*.ppt;*.xls;*.xlsx;*.html;*.htm;*.pdf;*.jpg;*.gif;*.png',
												fileSizeLimit:'15MB',
												swf:'plug-in/uploadify/uploadify.swf',
												uploader:'cgUploadController.do?saveFiles&jsessionid='+$("#sessionUID").val()+'',
												onUploadStart : function(file) {
													var cgFormId=$("input[name='id']").val();
													$('#attachment').uploadify("settings", "formData", {
														'cgFormId':cgFormId,
														'cgFormName':'work_demand',
														'cgFormField':'ATTACHMENT'
													});
												} ,
												onQueueComplete : function(queueData) {
													 var win = frameElement.api.opener;
													 win.reloadTable();
													 win.tip(serverMsg);
													 frameElement.api.close();
												},
												onUploadSuccess : function(file, data, response) {
													var d=$.parseJSON(data);
													if(d.success){
														var win = frameElement.api.opener;
														serverMsg = d.msg;
													}
												},
												onFallback: function() {
								                    tip("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试")
								                },
								                onSelectError: function(file, errorCode, errorMsg) {
								                    switch (errorCode) {
								                    case - 100 : tip("上传的文件数量已经超出系统限制的" + $('#file').uploadify('settings', 'queueSizeLimit') + "个文件！");
								                        break;
								                    case - 110 : tip("文件 [" + file.name + "] 大小超出系统限制的" + $('#file').uploadify('settings', 'fileSizeLimit') + "大小！");
								                        break;
								                    case - 120 : tip("文件 [" + file.name + "] 大小异常！");
								                        break;
								                    case - 130 : tip("文件 [" + file.name + "] 类型不正确！");
								                        break;
								                    }
								                },
								                onUploadProgress: function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {}
											});
										});
									</script>
									<span id="file_uploadspan"><input type="file" name="attachment" id="attachment" /></span>
								</div>
								<div class="form" id="filediv_file"></div>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">附件</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							级别:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="level" type="list"
									typeGroupCode="level" defaultVal="${workDemandPage.level}" hasLabel="false"  title="级别"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">级别</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/lyq/work/demand/workDemand.js"></script>
	  	<script type="text/javascript">
	  		function jeecgFormFileCallBack(data){
	  			if (data.success == true) {
					uploadFile(data);
				} else {
					if (data.responseText == '' || data.responseText == undefined) {
						$.messager.alert('错误', data.msg);
						$.Hidemsg();
					} else {
						try {
							var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
							$.messager.alert('错误', emsg);
							$.Hidemsg();
						} catch(ex) {
							$.messager.alert('错误', data.responseText + '');
						}
					}
					return false;
				}
				if (!neibuClickFlag) {
					var win = frameElement.api.opener;
					win.reloadTable();
				}
	  		}
	  		function upload() {
				$('#attachment').uploadify('upload', '*');
			}

			var neibuClickFlag = false;
			function neibuClick() {
				neibuClickFlag = true;
				$('#btn_sub').trigger('click');
			}
			function cancel() {
				$('#attachment').uploadify('cancel', '*');
			}
			function uploadFile(data){
				if(!$("input[name='id']").val()){
					if(data.obj!=null && data.obj!='undefined'){
						$("input[name='id']").val(data.obj.id);
					}
				}
				if($(".uploadify-queue-item").length>0){
					upload();
				}else{
					if (neibuClickFlag){
						alert(data.msg);
						neibuClickFlag = false;
					}else {
						var win = frameElement.api.opener;
						win.reloadTable();
						win.tip(data.msg);
						frameElement.api.close();
					}
				}
			}
	  	</script>
