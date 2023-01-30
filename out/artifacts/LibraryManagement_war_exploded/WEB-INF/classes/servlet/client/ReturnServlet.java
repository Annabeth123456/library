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

@WebServlet("/Return")

public class ReturnServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReturnServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String userId = request.getParameter("userId");
        String bookId = request.getParameter("bookId");

        int num = new LogDao().findNum(userId,bookId);

        if(num > 0){
            //能还，每次还一个

            //更新book
            new BookDao().return_book(bookId);

            //更新log
            //先num--
            new LogDao().return_log(userId,bookId);

            //再判断num，如果num=0,则status置0
            int num_new = new LogDao().findNum(userId,bookId);
            if(num_new == 0){
                new LogDao().SetStatusZero(userId,bookId);
            }


            response.sendRedirect(request.getContextPath() + "/MyLog");
            //response.sendRedirect(request.getContextPath() + "/ShowBooks");
        }
        else{
            PrintWriter out = response.getWriter();
            out.println("您未借阅该图书，归还失败！");
        }

        //request.getRequestDispatcher( "/administrator/book_all.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
