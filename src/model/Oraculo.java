package model;

import java.util.ArrayList;
import java.util.List;

public class Oraculo {

	public List<String> melhoresProdutos(String tipo) {
		
		List<String> lista = new ArrayList<>();
		if ("doce de leite".equalsIgnoreCase(tipo)) {
			lista.add("Vi�osa");
			lista.add("Boreal");
		} else if ("queijo mineiro".equalsIgnoreCase(tipo)) {
			lista.add("Candido Tostes");
			lista.add("Humait�");
			lista.add("Tia Tot�nia");
		}
		
		return lista; 
	}

}
