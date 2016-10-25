package model;

public class Palavra {

	private String valorEmPortugues;
	private String valorEmIngles;
	
	public Palavra(){}
	
	public Palavra(String valorEmPortugues, String valorEmIngles) {
		super();
		this.valorEmPortugues = valorEmPortugues;
		this.valorEmIngles = valorEmIngles;
	}
	
	public String getValorEmPortugues() {
		return valorEmPortugues;
	}
	public void setValorEmPortugues(String valorEmPortugues) {
		this.valorEmPortugues = valorEmPortugues;
	}
	public String getValorEmIngles() {
		return valorEmIngles;
	}
	public void setValorEmIngles(String valorEmIngles) {
		this.valorEmIngles = valorEmIngles;
	}
	@Override
	public String toString() {
		return "Palavra [valorEmPortugues=" + valorEmPortugues + ", valorEmIngles=" + valorEmIngles + "]";
	}
	
}
