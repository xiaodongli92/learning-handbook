<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>

<!--nav-->
<header id="header">
    <hgroup>
        <h1 class="site_title"><a href="${pageContext.request.contextPath}/main.do" >blog</a></h1>
        <h2 class="section_title">首页</h2>
        <div class="btn_view_site">
            <a href="${pageContext.request.contextPath}/signOut.do" class="navbar-link">退出</a>
        </div>
    </hgroup>
</header>
<section id="secondary_bar">
    <div class="user">
        <p>${sessionScope.userName}</p>
    </div>
</section>

