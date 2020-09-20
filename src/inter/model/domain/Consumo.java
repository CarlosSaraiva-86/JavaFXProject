package inter.model.domain;

import java.util.ArrayList;

public class Consumo {
	private float total;
	private ArrayList<Servico> servicos;
	
	public Consumo() {
		super();
		this.servicos = new ArrayList<Servico>();
	}

	public void setServicos(ArrayList<Servico> servicos) {
		this.servicos = servicos;
	}
	
	
	public float getTotal() {
		
		for(Servico servico : this.servicos) {
			this.total += servico.getValor() * servico.getQuantidade();
		}
		return this.total;
	}
	
}
