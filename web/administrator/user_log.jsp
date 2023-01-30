<%@ page contentType="text/html;charset=UTF-8" language="java" import ="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>用户借阅信息管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main_header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tableOp.css">

    <%--    href="${pageContext.request.contextPath}/client/css/main.css--%>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/tableOp.js"></script>
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
    <div class="content">
        <table>
            <%--        <td><a href="${pageContext.request.contextPath}/ShowBooks">点击查询</a></td>--%>
            <tr>
                <td><b>用户id</b></td>
                <td><b>图书id</b></td>
                <td><b>借阅数量</b></td>
                <td><b>归还状态</b></td>
                <td><b>操作</b></td>
            </tr>


            <c:forEach items="${requestScope.log_list}" var="find">
                <tr>
                    <td>${find.userId}</td>
                    <td>${find.bookId}</td>
                    <td>${find.num}</td>
                    <td>${find.status}</td>
                    <td>
                        <a href="#" onclick="click_show();">
                            <button class="edit"><b>编辑</b></button>
                        </a>
<%--                        <a href="${pageContext.request.contextPath}/DeleteBook?bookId=${find.bookId}" onclick="delSuccess();">--%>
<%--                            <button class="delete"><b>删除</b></button>--%>
<%--                        </a>--%>
                    </td>
                </tr>
            </c:forEach>



        </table>
    </div>
    <div id="upForm" style="display: none">
        <div class="up_title">更新图书信息</div>
        <div>
            <form action="${pageContext.request.contextPath}/updateUser" method="post" id="upBookform">

                <td><input type = "text" name="userId" placeholder="用户id"/><br/></td>
                <td><input type = "text" name="bookId" placeholder="图书id"/><br/></td>
                <td><input type = "text" name="num" placeholder="借阅数量"/><br/></td>
                <td><input type = "text" name="status" placeholder="借阅状态"/><br/></td>
                <input class="submit" type="submit" value="更新" onclick="upSuccess();">

            </form>
        </div>

        <div class="close" onclick="click_off();">x</div>
    </div>
</div>
</body>
</html>
