package prova2EstruturaDadosI;

import java.io.Serializable;

public class Transplantados implements Serializable, Comparable<Transplantados> {

	private Paciente paciente;
	private Orgao orgao;
	private String data;

	
	public Transplantados(Paciente paciente, Orgao orgao, String data) {
		super();
		this.paciente = paciente;
		this.orgao = orgao;
		this.data = data;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public int hashCode() {
		return getPaciente().getCodPaciente();
	}
	
	@Override
	public int compareTo(Transplantados o) {
		if (paciente.getCodPaciente() < o.getPaciente().getCodPaciente())
			return -1;
		if (paciente.getCodPaciente() == o.getPaciente().getCodPaciente())
			return 0;
		return 1;
	}

}
