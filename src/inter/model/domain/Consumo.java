package inter.model.domain;

import java.util.ArrayList;

public class Consumo extends Base{
	private float total;
	private ArrayList<Servico> servicos;
	

	private Aluguel aluguel;
	
	public Consumo() {
		super();
		this.servicos = new ArrayList<Servico>();
	}

	public void setServicos(Servico servicos) {
		this.servicos.add(servicos);
	}
	
	
	public float getTotal() {
		
		for(Servico servico : this.servicos) {
			this.total += servico.getValor();
		}
		return this.total;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}
	
	public ArrayList<Servico> getServicos() {
		return servicos;
	}
	
}
