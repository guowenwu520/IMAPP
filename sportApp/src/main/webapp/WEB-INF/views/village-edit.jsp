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
                    <input type="hidden" id="id" name="id" value="${id}">
                  <div class="layui-form-item">
                      <label for="title" class="layui-form-label">
                        社区名
                      </label>
                      <div class="layui-input-inline">
                          <input type="text" id="title" name="title" required="" lay-verify="title" value=${title}
                          autocomplete="off" class="layui-input">
                      </div>
                  </div>
                  <div class="layui-form-item">
                      <label for="villageDesc" class="layui-form-label">
                          描述
                      </label>
                      <div class="layui-input-inline">
                          <input type="text" id="villageDesc" name="villageDesc" required="" lay-verify="villageDesc" value='${villageDesc}'
                          autocomplete="off" class="layui-input">
                      </div>
              
                  </div>
                  <div class="layui-form-item">
                      <label for=""district"" class="layui-form-label">
                          语言
                      </label>
                      <div class="layui-input-inline">
                          <input type="text" id="district" name="district" required="" lay-verify="district" value="${district}"
                          autocomplete="off" class="layui-input">
                      </div>
                      <div class="layui-form-mid layui-word-aux">
                          <span class="x-red">*</span>
                      </div>
                  </div>
                  <div class="layui-form-item">
                      <label for="L_repass" class="layui-form-label">
                      </label>
                      <button  class="layui-btn" lay-filter="update" lay-submit="">
                          修改
                      </button>
                  </div>
              </form>
            </div>
        </div>
        <script>
               layui.use(['form', 'layer'],
            function() {
                $ = layui.jquery;
                var form = layui.form,
                layer = layui.layer;

                //自定义验证规则
              
                //监听提交
                form.on('submit(update)',
                function(data) {
                    var obj = JSON.stringify(data.field);
                    $.ajax({
                        type: 'post',
                        url: 'http://localhost:8080/sportApp/village/updateVillage',
                        contentType: "application/json",
                        data:obj,
                        success: function(result) {
                            alert("修改成功");
                        }
                    });

                        //关闭当前frame
                        xadmin.close();

                        // 可以对父窗口进行刷新 
                       xadmin.father_reload();
                    return false;
                });

            });</script>
    </body>

</html>
