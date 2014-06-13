package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.dao.SausenDAO;
import be.vdab.entitites.Saus;
import be.vdab.entitites.Spel;

// enkele imports ...

@WebServlet("/sausen/raden")
public class SausenRadenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/sausenraden.jsp";
	private static final String REDIRECT_URL = "/sausen/raden";
	private static final String SPEL = "spel";
	private final SausenDAO sausDAO = new SausenDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute(SPEL) == null) {
			ArrayList<Saus> sausen = sausDAO.findAll();
			int randomIndex = new Random().nextInt(sausen.size());
			Saus randomSaus = sausen.get(randomIndex);
			Spel spel = new Spel(randomSaus.getNaam());
			session.setAttribute(SPEL, spel);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("nieuwSpel") != null) {
			session.removeAttribute(SPEL);
		} else {
			String letter = request.getParameter("letter");
			Spel spel = (Spel) session.getAttribute(SPEL);
			if (spel != null && letter != null && !letter.isEmpty()) {
				spel.doeGok(letter.charAt(0));
				session.setAttribute(SPEL, spel);
			}
		}
		response.sendRedirect(response.encodeRedirectURL(
		request.getContextPath() + REDIRECT_URL));
	}
	
}