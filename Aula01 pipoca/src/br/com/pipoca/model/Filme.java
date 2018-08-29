package br.com.pipoca.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Filme {
	private String nome;
	private String diretor;
	private String descricao;
	private Genero genero;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dataLancamento;
	private String posterPath;
	private int popularidade;
	private int id;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public Date getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getPosterPath() {
		return posterPath;
	}
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
	public int getPopularidade() {
		return popularidade;
	}
	public void setPopularidade(int popularidade) {
		this.popularidade = popularidade;
	}

	@Override
	public String toString() {
		return "Filme [nome=" + nome + ", diretor=" + diretor + ", descricao=" + descricao + ", genero=" + genero
				+ ", dataLancamento=" + dataLancamento + ", posterPath=" + posterPath + ", popularidade=" + popularidade
				+ ", id=" + id + ", getId()=" + getId() + ", getNome()=" + getNome() + ", getDiretor()=" + getDiretor()
				+ ", getDescricao()=" + getDescricao() + ", getGenero()=" + getGenero() + ", getDataLancamento()="
				+ getDataLancamento() + ", getPosterPath()=" + getPosterPath() + ", getPopularidade()="
				+ getPopularidade() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
