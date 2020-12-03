package Controller;

import Model.Cars;
import Model.User;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "add-car", urlPatterns = {"/add-car.jsp"})
@MultipartConfig
public class addCar extends HttpServlet {

    private final String UPLOAD_DIRECTORY = "./../../uploads/";
    private static final long serialVersionUID = 1;

    public addCar() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User userObj = new User();
        try {
            List<User> users = userObj.getUsersWithRoleID(3);
            request.setAttribute("users", users);
            List<User> salesmen = userObj.getUsersWithRoleID(2);
            request.setAttribute("salesmen", salesmen);
        } catch (Exception e) {
            System.out.println("Excpetion in Add Car Servlet:" + e);
        }
        request.getRequestDispatcher("/WEB-INF/add-car.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        int listing = Integer.parseInt(request.getParameter("listing"));
        int year = Integer.parseInt(request.getParameter("year"));
        String reg = request.getParameter("reg");
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        String discription = request.getParameter("description");
        int buyPrice = Integer.parseInt(request.getParameter("buyPrice"));
        double listPrice = Double.parseDouble(request.getParameter("listPrice"));
        int odometer = Integer.parseInt(request.getParameter("odometer"));
        String kph = request.getParameter("kph");
        double engine = Double.parseDouble(request.getParameter("engine"));
        String fuel = request.getParameter("fuel");
        String transmission = request.getParameter("transmission");
        String body = request.getParameter("body");
        String isofix = request.getParameter("isofax");
        int motor_tax = Integer.parseInt(request.getParameter("tax"));
        int previous_owners = Integer.parseInt(request.getParameter("previousOwners"));
        String sales_person_username = request.getParameter("salesmanUsername");
        String previous_owner_username = request.getParameter("previousOwnerUsername");
        String full_service_history = request.getParameter("serviceHistory");
        String note = request.getParameter("note");

        Cars carObj = new Cars(listing, year, reg, make, model, color, discription, buyPrice, (int)listPrice, odometer, kph, engine, fuel, transmission, body, isofix, motor_tax, previous_owners, sales_person_username, previous_owner_username, full_service_history, note);
        int inserted = 0;
        try {
            inserted = carObj.insertCar();
        } catch (SQLException ex) {
            System.out.println("Exception in Add Car Controller while adding car" + ex);
            response.getWriter().write("-1"); 
        }

        if (inserted == 1) {

            //UPLOADING IMAGES CODE
            Collection<Part> parts = request.getParts();
            for (Part part : parts) {
                if (part.getName().equals("images")) {
                    String completePath = uploadImage(part, "UPLOADS/" + listing + "/images", request);
                    try {
                        carObj.insertImage(listing, completePath, "I");
                    } catch (SQLException ex) {
                        System.out.println("Exception in Add Car Controller while adding car images" + ex);
                        response.getWriter().write("-2"); 
                    }
                }

            }

            Part thubnailPart = request.getPart("thumbnail");
            String completePath = uploadImage(thubnailPart, "UPLOADS/" + listing + "/thumbnail/", request);
            try {
                carObj.insertImage(listing, completePath, "T");
                response.getWriter().write("1"); 
            } catch (SQLException ex) {
                System.out.println("Exception in Add Car Controller while adding car image thumbnail" + ex);
                response.getWriter().write("-3"); 
            }
        }

    }

    public String uploadImage(Part part, String directory, HttpServletRequest request) throws IOException {
        // Getting Application Path
        String appPath = request.getServletContext().getRealPath("");
        // File path where all files will be stored
        String imagePath = appPath + directory;
        // Creates the file directory if it does not exists
        File fileDir = new File(imagePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        //Get Image Name
        String imageName = part.getSubmittedFileName();
        //String completeImagePath= "./../../webapp/img/UPLOADS/"+File.separator + imageName;
        String completeImagePath = imagePath + File.separator + imageName;
        part.write(completeImagePath);
        return completeImagePath;
    }
}
