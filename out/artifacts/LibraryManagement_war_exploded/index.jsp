<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>图书管理系统</title>
    <link rel="stylesheet" href="./css/log_reg.css">
  </head>
  <style>

    .not_user{
      float: right;
      margin-top:50px;
    }
    .not_user a:hover{
      color:lightgrey;
    }


  </style>
  <body>
  <%
    String path = request.getContextPath();
  %>
  <div class="content">
    <div class="main" style="height: 280px">
      <h2 class="h1">图书馆管理系统登录</h2>
      <form action="<%=path%>/LoginServlet" method="post" id="form_login">
        <div class="d1">
          <td>
            <input type = "text" name="userId" placeholder="用户id"/><br/>
          </td>
        </div>
        <div class="d2">
          <td>
            <input type="password" name="password" placeholder="密码"/><br/>
          </td>
        </div>
        <div class="submit">
          <a href="javascript:document:form_login.submit();">登录</a>
        </div>

        <div class="not_user">
          <a href="./user/register.jsp" target="_blank">没有账号？点击注册</a>
        </div>
      </form>


    </div>
  </div>

  </body>
</html>
