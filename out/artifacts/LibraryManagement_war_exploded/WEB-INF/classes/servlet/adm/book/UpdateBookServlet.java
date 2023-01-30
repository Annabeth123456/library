package servlet.adm.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BookDao;
@WebServlet("/updateBook")

public class UpdateBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateBookServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String bookId = request.getParameter("bookId");
        String bookName = request.getParameter("bookName");
        String author = request.getParameter("author");
        String ISBN = request.getParameter("ISBN");
        String press = request.getParameter("press");
        String Total = request.getParameter("total");
        int total = Integer.parseInt(Total);
        String Remain = request.getParameter("remain");
        int remain = Integer.parseInt(Remain);

        new BookDao().updateBook(bookName,author,ISBN,press,total,remain,bookId);
        //new BookDao().updateBook("111","111","111","科学出版社",11,11,"0100");

        response.sendRedirect(request.getContextPath() + "/ShowBooks");

        //request.getRequestDispatcher( "/administrator/book_all.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
