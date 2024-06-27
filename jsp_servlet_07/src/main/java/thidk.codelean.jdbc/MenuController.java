package thidk.codelean.jdbc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/MenuController")
public class MenuController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MenuDbUtil menuDbUtil;

    @Resource(name = "jdbc/food_store")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        // create our student db util ... and pass in the conn pool / datasource
        try {
            menuDbUtil = new MenuDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");
            // if the command is missing, then default to listing students
            if(theCommand == null)
                theCommand = "list";
            // route to the appropriate method
            switch (theCommand) {
                case "ADD":
                    addMenu(request, response);
                    break;
                case "LOAD":
                    loadMenu(request, response);
                    break;
                case "UPDATE":
                    updateMenu(request, response);
                    break;
                case "DELETE":
                    deleteMenu(request, response);
                    break;
                default:
                    listMenu(request, response);
            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }

    }

    private void deleteMenu(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // read student id from form data
        String theMenuId = request.getParameter("menuId");

        // delete student from database
        menuDbUtil.deleteMenu(theMenuId);

        // send them back to "list students" page
        listMenu(request, response);
    }

    private void updateMenu(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read student info from form data
        int id = Integer.parseInt(request.getParameter("menuId"));
        String Name = request.getParameter("Name");
        String Description = request.getParameter("Description");
        // create a new student object
        Menu theMenu = new Menu(id, Name, Description);

        // perform update on database
        menuDbUtil.updateMenu(theMenu);

        // send them back to the "list students" page
        listMenu(request, response);

    }

    private void loadMenu(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read student id from form data
        String theMenuId = request.getParameter("menuId");

        // get student from database (db util)
         Menu theMenu = menuDbUtil.getMenu(theMenuId);

        // place student in the request attribute
        request.setAttribute("THE_MENU", theMenu);

        // send to jsp page: update-student-form.jsp
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read student info from form data
        String Name = request.getParameter("Name");
        String Description = request.getParameter("Description");

        // create a new student object
        Menu theMenu = new Menu(Name, Description);

        // add the student to the database
        menuDbUtil.addMenu(theMenu);

        // send back to main page (the student list)
        listMenu(request, response);
    }

    private void listMenu(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // get students from db util
        List<Menu> Menu = menuDbUtil.getMenu();

        // add students to the request
        request.setAttribute("MENU_LIST", Menu);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(request, response);
    }

}













