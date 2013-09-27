package lista16;

import fila.FilaEnc;
import hash.HashEnc;
import java.util.Comparator;
import utilitarios.Keyboard;
import utilitarios.MyIterator;
import utilitarios.Sort;

public class Demo {

	static HashEnc<Paciente> pacientes;
	static HashEnc<Orgao> orgaos;
	static FilaEnc<Paciente> filaPacientes;

	static class CompararPacientes implements Comparator<Paciente> {

		@Override
		public int compare(Paciente o1, Paciente o2) {
			return o1.getNomePaciente().compareToIgnoreCase(o2.getNomePaciente());
		}
	}

	static class CompararOrgaos implements Comparator<Orgao> {

		@Override
		public int compare(Orgao o1, Orgao o2) {
			return o1.getNomeOrgao().compareToIgnoreCase(o2.getNomeOrgao());
		}
	}

	public static void main(String[] args) {
		int opcao;
		int tamTabela = Keyboard.readInt("Entrar com o tamanho: ");
		pacientes = new HashEnc<Paciente>(tamTabela);
		orgaos = new HashEnc<Orgao>(tamTabela);
		filaPacientes = new FilaEnc<Paciente>();
		do {
			Keyboard.clrscr();
			opcao = Keyboard.menu("Incluir Pacientes/Listar Pacientes/Remover Paciente da Fila/Incluir Orgao"
							+ "/Listar Orgaos/Remover Orgaos/Listar Fila/Terminar");
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
			}
		} while (opcao < 8);
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
						filaPacientes.insira(pacientes.retrieve(paciente));
						System.out.println("Paciente cadastrada com sucesso");
					} else {
						System.out.println("Paciente ja existente!");
					}
				} else {
					char resp2 = Keyboard
							.readChar("Orgão não existente! Deseja cadastrar um novo Orgão? (s/n)");
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
				.println("Codigo  Nome do Paciente                Idade  Orgao");
		System.out
				.println("------  ------------------------------  -----  ------------");
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
			System.out.printf("%6d  %-30s  %5d  %-12s\n", pacienteAlfa[i]
					.getCodPaciente(), pacienteAlfa[i].getNomePaciente(),
					pacienteAlfa[i].getIdadePaciente(), pacienteAlfa[i]
							.getOrgao().getNomeOrgao());
		}
		Keyboard.waitEnter();
	}

	private static void removerPacientes() {
		Keyboard.clrscr();
		boolean removeu = false;
		int codOrgao = Keyboard.readInt("Entrar com o codigo do Orgao: ");
		Orgao orgao = new Orgao(codOrgao);
		if (!orgaos.contains(orgao)) {
			System.out.println("Orgao inexistente!");
		} else {
			orgao = orgaos.retrieve(orgao);
			MyIterator<Paciente> it = filaPacientes.iterator();
			Paciente paciente = it.getFirst();
			while (paciente != null) {
				if (paciente.getOrgao() == orgao) {
					paciente = pacientes.retrieve(paciente);
					System.out.println("Codigo: " + paciente.getCodPaciente()
							+ "\nNome: " + paciente.getNomePaciente()
							+ "\nIdade: " + paciente.getIdadePaciente()
							+ "\nOrgao: " + paciente.getOrgao().getNomeOrgao());
					pacientes.remove(paciente);
					it.remove();
					removeu = true;
					break;
				} else {
					paciente = it.getNext();
				}
			}
			if (removeu) {
				System.out.println("Atendido!");
			} else {
				System.out.println("Sem pacientes precisando desse org�o!");
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
		System.out.println("Codigo  Nome do Orgao");
		System.out.println("------  -------------");
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
			System.out.printf("%6d  %-30s\n", orgaoAlfa[i].getCodOrgao(),
					orgaoAlfa[i].getNomeOrgao());
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

	private static void listarFila() {
		Keyboard.clrscr();
		System.out
				.println("Cod Paciente  Nome do Paciente                Idade  Orgao");
		System.out
				.println("------------  ------------------------------  -----  ---------------");
		MyIterator<Paciente> it = filaPacientes.iterator();
		Paciente paciente = it.getFirst();
		while (paciente != null) {
			System.out.printf("%12d  %-30s  %5d  %-15s\n", paciente
					.getCodPaciente(), paciente.getNomePaciente(), paciente
					.getIdadePaciente(), paciente.getOrgao().getNomeOrgao());
			paciente = it.getNext();
		}
		Keyboard.waitEnter();
	}

}
