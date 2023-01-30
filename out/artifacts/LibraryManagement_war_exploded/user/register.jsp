<%--
  Created by IntelliJ IDEA.
  User: 石雪瑶
  Date: 2022/12/26
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册用户</title>
    <link rel="stylesheet" href="../css/log_reg.css">
</head>
<style>
    .box{
        width: 400px;
        height: 350px;
        background-color: black;
        /* opacity属性参数的不透明度是以数字表示的，
        从0.0到1.0，完全透明是0.0，完全不透明是1.0，数字越大代表元素越不透明。 */
        opacity: 0.6;
        margin-left: 10%;
        margin-top: 10%;
        text-align: center;
        align-items: center;
    }

    .id,.d3{
        height: 30px;
        width: 350px;
        font-size:18px;
        margin:20px;
        color:grey;
        text-align: center;
    }

    <% String path = request.getContextPath();%>
</style>
<body>
<div class="box">
    <h2 class="h1">用户注册</h2>
    <form action="<%=path%>/RegisterServlet" method="post" id="form_register">
        <div class="id">
            <td>
                <input type = "text" name="userId" placeholder="用户id"/><br/>
            </td>
        </div>
        <div class="d1">
            <td>
                <input type = "text" name="username" placeholder="用户名称"/><br/>
            </td>
        </div>

        <div class="d2">
            <td>
                <input type="password" name="password" placeholder="密码"/><br/>
            </td>
        </div>
        <div class="d3">
            <td>
                <input type="number" name="tel" placeholder="手机号码"/><br/>
            </td>
        </div>

        <div class="submit">
            <a href="javascript:document:form_register.submit();">点击提交</a>
            <!-- 设置提交函数进行事件提交
            document.form1.submit(); -->

        </div>
    </form>

</div>
</body>
</html>
