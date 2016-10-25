
public class Produto {

	private String nome;
	private int valor;
	
	public Produto(String nome, int valor) {
		super();
		this.nome = nome;
		this.valor = valor;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}

	public void darDesconto() {
		this.valor = this.valor*90/100;
	}
	
	public boolean isCaro() {
		return valor > 100;
	}
	
	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", valor=" + valor + "]";
	}
	
}
