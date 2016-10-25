package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/somar")
public class SomaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		int parcela1 = Integer.parseInt(request.getParameter("p1"));
		int parcela2 = Integer.parseInt(request.getParameter("p2"));
		int total = parcela1 + parcela2;
		
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Resultado da Soma</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>O resultado foi " + total + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
		
		
		String nome = request.getParameter("nome");
		response.getWriter().print("<html><h1>Oi "+nome+"!</h1></html>");
		
	}

}
