package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import be.vdab.dao.SausenDAO;

@WebServlet("/sausen")
public class SausenServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/sausen.jsp";
	
	private static final String REDIRECT_URL = "/sausen";
	
	private final SausenDAO sausenDAO = new SausenDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.setAttribute("sausen", sausenDAO.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}
	
	 @Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException {
		 
		 if (request.getParameterValues("nummer") != null) {
			 
			 for (String nummerAlsString:request.getParameterValues("nummer")){
	             long nummer = Long.parseLong(nummerAlsString);
	             sausenDAO.removeSaus(nummer);
	         }
			 
		 }
		 
		 response.sendRedirect(request.getContextPath() + REDIRECT_URL);

    }


}
