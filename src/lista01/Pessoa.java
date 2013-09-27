package lista01;

import java.io.Serializable;

public class Pessoa implements Serializable, Comparable<Pessoa> {
	private int codPessoa;
	private String nome;
	private Bem[] bens;
	private int totBens;

	public Pessoa(int codPessoa, String nome) {
		super();
		this.codPessoa = codPessoa;
		this.nome = nome;
		bens = new Bem[10];
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodPessoa() {
		return codPessoa;
	}

	public int getTotBens() {
		return totBens;
	}

	private boolean busque(int codBem) {
		for (int i = 0; i < totBens; i++) {
			if (codBem == bens[i].getCodBem())
				return true;
		}
		return false;
	}

	public boolean insiraBem(int codBem, String descricao,
			String dataAquisicao, float valor) {
		if ((totBens == bens.length) || busque(codBem))
			return false;
		bens[totBens] = new Bem(codBem, descricao, dataAquisicao, valor);
		totBens++;
		return true;
	}

	public float valorTotal() {
		float soma = 0;
		for (int i = 0; i < totBens; i++) {
			soma = soma + bens[i].getValor();
		}
		return soma;
	}

	public void imprimaBens() {
		System.out.println("CodPessoa: " + codPessoa + "   Nome da pessoa:"
				+ nome);
		System.out.println();
		System.out
				.println("CodBem  Descricao do Bem      Data Aquisicao        Valor");
		System.out
				.println("------  --------------------  --------------  -----------");
		for (int i = 0; i < totBens; i++) {
			System.out.printf("%6d  %-20s    %10s    %11.2f\n", bens[i]
					.getCodBem(), bens[i].getDescricao(), bens[i]
					.getDataAquisicao(), bens[i].getValor());
		}

	}

	@Override
	public int compareTo(Pessoa o) {
		if (codPessoa < o.codPessoa)
			return -1;
		if (codPessoa == o.codPessoa)
			return 0;
		return 1;
	}

}
