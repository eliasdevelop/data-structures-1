package prova2EstruturaDadosI;

import java.io.Serializable;

import utilitarios.MyIterator;
import fila.FilaEnc;

public class Orgao implements Serializable, Comparable<Orgao> {

	private int codOrgao;
	private String nomeOrgao;
	private FilaEnc<Paciente> filaPacientes = new FilaEnc<Paciente>(); 

	public Orgao(int codOrgao, String nomeOrgao) {
		super();
		this.codOrgao = codOrgao;
		this.nomeOrgao = nomeOrgao;
		filaPacientes = new FilaEnc<Paciente>();
	}

	public void inserirPaciente(Paciente paciente) {
		filaPacientes.insira(paciente);
	}

	public Paciente removerPaciente() {
		return filaPacientes.remova();
	}

	public int tamanhoFila() {
		return filaPacientes.size();
	}

	public void listarFila() {
		MyIterator<Paciente> it = filaPacientes.iterator();
		Paciente paciente = it.getFirst();
		while (paciente != null) {
			System.out.printf("%12d  %-30s  %5d \n",
					paciente.getCodPaciente(), paciente.getNomePaciente(),
					paciente.getIdadePaciente());
			paciente = it.getNext();
		}
	}

	public int posicaoDoPaciente(Paciente paciente) {
		MyIterator<Paciente> it = filaPacientes.iterator();
		Paciente pacienteA = it.getFirst();
		int cont = 1;
		while (pacienteA != null) {
			if (pacienteA.compareTo(paciente) == 0) {
				break;
			} else {
				cont++;
			}
			pacienteA = it.getNext();
		}
		return cont;
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
