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
                  <input type="hidden" name="testquestionsid" id="testquestionsid" value="${shitiid}"> 
                  <div class="layui-form-item">
                      <label for="timu" class="layui-form-label">
                          <span class="x-red">*</span>题目
                      </label>
                      <div class="layui-input-inline">
                          <input type="text" id="timu" name="timu" required="" lay-verify="timu"
                          autocomplete="off" class="layui-input">
                      </div>
                  </div>
                  <div class="layui-form-item">
                      <label for="selecta" class="layui-form-label">
                          <span class="x-red">*</span>A选项
                      </label>
                      <div class="layui-input-inline">
                          <input type="text" id="selecta" name="selecta" required="" lay-verify="selecta"
                          autocomplete="off" class="layui-input">
                      </div>
                  </div>
                 <div class="layui-form-item">
                      <label for="selectb" class="layui-form-label">
                          <span class="x-red">*</span>B选项
                      </label>
                      <div class="layui-input-inline">
                          <input type="text" id="selectb" name="selectb" required="" lay-verify="selectb"
                          autocomplete="off" class="layui-input">
                      </div>
                  </div>
                   <div class="layui-form-item">
                      <label for="selectc" class="layui-form-label">
                          <span class="x-red">*</span>C选项
                      </label>
                      <div class="layui-input-inline">
                          <input type="text" id="selectc" name="selectc" required="" lay-verify="selectc"
                          autocomplete="off" class="layui-input">
                      </div>
                  </div>
                   <div class="layui-form-item">
                      <label for="selectd" class="layui-form-label">
                          <span class="x-red">*</span>D选项
                      </label>
                      <div class="layui-input-inline">
                          <input type="text" id="selectd" name="selectd" required="" lay-verify="selectd"
                          autocomplete="off" class="layui-input">
                      </div>
                  </div>
                   <div class="layui-form-item">
                      <label for="daan" class="layui-form-label">
                          <span class="x-red">*</span>答案
                      </label>
                      <div class="layui-input-inline">
                          <input type="text" id="daan" name="daan" required="" lay-verify="daan"
                          autocomplete="off" class="layui-input">
                      </div>
                  </div>
                   <div class="layui-form-item">
                      <label for="jiexi" class="layui-form-label">
                          <span class="x-red">*</span>解析
                      </label>
                      <div class="layui-input-inline">
                          <input type="text" id="jiexi" name="jiexi" required="" lay-verify="jiexi"
                          autocomplete="off" class="layui-input">
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
            layui.use(['form', 'layer'],
            function() {
                $ = layui.jquery;
                var form = layui.form,
                    layer = layui.layer;
             
                
                //监听提交
                form.on('submit(add)',
                    function (data) {
                        alert("增加成功")
                      
                      var obj = JSON.stringify(data.field);
                        $.ajax({
                            type: 'post',
                            url: 'http://localhost:8080/sportApp/testquestion/addTi',
                            contentType: "application/json",
                            data:obj,
                            success: function(result) {

                            }
                        });
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
