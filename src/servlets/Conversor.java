package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/converterTemperatura")
public class Conversor extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String CELSIOUS = "celsious";
	private static final String FAHRENHEIT = "fahrenheit";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tipoConversao = request.getParameter("tipoConversao");
		double temperatura = 0;
		double temperaturaConvertida = 0;
		String resultMessage = null;
		
		try {
			temperatura = Double.parseDouble(request.getParameter("temp"));
			if (request.getParameter("temp").replaceAll("\\D", "").trim().length()==0)
				throw new NumberFormatException();
			
			if (CELSIOUS.equalsIgnoreCase(tipoConversao)) {
				temperaturaConvertida = ((temperatura * 9) / 5) + 32;
				resultMessage =  temperatura + " em Celsious " + "equivale a " + 
						temperaturaConvertida + " em Farenheit.";
			}		
			else if (FAHRENHEIT.equalsIgnoreCase(tipoConversao)) {
				temperaturaConvertida = ((temperatura - 32) * 5) / 9;
				resultMessage =  temperatura + " em Farenheit " + "equivale a " + 
						temperaturaConvertida + " em Celsious.";
			}	
			else {
				resultMessage = "Escolha uma conversão válida. ";
			}
		
		} catch (NumberFormatException e) {
			resultMessage = "Campo 'Temperatura' precisa ser um número.";
		}
			
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Resultado da Conversão</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>" + resultMessage + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
