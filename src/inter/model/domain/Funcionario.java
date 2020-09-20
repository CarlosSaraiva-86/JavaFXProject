package inter.model.domain;

public class Funcionario {
	
	private String nome;
	private String registroCarteira;
	private String funcao;
	
	public Funcionario(String nome, String registroCarteira, String funcao) {
		super();
		this.nome = nome;
		this.registroCarteira = registroCarteira;
		this.funcao = funcao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegistroCarteira() {
		return registroCarteira;
	}

	public void setRegistroCarteira(String registroCarteira) {
		this.registroCarteira = registroCarteira;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	

}
