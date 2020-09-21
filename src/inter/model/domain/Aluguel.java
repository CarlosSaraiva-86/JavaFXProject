package inter.model.domain;

import java.sql.Date;

public class Aluguel extends Base{
	private Date dataEntrada;
	private Date dataSaida;
	private Quarto quarto;
	private Hospede hospede;
	private int nmPessoas; 
	
	public Aluguel() {
		
	}
	
	public Aluguel(Date dataEntrada, Date dataSaida, int nmPessoas, Quarto quarto, Hospede hospede) {
		super();
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.nmPessoas = nmPessoas;
		this.quarto = quarto;
		this.hospede = hospede;
	}


	public int getNmPessoas() {
		return nmPessoas;
	}

	public void setNmPessoas(int nmPessoas) {
		this.nmPessoas = nmPessoas;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Quarto getQuarto() {
		return quarto;
	}
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	
	

}
