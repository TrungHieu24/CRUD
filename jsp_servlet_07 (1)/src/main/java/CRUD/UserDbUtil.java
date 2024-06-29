package CRUD;

import CRUD.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDbUtil {

    private DataSource dataSource;

    public UserDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<User> getUser() throws Exception {

        List<User> User = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            String url = "jdbc:mysql://localhost:3306/food_store";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);
//			myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from user order by FullName";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int id = myRs.getInt("id");
                String name = myRs.getString("FullName");
                String birthday = myRs.getString("Birthday");
                String email = myRs.getString("Email");
                String phone = myRs.getString("Phone");
                String passwords = myRs.getString("Password");
                String role = myRs.getString("Role");
                String address = myRs.getString("Address");
                String resetToken = myRs.getString("ResetToken");

                // create new user object
                User tempUser = new User(id, name, birthday, email, phone, passwords,role,address,resetToken);

                // add it to the list of users
                User.add(tempUser);
            }

            return User;
        }
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();   // doesn't really close it ... just puts back in connection pool
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void addUser(User theUser) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get a connection
            String url = "jdbc:mysql://localhost:3306/food_store";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            // create sql for insert
            String sql = "insert into user (FullName, Birthday, Email, Phone, Password, Role, Address, ResetToken) values (?, ?, ?, ?, ?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // set the param values for the menu
            myStmt.setString(1, theUser.getFullName());
            myStmt.setString(2, theUser.getBirthday());
            myStmt.setString(3, theUser.getEmail());
            myStmt.setString(4, theUser.getPhone());
            myStmt.setString(5, theUser.getPassword());
            myStmt.setString(6, theUser.getRole());
            myStmt.setString(7, theUser.getAddress());
            myStmt.setString(8, theUser.getResetToken());// Add this line

            // execute sql insert
            myStmt.execute();
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    public User getUser(String theUserId) throws Exception {

        User theUser = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int userId;

        try {
            // convert menu id to int
            userId = Integer.parseInt(theUserId);

            // get a connection
            String url = "jdbc:mysql://localhost:3306/food_store";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            // create sql to get selected menu
            String sql = "select * from user where id=?";

            // create prepared statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, userId);

            // execute statement
            myRs = myStmt.executeQuery();

            // retrieve data from result set row
            if (myRs.next()) {
                String name = myRs.getString("FullName");
                String birthday = myRs.getString("Birthday");
                String email = myRs.getString("Email");
                String phone = myRs.getString("Phone");
                String passwords = myRs.getString("Password");
                String role = myRs.getString("Role");
                String address = myRs.getString("Address");
                String resetToken = myRs.getString("ResetToken"); // Add this line

                // use the menuId during construction
                theUser = new User(userId, name, birthday, email, phone, passwords,role,address,resetToken);
            } else {
                throw new Exception("Could not find user id: " + userId);
            }

            return theUser;
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public void updateUser(User theUser) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get a connection
            String url = "jdbc:mysql://localhost:3306/food_store";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            // create SQL update statement
            String sql = "update user set FullName=?, Birthday=?, Email=?, Phone=?, Password=?, Role=?, Address=?, ResetToken=? where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, theUser.getFullName());
            myStmt.setString(2, theUser.getBirthday());
            myStmt.setString(3, theUser.getEmail());
            myStmt.setString(4, theUser.getPhone());
            myStmt.setString(5, theUser.getPassword());
            myStmt.setString(6, theUser.getRole());
            myStmt.setString(7, theUser.getAddress());
            myStmt.setString(8, theUser.getResetToken());

            // execute SQL statement
            myStmt.execute();
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }


    public void deleteUser(String theUserId) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // convert student id to int
            int userId = Integer.parseInt(theUserId);

            // get connection to database
//			myConn = dataSource.getConnection();
            // get a connection
            String url = "jdbc:mysql://localhost:3306/food_store";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);

            // create sql to delete student
            String sql = "delete from user where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, userId);

            // execute sql statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC code
            close(myConn, myStmt, null);
        }
    }
}















