<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/head.inc" %>
    <title>出错了</title>
</head>

<body>
<!-- top -->
<jsp:include page="/WEB-INF/include/top.jsp"/>

<div class="container" style="position: relative;">
    <!--left-->
    <jsp:include page="/WEB-INF/include/left.jsp"/>

    <!-- right -->
    <!--right-->
    <div class="col-sm-9">
        <div style="text-align: center;margin: 10px auto;">
            <br><br><br>
            出错了，${errMsg}
        </div>
    </div>
</div>
</body>
</html>