package servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;

import Dao.BookDao;
import Dao.LogDao;
import domain.Book;
import domain.Log;
import domain.User;

@WebServlet("/MyLog")

public class UserLogServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public UserLogServlet(){
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String userId = request.getParameter("bookId");

        User user = (User)request.getSession().getAttribute("user");
        String userId = user.getUserId();
        ArrayList<Log> mylog = new ArrayList<>();

        try {
            mylog = new LogDao().findUserLog(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("mylog",mylog);
        request.getRequestDispatcher( "/user/user_mylog.jsp").forward(request,response);

//        response.sendRedirect(request.getContextPath() + "/ShowBooks");
//        request.getRequestDispatcher(request.getContextPath() + "/ShowBooks").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

