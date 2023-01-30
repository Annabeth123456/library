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
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main_header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tableOp.css">
    <%--    href="${pageContext.request.contextPath}/client/css/main.css--%>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<%--    <script src="${pageContext.request.contextPath}/js/tableOp.js"></script>--%>

    <script type="text/javascript">
        function onSearch(obj){//js函数开始
            //因为是即时查询，需要用setTimeout进行延迟，让值写入到input内，再读取
            setTimeout(function() {

                    var storeId = document.getElementById('store');//获取table的id标识
                    var rows = storeId.rows;
                    var key = obj.value;//获取输入框的值
                    var searchCol = 0;//要搜索的哪一列，这里是第一列，从0开始数起

                    for (var i = 1; i < rows.length; i++) {
                        //按行搜索
                        for(var j = 0; j< rows[i].cells.length;j++) {
                            //按列搜索
                            var searchText = storeId.rows[i].cells[j].innerHTML;//取得每个小cell的值
                            //判断是否匹配，匹配直接break，不匹配j++接着往后找
                            if (searchText.match(key)) {//用match函数进行筛选，如果input的值，即变量key的值为空，返回的是ture，
                                storeId.rows[i].style.display = '';//显示行操作，
                                break;
                            } else {
                                storeId.rows[i].style.display = 'none';//隐藏行操作
                            }
                        }
                    }
                },
                200,)
        }
    </script>
</head>

<body>
<%
    Object obj = session.getAttribute("user");
    String userId = ((User)obj).getUserId();
%>
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

    <div class="head">
        <span><b>图书搜索</b></span>
        <input type="text" class="searcher" onkeyup="onSearch(this)" placeholder="请输入图书信息">
        <button class="search_btn" onclick="Op();"><b>查询</b></button>
    </div>


    <div class="content">
        <table id="store">
            <%--        <td><a href="${pageContext.request.contextPath}/ShowBooks">点击查询</a></td>--%>
            <tr>
                <td><b>图书id</b></td>
                <td><b>图书名称</b></td>
                <td><b>作者</b></td>
                <td><b>ISBN</b></td>
                <td><b>出版社</b></td>
                <td><b>总数</b></td>
                <td><b>剩余数量</b></td>
                <td><b>操作</b></td>
            </tr>


            <c:forEach items="${requestScope.books}" var="find">
                <tr>
                    <td>${find.bookId}</td>
                    <td>${find.bookName}</td>
                    <td>${find.author}</td>
                    <td>${find.ISBN}</td>
                    <td>${find.press}</td>
                    <td>${find.total}</td>
                    <td>${find.remain}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/Borrow?userId=<%=userId%>&bookId=${find.bookId}&remain=${find.remain}" onclick="Op();">
<%--                            <a href="${pageContext.request.contextPath}/DeleteBook?bookId=${find.bookId}" onclick="delSuccess();">--%>
<%--                            多个参数？？    <a href="url?参数=值&参数=值"    --%>
                            <button class="edit"><b>借阅</b></button>
<%--                            ${pageContext.request.contextPath}/DeleteBook?bookId=${find.bookId}--%>
                        </a>
                    </td>
                </tr>
            </c:forEach>



        </table>
    </div>

</div>
</body>
<script src="${pageContext.request.contextPath}/js/tableOp.js"></script>
</html>
