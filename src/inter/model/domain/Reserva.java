package inter.model.domain;

public class Reserva extends Base{
	private String dataEntrada;
	private String dataSaida;
	private Quarto quarto;
	private Hospede hospede;
	
		
	public Reserva(String dataEntrada, String dataSaida, Quarto quarto, Hospede hospede) {
		super();
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.quarto = quarto;
		this.hospede = hospede;
	}
	
	

	public Hospede getHospede() {
		return hospede;
	}



	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}



	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	
	
}
