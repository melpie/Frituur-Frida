package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import be.vdab.dao.SausenDAO;
import be.vdab.entitites.Saus;

/**
 * Servlet implementation class IngredientenServlet
 */
@WebServlet("/sausen/ingredienten")
public class IngredientenServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private static final String VIEW="/WEB-INF/JSP/ingredienten.jsp";
    private final SausenDAO sausenDAO = new SausenDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String ingredient = request.getParameter("ingredient");
    	if (ingredient != null) {
	    	Map<String, String> fouten = new HashMap<>();
	    	if (ingredient.isEmpty()) {
	    		fouten.put("ingredient", "verplicht");
	    	} else {
	    		Iterable<Saus> sausen = sausenDAO.findByIngredient(ingredient);
	    		request.setAttribute("sausen", sausen);
	    	}
	    	request.setAttribute("fouten", fouten);
    	}
    	RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
    	dispatcher.forward(request, response);
    }


}
