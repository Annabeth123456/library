package Dao;

import domain.Log;
import domain.Book;

import java.sql.*;
import java.util.ArrayList;
public class LogDao {
    public LogDao(){
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

    //初始化日志内容(添加）
    public boolean addLog(String userId,String bookId,int num,String status) {
        System.out.println("已完成对应日志内容初始化");

        Connection conn = getConnection();
        PreparedStatement pstm = null;
        String sql = "insert into log(u_id,b_id,num,status) values(?,?,?,?)";

        //addUser函数类型为布尔，设定结果res初始值为false，后续再设定为true，并最终返回res
        boolean res = false;
        try {
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, userId);
            pstm.setString(2, bookId);
            pstm.setInt(3, num);
            pstm.setString(4, status);

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


    //查找全部日志
    public ArrayList<Log> showLog() throws SQLException {
        //System.out.println("已完成用户"+username+"日志的查找操作");
//        System.out.println("已完成日志的查找操作");
        //String sql = "select * from log where username=?";
        //String sql="select * from user where username=?";
        String sql = "select * from log";

        Connection conn = getConnection();
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        //ArrayList<E> objectName =new ArrayList<>();　 // 初始化
        ArrayList<Log> log_list = new ArrayList<>();

        try{
            while(rs.next()){
                Log log = new Log();
                //Log是类，log是实例化的对象，也是arraylist的一个项
                log.setUserId(rs.getString("u_id"));
                log.setBookId(rs.getString("b_id"));
                log.setNum(rs.getInt("num"));
                log.setStatus(rs.getString("status"));

//                System.out.println("用户id:"+rs.getString("username")+"\t商品id:"+rs.getInt("id")+"\t购买状态:"+rs.getString("flag")+"\t购买数量:"+rs.getInt("b_num"));

                //把这个对象添加到log_list中
                log_list.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return log_list;
    }

    //根据用户名查找日志浏览和购买记录,在user自己里面显示
    public ArrayList<Log> findUserLog(String userId) throws SQLException {
        String sql = "select * from log where u_id=?";

        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, userId);
        rs = pstm.executeQuery();
        ArrayList<Log> user_log = new ArrayList<>();

        try{
            while(rs.next()){
                Log log = new Log();

                log.setUserId(rs.getString("u_id"));
                log.setBookId(rs.getString("b_id"));
                log.setNum(rs.getInt("num"));
                log.setStatus(rs.getString("status"));

//                System.out.println("用户名:"+rs.getString("username")+"\t浏览商品id:"+rs.getInt("id")+"\t购买状态:"+rs.getString("flag")+"\t购买数量:"+rs.getInt("b_num"));

                //把这个对象添加到log_list中
                user_log.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstm.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user_log;
    }

    //借书
    public boolean borrow_log(String userId,String bookId){

        Connection conn = getConnection();
        PreparedStatement pstm = null;

        //不用分类，只要要执行这个函数一定要把flag=1， 0是初始值
        String sql = "update log set status='1', num=num+1 where u_id=? and b_id=?";

        //addUser函数类型为布尔，设定结果res初始值为false，后续再设定为true，并最终返回res
        boolean res = false;
        try {
            pstm = conn.prepareStatement(sql);
            //预编译接口，代替上述问号
            pstm.setString(1, userId);
            pstm.setString(2, bookId);

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

    //还书
    public boolean return_log(String userId,String bookId){

        Connection conn = getConnection();
        PreparedStatement pstm = null;


        String sql = "update log set num=num-1 where u_id=? and b_id=?";
        //先判断num，num为0则status置为0（在另外一个函数实现

        //addUser函数类型为布尔，设定结果res初始值为false，后续再设定为true，并最终返回res
        boolean res = false;
        try {
            pstm = conn.prepareStatement(sql);
            //预编译接口，代替上述问号
            pstm.setString(1, userId);
            pstm.setString(2, bookId);

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

    //status置0
    public boolean SetStatusZero(String userId,String bookId){

        Connection conn = getConnection();
        PreparedStatement pstm = null;

        String sql = "update log set status=0 where u_id=? and b_id=?";
        //先判断num，num为0则status置为0（在另外一个函数实现

        //addUser函数类型为布尔，设定结果res初始值为false，后续再设定为true，并最终返回res
        boolean res = false;
        try {
            pstm = conn.prepareStatement(sql);
            //预编译接口，代替上述问号
            pstm.setString(1, userId);
            pstm.setString(2, bookId);

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

    //根据某一用户对于某一图书是否有记录
    public Log findUser_Book(String userId,String bookId){

        String sql = "select * from log where u_id=? and b_id=?";
        Connection conn = getConnection();
        PreparedStatement pstm = null;

        ResultSet rs = null;
        Log log = new Log();
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, userId);
            pstm.setString(2, bookId);
            rs = pstm.executeQuery();
            //executeQuery用于产生单个结果集的语句，例如 SELECT 语句。
            //如果执行存储过程将产生大于 1 的更新计数，或生成多个结果集，则使用 execute 方法执行存储过程。

            if (rs.next()) {//判断是否有数据返回

                log.setUserId(rs.getString("u_id"));
                log.setBookId(rs.getString("b_id"));

                log.setNum(rs.getInt("num"));
                log.setStatus(rs.getString("status"));

                //System.out.println("username: "+rs.getString("username")+" password: "+rs.getString("password")+" email: "+rs.getString("email"));
            }
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
        return log;
    }

    //根据u_id,b_id查找对应num
    public int findNum(String userId,String bookId){

        Connection conn = getConnection();
        PreparedStatement pstm = null;
        String sql = "select num from log where u_id=? and b_id=?";
        //rs是结果集
        ResultSet rs = null;
        int num=0;
        Log log = new Log();
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, userId);
            pstm.setString(2, bookId);
            rs = pstm.executeQuery();
            //executeQuery用于产生单个结果集的语句，例如 SELECT 语句。
            //如果执行存储过程将产生大于 1 的更新计数，或生成多个结果集，则使用 execute 方法执行存储过程。

            if (rs.next()) {//判断是否有数据返回

//                log.setUserId(rs.getString("u_id"));
//                log.setBookId(rs.getString("b_id"));
//
//                log.setNum(rs.getInt("num"));
//                log.setStatus(rs.getString("status"));
                num = rs.getInt("num");

                //System.out.println("username: "+rs.getString("username")+" password: "+rs.getString("password")+" email: "+rs.getString("email"));
            }
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
        return num;
    }

    //update
    public boolean updateUser(int num,String status,String userId,String bookId){

        //建立数据库连接并初始化参数
        Connection conn = getConnection();
        PreparedStatement pstm = null;

        //不用分类，只要要执行这个函数一定要把status=1， 0是初始值
        //String sql = "insert into product(id,price,status,p_num) values(?,?,?,?)"
        //update zuzi set username=?,address=?,man=? where id=?"
        String sql = "update log set num=?,status=? where u_id=? and b_id=?";

        //addUser函数类型为布尔，设定结果res初始值为false，后续再设定为true，并最终返回res
        boolean res = false;
        try {
            pstm = conn.prepareStatement(sql);
            //预编译接口，代替上述问号

            pstm.setInt(1,num);
            pstm.setString(2,status);
            pstm.setString(3,userId);
            pstm.setString(4,bookId);

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




    public static void main(String[] args) throws SQLException {
        //测试方法
        //先new一个dao类，再调动相应函数并传参

        //System.out.println(new UserDao().findUser("John"));
        //new ProductDao().addProduct(100, "10000","0",0);
        //new LogDao().addLog("Alice",12,"0",0);

        //System.out.println(new LogDao().showLog());
        //System.out.println(new LogDao().findUserLog("John"));

//        //测试一下更新
//        System.out.println(new LogDao().showLog());
//        new LogDao().updateLog("John",11);

        //new ProductDao().updateProduct(11);
//        ArrayList<Log> log_list = new ArrayList<>();
//        log_list = new LogDao().showLog();
//        new LogDao().updateLog("John",62);
        //new LogDao().addLog("001","1111",0,"0");
//        new LogDao().addLog("001","0002",0,"0");
//        new LogDao().addLog("001","0003",0,"0");
//        new LogDao().addLog("002","0001",0,"0");
//        new LogDao().addLog("002","0002",0,"0");

//        System.out.println(new LogDao().findNum("001","0001"));

    }

}
