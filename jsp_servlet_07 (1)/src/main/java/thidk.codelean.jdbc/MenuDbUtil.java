package thidk.codelean.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class MenuDbUtil {

    private DataSource dataSource;

    public MenuDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Menu> getMenu() throws Exception {

        List<Menu> Menu = new ArrayList<>();

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
            String sql = "select * from menu order by Name";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int id = myRs.getInt("id");
                String name = myRs.getString("Name");
                int price = myRs.getInt("Price");
                String description = myRs.getString("Description");
                String imagePath = myRs.getString("Image"); // Add this line

                // create new menu object
                Menu tempMenu = new Menu(id, name, price, description, imagePath);

                // add it to the list of menus
                Menu.add(tempMenu);
            }

            return Menu;
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

    public void addMenu(Menu theMenu) throws Exception {

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
            String sql = "insert into menu (Name, Price, Description, Image) values (?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // set the param values for the menu
            myStmt.setString(1, theMenu.getName());
            myStmt.setInt(2, theMenu.getPrice());
            myStmt.setString(3, theMenu.getDescription());
            myStmt.setString(4, theMenu.getImagePath()); // Add this line

            // execute sql insert
            myStmt.execute();
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    public Menu getMenu(String theMenuId) throws Exception {

        Menu theMenu = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int menuId;

        try {
            // convert menu id to int
            menuId = Integer.parseInt(theMenuId);

            // get a connection
            String url = "jdbc:mysql://localhost:3306/food_store";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url, username, password);

            // create sql to get selected menu
            String sql = "select * from menu where id=?";

            // create prepared statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, menuId);

            // execute statement
            myRs = myStmt.executeQuery();

            // retrieve data from result set row
            if (myRs.next()) {
                String name = myRs.getString("Name");
                int price = myRs.getInt("Price");
                String description = myRs.getString("Description");
                String imagePath = myRs.getString("Image"); // Add this line

                // use the menuId during construction
                theMenu = new Menu(menuId, name, price, description, imagePath);
            } else {
                throw new Exception("Could not find menu id: " + menuId);
            }

            return theMenu;
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public void updateMenu(Menu theMenu) throws Exception {

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
            String sql = "update menu set Name=?, Price=?, Description=?, Image=? where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, theMenu.getName());
            myStmt.setInt(2, theMenu.getPrice());
            myStmt.setString(3, theMenu.getDescription());
            myStmt.setString(4, theMenu.getImagePath()); // Add this line
            myStmt.setInt(5, theMenu.getId());

            // execute SQL statement
            myStmt.execute();
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }


    public void deleteMenu(String theMenuId) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // convert student id to int
            int menuId = Integer.parseInt(theMenuId);

            // get connection to database
//			myConn = dataSource.getConnection();
            // get a connection
            String url = "jdbc:mysql://localhost:3306/food_store";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            myConn = DriverManager.getConnection(url,username,password);

            // create sql to delete student
            String sql = "delete from menu where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, menuId);

            // execute sql statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC code
            close(myConn, myStmt, null);
        }
    }
}















