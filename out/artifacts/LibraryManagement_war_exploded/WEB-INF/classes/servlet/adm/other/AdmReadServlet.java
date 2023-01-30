package servlet.adm.other;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admread")
//public class AdmReadServlet {
//}
public class AdmReadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdmReadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            /* 读入TXT文件 */
            String pathname = "C:/Users/石雪瑶/IdeaProjects/LibraryManagement/web/file/adm_read.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径

            File file = new File(pathname);
            List<String> lines=new ArrayList<String>();
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();

            PrintWriter out = response.getWriter();
            //输出到下一个页面
            out.println(lines);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
// TODO Auto-generated method stub
        doGet(request, response);
    }
}
