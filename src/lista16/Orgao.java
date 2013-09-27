package lista16;

import java.io.Serializable;

public class Orgao implements Serializable, Comparable<Orgao> {

	private int codOrgao;
	private String nomeOrgao;

	public Orgao(int codOrgao, String nomeOrgao) {
		super();
		this.codOrgao = codOrgao;
		this.nomeOrgao = nomeOrgao;
	}

	public void setNomeOrgao(String nomeOrgao) {
		this.nomeOrgao = nomeOrgao;
	}

	public Orgao(int codOrgao) {
		this.codOrgao = codOrgao;
	}

	public int getCodOrgao() {
		return codOrgao;
	}

	public String getNomeOrgao() {
		return nomeOrgao;
	}

	public int hashCode() {
		return codOrgao;
	}

	@Override
	public int compareTo(Orgao orgao) {
		if (codOrgao < orgao.codOrgao)
			return -1;
		if (codOrgao == orgao.codOrgao)
			return 0;
		return 1;
	}
}
