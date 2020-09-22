package inter.model.domain;

public class Pagamento {
	private String pagamento;

	public Pagamento(String pagamento) {
		super();
		this.pagamento = pagamento;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	
	@Override
	public String toString() {
		return pagamento;
	}
}
