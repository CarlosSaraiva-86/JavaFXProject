package inter.model.domain;


public class Diaria {
	private float total;
	private int diaria;
	private Aluguel aluguel;
	
	public Diaria(Aluguel aluguel) {
		super();
		this.diaria = aluguel.getDataSaida().getDate() - aluguel.getDataEntrada().getDate();
		this.aluguel = aluguel;
	}

	public int getDiaria() {
		return diaria;
	}

	public float getTotal() {
		
		total = this.aluguel.getQuarto().getValor() * this.diaria;
		return total;
	}
	
	
}
