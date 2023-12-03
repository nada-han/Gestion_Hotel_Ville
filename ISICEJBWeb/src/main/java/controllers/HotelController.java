package controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import dao.hotel.IDaoLocale;
import entities.Hotel;
import entities.Ville;

@WebServlet("/hotelController")
public class HotelController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    private IDaoLocale<Hotel> hotelEjb;
    
    @EJB
    private IDaoLocale<Ville> villeEjb;
       
    public HotelController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // Create new hotel
            String nom = request.getParameter("nom");
            String adresse = request.getParameter("adresse");
            String telephone = request.getParameter("telephone");

            String idParam = request.getParameter("id");
            int id = (idParam != null && !idParam.isEmpty() && idParam.matches("\\d+")) ? Integer.parseInt(idParam) : 0;

            // Retrieve the selected Ville from the database
            Ville ville = villeEjb.findById(id);

            // Create the hotel with the selected Ville
            Hotel hotel = new Hotel();
            hotel.setNom(nom);
            hotel.setAdresse(adresse);
            hotel.setTelephone(telephone);
            hotel.setVille(ville);

            hotelEjb.create(hotel);


            // Redirect to Hotel.jsp
            response.sendRedirect(request.getContextPath() + "/Hotel.jsp");
            return;
        } else if ("delete".equals(action)) {
            // Delete hotel
            int hotelId = Integer.parseInt(request.getParameter("id"));
            Hotel hotelToDelete = hotelEjb.findById(hotelId);
            hotelEjb.delete(hotelToDelete);

            // Redirect to Hotel.jsp
            response.sendRedirect(request.getContextPath() + "/Hotel.jsp");
            return;
        } else if ("update".equals(action)) {
            // Update hotel
            int hotelId = Integer.parseInt(request.getParameter("id"));
            String updatedNom = request.getParameter("updatedNom");

            Hotel updatedHotel = hotelEjb.findById(hotelId);
            updatedHotel.setNom(updatedNom);

            hotelEjb.update(updatedHotel);

            // Forward to updateHotel.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/updateHotel.jsp");
            dispatcher.forward(request, response);
            return; // Important to return to avoid further execution
        }

        // Retrieve and display the list of hotels
        List<Hotel> hotels = hotelEjb.findAll();
        request.setAttribute("hotels", hotels);

        // No need to retrieve and include the list of villes
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Hotel.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

