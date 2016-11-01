package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import model.service.ComentarioService;
import model.service.TopicoService;
import model.service.UsuarioService;

@WebServlet
public abstract class BaseController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String ACAO_PARAMETER = "acao";
	public static final String ACAO_LISTAR = "listar";
	public static final String ACAO_EXIBIR = "exibir";
	public static final String ACAO_INSERIR = "inserir";
	public static final String ACAO_AUTENTICAR_USUARIO = "autenticar";
	public static final String ACAO_RANKING_USUARIOS = "ranking";
	
	protected String acao = null;
	protected UsuarioService usuarioService = new UsuarioService();
	protected TopicoService topicoService = new TopicoService();
	protected ComentarioService comentarioService = new ComentarioService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		addErrorMessage(request);
		addInfoMessage(request);
		executa(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		addErrorMessage(request);
		addInfoMessage(request);
		executa(request, response);
	}

	protected abstract void executa(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
	
	private void addErrorMessage(HttpServletRequest request) {
		
		String erro = (String)request.getAttribute("erro");
		if (!StringUtils.isEmpty(erro)) 
			request.setAttribute("erro", erro);
	}
	
	private void addInfoMessage(HttpServletRequest request) {
		
		String msg = (String)request.getAttribute("info");
		if (!StringUtils.isEmpty(msg)) 
			request.setAttribute("info", msg);
	}

}
