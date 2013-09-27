package prova2EstruturaDadosI;

import java.util.Comparator;
import utilitarios.Keyboard;
import utilitarios.MyIterator;
import utilitarios.Sort;
import fila.FilaEnc;
import hash.HashEnc;

public class Demo {

	static HashEnc<Paciente> pacientes;
	static HashEnc<Orgao> orgaos;
	static HashEnc<Transplantados> transplantados;

	static class CompararPacientes implements Comparator<Paciente> {

		@Override
		public int compare(Paciente o1, Paciente o2) {
			return o1.getNomePaciente().compareToIgnoreCase(
					o2.getNomePaciente());
		}
	}

	static class CompararOrgaos implements Comparator<Orgao> {

		@Override
		public int compare(Orgao o1, Orgao o2) {
			return o1.getNomeOrgao().compareToIgnoreCase(o2.getNomeOrgao());
		}
	}

	static class CompararTransplantados implements Comparator<Transplantados> {

		@Override
		public int compare(Transplantados o1, Transplantados o2) {
			return o1.getPaciente().getNomePaciente()
					.compareToIgnoreCase(o2.getPaciente().getNomePaciente());
		}
	}

	public static void main(String[] args) {
		int opcao;
		int tamTabela = Keyboard.readInt("Entrar com o tamanho: ");
		pacientes = new HashEnc<Paciente>(tamTabela);
		orgaos = new HashEnc<Orgao>(tamTabela);
		transplantados = new HashEnc<Transplantados>(tamTabela);
		do {
			Keyboard.clrscr();
			opcao = Keyboard
					.menu("Incluir Pacientes/Listar Pacientes/Remover Paciente da Fila/Incluir Orgao"
							+ "/Listar Orgaos/Remover Orgaos/Listar Fila/Listar Transplantados/Terminar");
			switch (opcao) {
			case 1:
				incluirPacientes();
				break;
			case 2:
				listarPacientes();
				break;
			case 3:
				removerPacientes();
				break;
			case 4:
				incluirOrgao();
				break;
			case 5:
				listarOrgaos();
				break;
			case 6:
				removerOrgaos();
				break;
			case 7:
				listarFila();
				break;
			case 8:
				listarTransplantados();
			}
		} while (opcao < 9);
		System.out.println("\nFim do programa");

	}

	private static void incluirPacientes() {
		char resp;
		do {
			Keyboard.clrscr();
			int codPaciente = Keyboard.readInt("Entrar com o codigo: ");
			Paciente paciente = new Paciente(codPaciente);
			if (pacientes.contains(paciente))
				System.out.println("Codigo ja existente");
			else {
				paciente.setNomePaciente(Keyboard
						.readString("Entrar com o nome: "));
				paciente.setIdadePaciente(Keyboard
						.readInt("Entrar com a idade: "));
				int codOrgao = Keyboard
						.readInt("Entrar com o codigo do Orgao: ");
				Orgao orgao = new Orgao(codOrgao);
				orgao = orgaos.retrieve(orgao);
				if (orgao != null) {
					paciente.setOrgao(orgao);
					if (pacientes.add(paciente)) {
						paciente = pacientes.retrieve(paciente);
						orgao.inserirPaciente(paciente);
						System.out.println("Paciente cadastrada com sucesso");
					} else {
						System.out.println("Paciente ja existente!");
					}
				} else {
					char resp2 = Keyboard
							.readChar("Org�o n�o existente! Deseja cadastrar um novo Org�o? (s/n)");
					if (resp2 == 's')
						incluirOrgao();
					return;
				}
			}
			resp = Keyboard.readChar("Outro paciente(s/n)? ");
		} while (resp == 's');

	}

	private static void listarPacientes() {
		Keyboard.clrscr();
		System.out
				.println("Codigo  Nome do Paciente                Idade  Orgao         Posicao");
		System.out
				.println("------  ------------------------------  -----  ------------  -------");
		Paciente[] pacienteAlfa = new Paciente[pacientes.size()];
		int j = 0;
		MyIterator<Paciente> it = pacientes.iterator();
		Paciente paciente = it.getFirst();
		while (paciente != null) {
			pacienteAlfa[j] = paciente;
			j++;
			paciente = it.getNext();
		}
		Sort.quickSort(pacienteAlfa, new CompararPacientes());
		for (int i = 0; i < pacienteAlfa.length; i++) {
			int posicao = pacienteAlfa[i].getOrgao().posicaoDoPaciente(
					pacienteAlfa[i]);
			System.out.printf("%6d  %-30s  %5d  %-12s  %5d\n", pacienteAlfa[i]
					.getCodPaciente(), pacienteAlfa[i].getNomePaciente(),
					pacienteAlfa[i].getIdadePaciente(), pacienteAlfa[i]
							.getOrgao().getNomeOrgao(), posicao);
		}
		Keyboard.waitEnter();
	}

