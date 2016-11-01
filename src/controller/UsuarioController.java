package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.domain.Usuario;

@WebServlet("/usuarioController")
public class UsuarioController extends BaseController {

	private static final long serialVersionUID = 1L;

	protected void executa(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		acao = request.getParameter(ACAO_PARAMETER);
		
		switch (acao) {

		case ACAO_INSERIR:
			cadastrar(request, response);
			break;

		case ACAO_AUTENTICAR_USUARIO:
			autenticar(request, response);
			break;
			
		case ACAO_RANKING_USUARIOS:
			ranking(request, response);
			break;

		default:
			throw new RuntimeException("Ação desconhecida.");
		}
	}

	private void ranking(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			List<Usuario> ranking = usuarioService.ranking();
			request.setAttribute("ranking", ranking);
			request.getRequestDispatcher("/ranking.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("erro", e.getMessage());
			throw new RuntimeException(e.getMessage());
		}		
	}
	
	private void autenticar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		try {
			Usuario usuarioLogado = usuarioService.autenticar(login, senha);
			request.getSession().setAttribute("usuario", usuarioLogado);
			response.sendRedirect("topicoController?acao="+ACAO_LISTAR);
			//request.getRequestDispatcher("/topicoController?acao=listar").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			
			Usuario usuario = new Usuario(nome, email, login, senha);
			
			usuarioService.inserir(usuario);
			
			request.setAttribute("sucessoCadastro", "Cadastro realizado.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/novo-usuario.jsp").forward(request, response);
		}	
		
	}
}
