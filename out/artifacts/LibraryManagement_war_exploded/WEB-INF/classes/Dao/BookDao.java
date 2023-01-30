package Dao;

import domain.Log;
import domain.Book;
import domain.User;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public BookDao(){
    }

    //获得连接
    public static Connection getConnection() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/library";//修改为自己的数据库
        String user = "root";//修改未自己数据库的用户名密码
        String password = "";//修改未自己数据库的名密码
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //添加商品(id,price,status,p_num)
    public boolean addBook(String bookId,String bookName,String author,String ISBN,String press,int total,int remain) {
        System.out.println("已完成商品"+bookId+"的添加操作");
        //建立数据库连接并初始化参数
        Connection conn = getConnection();
        //PreparedStatement的第一次执行消耗是很高的. 它的性能体现在后面的重复执行;
        // Statement适合只执行一次，预编译，可避免sql注入攻击
        PreparedStatement pstm = null;
        String sql = "insert into book(b_id,b_name,author,ISBN,press,total,remain) values(?,?,?,?,?,?,?)";

        //addUser函数类型为布尔，设定结果res初始值为false，后续再设定为true，并最终返回res
        boolean res = false;
        try {
            pstm = conn.prepareStatement(sql);
            //预编译接口，代替上述问号

            pstm.setString(1, bookId);
            pstm.setString(2, bookName);
            pstm.setString(3, author);
            pstm.setString(4, ISBN);
            pstm.setString(5, press);
            pstm.setInt(6, total);
            pstm.setInt(7, remain);

            res = (pstm.executeUpdate() == 1);
            //executeUpdate() 用于执行 INSERT、UPDATE 、 DELETE 语句或不返回任何内容的 SQL 语句
        } catch (SQLException e) {
            if (!e.getMessage().contains("PRIMARY")) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public boolean updateBook(String bookName,String author,String ISBN,String press,int total,int remain,String bookId){

        //建立数据库连接并初始化参数
        Connection conn = getConnection();
        PreparedStatement pstm = null;

        //不用分类，只要要执行这个函数一定要把status=1， 0是初始值
        //String sql = "insert into product(id,price,status,p_num) values(?,?,?,?)"
        //update zuzi set username=?,address=?,man=? where id=?"
        String sql = "update book set b_name=?,author=?,ISBN=?,press=?,total=?,remain=? where b_id=?";

        //addUser函数类型为布尔，设定结果res初始值为false，后续再设定为true，并最终返回res
        boolean res = false;
        try {
            pstm = conn.prepareStatement(sql);
            //预编译接口，代替上述问号

            pstm.setString(1,bookName);
            pstm.setString(2,author);
            pstm.setString(3,ISBN);
            pstm.setString(4,press);
            pstm.setInt(5,total);
            pstm.setInt(6,remain);

            pstm.setString(7, bookId);
            //new BookDao().updateBook("0100","111","111","111","科学出版社",11,11);

            res = (pstm.executeUpdate() == 1);
            //executeUpdate() 用于执行 INSERT、UPDATE 、 DELETE 语句或不返回任何内容的 SQL 语句
        } catch (SQLException e) {
            if (!e.getMessage().contains("PRIMARY")) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public boolean borrow_book(String bookId){

        Connection conn = getConnection();
        PreparedStatement pstm = null;

        //不用分类，只要要执行这个函数一定要把flag=1， 0是初始值
        String sql = "update book set remain=remain-1 where b_id=?";

        //addUser函数类型为布尔，设定结果res初始值为false，后续再设定为true，并最终返回res
        boolean res = false;
        try {
            pstm = conn.prepareStatement(sql);
            //预编译接口，代替上述问号
            pstm.setString(1, bookId);

            res = (pstm.executeUpdate() == 1);
            //executeUpdate() 用于执行 INSERT、UPDATE 、 DELETE 语句或不返回任何内容的 SQL 语句
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public boolean return_book(String bookId){

        Connection conn = getConnection();
        PreparedStatement pstm = null;

        //不用分类，只要要执行这个函数一定要把flag=1， 0是初始值
        String sql = "update book set remain=remain+1 where b_id=?";

        //addUser函数类型为布尔，设定结果res初始值为false，后续再设定为true，并最终返回res
        boolean res = false;
        try {
            pstm = conn.prepareStatement(sql);
            //预编译接口，代替上述问号
            pstm.setString(1, bookId);

            res = (pstm.executeUpdate() == 1);
            //executeUpdate() 用于执行 INSERT、UPDATE 、 DELETE 语句或不返回任何内容的 SQL 语句
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    //根据商品id查找商品信息
//    public Product findProduct(int id){
//        System.out.println("已完成商品"+id+"的查找操作");
//        String sql = "select * from product where id=?";
//        Connection conn = getConnection();
//        PreparedStatement pstm = null;
//
//        ResultSet rs = null;
//        Product product = new Product();
//        try{
//            pstm = conn.prepareStatement(sql);
//            pstm.setInt(1, id);
//            rs = pstm.executeQuery();
//            //executeQuery用于产生单个结果集的语句，例如 SELECT 语句。
//            //如果执行存储过程将产生大于 1 的更新计数，或生成多个结果集，则使用 execute 方法执行存储过程。
//
//            if (rs.next()) {//判断是否有数据返回
//
//                product.setId(rs.getInt("id"));
//                product.setPrice(rs.getString("price"));
//                product.setStatus(rs.getString("status"));
//                product.setProductSoldNum(rs.getInt("p_num"));
//
//                //System.out.println("username: "+rs.getString("username")+" password: "+rs.getString("password")+" email: "+rs.getString("email"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (pstm != null) pstm.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return product;
//    }


    //查找所有商品信息
    public ArrayList<Book> showBooks() throws SQLException {
        System.out.println("已完成全部商品信息的查找操作");
        String sql = "select * from book";

        Connection conn = getConnection();
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        //ArrayList<E> objectName =new ArrayList<>();　 // 初始化
        ArrayList<Book> books = new ArrayList<>();
//        System.out.println(rs.getString("bookId")+"1");
//        System.out.println(rs.getString("b_id")+"2");


        try{
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
//                //绑定数据d
//                request.setAttribute("list",list);
//                //请求转发到find
//                request.getRequestDispatcher("findAll.jsp").forward(request,response);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return books;
    }


    //根据商品id删除商品
    public boolean delBook(String bookId){
//        System.out.println("已完成商品id为"+bookId+"的删除操作");
        //建立数据库连接并初始化参数
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        String sql = "delete from book where b_id=?";
        //String sql = "delete from user where username = ?";

        //addUser函数类型为布尔，设定结果res初始值为false，后续再设定为true，并最终返回res
        boolean res = false;
        try {
            pstm = conn.prepareStatement(sql);
            //预编译接口，代替上述问号
            pstm.setString(1, bookId);
            //别忘记传参
            res = (pstm.executeUpdate() == 1);
            //executeUpdate() 用于执行 INSERT、UPDATE 、 DELETE 语句或不返回任何内容的 SQL 语句
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }


    public static void main(String[] args) throws SQLException {
        //测试方法
        //先new一个dao类，再调动相应函数并传参

        //System.out.println(new UserDao().findUser("John"));
        //new ProductDao().addProduct(100, "10000","0",0);
        //new ProductDao().delProduct(100);


//        Product product = new ProductDao().findProduct(51);
//        System.out.println("id名："+product.getId()+"价格："+product.getPrice()+"状态："+product.getStatus()+"卖出的数量："+product.getProductSoldNum());
        //new ProductDao().showProducts();                                                                978-7-0304-5209-2
//        new BookDao().addBook("0013","基于java的软件开发全过程实战","周雪芹","978-7-0304-9156-5","科学出版社",6,6);
        new BookDao().addBook("2222","1","1","9","科学出版社",0,0);
////        new BookDao().addBook("0101","1","1","9","科学出版社",6,6);
////        new BookDao().addBook("0102","1","1","9","科学出版社",6,6);
////        new BookDao().addBook("0103","1","1","9","科学出版社",6,6);
//        new BookDao().addBook("0104","1","1","9","科学出版社",6,6);
//        new BookDao().delBook("0100");
        //new BookDao().updateBook("111","111","111","科学出版社",11,11,"0100");
//        new BookDao().borrow_book("0001");
    }

}
