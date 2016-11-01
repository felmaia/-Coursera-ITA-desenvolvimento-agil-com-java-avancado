package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.domain.Comentario;
import model.domain.Topico;
import model.domain.Usuario;

@WebServlet("/comentarioController")
public class ComentarioController extends BaseController {

	private static final long serialVersionUID = 1L;

	protected void executa(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		acao = request.getParameter(ACAO_PARAMETER);
		
		switch (acao) {

		case ACAO_INSERIR:
			inserir(request, response);
			break;

		default:
			throw new RuntimeException("Ação desconhecida.");
		}
	}

	private void inserir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer topicoId = null;
		
		try {
			
			String texto = request.getParameter("texto");
			topicoId = Integer.parseInt(request.getParameter("id"));
			
			Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuario");
			
			Comentario comentario = new Comentario(usuarioLogado, new Topico(topicoId), texto);
			
			comentarioService.inserir(comentario);
			
			request.getRequestDispatcher("topicoController?acao="+ACAO_EXIBIR+"&id="+topicoId).forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("topicoController?acao="+ACAO_EXIBIR+"&id="+topicoId).forward(request, response);
		}	
		
	}
}
