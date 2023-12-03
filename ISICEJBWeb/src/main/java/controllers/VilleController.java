package controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.ville.IDaoLocale;
import entities.Ville;

@WebServlet("/villeController")
public class VilleController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    private IDaoLocale<Ville> ejb;
       
    public VilleController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // Create new city
            String nom = request.getParameter("ville");
            ejb.create(new Ville(nom));
            
         // Redirect to ville.jsp
            response.sendRedirect(request.getContextPath() + "/ville.jsp");
            return;
        } else if ("delete".equals(action)) {
        	// Delete city
        	int villeId = Integer.parseInt(request.getParameter("id"));
        	Ville villeToDelete = ejb.findById(villeId);
        	ejb.delete(villeToDelete);

        	// Redirect to ville.jsp
        	response.sendRedirect(request.getContextPath() + "/ville.jsp");
        	return;

        } else if ("update".equals(action)) {
        	// Update city
            int villeId = Integer.parseInt(request.getParameter("id"));
            String updatedNom = request.getParameter("updatedVille");
            Ville updatedVille = new Ville(updatedNom);
            updatedVille.setId(villeId);
            ejb.update(updatedVille);

            // Forward to updateville.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/updateville.jsp");
            dispatcher.forward(request, response);
            return; // Important to return to avoid further execution
        }

        // Retrieve and display the list of cities
        request.setAttribute("villes", ejb.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}