package servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BookDao;
import Dao.LogDao;
import domain.Log;

@WebServlet("/Borrow")

public class BorrowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BorrowServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String userId = request.getParameter("userId");
        String bookId = request.getParameter("bookId");

        String Remain = request.getParameter("remain");
        int remain = Integer.parseInt(Remain);

        if(remain>0){
            //能借
            //先判断有没有log，没有就建立一个新的
            //创建exist来判断该用户是否建立过相关日志
            Log exist = new LogDao().findUser_Book(userId,bookId);
            if(exist.getBookId()==null){
                //是空的
                new LogDao().addLog(userId,bookId, 0,"0");
                //System.out.println("该用户未浏览过该商品，已完成浏览日志初始化操作");
            }

            //更新log
            new LogDao().borrow_log(userId,bookId);
            //更新book
            new BookDao().borrow_book(bookId);
            response.sendRedirect(request.getContextPath() + "/BookOp");
            //response.sendRedirect(request.getContextPath() + "/ShowBooks");
        }
        else{
            PrintWriter out = response.getWriter();
            out.println("图书库存数量为0，借阅失败！");
        }

     //request.getRequestDispatcher( "/administrator/book_all.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
