import java.util.List;

public class Principal {

	 public static void main(String[] args) throws Exception {
		
		 Pessoa p1 = new Pessoa("Eduardo", "Guerra", 36);
		 Pessoa p2 = new Pessoa("Eduardo", "Paz", 37);
		 
		 List<Diferenca> difs = Comparador.comparar(p1, p2);
		 
		 difs.forEach(System.out::println);
	}

}
