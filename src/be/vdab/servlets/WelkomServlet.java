package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import be.vdab.entitites.Adres;
import be.vdab.entitites.Gemeente;

import java.util.Calendar;


/**
 * Servlet implementation class WelkomServlet
 */
@WebServlet("/welkom")
public class WelkomServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/welkom.jsp";
	
	private final Adres adres = new Adres();
	
	public void init() throws ServletException {
		 ServletContext servletContext = this.getServletContext();
	     adres.setHuisNr(servletContext.getInitParameter("huisnummer"));
	     adres.setStraat(servletContext.getInitParameter("straat"));
	     Gemeente gemeente = new Gemeente();
	     gemeente.setNaam(servletContext.getInitParameter("gemeente"));
	     gemeente.setPostCode(Integer.parseInt(servletContext.getInitParameter("postcode")));
	     adres.setGemeente(gemeente);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Calendar calendar = Calendar.getInstance();
		int dagVanDeWeek = calendar.get(Calendar.DAY_OF_WEEK);
		request.setAttribute("openGesloten", dagVanDeWeek == Calendar.MONDAY || dagVanDeWeek == Calendar.THURSDAY ? "Vandaag zijn we gesloten." : "Vandaag zijn we open.");
		request.setAttribute("adres", adres);
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
		
	}

}
