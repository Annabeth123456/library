package servlet.adm.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import Dao.BookDao;
@WebServlet("/DeleteBook")
public class DeleteBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteBookServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        User user = (User)request.getSession().getAttribute("user");
//        String userId = user.getUserId();
        new BookDao().delBook(bookId);
        response.sendRedirect(request.getContextPath() + "/ShowBooks");
//        request.getRequestDispatcher(request.getContextPath() + "/ShowBooks").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

