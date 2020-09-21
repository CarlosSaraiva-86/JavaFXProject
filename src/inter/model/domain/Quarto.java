package inter.model.domain;

public class Quarto extends Base{
	private int nmQuarto;
	private String tipo;
	private String disponibilidade;
	private float valor;
	
	public Quarto() {
		
	}
	
	public Quarto(int nmQuarto, String tipo, float valor) {
		super();
		this.nmQuarto = nmQuarto;
		this.tipo = tipo;
		this.valor = valor;
	}
	public int getNmQuarto() {
		return nmQuarto;
	}
	public void setNmQuarto(int nmQuarto) {
		this.nmQuarto = nmQuarto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
}
