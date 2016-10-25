package model;

import java.util.ArrayList;
import java.util.List;

public class Oraculo {

	public List<String> melhoresProdutos(String tipo) {
		
		List<String> lista = new ArrayList<>();
		if ("doce de leite".equalsIgnoreCase(tipo)) {
			lista.add("Viçosa");
			lista.add("Boreal");
		} else if ("queijo mineiro".equalsIgnoreCase(tipo)) {
			lista.add("Candido Tostes");
			lista.add("Humaitá");
			lista.add("Tia Totônia");
		}
		
		return lista; 
	}

}
