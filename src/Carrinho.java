import java.util.ArrayList;
import java.util.List;

public class Carrinho {

	private List<Produto> lista = new ArrayList<>();

	public Carrinho(List<Produto> lista) {
		super();
		this.lista = lista;
	}
	
	public void darDesconto(int porcentagem, Condicao<Produto> cond) {
		for (Produto p : lista) {
			if (cond.incluir(p)) {
				p.setValor(p.getValor()*(100-porcentagem)/100);
			}
		}
	}

	public List<Produto> getLista() {
		return lista;
	}
	
	

}
