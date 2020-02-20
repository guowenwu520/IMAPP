<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html class="x-admin-sm">
    <head>
        <meta charset="UTF-8">
        <title>欢迎页面-X-admin2.2</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="../css/font.css">
        <link rel="stylesheet" href="../css/xadmin.css">
        <script src="../lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="../js/xadmin.js"></script>
        <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="x-nav">
          <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
            <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
        </div>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body ">
                            <div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="layadmin-useradmin-formlist">
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                       <input type="text" name="username" id="usernameid" placeholder="请输入题目" autocomplete="off" class="layui-input">
                                   </div>
                                </div>
                                <div class="layui-inline "  >
                                    <button class="layui-btn"  onclick="Search()"><i class="layui-icon">&#xe615;</i></button>
                                </div>
                            </div>
                        </div>

                         <div class="layui-card-body ">
                            <table class="layui-table layui-form" id="mytable" lay-filter="ctuae"></table>
                            <script type="text/html" id="barDemo">
                                   <a title="删除" lay-event="del" href="javascript:;">
                                    <i class="layui-icon">&#xe640;</i>
                                </a>
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
    <script>
        var table;
        var ids='${shitiid}';
        layui.use('table',function(){
            table=layui.table;
            table.render({
                elem:'#mytable',
                url:'http://localhost:8080/sportApp/testquestion/getTis',
                page:true,
                limit:5,
                id:"adminid",
                cols:[[
                    {field: 'tiid',title:"ID",width:100},
                    {field: 'timu',title:"题目",width:100},
                    {field: 'selecta',title:"选项Ａ",width:100},
                    {field: 'selectb',title:"选项Ｂ",width:100},
                    {field: 'selectc',title:"选项Ｃ",width:100},
                    {field: 'selectd',title:"选项Ｄ",width:100},
                    {field: 'daan',title:"答案",width:100},
                    {field: 'jiexi',title:"解析",width:100},
                    {field:'right', title: '操作', width:100,toolbar:"#barDemo"}
                ]]
            });
            table.reload('adminid', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                	testquestionsid: ids
                }
            });
            //监听工具条
            table.on('tool(ctuae)', function(obj){
                var data = obj.data;
               if(obj.event === 'del'){
                    layer.confirm('确定删除吗', function(index){
                        $.ajax({
                            type: 'get',
                            url: 'http://localhost:8080/sportApp/User/deleteTi?tiid='+data.tiid,
                            success: function(result) {
                                alert("删除成功")
                                obj.del();
                                layer.close(index);
                            }
                        });

                    });
                } 
            });
        });
        function Search() {
            var demoReload = $('#usernameid');
            //执行重载
            table.reload('adminid', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    keyword: demoReload.val(),
                    testquestionsid: ids
                }
            });
        };
    </script>
</html>