package servlet.adm.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;

import domain.Log;

@WebServlet("/ShowUsers")


public class ShowUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowUserServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//1导入驱动jar包
//2注册驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver") ;
            //这里会抛出异常 throws Exception直接全部抛出 只抛出一个的话后面还是会接着叫你抛出异常

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");

            String sql = "select * from log";
//5获取执行SQL的对象 statement
            Statement stmt = conn.createStatement();
//            执行SQL语句返回提交
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Log> log_list = new ArrayList<>();
            while(rs.next()){
                Log log = new Log();
                //Log是类，log是实例化的对象，也是arraylist的一个项
                log.setUserId(rs.getString("u_id"));
                log.setBookId(rs.getString("b_id"));
                log.setNum(rs.getInt("num"));
                log.setStatus(rs.getString("status"));

                //把这个对象添加到log_list中
                log_list.add(log);
            }
            conn.close();
            rs.close();
            stmt.close();
            //绑定数据d
            request.setAttribute("log_list",log_list);
            //请求转发
            //request.getRequestDispatcher("/administrator/adm_index.jsp").forward(request,response);
            request.getRequestDispatcher( "/administrator/user_log.jsp").forward(request,response);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

