package servlet.adm.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.LogDao;
@WebServlet("/updateUser")

public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateUserServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String userId = request.getParameter("userId");
        String bookId = request.getParameter("bookId");
        String Num = request.getParameter("num");
        int num = Integer.parseInt(Num);
        String status = request.getParameter("status");

        new LogDao().updateUser(num,status,userId,bookId);
//        new LogDao().updateUser(1,"1","001","0001");


        response.sendRedirect(request.getContextPath() + "/ShowUsers");

        //request.getRequestDispatcher( "/administrator/book_all.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
