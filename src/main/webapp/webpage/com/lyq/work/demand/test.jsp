<script type="text/javascript" src="plug-in/mutiLang/zh-cn.js"></script>
<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="plug-in/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="plug-in/jquery-plugs/storage/jquery.storageapi.min.js"></script>
<script type="text/javascript" src="plug-in/tools/dataformat.js"></script>
<link id="easyuiTheme" rel="stylesheet" href="plug-in/easyui/themes/metrole/easyui.css" type="text/css"></link>
<link id="easyuiTheme" rel="stylesheet" href="plug-in/easyui/themes/metrole/main.css" type="text/css"></link>
<link id="easyuiTheme" rel="stylesheet" href="plug-in/easyui/themes/metrole/icon.css" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="plug-in/accordion/css/accordion.css">
<link rel="stylesheet" type="text/css" href="plug-in/accordion/css/icons.css">
<script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.1.3.2.js"></script>
<script type="text/javascript" src="plug-in/easyui/locale/zh-cn.js"></script>
<script type="text/javascript" src="plug-in/tools/syUtil.js"></script>
<script type="text/javascript" src="plug-in/easyui/extends/datagrid-scrollview.js"></script>
<script type="text/javascript" src="plug-in/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="plug-in/tools/css/metrole/common.css" type="text/css"></link>
<script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js?skin=metrole"></script>
<script type="text/javascript" src="plug-in/ace/js/bootstrap-tab.js"></script>
<script type="text/javascript" src="plug-in/tools/curdtools_zh-cn.js"></script>
<script type="text/javascript" src="plug-in/tools/easyuiextend.js"></script>
<script type="text/javascript" src="plug-in/jquery-plugs/hftable/jquery-hftable.js"></script>
<script type="text/javascript" src="plug-in/tools/json2.js"></script>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">


        <script type="text/javascript">$(function () {
            storage = $.localStorage;
            if (!storage)storage = $.cookieStorage;
            $('#workDemandList').datagrid({
                idField: 'id',
                title: '需求单',
                url: 'workDemandController.do?datagrid&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,title,content,proposeMan,proposeDate,programmer,finishTime,finishRemark,attachment,level,',
                fit: true,
                queryParams: {},
                loadMsg: '数据加载中...',
                pageSize: 10,
                pagination: true,
                pageList: [10, 20, 30],
                sortOrder: 'asc',
                rownumbers: true,
                singleSelect: false,
                fitColumns: false,
                striped: true,
                showFooter: true,
                frozenColumns: [[{field: 'ck', checkbox: 'true'},]],
                columns: [[{field: 'id', title: '主键', width: 120, hidden: true, sortable: true}, {
                    field: 'createName',
                    title: '创建人名称',
                    width: 120,
                    hidden: true,
                    sortable: true
                }, {
                    field: 'createBy',
                    title: '创建人登录名称',
                    width: 120,
                    hidden: true,
                    sortable: true
                }, {
                    field: 'createDate',
                    title: '创建日期',
                    width: 120,
                    hidden: true,
                    sortable: true,
                    formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd', value);
                    }
                }, {field: 'updateName', title: '更新人名称', width: 120, hidden: true, sortable: true}, {
                    field: 'updateBy',
                    title: '更新人登录名称',
                    width: 120,
                    hidden: true,
                    sortable: true
                }, {
                    field: 'updateDate',
                    title: '更新日期',
                    width: 120,
                    hidden: true,
                    sortable: true,
                    formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd', value);
                    }
                }, {
                    field: 'sysOrgCode',
                    title: '所属部门',
                    width: 120,
                    hidden: true,
                    sortable: true
                }, {
                    field: 'sysCompanyCode',
                    title: '所属公司',
                    width: 120,
                    hidden: true,
                    sortable: true
                }, {
                    field: 'bpmStatus',
                    title: '流程状态',
                    width: 120,
                    sortable: true,
                    formatter: function (value, rec, index) {
                        if (value == undefined) return '';
                        var valArray = value.split(',');
                        if (valArray.length > 1) {
                            var checkboxValue = '';
                            for (var k = 0; k < valArray.length; k++) {
                                if (valArray[k] == '1') {
                                    checkboxValue = checkboxValue + '未解决' + ',';
                                }
                                if (valArray[k] == '2') {
                                    checkboxValue = checkboxValue + '已解决' + ',';
                                }
                                if (valArray[k] == '0') {
                                    checkboxValue = checkboxValue + '作废' + ',';
                                }
                            }
                            return checkboxValue.substring(0, checkboxValue.length - 1);
                        } else {
                            if (value == '1') {
                                return '未解决';
                            }
                            if (value == '2') {
                                return '已解决';
                            }
                            if (value == '0') {
                                return '作废';
                            } else {
                                return value;
                            }
                        }
                    }
                }, {field: 'title', title: '标题', width: 300, sortable: true}, {
                    field: 'content',
                    title: '需求内容',
                    width: 300,
                    sortable: true
                }, {field: 'proposeMan', title: '提出人', width: 120, sortable: true}, {
                    field: 'proposeDate',
                    title: '提出日期',
                    width: 120,
                    sortable: true,
                    formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd', value);
                    }
                }, {field: 'programmer', title: '工程师', width: 120, sortable: true}, {
                    field: 'finishTime',
                    title: '完成时间',
                    width: 120,
                    sortable: true,
                    formatter: function (value, rec, index) {
                        return new Date().format('yyyy-MM-dd hh:mm:ss', value);
                    }
                }, {field: 'finishRemark', title: '完成备注', width: 300, sortable: true}, {
                    field: 'attachment',
                    title: '附件',
                    width: 120,
                    hidden: true,
                    sortable: true
                }, {
                    field: 'level', title: '级别', width: 120, sortable: true, formatter: function (value, rec, index) {
                        if (value == undefined) return '';
                        var valArray = value.split(',');
                        if (valArray.length > 1) {
                            var checkboxValue = '';
                            for (var k = 0; k < valArray.length; k++) {
                                if (valArray[k] == '1') {
                                    checkboxValue = checkboxValue + '1' + ',';
                                }
                                if (valArray[k] == '2') {
                                    checkboxValue = checkboxValue + '2' + ',';
                                }
                                if (valArray[k] == '3') {
                                    checkboxValue = checkboxValue + '3' + ',';
                                }
                                if (valArray[k] == '4') {
                                    checkboxValue = checkboxValue + '4' + ',';
                                }
                                if (valArray[k] == '5') {
                                    checkboxValue = checkboxValue + '5' + ',';
                                }
                            }
                            return checkboxValue.substring(0, checkboxValue.length - 1);
                        } else {
                            if (value == '1') {
                                return '1';
                            }
                            if (value == '2') {
                                return '2';
                            }
                            if (value == '3') {
                                return '3';
                            }
                            if (value == '4') {
                                return '4';
                            }
                            if (value == '5') {
                                return '5';
                            } else {
                                return value;
                            }
                        }
                    }
                }, {
                    field: 'opt', title: '操作', width: 100, formatter: function (value, rec, index) {
                        if (!rec.id) {
                            return '';
                        }
                        var href = '';
                        href += "[<a href='#' onclick=delObj('workDemandController.do?doDel&id=" + rec.id + "','workDemandList')>";
                        href += "删除</a>]";
                        return href;
                    }
                }]],
                onLoadSuccess: function (data) {
                    $("#workDemandList").datagrid("clearSelections");
                },
                onClickRow: function (rowIndex, rowData) {
                    rowid = rowData.id;
                    gridname = 'workDemandList';
                }
            });
            $('#workDemandList').datagrid('getPager').pagination({
                beforePageText: '',
                afterPageText: '/{pages}',
                displayMsg: '{from}-{to}共 {total}条',
                showPageList: true,
                showRefresh: true
            });
            $('#workDemandList').datagrid('getPager').pagination({
                onBeforeRefresh: function (pageNumber, pageSize) {
                    $(this).pagination('loading');
                    $(this).pagination('loaded');
                }
            });
            try {
                restoreheader();
            } catch (ex) {
            }
        });
        function reloadTable() {
            try {
                $('#' + gridname).datagrid('reload');
                $('#' + gridname).treegrid('reload');
            } catch (ex) {
            }
        }
        function reloadworkDemandList() {
            $('#workDemandList').datagrid('reload');
        }
        function getworkDemandListSelected(field) {
            return getSelected(field);
        }
        function getSelected(field) {
            var row = $('#' + gridname).datagrid('getSelected');
            if (row != null) {
                value = row[field];
            } else {
                value = '';
            }
            return value;
        }
        function getworkDemandListSelections(field) {
            var ids = [];
            var rows = $('#workDemandList').datagrid('getSelections');
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i][field]);
            }
            ids.join(',');
            return ids
        }
        ;
        function getSelectRows() {
            return $('#workDemandList').datagrid('getChecked');
        }
        function saveHeader() {
            var columnsFields = null;
            var easyextends = false;
            try {
                columnsFields = $('#workDemandList').datagrid('getColumns');
                easyextends = true;
            } catch (e) {
                columnsFields = $('#workDemandList').datagrid('getColumnFields');
            }
            var cols = storage.get('workDemandListhiddenColumns');
            var init = true;
            if (cols) {
                init = false;
            }
            var hiddencolumns = [];
            for (var i = 0; i < columnsFields.length; i++) {
                if (easyextends) {
                    hiddencolumns.push({field: columnsFields[i].field, hidden: columnsFields[i].hidden});
                } else {
                    var columsDetail = $('#workDemandList').datagrid("getColumnOption", columnsFields[i]);
                    if (init) {
                        hiddencolumns.push({
                            field: columsDetail.field,
                            hidden: columsDetail.hidden,
                            visible: (columsDetail.hidden == true ? false : true)
                        });
                    } else {
                        for (var j = 0; j < cols.length; j++) {
                            if (cols[j].field == columsDetail.field) {
                                hiddencolumns.push({
                                    field: columsDetail.field,
                                    hidden: columsDetail.hidden,
                                    visible: cols[j].visible
                                });
                            }
                        }
                    }
                }
            }
            storage.set('workDemandListhiddenColumns', JSON.stringify(hiddencolumns));
        }
        function isShowBut() {
            var isShowSearchId = $('#isShowSearchId').val();
            if (isShowSearchId == "true") {
                $("#searchColums").hide();
                $('#isShowSearchId').val("false");
                $('#columsShow').remove("src");
                $('#columsShow').attr("src", "plug-in/easyui/themes/default/images/accordion_expand.png");
            } else {
                $("#searchColums").show();
                $('#isShowSearchId').val("true");
                $('#columsShow').remove("src");
                $('#columsShow').attr("src", "plug-in/easyui/themes/default/images/accordion_collapse.png");
            }
        }
        function restoreheader() {
            var cols = storage.get('workDemandListhiddenColumns');
            if (!cols)return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    if (cols.visible != false)$('#workDemandList').datagrid((cols[i].hidden == true ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }
        function resetheader() {
            var cols = storage.get('workDemandListhiddenColumns');
            if (!cols)return;
            for (var i = 0; i < cols.length; i++) {
                try {
                    $('#workDemandList').datagrid((cols.visible == false ? 'hideColumn' : 'showColumn'), cols[i].field);
                } catch (e) {
                }
            }
        }
        function workDemandListsearch() {
            try {
                if (!$("#workDemandListForm").Validform({tiptype: 3}).check()) {
                    return false;
                }
            } catch (e) {
            }
            if (true) {
                var queryParams = $('#workDemandList').datagrid('options').queryParams;
                $('#workDemandListtb').find('*').each(function () {
                    queryParams[$(this).attr('name')] = $(this).val();
                });
                $('#workDemandList').datagrid({
                    url: 'workDemandController.do?datagrid&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,title,content,proposeMan,proposeDate,proposeDate_begin,proposeDate_end,programmer,finishTime,finishRemark,attachment,level,',
                    pageNumber: 1
                });
            }
        }
        function dosearch(params) {
            var jsonparams = $.parseJSON(params);
            $('#workDemandList').datagrid({
                url: 'workDemandController.do?datagrid&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,title,content,proposeMan,proposeDate,proposeDate_begin,proposeDate_end,programmer,finishTime,finishRemark,attachment,level,',
                queryParams: jsonparams
            });
        }
        function workDemandListsearchbox(value, name) {
            var queryParams = $('#workDemandList').datagrid('options').queryParams;
            queryParams[name] = value;
            queryParams.searchfield = name;
            $('#workDemandList').datagrid('reload');
        }
        $('#workDemandListsearchbox').searchbox({
            searcher: function (value, name) {
                workDemandListsearchbox(value, name);
            }, menu: '#workDemandListmm', prompt: '请输入查询关键字'
        });
        function EnterPress(e) {
            var e = e || window.event;
            if (e.keyCode == 13) {
                workDemandListsearch();
            }
        }
        function searchReset(name) {
            $("#" + name + "tb").find(":input").val("");
            var queryParams = $('#workDemandList').datagrid('options').queryParams;
            $('#workDemandListtb').find('*').each(function () {
                queryParams[$(this).attr('name')] = $(this).val();
            });
            $('#workDemandList').datagrid({
                url: 'workDemandController.do?datagrid&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,title,content,proposeMan,proposeDate,proposeDate_begin,proposeDate_end,programmer,finishTime,finishRemark,attachment,level,',
                pageNumber: 1
            });
        }</script>
        <table width="100%" id="workDemandList" toolbar="#workDemandListtb"></table>
        <div id="workDemandListtb" style="padding:3px; height: auto">
            <div name="searchColums" id="searchColums"><input id="isShowSearchId" type="hidden" value="false"/><input
                    id="_sqlbuilder" name="sqlbuilder" type="hidden"/>

                <form id='workDemandListForm'>
                    <link rel="stylesheet" href="plug-in/Validform/css/style.css" type="text/css">
                    <link rel="stylesheet" href="plug-in/Validform/css/tablefrom.css" type="text/css">
                    <script type="text/javascript" src="plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js"></script>
                    <script type="text/javascript" src="plug-in/Validform/js/Validform_Datatype_zh-cn.js"></script>
                    <script type="text/javascript" src="plug-in/Validform/js/datatype_zh-cn.js"></script>
                    <span style="display:-moz-inline-box;display:inline-block;"><span
                            style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; "
                            title="流程状态">流程状态：</span><select name="bpmStatus" WIDTH="100" style="width: 104px">
                        <option value="">---请选择---</option>
                        <option value="1">未解决</option>
                        <option value="2">已解决</option>
                        <option value="0">作废</option>
                    </select></span><span style="display:-moz-inline-box;display:inline-block;"><span
                        style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; "
                        title="标题">标题：</span><input onkeypress="EnterPress(event)" onkeydown="EnterPress()" type="text"
                                                    name="title" class="inuptxt" style="width: 100px"/></span><span
                        style="display:-moz-inline-box;display:inline-block;"><span
                        style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; "
                        title="需求内容">需求内容：</span><input onkeypress="EnterPress(event)" onkeydown="EnterPress()"
                                                        type="text" name="content" class="inuptxt"
                                                        style="width: 100px"/></span><span
                        style="display:-moz-inline-box;display:inline-block;"><span
                        style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; "
                        title="提出人">提出人：</span><input onkeypress="EnterPress(event)" onkeydown="EnterPress()"
                                                      type="text" name="proposeMan" class="inuptxt"
                                                      style="width: 100px"/></span><span
                        style="display:-moz-inline-box;display:inline-block;"><span
                        style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; "
                        title="提出日期">提出日期：</span><input type="text" name="proposeDate_begin" style="width: 94px"
                                                        class="inuptxt"/><span
                        style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span><input
                        type="text" name="proposeDate_end" style="width: 94px" class="inuptxt"/></span><span
                        style="display:-moz-inline-box;display:inline-block;"><span
                        style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; "
                        title="工程师">工程师：</span><input onkeypress="EnterPress(event)" onkeydown="EnterPress()"
                                                      type="text" name="programmer" class="inuptxt"
                                                      style="width: 100px"/></span><span
                        style="display:-moz-inline-box;display:inline-block;"><span
                        style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; "
                        title="完成备注">完成备注：</span><input onkeypress="EnterPress(event)" onkeydown="EnterPress()"
                                                        type="text" name="finishRemark" class="inuptxt"
                                                        style="width: 100px"/></span><span
                        style="display:-moz-inline-box;display:inline-block;"><span
                        style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; "
                        title="级别">级别：</span><select name="level" WIDTH="100" style="width: 104px">
                    <option value="">---请选择---</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select></span></form>
            </div>
            <div style="height:30px;" class="datagrid-toolbar"><span style="float:left;"><a href="#"
                                                                                            class="easyui-linkbutton"
                                                                                            plain="true" icon="icon-add"
                                                                                            onclick="add('录入','workDemandController.do?goAdd','workDemandList',null,null)">录入</a><a
                    href="#" class="easyui-linkbutton" plain="true" icon="icon-edit"
                    onclick="update('编辑','workDemandController.do?goUpdate','workDemandList',null,null)">编辑</a><a
                    href="#" class="easyui-linkbutton" plain="true" icon="icon-remove"
                    onclick="deleteALLSelect('批量删除','workDemandController.do?doBatchDel','workDemandList',null,null)">批量删除</a><a
                    href="#" class="easyui-linkbutton" plain="true" icon="icon-search"
                    onclick="detail('查看','workDemandController.do?goUpdate','workDemandList',null,null)">查看</a><a
                    href="#" class="easyui-linkbutton" plain="true" icon="icon-put"
                    onclick="ImportXls('导入','null','workDemandList',null,null)">导入</a><a href="#"
                                                                                         class="easyui-linkbutton"
                                                                                         plain="true" icon="icon-putout"
                                                                                         onclick="ExportXls('导出','null','workDemandList',null,null)">导出</a><a
                    href="#" class="easyui-linkbutton" plain="true" icon="icon-putout"
                    onclick="ExportXlsByT('模板下载','null','workDemandList',null,null)">模板下载</a></span><span
                    style="float:right"><a href="#" class="easyui-linkbutton" iconCls="icon-search"
                                           onclick="workDemandListsearch()">查询</a><a href="#" class="easyui-linkbutton"
                                                                                     iconCls="icon-reload"
                                                                                     onclick="searchReset('workDemandList')">重置</a></span>
            </div>
        </div>
    </div>

    <script src="webpage/com/lyq/work/demand/workDemandList.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            //给时间控件加上样式
            $("#workDemandListtb").find("input[name='createDate']").attr("class", "Wdate").click(function () {
                WdatePicker({dateFmt: 'yyyy-MM-dd'});
            });
            $("#workDemandListtb").find("input[name='updateDate']").attr("class", "Wdate").click(function () {
                WdatePicker({dateFmt: 'yyyy-MM-dd'});
            });
            $("#workDemandListtb").find("input[name='proposeDate_begin']").attr("class", "Wdate").click(function () {
                WdatePicker({dateFmt: 'yyyy-MM-dd'});
            });
            $("#workDemandListtb").find("input[name='proposeDate_end']").attr("class", "Wdate").click(function () {
                WdatePicker({dateFmt: 'yyyy-MM-dd'});
            });
            $("#workDemandListtb").find("input[name='finishTime']").attr("class", "Wdate").click(function () {
                WdatePicker({dateFmt: 'yyyy-MM-dd'});
            });
        });


        //导入
        function ImportXls() {
            openuploadwin('Excel导入', 'workDemandController.do?upload', "workDemandList");
        }

        //导出
        function ExportXls() {
            JeecgExcelExport("workDemandController.do?exportXls", "workDemandList");
        }

        //模板下载
        function ExportXlsByT() {
            JeecgExcelExport("workDemandController.do?exportXlsByT", "workDemandList");
        }
    </script>