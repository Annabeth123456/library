<%@ page import="java.util.ArrayList" %>
<%@ page import="Dao.UserDao"
         import="Dao.BookDao"
         import="domain.User"
         import="domain.Book"
         import="servlet.adm.user.LoginServlet"
%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>图书管理员</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main_header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">

    <script>
    function a(){
        alert("欢迎您，管理员！")
    }
    window.onload = function(){
        a();
    }
    </script>
</head>
<body>
<%
    ArrayList<Book> books = new ArrayList<Book>();
    books = new BookDao().showBooks();
    request.setAttribute("books",books);
%>
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
<%--            <a href="${pageContext.request.contextPath}/administrator/user_mylog.jsp">用户借阅信息管理</a>--%>
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

    <div class="content">
        <table>
<%--            <td><a href="${pageContext.request.contextPath}/ShowBooks">点击查询</a></td>--%>
            <tr>
                <td><b>图书id</b></td>
                <td><b>图书名称</b></td>
                <td><b>作者</b></td>
                <td><b>ISBN</b></td>
                <td><b>出版社</b></td>
                <td><b>总数</b></td>
                <td><b>剩余数量</b></td>
<%--                <td><b>操作</b></td>--%>
            </tr>

                    <%
                        for(int i = 0;i<books.size();i++){
                    %>
                    <tr>
                        <td><%=books.get(i).getbookId()%></td>
                        <td><%=books.get(i).getbookName()%></td>
                        <td><%=books.get(i).getAuthor()%></td>
                        <td><%=books.get(i).getISBN()%></td>
                        <td><%=books.get(i).getPress()%></td>
                        <td><%=books.get(i).getTotal()%></td>
                        <td><%=books.get(i).getRemain()%></td>
<%--                        <td class="bookLog_op">--%>
<%--                            <button class="edit" onclick="delBybookId(${})"><b>编辑</b></button>--%>
<%--            &lt;%&ndash;                <a href="${pageContext.request.contextPath}/findProductById?p_id=${p.p_id }">&ndash;%&gt;--%>
<%--                            <button class="delete"><b>删除</b></button>--%>
<%--                        </td>--%>
                    </tr><% }%>
        </table>
    </div>
</div>
</body>
</html>
