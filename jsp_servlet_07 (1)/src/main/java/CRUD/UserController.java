package CRUD;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import CRUD.User;
import CRUD.UserDbUtil;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDbUtil userDbUtil;

    @Resource(name = "jdbc/food_store")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            userDbUtil = new UserDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String theCommand = request.getParameter("command");
            if (theCommand == null)
                theCommand = "list";
            switch (theCommand) {
                case "ADD":
                    addUser(request, response);
                    break;
                case "LOAD":
                    loadUser(request, response);
                    break;
                case "UPDATE":
                    updateUser(request, response);
                    break;
                case "DELETE":
                    deleteUser(request, response);
                    break;
                default:
                    listUser(request, response);
            }
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");

        if (command == null) {
            command = "LIST";
        }

        switch (command) {
            case "ADD":
                try {
                    addUser(request, response);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;

            case "UPDATE":
                try {
                    updateUser(request, response);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;
            // Other cases...

            default:
                // Your default action...
                break;
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String theUserId = request.getParameter("userId");
        userDbUtil.deleteUser(theUserId);
        listUser(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("userId"));
        String name = request.getParameter("FullName");
        String birthday = request.getParameter("Birthday");
        String phone = request.getParameter("Phone");
        String passwords = request.getParameter("Password");
        String role = request.getParameter("Role");
        String address = request.getParameter("Address");
        String email = request.getParameter("email");
        String resetToken = request.getParameter("ResetToken");

        User theUser = new User(id, name, birthday, phone, passwords, role, address,email, resetToken);
        userDbUtil.updateUser(theUser);
        listUser(request, response);
    }

    private void loadUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read student id from form data
        String theUserId = request.getParameter("userId");

        // get student from database (db util)
        User theUser = userDbUtil.getUser(theUserId);

        // place student in the request attribute
        request.setAttribute("THE_USER", theUser);

        // send to jsp page: update-student-form.jsp
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/CRUD/update-user-form.jsp");
        dispatcher.forward(request, response);
    }

    protected void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String name = request.getParameter("FullName");
        String birthday = request.getParameter("Birthday");
        String phone = request.getParameter("Phone");
        String passwords = request.getParameter("Password");
        String role = request.getParameter("Role");
        String address = request.getParameter("Address");
        String email = request.getParameter("email");
        String resetToken = request.getParameter("ResetToken");

        // create a new user object
        User theUser = new User(name, birthday, phone, passwords, role, address, email, resetToken);

        // add the user to the database
        userDbUtil.addUser(theUser);

        // send back to main page (the user list)
        listUser(request, response);
    }


    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // get students from db util
        List<User> User = userDbUtil.getUser();

        // add students to the request
        request.setAttribute("USER_LIST", User);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CRUD/list-user.jsp");
        dispatcher.forward(request, response);
    }

}
