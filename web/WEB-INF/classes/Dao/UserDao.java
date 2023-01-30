package Dao;

import domain.Book;
import domain.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    public UserDao() {
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

    //添加用户
    public boolean addUser(String userId,String username, String password, String tel){
//        System.out.println("已完成用户"+userId+"的注册");

        //建立数据库连接并初始化参数
        Connection conn = getConnection();
        //PreparedStatement的第一次执行消耗是很高的. 它的性能体现在后面的重复执行;
        // Statement适合只执行一次，预编译，可避免sql注入攻击
        PreparedStatement pstm = null;
        String sql = "insert into user(u_id,username,password,tel) values(?,?,?,?)";


        //addUser函数类型为布尔，设定结果res初始值为false，后续再设定为true，并最终返回res
        boolean res = false;
        try {
            pstm = conn.prepareStatement(sql);
            //预编译接口，代替上述问号
            pstm.setString(1, userId);
            pstm.setString(2, username);
            pstm.setString(3, password);
            pstm.setString(4, tel);

            res = (pstm.executeUpdate() == 1);
            //executeUpdate() 用于执行 INSERT、UPDATE 、 DELETE 语句或不返回任何内容的 SQL 语句
        } catch (SQLException e) {//处理异常
            //当try语句中出现异常是时，会执行catch中的语句，
            // java运行时系统会自动将catch括号中的Exception e 初始化，也就是实例化Exception类型的对象。e是此对象引用名称。
            // 然后e（引用）会自动调用Exception类中指定的方法，也就出现了e.printStackTrace() ;
            //printStackTrace()方法的意思是：在命令行打印异常信息在程序中出错的位置及原因。
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

    //根据用户名查找用户,用于登录；login
    //返回类型为user，是User类的一个实例（想要查找的实例），这里和数据库的user没有关系
    public User findUser(String userId){
//        System.out.println("已完成用户"+userId+"的登录");

        //登录finduser整体思路：创建一个新的空user，将利用username查询到的用户信息（ex:getString("password"))拷贝到新的user对应项上，最终返回这个新的user
        String sql = "select * from user where u_id=?";
        Connection conn = getConnection();
        PreparedStatement pstm = null;

        ResultSet rs = null;
        User user = new User();
        //new一个新用户？？
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, userId);
            rs = pstm.executeQuery();
            //executeQuery用于产生单个结果集的语句，例如 SELECT 语句。
            //如果执行存储过程将产生大于 1 的更新计数，或生成多个结果集，则使用 execute 方法执行存储过程。

            if (rs.next()) {//判断是否有数据返回

                //在结果集里面得到数据（getString）
                //并传输给user（new的新用户，用于返回结果）
                user.setUserId(rs.getString("u_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setTel(rs.getString("tel"));
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
        return user;
    }

    //删除用户
    public boolean delUser(String userId){
        System.out.println("已完成用户"+userId+"的删除操作");
        //建立数据库连接并初始化参数
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        String sql = "delete from user where u_id=?";

        //addUser函数类型为布尔，设定结果res初始值为false，后续再设定为true，并最终返回res
        boolean res = false;
        try {
            pstm = conn.prepareStatement(sql);
            //预编译接口，代替上述问号
            pstm.setString(1, userId);
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

    //用户批量输出
    public ArrayList<User> showUsers() throws SQLException {
        String sql = "select * from user";

        Connection conn = getConnection();
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        //ArrayList<E> objectName =new ArrayList<>();　 // 初始化
        ArrayList<User> users = new ArrayList<>();

        try{
            while(rs.next()){
                User user = new User();
                user.setUserId(rs.getString("u_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setTel(rs.getString("tel"));

                users.add(user);
                System.out.println("用户id："+user.getUserId()+"用户名："+user.getUsername()+"密码："+user.getPassword()+"电话："+user.getTel());
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

        return users;
    }


    public static void main(String[] args) throws SQLException {
        //测试方法
        //System.out.println(new UserDao().findUser("John"));
        //new UserDao().addUser("002", "李强","123456","1324568034");
//        User user = new UserDao().findUser("001");
//        System.out.println("用户名："+user.getUsername()+"密码："+user.getPassword()+"电话："+user.getTel());
//        new UserDao().delUser("sxy");
        new UserDao().showUsers();

    }

}




