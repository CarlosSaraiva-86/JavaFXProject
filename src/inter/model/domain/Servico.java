package inter.model.domain;

public class Servico extends Base {
	private String tipoServico;
	private float valor;
	
	public Servico() {
		
	}
	
	public Servico(String tipoServico, float valor) {
		super();
		this.tipoServico = tipoServico;
		this.valor = valor;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return tipoServico;
	}
}
