<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="Dao.UserDao"
         import="Dao.LogDao"
         import="Dao.BookDao"
         import="domain.User"
         import="domain.Log"
         import="domain.Book"
         import ="java.sql.SQLException"
%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<html>
<head>
  <title>我的借阅记录</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main_header.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tableOp.css">
  <%--    href="${pageContext.request.contextPath}/client/css/main.css--%>
  <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/js/tableOp.js"></script>

  <script type="text/javascript">

  </script>
</head>
<body>
<div class="main">
  <div class="nav">
    <div class="left_nav">
      <span class="logo">library</span>
      <span class="sep"></span>
      <a href="${pageContext.request.contextPath}/BookOp">首页</a>
      <span class="sep"></span>
      <a href="${pageContext.request.contextPath}/MyLog">我的借阅记录</a>
      <span class="logo">Copyright © 2023 Annabeth</span>

    </div>

    <div class="right_nav">
      <span class="sep"></span>
      <a href="${pageContext.request.contextPath}/LogoutServlet">退出登录</a>
      <span class="sep"></span>
      <a href="${pageContext.request.contextPath}/userread">帮助中心</a>


    </div>
  </div>




  <div class="content">
    <table>

      <tr>
        <td><b>用户id</b></td>
        <td><b>图书id</b></td>
        <td><b>借阅数量</b></td>
        <td><b>归还状态</b></td>
        <td><b>操作</b></td>
      </tr>


      <c:forEach items="${requestScope.mylog}" var="find">
        <tr>
          <td>${find.userId}</td>
          <td>${find.bookId}</td>
          <td>${find.num}</td>
          <td>${find.status}</td>
          <td>
            <a href="${pageContext.request.contextPath}/Return?userId=${find.userId}&bookId=${find.bookId}" onclick="Op();">
<%--                ${pageContext.request.contextPath}/Return?userId=<%=userId%>&bookId=${find.bookId}&remain=${find.remain}--%>
              <button class="delete"><b>还书</b></button>
            </a>
          </td>
        </tr>
      </c:forEach>



    </table>
  </div>

</div>
</body>
</html>
