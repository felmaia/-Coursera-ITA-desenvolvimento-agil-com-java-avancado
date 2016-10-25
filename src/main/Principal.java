package main;

import java.util.ArrayList;
import java.util.List;

public class Principal {
	
	public static void main(String[] args) {
		
		Cesta<Fruta> frutas = new Cesta<>();
		frutas.adiciona(new Fruta("Ma��"));
		frutas.adiciona(new Fruta("Banana"));
		frutas.adiciona(new Fruta("Mam�o"));
		frutas.adiciona(new Fruta("P�ra"));
		frutas.adiciona(new Fruta("Uva"));
		frutas.adiciona(new FrutaVermelha("Morango"));
		
		List<FrutaVermelha> vermelhas = new ArrayList<>();
		vermelhas.add(new FrutaVermelha("Amora"));
		vermelhas.add(new FrutaVermelha("Cereja"));
		
		frutas.adicionaTodos(vermelhas); 
		//--> ESPERA LIST<Fruta> QUE � DIFERENTE DE LIST<FrutaVermelha>
		//POIS O TIPO GENERICO DE LIST � INVARIANTE, E NAO COVARIANTE COMO ACONTECE COM ARRAYS
		//PARA FUNCIONAR, USAR O CORINGA NO M�TODO: ? Extends E
		
		while (frutas.temItens()) {
			System.out.println(frutas.retira());
		}
	}

}
