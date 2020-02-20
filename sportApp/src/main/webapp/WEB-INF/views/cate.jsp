<!DOCTYPE html>
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
        <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
        <script src="../lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="../js/xadmin.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
                <a href="">首页</a>
                <a>
                    <cite>导航元素</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
                <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
            </a>
        </div>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body ">
                            <div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="layadmin-useradmin-formlist">
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <input type="text" name="username" id="usernameid" placeholder="请输入类型名" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-inline "  >
                                    <button class="layui-btn"  onclick="Search()"><i class="layui-icon">&#xe615;</i></button>
                                </div>
                            </div>
                        </div>

                        <div class="layui-card-header">
                            <button class="layui-btn" onclick="xadmin.open('添加类型','../User/ZhongZhuan?key=cate-add',600,400)"><i class="layui-icon"></i>添加</button>
                        </div>
                        <div class="layui-card-body ">
                            <table class="layui-table layui-form" id="mytable" lay-filter="ctuae"></table>
                            <script type="text/html" id="barDemo">
                                <a title="编辑" lay-event="edit"  href="javascript:;">
                                    <i class="layui-icon">&#xe642;</i>
                                </a>
                                <a title="删除" lay-event="del" href="javascript:;">
                                    <i class="layui-icon">&#xe640;</i>
                                </a>
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            var table;
            layui.use('table',function(){
                table=layui.table;
                table.render({
                    elem:'#mytable',
                    url:'http://localhost:8080/sportApp/User/getClasss',
                    page:true,
                    limit:5,
                    id:"adminid",
                    cols:[[
                        {field: 'id',title:"ID",width:230},
                        {field: 'classname',title:"标题",width:230},
                        {field: 'classs',title:"类型",width:230},
                        {field: 'classnumber',title:"数量",width:230},
                        {field:'right', title: '操作', width:230,toolbar:"#barDemo"}
                    ]]
                });
                //监听工具条
                table.on('tool(ctuae)', function(obj){
                    var data = obj.data;
                    if(obj.event === 'del'){
                        layer.confirm('确定删除吗', function(index){
                            $.ajax({
                                type: 'get',
                                url: 'http://localhost:8080/sportApp/User/deleteClass?id='+data.id,
                                success: function(result) {
                                    alert("删除成功")
                                    obj.del();
                                    layer.close(index);
                                }
                            });

                        });
                    } else if(obj.event === 'edit'){
                        xadmin.open('编辑','../User/ZhongZhuanId?key=cate-edit&adminid='+data.id+"&classtable=runclass");
                        //  layer.alert('编辑行：<br>'+ JSON.stringify(data))
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
                        keyword: demoReload.val()
                    }
                });
            };
        </script>
    </body>
</html>
