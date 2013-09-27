package lista11;

public class Pessoa implements Comparable<Pessoa>{

	private int codPessoa;
	private String nomePessoa;
	
	public Pessoa(int codPessoa, String nomePessoa) {
		this.codPessoa = codPessoa;
		this.nomePessoa = nomePessoa;
	}
	
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

	@Override
   public int compareTo(Pessoa pessoa) {
		if (codPessoa < pessoa.codPessoa)
			return -1;
		if (codPessoa == pessoa.codPessoa)
			return 0;
		return 1;
   }

}