	private static void removerPacientes() {
		Keyboard.clrscr();
		int codOrgao = Keyboard.readInt("Entrar com o codigo do Orgao: ");
		Orgao orgao = new Orgao(codOrgao);
		if (!orgaos.contains(orgao)) {
			System.out.println("Orgão inexistente!");
		} else {
			orgao = orgaos.retrieve(orgao);
			if (orgao.tamanhoFila() == 0) {
				System.out.println("Sem pacientes precisando desse orgão!");
			}else{
				Paciente paciente = orgao.removerPaciente();
				if (paciente != null) {
					paciente = pacientes.retrieve(paciente);
					System.out.println("Codigo: " + paciente.getCodPaciente()
							+ "\nNome: " + paciente.getNomePaciente() + "\nIdade: "
							+ paciente.getIdadePaciente() + "\nOrgao: "
							+ paciente.getOrgao().getNomeOrgao());
					pacientes.remove(paciente);
					String data = Keyboard.readString("Entrar com a Data: ");
					Transplantados transplantado = new Transplantados(paciente, orgao, data);
					transplantados.add(transplantado);
					System.out.println("Atendido!");
				}	
			}
		}
		Keyboard.waitEnter();
	}

	private static void incluirOrgao() {
		char resp;
		do {
			Keyboard.clrscr();
			int codOrgao = Keyboard.readInt("Entrar com o codigo: ");
			Orgao orgao = new Orgao(codOrgao);
			if (orgaos.contains(orgao))
				System.out.println("Codigo ja existente");
			else {
				orgao.setNomeOrgao(Keyboard.readString("Entrar com o nome: "));
				if (orgaos.add(orgao)) {
					System.out.println("Orgao cadastrada com sucesso");
				} else {
					System.out.println("Orgao ja existente!");
				}
			}
			resp = Keyboard.readChar("Outro Org�o(s/n)? ");
		} while (resp == 's');
	}

	private static void listarOrgaos() {
		Keyboard.clrscr();
		System.out.println("Codigo  Nome do Orgao  TamanhoFila");
		System.out.println("------  -------------  -----------");
		Orgao[] orgaoAlfa = new Orgao[orgaos.size()];
		int j = 0;
		MyIterator<Orgao> it = orgaos.iterator();
		Orgao orgao = it.getFirst();
		while (orgao != null) {
			orgaoAlfa[j] = orgao;
			j++;
			orgao = it.getNext();
		}
		Sort.quickSort(orgaoAlfa, new CompararOrgaos());
		for (int i = 0; i < orgaoAlfa.length; i++) {
			System.out.printf("%6d  %-13s  %5d\n", orgaoAlfa[i].getCodOrgao(),
					orgaoAlfa[i].getNomeOrgao(), orgaoAlfa[i].tamanhoFila());
		}
		Keyboard.waitEnter();
	}

	private static void removerOrgaos() {
		Keyboard.clrscr();
		int codOrgao = Keyboard.readInt("Entrar com o codigo: ");
		Orgao orgao = new Orgao(codOrgao);
		orgao = orgaos.retrieve(orgao);
		if (orgao == null)
			System.out.println("Codigo inexistente");
		else {
			boolean existe = false;
			MyIterator<Paciente> it = pacientes.iterator();
			Paciente paciente = it.getFirst();
			while (paciente != null) {
				if (paciente.getOrgao() == orgao) {
					existe = true;
					break;
				}
				paciente = it.getNext();
			}
			if (!existe) {
				System.out.println("Nome do Org�o: " + orgao.getNomeOrgao());
				char resp = Keyboard.readChar("Deseja excluir(s/n)? ");
				if (resp == 's') {
					orgaos.remove(orgao);
					System.out.println("Org�o excluido");
				} else
					System.out.println("Org�o n�o excluido");
			} else {
				System.out
						.println("Existe paciente cadastrado para o org�o. Org�o n�o removido.");
			}
		}
		Keyboard.waitEnter();
	}

	private static void listarTransplantados() {
		Keyboard.clrscr();
		System.out
				.println("Nome do Paciente  Orgao Transplantado  Data do Transplante");
		System.out
				.println("----------------  -------------------  -------------------");
		Transplantados[] transplantadosAlfa = new Transplantados[transplantados
				.size()];
		int j = 0;
		MyIterator<Transplantados> it = transplantados.iterator();
		Transplantados transplantado = it.getFirst();
		while (transplantado != null) {
			transplantadosAlfa[j] = transplantado;
			j++;
			transplantado = it.getNext();
		}
		Sort.quickSort(transplantadosAlfa, new CompararTransplantados());
		for (int i = 0; i < transplantadosAlfa.length; i++) {
			System.out
					.printf("%-16s  %-19s  %-19s\n", transplantadosAlfa[i]
							.getPaciente().getNomePaciente(),
							transplantadosAlfa[i].getOrgao().getNomeOrgao(),
							transplantadosAlfa[i].getData());
		}
		Keyboard.waitEnter();
	}

	private static void listarFila() {
		Keyboard.clrscr();
		int codOrgao = Keyboard.readInt("Entrar com o codigo do orgao:");
		Orgao orgao = new Orgao(codOrgao);
		if (!orgaos.contains(orgao)) {
			System.out.println("Orgao Inexistente!");
		} else {
			orgao = orgaos.retrieve(orgao);
			System.out.println("Org�o: " + orgao.getNomeOrgao());
			System.out
					.println("Cod Paciente  Nome do Paciente                Idade");
			System.out
					.println("------------  ------------------------------  -----");
			orgao.listarFila();
		}
		Keyboard.waitEnter();
	}
}
