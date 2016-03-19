<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/head.inc" %>
    <link rel="stylesheet" href="${ctx}/static/css/user_info.css" type="text/css"/>
    <style>
        .col-sm-9{
            font-size: 18px;
        }
    </style>
</head>

<body>
<!-- top -->
<jsp:include page="/WEB-INF/include/top.jsp"/>

<div class="container" style="position: relative;">
    <!--left-->
    <jsp:include page="/WEB-INF/include/left.jsp"/>
    <!-- right -->
    <div class="col-sm-9">

        <div class="panel panel-default">
            <div class="panel-heading" style="overflow:auto;">
                <h4 style="float:left">个人信息</h4>
            </div>
        </div>
        <form action="${ctx}/user/saveUserInfo.do" method="post" id="form">
            <table class="table table-hover table-bordered table-striped" style="width: 80%;margin-left: 10%">
                <tr>
                    <th class="left_tr">类型</th>
                    <th>你的信息</th>
                </tr>
                <tr>
                    <td class="left_tr">昵称</td>
                    <td><input type="text" class="form-control" style="width: 300px;" id="name" value="${user.name}"></td>
                </tr>
                <tr>
                    <td class="left_tr">真实姓名</td>
                    <td><input type="text" class="form-control" style="width: 300px;" id="realName" value="${user.realName}"></td>
                </tr>
                <tr>
                    <td class="left_tr">生日</td>
                    <td><input type="text" class="form-control my-date" style="width: 300px;" id="birthday"
                               value=<fmt:formatDate value="${userInfo.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>></td>
                </tr>
                <tr>
                    <td class="left_tr">邮箱</td>
                    <td><input type="text" class="form-control" style="width: 300px;" id="email" value="${user.email}" disabled></td>
                </tr>
                <tr>
                    <td class="left_tr">手机号</td>
                    <td><input type="text" class="form-control" style="width: 300px;" id="mobile" value="${user.mobile}"></td>
                </tr>
                <tr>
                    <td class="left_tr">注册时间</td>
                    <td><input type="text" class="form-control" style="width: 300px;" id="registerDate" disabled
                        value="<fmt:formatDate value="${user.registerDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"> </td>
                </tr>
                <tr>
                    <td class="left_tr">所在地</td>
                    <td>
                        <select id="provinceCode" class="form-control" style="width: 150px;float: left" model-value="${userInfo.provinceCode}">
                            <option value="">省份</option>
                        </select>
                        <select id="cityCode" class="form-control" style="width: 150px;float: left;height: 100%;" model-value="${userInfo.cityCode}">
                            <option value="">城市</option>
                        </select>
                        <select id="countyCode" class="form-control" style="width: 150px;float: left" model-value="${userInfo.countyCode}">
                            <option value="">区县</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="left_tr">性别</td>
                    <td>
                        <input type="radio" name="gender" value="1" ${userInfo.gender==1?'checked':''}>男
                        &nbsp;&nbsp;&nbsp;
                        <input type="radio" name="gender" value="2" ${userInfo.gender==2?'checked':''}>女
                    </td>
                </tr>
                <tr>
                    <td class="left_tr">性取向</td>
                    <td>
                        <input type="radio" name="sexualOrientation" value="1" ${userInfo.sexualOrientation==1?'checked':''}>男
                        &nbsp;&nbsp;&nbsp;
                        <input type="radio" name="sexualOrientation" value="2" ${userInfo.sexualOrientation==2?'checked':''}>女
                    </td>
                </tr>
                <tr>
                    <td class="left_tr">感情状况</td>
                    <td>
                        <select id="maritalStatus" class="form-control" style="width: 300px;text-align: center" model-value="${userInfo.maritalStatus}">
                            <option value="">请选择</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="left_tr">血型</td>
                    <td>
                        <select id="bloodType" class="form-control" style="width: 300px;text-align: center" model-value="${userInfo.bloodType}">
                            <option value="">请选择</option>
                            <option value="1">A型</option>
                            <option value="2">B型</option>
                            <option value="3">AB型</option>
                            <option value="4">O型</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="left_tr">简介</td>
                    <td>
                        <textarea type="text" class="form-control" style="width: 300px;height: 70px;" id="profile">${userInfo.profile}</textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="button" id="save" class="btn btn-large btn-block" style="width: 300px;margin-left: 30%;">
                            保存
                        </button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <!--right-->
</div>
</body>
<script>
    $(function(){
        initPage();
        $(".my-date").datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            todayBtn: 'linked',
            language: 'zh-CN'
        });
        $("#provinceCode").on('change',function(){
            var provinceCode = $("#provinceCode").val();
            initCitySelect(provinceCode);
        })
        $("#cityCode").on('change',function(){
            var cityCode = $("#cityCode").val();
            initCountySelect(cityCode);
        })
        $("#save").bind('click',function(){
            saveUser();
            saveUserInfo();
        })
    })
    function initPage(){
        var provinceCode = $("#provinceCode").attr('model-value');
        var cityCode = $("#cityCode").attr("model-value");
        var countyCode = $("#countyCode").attr("model-value");
        initProvinceSelect(provinceCode);
        initCitySelect(provinceCode,cityCode);
        initCountySelect(cityCode,countyCode);
        initCodeItemMap();
        var bloodType = $("#bloodType").attr('model-value');
        $("#bloodType").val(bloodType);
    }
    function initCountySelect(cityCode,countyCode){
        if (cityCode==''){
            return false;
        }
        $("#countyCode").html("<option value=''>区县</option>");
        $.ajax({
            url: '${ctx}/bs/getCountyMap.do',
            type: 'post',
            dataType: 'json',
            data: {
                cityCode: cityCode
            },
            success: function(data){
                if (data.errorCode==1){
                    alert(data.errorMessage);
                } else {
                    var map = data.data;
                    var checkEq;
                    $.each(map, function (key, values) {
                        checkEq = (key==countyCode);
                        $("#countyCode").append("<option value='" + key + "' "+(checkEq?"selected":"")+">" + values + "</option>");
                    });
                }
            }
        })
    }
    function initCitySelect(provinceCode,cityCode){
        if (provinceCode == ''){
            return false;
        }
        $("#cityCode").html("<option value=''>城市</option>");
        $.ajax({
            url: '${ctx}/bs/getCityMap.do',
            type: 'post',
            dataType: 'json',
            data: {
                provinceCode: provinceCode
            },
            success: function(data){
                if (data.errorCode==1){
                    alert(data.errorMessage);
                } else {
                    var map = data.data;
                    var checkEq;
                    $.each(map, function (key, values) {
                        checkEq = (key==cityCode);
                        $("#cityCode").append("<option value='" + key + "' "+(checkEq?"selected":"")+">" + values + "</option>");
                    });
                }
            }
        })
    }
    function initProvinceSelect(provinceCode){
        $.ajax({
            url: '${ctx}/bs/getProvinceMap.do',
            type: 'post',
            dataType: 'json',
            success: function(data){
                if (data.errorCode==1){
                    alert(data.errorMessage);
                } else {
                    var map = data.data;
                    var checkEq;
                    $.each(map, function (key, values) {
                        checkEq = (key==provinceCode);
                        $("#provinceCode").append("<option value='"+key+"' "+(checkEq?"selected":"")+">" + values + "</option>");
                    });
                }
            }
        })
    }
    function initCodeItemMap(){
        var maritalStatus = $("#maritalStatus").attr("model-value");
        $.ajax({
            url: '${ctx}/bs/codeItemMap.do',
            type: 'post',
            dataType: 'json',
            data: {
                codeSetValue:'maritalStatus'
            },
            success: function(data){
                if (data.errorCode==1){
                    alert(data.errorMessage);
                } else {
                    var maritalStatusMap = data.data;
                    var checkEq;
                    $.each(maritalStatusMap, function (key, values) {
                        checkEq = (key==maritalStatus);
                        $("#maritalStatus").append("<option value='"+key+"' "+(checkEq?"selected":"")+">" + values + "</option>");
                    });
                }
            }
        })
    }
    function saveUser(){
        var name = $("#name").val();
        var realName = $("#realName").val();
        var mobile = $("#mobile").val();
        $.ajax({
            url:'${ctx}/user/saveUser.do',
            type:'post',
            dataType:'json',
            data:{
                name:name,
                mobile:mobile,
                realName:realName
            },
            success:function(data){
                if (data.errorCode==1){
                    alert(data.errorMessage);
                }
            }
        });
    }
    function saveUserInfo(){
        var birthday = $("#birthday").val();
        var registerDate = $("#registerDate").val();
        var provinceCode = $("#provinceCode").val();
        var cityCode = $("#cityCode").val();
        var countyCode = $("#countyCode").val();
        var gender = $("input[name=gender]:checked").val();
        var sexualOrientation = $("input[name=sexualOrientation]:checked").val();
        var maritalStatus = $("#maritalStatus").val();
        var bloodType = $("#bloodType").val();
        var profile = $("#profile").val();
        $.ajax({
            url:'${ctx}/user/saveUserInfo.do',
            type:'post',
            dataType:'json',
            data:{
                provinceCode:provinceCode,
                cityCode:cityCode,
                countyCode:countyCode,
                gender:gender,
                sexualOrientation:sexualOrientation,
                maritalStatus:maritalStatus,
                birthday:birthday,
                bloodType:bloodType,
                profile:profile
            },
            success:function(data){
                if (data.errorCode==1){
                    alert(data.errorMessage);
                }else{
                    location.reload();
                }
            }
        })
    }
</script>
</html>