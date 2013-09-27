package lista01;

import java.io.Serializable;


public class Bem implements Serializable {
	private int codBem;
	private String descricao;
	private String dataAquisicao;
	private float valor;
	
	public Bem(int codBem, String descricao, 
			String dataAquisicao, float valor) {
	   super();
	   this.codBem = codBem;
	   this.descricao = descricao;
	   this.dataAquisicao = dataAquisicao;
	   this.valor = valor;
   }

	public Bem(int codBem) {
	   super();
	   this.codBem = codBem;
   }

	public String getDescricao() {
   	return descricao;
   }

	public void setDescricao(String descricao) {
   	this.descricao = descricao;
   }

	public String getDataAquisicao() {
   	return dataAquisicao;
   }

	public void setDataAquisicao(String dataAquisicao) {
   	this.dataAquisicao = dataAquisicao;
   }

	public float getValor() {
   	return valor;
   }

	public void setValor(float valor) {
   	this.valor = valor;
   }

	public int getCodBem() {
   	return codBem;
   }
	
	
	
	
}

