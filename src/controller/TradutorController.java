package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Palavra;
import model.TradutorService;

@WebServlet("/tradutorController")
public class TradutorController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private TradutorService tradutorService = new TradutorService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		if (getServletContext().getAttribute("listaDePalavras") == null) {
			List<Palavra> listaDePalavras = tradutorService.carregarPalavras(getServletContext());
			getServletContext().setAttribute("listaDePalavras", listaDePalavras);
		}
		
		String palavra = request.getParameter("palavra");
		
		String palavraTraduzida = tradutorService.traduzirPalavra(palavra,
				(List<Palavra>)getServletContext().getAttribute("listaDePalavras"));
		
		request.setAttribute("palavraTraduzida", palavraTraduzida);
		
		request.getRequestDispatcher("resposta.jsp").forward(request, response);
	}

}
