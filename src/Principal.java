import java.util.ArrayList;
import java.util.List;

public class Principal {

	private static List<Produto> lista = new ArrayList<>();
	
	public static void main(String[] args) {

		lista.add(new Produto("Tenis", 300));
		lista.add(new Produto("Camiseta", 80));
		lista.add(new Produto("Cinto", 50));

		teste1();
		teste2();
	}

	private static void teste1() {
		
		//lista.forEach(p -> p.setValor(p.getValor()*90/100));
		//lista.forEach(p -> p.darDesconto());
		lista.forEach(Produto::darDesconto);
		
		//lista.forEach(p -> System.out.println(p));
		lista.forEach(System.out::println);
	}
	
	private static void teste2() {
		
		Carrinho c = new Carrinho(lista);
		//c.darDesconto(20, p -> p.getValor() < 100);
		c.darDesconto(10, Produto::isCaro);
		c.getLista().forEach(System.out::println);
	}

}
