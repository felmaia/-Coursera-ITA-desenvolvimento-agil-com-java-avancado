package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

public class TradutorService {

	private static final String PALAVRAS_FILE_PATH = "/WEB-INF/palavras.txt";
	
	public String traduzirPalavra(String palavra, List<Palavra> listaDePalavras) {
		
		//listaDePalavras.forEach(System.out::println);
		
		Optional<Palavra> optional = listaDePalavras.stream()
                .filter(p -> palavra.equalsIgnoreCase(p.getValorEmPortugues()) || palavra.equalsIgnoreCase(p.getValorEmIngles()))
                .findFirst();

		if(optional.isPresent()) {
			Palavra p = optional.get();
			if (palavra.equalsIgnoreCase(p.getValorEmIngles()))
				return p.getValorEmPortugues();
			else
				return p.getValorEmIngles();
		}
		return palavra;
	}
	
	public List<Palavra> carregarPalavras(ServletContext context) {
		
		InputStream inputStream = context.getResourceAsStream(PALAVRAS_FILE_PATH);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		List<Palavra> listaDePalavras = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(inputStreamReader))
		{
			br
			.lines()
            .map(linha-> linha.split("="))
            .forEach((palavras -> listaDePalavras.add(new Palavra(palavras[0], palavras[1]))));
			
			/*String currentLine = null;
			while ((currentLine = br.readLine()) != null) {
				String[] termos = currentLine.split("=");
				Palavra palavra = new Palavra();
				palavra.setValorEmPortugues(termos[0]);
				palavra.setValorEmIngles(termos[1]);
				listaDePalavras.add(palavra);
			}*/

		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaDePalavras;
	}

}
