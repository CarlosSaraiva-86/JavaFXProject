package inter.model.domain;

public class Servico {
	private String tipoServico;
	private int quantidade;
	private float valor;
	
	public Servico(String tipoServico, int quantidade, float valor) {
		super();
		this.tipoServico = tipoServico;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
}
