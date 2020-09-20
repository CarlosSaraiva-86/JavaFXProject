package inter.model.domain;

public class Diaria {
	private float total;
	private int diaria;
	private Aluguel aluguel;
	
	public Diaria(int diaria, Aluguel aluguel) {
		super();
		this.diaria = diaria;
		this.aluguel = aluguel;
	}

	public float getTotal() {
		
		total = this.aluguel.getQuarto().getValor() * this.diaria;
		return total;
	}
	
	
}
