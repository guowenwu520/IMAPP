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
        <script type="text/javascript" src="../lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="../js/xadmin.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
            <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
            <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="layui-fluid">
            <div class="layui-row">
                <form class="layui-form">
                  <div class="layui-form-item">
                      <label for="yuyan" class="layui-form-label">
                          <span class="x-red">*</span>语言
                      </label>
                      <div class="layui-input-inline">
                          <input type="text" id="yuyan" name="yuyan" required="" lay-verify="yuyan"
                          autocomplete="off" class="layui-input">
                      </div>
                  </div>
                  <div class="layui-form-item">
                      <label for="miaoshi" class="layui-form-label">
                          <span class="x-red">*</span>描述
                      </label>
                      <div class="layui-input-inline">
                          <input type="text" id="miaoshi" name="miaoshi" required="" lay-verify="miaoshi"
                          autocomplete="off" class="layui-input">
                      </div>
                  </div>
                  <div class="layui-form-item">
                      <label for="img" class="layui-form-label">
                          <span class="x-red">*</span>图片
                      </label>
                      <div class="layui-input-inline">
                         <button type="button" class="btn btn-primary" id="chooseFile">选择图片</button>
                          <input type="text" id="img" name="img" required="" lay-verify="img"
                          autocomplete="off" class="layui-input">
                      </div>
                      <div class="layui-form-mid layui-word-aux">
                          <span class="x-red">*</span>
                      </div>
                  </div>
                  <div class="layui-form-item">
                      <label for="L_repass" class="layui-form-label">
                      </label>
                      <button  class="layui-btn"  id="upload" lay-filter="add" lay-submit="">
                          增加
                      </button>
                  </div>
              </form>
            </div>
        </div>
        <script>
        var uploadListIns;
            layui.use(['form', 'layer','upload'],
            function() {
                $ = layui.jquery;
                var form = layui.form,
                    layer = layui.layer;
                upload = layui.upload;
             
                uploadListIns = upload.render({
                    elem: '#chooseFile',   //选择文件的按钮
                    url: 'http://localhost:8080/sportApp/testquestion/addShiJuan',   //后台处理文件长传的方法
                    data:{'yuyan':function(){
                        return $("#yuyan").val();
                    },'miaoshi':function(){
                        return $("#miaoshi").val();
                    }},
                    accept: 'file', 
                    multiple: false,     //是否允许多文件上传
                    acceptMime: 'image/*',  //规定打开文件选择框时，筛选出的文件类型
                    field:'file',       
                    auto: false,   
                    bindAction: '#upload',   //用来触发上传的按钮ID
                    choose: function(obj){    //选择文件后的回调函数，本例中在此将选择的文件进行展示
                        //读取本地文件
                        obj.preview(function(index, file, result){
                        	$("#img").val(file.name);
                             
                        });
                    }
                });
                
                //监听提交
                form.on('submit(add)',
                    function (data) {
                        alert("增加成功")
                      

                        //console.log(obj);
                        //发异步，把数据提交给php
                        // layer.alert(, {
                        //     icon: 6
                        // },
                        //function() {
                            //关闭当前frame
                            xadmin.close();

                            // 可以对父窗口进行刷新
                            xadmin.father_reload();
                        //};
                        return false;
                    });
            });
         
        </script>
    </body>

</html>
