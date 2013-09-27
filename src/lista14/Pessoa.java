package lista14;

import java.io.Serializable;

public class Pessoa implements Serializable, Comparable<Pessoa>{

	private int codPessoa;
	private String nomePessoa;
	private int idade;

	public Pessoa(int codPessoa){
		this.codPessoa = codPessoa;
	}

	public int getCodPessoa() {
		return codPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	
	public int getIdade() {
   	return idade;
   }

	public void setIdade(int idade) {
   	this.idade = idade;
   }
	
	@Override
	public String toString(){
		return Integer.toString(codPessoa);
	}

	@Override
	public int hashCode() {
		return codPessoa;
	}

	@Override
   public int compareTo(Pessoa pessoa) {
		if (codPessoa < pessoa.codPessoa)
			return -1;
		if (codPessoa == pessoa.codPessoa)
			return 0;
		return 1;

   }	

}
