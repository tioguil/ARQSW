package Ex02.atv01;


public abstract class Nome {
	
	private String nome;
	private String sobrenome;
	
	public abstract void exibir();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

}