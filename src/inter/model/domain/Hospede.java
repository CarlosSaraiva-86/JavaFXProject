package inter.model.domain;

public class Hospede extends Base {
	private String nome;
	private String endereco;
	private String rg;
	private String telefone;
	private String email;
	public Hospede(String nome, String endereco, String rg, String telefone, String email) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.rg = rg;
		this.telefone = telefone;
		this.email = email;
	}
	public Hospede() {
		// TODO Auto-generated constructor stub
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
