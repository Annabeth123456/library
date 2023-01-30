<%@ page contentType="text/html;charset=UTF-8" language="java" import ="java.util.*"%>
<%@ page import="Dao.UserDao"
         import="Dao.BookDao"
         import="Dao.LogDao"
         import="domain.User"
         import="domain.Book"
         import="domain.Log"
         import="servlet.adm.user.LoginServlet"
         import="servlet.adm.user.LoginServlet"
%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>用户注册信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main_header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
    <%--    href="${pageContext.request.contextPath}/client/css/main.css--%>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
<div class="main">
    <div class="nav">
        <div class="left_nav">
            <span class="logo">library</span>
            <span class="sep"></span>
            <a class="Fpage" href="${pageContext.request.contextPath}/administrator/adm_index.jsp">首页</a>

            <span class="sep"></span>
            <a href="${pageContext.request.contextPath}/ShowBooks" >图书信息管理</a>

            <span class="sep"></span>
            <a href="${pageContext.request.contextPath}/administrator/user_info.jsp" >用户信息查询</a>

            <span class="sep"></span>
            <a href="${pageContext.request.contextPath}/ShowUsers">用户借阅信息管理</a>
            <span class="logo">Copyright © 2023 Annabeth</span>

        </div>

        <div class="right_nav">
            <span class="sep"></span>
            <a href="${pageContext.request.contextPath}/LogoutServlet">退出登录</a>
            <span class="sep"></span>
            <a href="${pageContext.request.contextPath}/admread">帮助中心</a>


        </div>
    </div>
    <%
        ArrayList<User> user_list = new ArrayList<User>();
        user_list = new UserDao().showUsers();
    %>
    <div class="content">
        <table>
            <%--            <td><a href="${pageContext.request.contextPath}/ShowBooks">点击查询</a></td>--%>
            <tr>
                <td><b>用户id</b></td>
                <td><b>用户名称</b></td>
                <td><b>密码</b></td>
                <td><b>电话号码</b></td>
            </tr>

            <%
                for(int i = 0;i<user_list.size();i++){
            %>
            <tr>
                <td><%=user_list.get(i).getUserId()%></td>
                <td><%=user_list.get(i).getUsername()%></td>
                <td><%=user_list.get(i).getPassword()%></td>
                <td><%=user_list.get(i).getTel()%></td>
            </tr>
                <% }%>
        </table>
    </div>
</div>
</body>
</html>
