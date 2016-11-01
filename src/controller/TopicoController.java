package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.domain.Topico;
import model.domain.Usuario;

@WebServlet("/topicoController")
public class TopicoController extends BaseController {

	private static final long serialVersionUID = 1L;

	protected void executa(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		acao = request.getParameter(ACAO_PARAMETER);
		
		switch (acao) {

		case ACAO_INSERIR:
			inserir(request, response);
			break;

		case ACAO_LISTAR:
			listar(request, response);
			break;
			
		case ACAO_EXIBIR:
			exibir(request, response);
			break;

		default:
			throw new RuntimeException("Ação desconhecida.");
		}
	}

	private void exibir(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		try {
			
			Integer topicoId = Integer.parseInt(request.getParameter("id"));
			Topico topico = topicoService.buscar(topicoId);
			request.setAttribute("topico", topico);
			
		if (topico.getComentarios().isEmpty())
			request.setAttribute("info", "Seja o primeiro a comentar!");
			
			request.getRequestDispatcher("/topico.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/listar-topicos.jsp").forward(request, response);
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			List<Topico> topicos = topicoService.listar();
			
			if (topicos.isEmpty())
				request.setAttribute("info", "Nenhum tópico encontrado.");
			else
				request.setAttribute("topicos", topicos);
			request.getRequestDispatcher("/listar-topicos.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("erro", e.getMessage());
			throw new RuntimeException(e.getMessage());
		}	
	}
	
	private void inserir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			String titulo = request.getParameter("titulo");
			String texto = request.getParameter("conteudo");
			
			Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuario");
			
			Topico topico = new Topico(usuarioLogado, titulo, texto);
			
			topicoService.inserir(topico);
			
			request.setAttribute("sucessoCadastro", "Novo tópico criado.");
			request.getRequestDispatcher("topicoController?acao="+ACAO_LISTAR).forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("/novo-topico.jsp").forward(request, response);
		}	
		
	}
}
