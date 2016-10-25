package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Oraculo;

/**
 * Servlet implementation class OraculoController
 */
@WebServlet("/oraculoController")
public class OraculoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//pegar parâmetros
		String tipoProduto = request.getParameter("produto");
		
		//chamar regra de negócios
		Oraculo oraculo = new Oraculo();
		List<String> produtos = oraculo.melhoresProdutos(tipoProduto);
		
		//disponibilizar a resposta no escopo adequado
		request.setAttribute("produtos", produtos);
		
		//passar o controle para um view
		request.getRequestDispatcher("resposta.jsp").forward(request, response);
	}

}
