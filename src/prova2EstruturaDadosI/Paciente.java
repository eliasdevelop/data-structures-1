package prova2EstruturaDadosI;

import java.io.Serializable;

public class Paciente implements Serializable, Comparable<Paciente> {

	private int codPaciente;
	private String nomePaciente;
	private int idadePaciente;
	private Orgao orgao;

	public Paciente(int codPaciente, String nomePaciente, int idadePaciente,
			Orgao orgao) {
		super();
		this.codPaciente = codPaciente;
		this.nomePaciente = nomePaciente;
		this.idadePaciente = idadePaciente;
		this.orgao = orgao;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public void setIdadePaciente(int idadePaciente) {
		this.idadePaciente = idadePaciente;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public Paciente(int codPaciente) {
		this.codPaciente = codPaciente;
	}

	public int getCodPaciente() {
		return codPaciente;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public int getIdadePaciente() {
		return idadePaciente;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public int hashCode() {
		return codPaciente;
	}

	@Override
	public int compareTo(Paciente paciente) {
		if (codPaciente < paciente.codPaciente)
			return -1;
		if (codPaciente == paciente.codPaciente)
			return 0;
		return 1;
	}
}
