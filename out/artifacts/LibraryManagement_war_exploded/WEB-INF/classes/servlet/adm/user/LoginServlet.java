package servlet.adm.user;

//导入库
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//导入user相关
import Dao.UserDao;
import domain.User;

@WebServlet("/LoginServlet")
//1、jsp页面 通过action提交到LoginServlet 类：
// <form action="LoginServlet " method="post" >

//2、创建LoginServlet类来处理jsp页面发出的请求
//@WebServlet("/LoginServlet")，可以不再web.xml中配置
//public class LoginServlet extends HttpServlet{

public class LoginServlet extends HttpServlet{


    private static final long serialVersionUID = 1L;

    //先创建post
    public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //接受表单信息
        //request.getParameter()方法是获取通过类似post，get等方式传入的数据，即获取客户端到服务端的数据，代表HTTP请求数据。
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        //request.setAttribute()方法是将request.getParameter()方法获取的数据保存到request域中，即将获取的数据重新封装到一个域中。
        request.setAttribute("userId", userId);
        request.setAttribute("password", password);


//        User user = new UserDao().findUser(userId);
        User user = new UserDao().findUser(userId);
//        System.out.println(userId+" "+password);
//        System.out.println(user.getUserId()+" "+user.getPassword());


        if(user.getUserId()!=null){
            //user总是非空，所以用不存在用户来判断
            //用户存在，则验证密码
            if(user.getPassword().equals(password)){
                //如果数据库中的密码和传入的密码一致

                System.out.println("登陆成功");
                //将用户存入session中
                request.getSession().setAttribute("user",user);
                //跳转到首页
//                response.sendRedirect("index.jsp");
//                response.sendRedirect("/user/user_index.jsp");
                if(userId.equals("000")){
//                    request.getRequestDispatcher("/administrator/adm_index.jsp").forward(request,response);
                    response.sendRedirect(request.getContextPath() + "/administrator/adm_index.jsp");
                    return;
                }
//                request.getRequestDispatcher("/user/user_index.jsp").forward(request,response);
                //response.sendRedirect(request.getContextPath() + "/user/user_index.jsp");

                response.sendRedirect(request.getContextPath() + "/BookOp");
                //response.sendRedirect(request.getContextPath() + "/ShowUsers");

                return;

            }else{
                //密码错误
                System.out.println("密码错误");
//                request.setAttribute("loginError","密码错误");
//                //response.sendRedirect("/client/login_fail.jsp");
//                request.getRequestDispatcher("/client/login_fail.jsp").forward(request,response);

                PrintWriter out = response.getWriter();
                //输出到下一个页面
                out.println("登录失败，用户不存在或者密码错误，请重新登录");
            }
        }else{
            //用户不存在
            System.out.println("用户不存在");

            PrintWriter out = response.getWriter();
            //输出到下一个页面
            out.println("登录失败，用户不存在或者密码错误，请重新登录");


        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
