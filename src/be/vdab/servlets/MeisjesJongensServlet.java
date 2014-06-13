package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MeisjesJongensServlet
 */
@WebServlet("/meisjesjongens")
public class MeisjesJongensServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/meisjesjongens.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		
		Cookie cookie  = new Cookie("meisjesjongens", "");
		if (request.getParameter("meisjes") != null) {
			cookie = new Cookie("meisjesjongens", "meisjes");
		} else if (request.getParameter("jongens") != null) {
			cookie = new Cookie("meisjesjongens", "jongens");
		}
		cookie.setMaxAge(60 * 30);
		response.addCookie(cookie);
		response.sendRedirect(request.getRequestURI());
		
	}

}
