package servlet.adm.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;

import domain.Book;

@WebServlet("/ShowBooks")


public class ShowBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowBooksServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//1导入驱动jar包
//2注册驱动
            try {
                Class.forName("com.mysql.cj.jdbc.Driver") ;
                //这里会抛出异常 throws Exception直接全部抛出 只抛出一个的话后面还是会接着叫你抛出异常

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");

                String sql = "select * from book";
//5获取执行SQL的对象 statement
                Statement stmt = conn.createStatement();
//            执行SQL语句返回提交
                ResultSet rs = stmt.executeQuery(sql);
                ArrayList<Book> books = new ArrayList<>();
                while(rs.next()){
                    Book book = new Book();
                    book.setbookId(rs.getString("b_id"));
                    book.setbookName(rs.getString("b_name"));
                    book.setAuthor(rs.getString("author"));
                    book.setISBN(rs.getString("ISBN"));
                    book.setPress(rs.getString("press"));
                    book.setTotal(rs.getInt("total"));
                    book.setRemain(rs.getInt("remain"));

                    books.add(book);
                }
                conn.close();
                rs.close();
                stmt.close();
                //绑定数据d
                request.setAttribute("books",books);
                //请求转发
                //request.getRequestDispatcher("/administrator/adm_index.jsp").forward(request,response);
                request.getRequestDispatcher( "/administrator/book_all.jsp").forward(request,response);



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

