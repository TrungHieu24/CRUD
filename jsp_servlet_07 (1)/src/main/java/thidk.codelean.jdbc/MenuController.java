package thidk.codelean.jdbc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/MenuController")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 15    // 15 MB
)
public class MenuController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MenuDbUtil menuDbUtil;

    @Resource(name = "jdbc/food_store")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            menuDbUtil = new MenuDbUtil(dataSource);
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");

        if (command == null) {
            command = "LIST";
        }

        switch (command) {
            case "ADD":
                try {
                    addMenu(request, response);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;

            case "UPDATE":
                try {
                    updateMenu(request, response);
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

    private void deleteMenu(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String theMenuId = request.getParameter("menuId");
        menuDbUtil.deleteMenu(theMenuId);
        listMenu(request, response);
    }

    private void updateMenu(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("menuId"));
        String name = request.getParameter("Name");
        int price = Integer.parseInt(request.getParameter("Price"));
        String description = request.getParameter("Description");
        String imagePath = uploadImage(request);

        Menu theMenu = new Menu(id, name, price, description, imagePath);
        menuDbUtil.updateMenu(theMenu);
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

    protected void addMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String name= request.getParameter("Name");
        int price = Integer.parseInt(request.getParameter("Price"));
        String description = request.getParameter("Description");

        // Handle image upload
        Part filePart = request.getPart("Image");
        String fileName = getSubmittedFileName(filePart);
        // Get the real path for webapp/images
        String uploadDir = getServletContext().getRealPath("/images");
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        String filePath = uploadDir + File.separator + fileName;

        // Save the file on server
        filePart.write(filePath);
        // create a new menu object
        Menu theMenu = new Menu(name, price, description, fileName);

        // add the menu to the database
        menuDbUtil.addMenu(theMenu);

        // send back to main page (the menu list)
        listMenu(request, response);
    }

    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf(File.separator) + 1).replace("\"", "");
            }
        }
        return null;
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


    private String uploadImage(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart("Image");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        filePart.write(uploadPath + File.separator + fileName);
        return "uploads" + File.separator + fileName;
    }
}
