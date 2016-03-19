<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>注册</title>
    <%@ include file="/WEB-INF/include/head.inc" %>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="col-xs-7">
            <p class="navbar-text navbar-left">注册</p>
        </div>
        <div class="col-xs-5">
            <p class="navbar-text navbar-right"><a href="${ctx}/goSignIn.do" class="navbar-link">登录</a></p>
        </div>
    </div>
</nav>
<div class="login-from-box">
    <form class="form-horizontal" role="form" method="post">
        <div class="form-group">
            <label for="email" class="col-xs-4 control-label">邮箱：</label>
            <div class="col-xs-8">
                <input type="text" class="form-control input-xlarge" id="email" name="email" placeholder="邮箱">
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-xs-4 control-label">昵称：</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" id="name" name="name" placeholder="昵称">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-xs-4 control-label">密码：</label>
            <div class="col-xs-8">
                <input type="password" class="form-control" id="password" name="password" placeholder="密码">
            </div>
        </div>
        <%--<div class="form-group">--%>
            <%--<div class="col-xs-10 col-xs-offset-2"--%>
                 <%--style="background:#F7F7E7;margin-bottom:5px; font-size:12px; line-height:30px">--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="form-group">
            <div class=" col-xs-12">
                <button type="button" id="btn1" class="btn btn-lg btn-primary btn-submit">注册</button>
            </div>
        </div>
    </form>
</div>
</body>
<script>
    $(function(){
        $("#btn1").click(function(){
            var email = $("#email").val();
            var password = $("#password").val();
            var name = $("#name").val();
            if (email==''){
                alert("邮箱不能为空");
                $("#email").focus();
                return false;
            }
            var email_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
            if (!email_str.test(email)){
                alert("邮箱格式不对");
                $("#email").focus();
                return false;
            }
            if (name == ''){
                alert("昵称不能为空");
                $("#name").focus();
                return false;
            }
            if (password==''){
                alert("密码不能为空");
                $("#password").focus();
                return false;
            }
            $.ajax({
                url: '${ctx}/signUp.do',
                type: 'post',
                dataType: 'json',
                data: {
                    email: email,
                    password: password,
                    name:name
                },
                success: function(data){
                    if (data.errorCode==1){
                        alert(data.errorMessage);
                    } else {
                        location.href='${ctx}/goSignIn.do';
                    }
                }
            })
        })
    })
</script>
</html>