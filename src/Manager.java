import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Manager {
	private static int i = 0, j = 0;
    private static LinkedList<Projeto> projetos = new LinkedList<>();
	private static LinkedList<Usuario> usuarios = new LinkedList<>();
	private static LinkedList<Atividade> atividades = new LinkedList<>();
	
	private static String readLine() {
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));

		try {
			return leitor.readLine();
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	private static int readOption(int [] opcoes) {
		int opcao;

		while(true) {
			System.out.print("Opção ");
			try {
				opcao = Integer.parseInt(readLine());
			} catch (NumberFormatException e) {
				System.out.println("\nOpção inválida!\n");
				continue;
			}
			
			for(int o : opcoes) {
				if(opcao == o) {
					return opcao;
				}
			}
			
			System.out.println("\nOpção inválida!\n");
		}
	}
	
	private static int readInt(int min, int max) {
		int numero;
		
		while(true) {
			try {
				numero = Integer.parseInt(readLine());
			} catch (NumberFormatException e) {
				return min - 1;
			}
			
			if(numero < min || numero > max) {
				return max + 1;
			}
			
			return numero;
		}
	}
	
	private static int readId(String label) {
		int idUser;
		
		while(true) {
			System.out.print(label + ": ");
			try {
				idUser = Integer.parseInt(readLine());			
				
			} catch (NumberFormatException e) {
				System.out.println("\n" + label + " invalido!\n");
				continue;
			}
			return idUser;
		}	
	}

	private static LocalDateTime readDate() {	
		LocalDate data;
		LocalTime time;
		int dia, mes, ano, hora, min;
		
		while (true) {
			while(true) {
				System.out.print("Dia: ");
				dia = readInt(1,31);
				
				if(dia <= 0 || dia >= 32) {
					System.out.print("\nDia inválido! Insira apenas um dia entre 1 e 31.\n");
				} else {
					break;
				}
			}
			
			while(true) {
				System.out.print("Mês: ");
				mes = readInt(1,12);
				
				if(mes <= 0 || mes >= 13) {
					System.out.print("\nMês inválido! Insira apenas um mês entre 1 e 12.\n");
				} else {
					break;
				}
			}
			
			while(true) {
				System.out.print("Ano: ");
				ano = readInt(2000,2022);
				
				if(ano <= 1999 || ano >= 2023) {
					System.out.print("\nAno inválido!\n");
				} else {
					break;
				}
			}
			
			try { 
				data = LocalDate.of(ano, mes, dia);
				break;
			} catch (DateTimeException e) {
				System.out.println("\nData inválida!\n");
				continue;
			}
		}

		while(true) {
			while(true) {
				System.out.print("Hora: ");
				hora = readInt(0,23);
				
				if(hora <= -1 | hora >= 24) {
					System.out.print("\nHora inválida! Insira um número entre 0 e 23.\n");
				} else {
					break;
				}
			}
			
			while(true) {
				System.out.print("Minuto: ");
				min = readInt(0,59);
				
				if(min <= -1 || min >= 60) {
					System.out.print("\nMinuto inválido! Insira um número entre 0 e 59.\n");
				} else {
					break;
				}
			}
			
			try {
				time = LocalTime.of(hora, min);			
				break;
			} catch (DateTimeException e) {
				System.out.println("\nData inválida!\n");
				continue;
			}
		}

		return LocalDateTime.of(data, time);
	}
	
    // Menu principal

	private static int menuPrincipal() {
		System.out.println("Sistema de Gerenciamento de Projetos\n");
        System.out.println("Seja bem-vindo(a) ao sistema de gerenciamento de projetos, escolha uma das opções a seguir para continuar.\n\n");
		System.out.println("(1) Projetos");
		System.out.println("(2) Usuários");
		System.out.println("(3) Atividades");
		System.out.println("(4) Relatórios");
		System.out.println("(5) Sair");
						
		return readOption(new int[]{1,2,3,4,5});
	}
	
	private static void telaProjetos() {
		while(true) {
			System.out.println("\nProjetos\n");
			System.out.println("(1) Criar novo projeto");
			System.out.println("(2) Editar projeto existente");
			System.out.println("(3) Consultar projeto existente");
			System.out.println("(4) Voltar");
							
			switch(readOption(new int[]{1,2,3,4})) {
			case 1:
				novoProjeto();
				break;
			case 2:
				modificarProjeto();
				break;
			case 3:
				consultarProjetos();
				break;
			case 4:
				System.out.println("");
				return;
			}
		}
	}

	private static void novoProjeto() {
		Projeto p = new Projeto(i++);
		
		while(true) {
			System.out.println("\nCriar Novo Projeto");
			System.out.println("(1) Descrição do projeto");
			System.out.println("(2) Coordenador do projeto");
			System.out.println("(3) Profissionais envolvidos");
			System.out.println("(4) Atividades");
			System.out.println("(5) Data e Hora de Início");
			System.out.println("(6) Data e Hora de Término");
			System.out.println("(7) Status do projeto");
			System.out.println("(8) Criar projeto e voltar");
			System.out.println("(9) Cancelar a criação");
			switch(readOption(new int[]{1,2,3,4,5,6,7,8,9})) {
			case 1:
				modificarDescricaoProjeto(p);
				break;
			case 2:
				modificarCoordenador(p);
				break;
			case 3:
				submenuProfissionaisProjeto(p);
				break;
			case 4:
				submenuAtividadesProjeto(p);
				break;
			case 5:
				modificarInicioProjeto(p);
				break;
			case 6:
				modificarTerminoProjeto(p);
				break;
			case 7:
				modificarStatus(p);
				break;
			case 8:
				projetos.add(p);
				System.out.println("Projeto criado com sucesso!");
				return;
			case 9:
				System.out.println("Operação cancelada!");
				return;
			}	
		}
	}
	
	private static void modificarProjeto() {
		if(projetos.size() == 0) {
			System.out.println("Não há projetos para editar!\n");
			return;
		}
		
		System.out.println("\n Editar projeto");
		
		System.out.println("\nProjetos cadastrados:");
		System.out.println("----------------------------------------------");
		for(Projeto p : projetos) {
			p.print("", "----------------------------------------------\n", true);
		}
		
		while(true) {
			int id = readId("Id do projeto");
			Projeto proj = null;
			boolean encontrado = false;
			
			for(Projeto p : projetos) {
				if (p.getId() == id) {
					encontrado = true;
					proj = p;
					break;
				}
			}
			
			if(encontrado) {
				proj.print("\n----------------------------------------------\n",
						"----------------------------------------------\n", true);
				
				while(true) {
					System.out.println("\n Editar projeto");
					System.out.println("(1) Descrição do projeto");
			        System.out.println("(2) Coordenador do projeto");
			        System.out.println("(3) Profissionais envolvidos");
			        System.out.println("(4) Atividades");
			        System.out.println("(5) Data e Hora de Início");
			        System.out.println("(6) Data e Hora de Término");
			        System.out.println("(7) Status do projeto");
					System.out.println("(8) Cancelar");
					switch(readOption(new int[]{1,2,3,4,5,6,7,8})) {
					case 1:
						modificarDescricaoProjeto(proj);
						break;
					case 2:
						modificarCoordenador(proj);
						break;
					case 3:
						submenuProfissionaisProjeto(proj);
						break;
					case 4:
						submenuAtividadesProjeto(proj);
						break;
					case 5:
						modificarInicioProjeto(proj);
						break;
					case 6:
						modificarTerminoProjeto(proj);
						break;
					case 7:
						modificarStatus(proj);
						break;
					case 8:
						System.out.println("Operação cancelada!");
						return;
					}
				}
			} else {
				System.out.println("\nProjeto não encontrado!");
			}
		}
	}
	
	private static void consultarProjetos() {
		if(projetos.size() == 0) {
			System.out.println("Não há projetos para consultar!\n");
			return;
		}
				
		System.out.println("\n Consultar projetos");
		
		System.out.println("(1) Lista de Projetos");
		System.out.println("(2) Buscar por ID");
		
		switch(readOption(new int[] {1,2})) {
		case 1:
			System.out.println("\n Lista de Projetos");
			System.out.println("----------------------------------------------");
			
			for(Projeto p : projetos) {
				p.print("", "----------------------------------------------\n", false);
			}
			
			return;
		case 2:
			System.out.println("\nBuscar por ID");
			int id = readId("ID");
			
			for(Projeto p : projetos) {
				if(p.getId() == id) {
					p.print("\n----------------------------------------------\n",
							"----------------------------------------------\n", false);
					return;
				}				
			}
			
			System.out.println("\nProjeto não encontrado!");
			
			return;
		}
		
	}
	
	private static void submenuProfissionaisProjeto(Projeto p) {
		while(true) {
			System.out.println("\nProfissionais do projeto");
			System.out.println("(1) Adicionar profissionar");
			System.out.println("(2) Remover profissionar");
			System.out.println("(3) Listar profissionais");
			System.out.println("(4) Voltar");
			switch(readOption(new int[]{1,2,3,4})) {
			case 1:
				adicionarProfissionalProjeto(p);
				break;
			case 2:
				removerProfissionalProjeto(p);
				break;
			case 3:
				listarProfissionalProjeto(p);
				break;
			case 4:
				return;
			}
			
		}
	}
	
	private static void submenuAtividadesProjeto(Projeto p) {
		while(true) {
			System.out.println("\n Atividades do projeto");
			System.out.println("(1) Adicionar atividade");
			System.out.println("(2) Remover atividade");
			System.out.println("(3) Listar atividades");
			System.out.println("(4) Voltar");
			switch(readOption(new int[]{1,2,3,4})) {
			case 1:
				adicionarAtividadesProjeto(p);
				break;
			case 2:
				removerAtividadesProjeto(p);
				break;
			case 3:
				listarAtividadesProjeto(p);
				break;
			case 4:
				return;
			}
			
		}
	}
	
	private static void modificarDescricaoProjeto(Projeto p) {
		String descricao;
		
		if(p.getDescricao() == null) {
			System.out.println("\n Descrição do Projeto");
			
			System.out.println("Descrição: ");
			descricao = readLine();
			
			p.setDescricao(descricao);
			System.out.println("\nDescrição incluída com sucesso!");
			return;
						
		} else {
			System.out.println("\n Alterar Descrição do Projeto");
			
			System.out.println("Nova descrição: ");
			descricao = readLine();
			
			System.out.println("\nDeseja substituir a descrição?");
			
			System.out.println("\nDescrição antiga: " + p.getDescricao());
			
			System.out.println("Nova descrição: " + descricao);
			
			System.out.println("\n(1) Confirmar e substituir");
			System.out.println("(2) Cancelar e voltar");
			
			switch(readOption(new int[] {1,2})) {
			case 1:
				p.setDescricao(descricao);
				System.out.println("\nDescrição alterada com sucesso!");
				return;
			case 2:
				System.out.println("\nOperação cancelada!");
				return;
			}
		}
	}
	
	private static void modificarCoordenador(Projeto p) {
		
		if(p.getCoordenador() == null) {
			System.out.println("\nCoordenador de Projeto");
			
			while(true) {
				int idUser = readId("Id do Coordenador");
				Usuario user = null;
				boolean encontrado = false;
				
				for(Usuario u : usuarios) {
					if(u.getIdUser() == idUser) {
						encontrado = true;
						user = u;
						break;
					}
				}
				
				if(encontrado) {
					user.print("\n----------------------------------------------\n",
							"----------------------------------------------\n");
					
					if(Projeto.validaCoordenador(user)) {
						System.out.println("\nConfirma este usuário como coordenador do projeto?");
						
						System.out.println("\n(1) Confirmar");
						System.out.println("(2) Inserir outro id");
						System.out.println("(3) Cancelar e voltar");
						
						switch(readOption(new int[] {1,2,3})) {
						case 1:
							p.setCoordenador(user);
							System.out.println("\nCoordenador incluído com sucesso!");
							return;
						case 2:
							continue;
						case 3:
							System.out.println("\nOperação cancelada!");
							return;
						}					
					} else {
						System.out.println("\nO usuário encontrado não pode ser coordenador, pois não é Professor nem Pesquisador!");
						
						System.out.println("\nDeseja cadastrar um novo usuário?");
						
						System.out.println("\n(1) Cadastrar novo usuario");
						System.out.println("(2) Inserir outro id");
						System.out.println("(3) Cancelar e voltar");
						
						switch(readOption(new int[] {1,2,3})) {
						case 1:
							cadastrarUsuario();
							continue;
						case 2:
							continue;
						case 3:
							System.out.println("\nOperação cancelada!");
							return;
						}
					}					
				} else {
					System.out.println("\nRA do Coordenador não encontrado!");
					
					System.out.println("\nDeseja cadastrar um novo usuário?");
					
					System.out.println("(1) Cadastrar novo usuario");
					System.out.println("(2) Inserir outro id");
					System.out.println("(3) Cancelar e voltar");
					
					switch(readOption(new int[] {1,2,3})) {
					case 1:
						cadastrarUsuario();
						continue;
					case 2:
						continue;
					case 3:
						System.out.println("\nOperação cancelada!");
						return;
					}
				}
			}						
		} else {
			System.out.println("\n Alterar Coordenador de Projeto");
			
			while(true) {
				int idUser = readId("Id do Novo Coordenador");
				Usuario user = null;
				boolean encontrado = false;
				
				for(Usuario u : usuarios) {
					if(u.getIdUser() == idUser) {
						encontrado = true;
						user = u;
						break;
					}
				}
				
				if(encontrado) {
					p.getCoordenador().print("\n----------------------------------------------\nAntigo Coordenador:\n",
							"----------------------------------------------\n");
					
					user.print("Novo Coordenador:\n",
							"----------------------------------------------\n");
					
					if(Projeto.validaCoordenador(user)) {
						System.out.println("\nConfirmar a substituição do coordenador do projeto?");
						
						System.out.println("\n(1) Confirmar");
						System.out.println("(2) Inserir outro id");
						System.out.println("(3) Cancelar e voltar");
						
						switch(readOption(new int[] {1,2,3})) {
						case 1:
							p.setCoordenador(user);
							System.out.println("\nCoordenador substituido com sucesso!");
							return;
						case 2:
							continue;
						case 3:
							System.out.println("\nOperação cancelada!");
							return;
						}					
					} else {
						System.out.println("\nO usuário encontrado não pode ser coordenador, pois não é Professor nem Pesquisador!");
						
						System.out.println("\nDeseja cadastrar um novo usuário?");
						
						System.out.println("\n(1) Cadastrar novo usuario");
						System.out.println("(2) Inserir outro id");
						System.out.println("(3) Cancelar e voltar");
						
						switch(readOption(new int[] {1,2,3})) {
						case 1:
							cadastrarUsuario();
							continue;
						case 2:
							continue;
						case 3:
							System.out.println("\nOperação cancelada!");
							return;
						}
					}					
				} else {
					System.out.println("Id do Novo Coordenador não encontrado!");
					
					System.out.println("\nDeseja cadastrar um novo usuário?");
					
					System.out.println("\n(1) Cadastrar novo usuario");
					System.out.println("(2) Inserir outro id");
					System.out.println("(3) Cancelar e voltar");
					
					switch(readOption(new int[] {1,2,3})) {
					case 1:
						cadastrarUsuario();
						continue;
					case 2:
						continue;
					case 3:
						System.out.println("\nOperação cancelada!");
						return;
					}
				}
			}	
		}
	}
	
	private static void modificarInicioProjeto(Projeto p) {
		LocalDateTime inicio;
		
		if(p.getInicio() == null) {
			System.out.println("\nData de Início do Projeto");
			
			System.out.println("Data de início: ");
			inicio = readDate();
			
			p.setInicio(inicio);
			System.out.println("\nData de início incluída com sucesso!");
			return;
						
		} else {
			System.out.println("\nAlterar Data de Início de Projeto");
		
			System.out.println("Nova data de início:");
			inicio = readDate();
			
			System.out.println("\nDeseja substituir a data de início?");
			
			System.out.println("\nData de início antigo: " + p.getInicio().toString());
			
			System.out.println("Nova data de início: " + inicio.toString());
			
			System.out.println("\n(1) Confirmar e substituir");
			System.out.println("(2) Cancelar e voltar");
			
			switch(readOption(new int[] {1,2})) {
			case 1:
				p.setInicio(inicio);
				System.out.println("\nData de início alterada com sucesso!");
				return;
			case 2:
				System.out.println("\nOperação cancelada!");
				return;
			}
		}		
	}
	
	private static void modificarTerminoProjeto(Projeto p) {
		LocalDateTime termino;
		
		if(p.getTermino() == null) {
			System.out.println("\nData de Término do Projeto");
			
			System.out.println("Data de término: ");
			termino = readDate();
			
			p.setTermino(termino);
			System.out.println("\nData de término incluída com sucesso!");
			return;
						
		} else {
			System.out.println("\nAlterar Data de Término de Projeto");
		
			System.out.println("Nova data de término:");
			termino = readDate();
			
			System.out.println("\nDeseja substituir a data de término?");
			
			System.out.println("\nData de término antigo: " + p.getTermino().toString());
			
			System.out.println("Nova data de término: " + termino.toString());
			
			System.out.println("\n(1) Confirmar e substituir");
			System.out.println("(2) Cancelar e voltar");
			
			switch(readOption(new int[] {1,2})) {
			case 1:
				p.setTermino(termino);
				System.out.println("\nData de término alterada com sucesso!");
				return;
			case 2:
				System.out.println("\nOperação cancelada!");
				return;
			}
		}		
	}
	
	private static void adicionarProfissionalProjeto(Projeto p) {
		while(true) {
			System.out.println("\nAdicionar Profissional ao Projeto");
			
			int idUser = readId("Id");
			Usuario user = null;
			boolean encontrado = false;
			
			for(Usuario u : usuarios) {
				if(u.getIdUser() == idUser) {
					encontrado = true;
					user = u;
					break;
				}
			}
			
			if(encontrado) {
				user.print("\n----------------------------------------------\n",
						"----------------------------------------------\n");
				
				System.out.println("\nConfirma inclusão deste usuário ao projeto?");
				
				System.out.println("\n(1) Confirmar");
				System.out.println("(2) Inserir outro RA");
				System.out.println("(3) Cancelar e voltar");
				
				switch(readOption(new int[] {1,2,3})) {
				case 1:
					p.inserirProfissional(user);
					System.out.println("\nProfissional inserido ao projeto com sucesso!");
					return;
				case 2:
					continue;
				case 3:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("\nId não encontrado!");
				
				System.out.println("\nDeseja cadastrar um novo usuário?");
				
				System.out.println("\n(1) Cadastrar novo usuario");
				System.out.println("(2) Inserir outro RA");
				System.out.println("(3) Cancelar e voltar");
				
				switch(readOption(new int[] {1,2,3})) {
				case 1:
					cadastrarUsuario();
					continue;
				case 2:
					continue;
				case 3:
					System.out.println("\nOperação cancelada!");
					return;
				}
			}
		}
	}
	
	private static void removerProfissionalProjeto(Projeto p) {
		System.out.println("\nRemover Profissional ao Projeto");
		
		while(true) {
			int idUser = readId("Id");
			Usuario user = null;
			boolean encontrado = false;
			
			for(Usuario u : usuarios) {
				if(u.getIdUser() == idUser) {
					encontrado = true;
					user = u;
					break;
				}
			}
			
			if(encontrado) {
				user.print("\n----------------------------------------------\n",
						"----------------------------------------------\n");
				
				System.out.println("\nConfirma exclusão deste usuário ao projeto?");
				
				System.out.println("\n(1) Confirmar exclusão");
				System.out.println("(2) Inserir outro id");
				System.out.println("(3) Cancelar e voltar");
				
				switch(readOption(new int[] {1,2,3})) {
				case 1:
					p.removerProfissional(user);
					System.out.println("\nProfissional removido com sucesso!");
					return;
				case 2:
					continue;
				case 3:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("Id não encontrado!");
				
				System.out.println("\n(1) Inserir outro id");
				System.out.println("(2) Cancelar e voltar");
				
				switch(readOption(new int[] {1,2})) {
				case 1:
					continue;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			}
		}
	}
	
	private static void listarProfissionalProjeto(Projeto p) {
		System.out.println("\nLista de Profissionais do projeto");
		
		System.out.println("----------------------------------------------");
		for(Usuario u : p.getProfissionais()) {
			u.print("","----------------------------------------------\n");
		}
		
		return;
	}
	
	private static void adicionarAtividadesProjeto(Projeto p) {
		while(true) {
			System.out.println("\nAdicionar Atividade ao Projeto");
			
			int id = readId("ID da Atividade");
			Atividade ativ = null;
			boolean encontrado = false;
			
			for(Atividade a : atividades) {
				if(a.getId() == id) {
					encontrado = true;
					ativ = a;
					break;
				}
			}
			
			if(encontrado) {
				ativ.print("\n----------------------------------------------\n",
						"----------------------------------------------\n",true);
				
				System.out.println("\nConfirma inclusão desta atividade ao projeto?");
				
				System.out.println("\n(1) Confirmar");
				System.out.println("(2) Inserir outra ID");
				System.out.println("(3) Cancelar e voltar");
				
				switch(readOption(new int[] {1,2,3})) {
				case 1:
					p.inserirAtividade(ativ);
					System.out.println("\nAtividade inserida ao projeto com sucesso!");
					return;
				case 2:
					continue;
				case 3:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("\nID de atividade não encontrada!");
				
				System.out.println("\nDeseja adicionar uma nova atividade ao sistema?");
				
				System.out.println("\n(1) Cadastrar nova atividade");
				System.out.println("(2) Inserir outra ID");
				System.out.println("(3) Cancelar e voltar");
				
				switch(readOption(new int[] {1,2,3})) {
				case 1:
					adicionarAtividade();
					continue;
				case 2:
					continue;
				case 3:
					System.out.println("\nOperação cancelada!");
					return;
				}
			}
		}
	}
	
	private static void removerAtividadesProjeto(Projeto p) {
		System.out.println("\nRemover Atividade ao Projeto");
		
		while(true) {
			int id = readId("ID");
			Atividade ativ = null;
			boolean encontrado = false;
			
			for(Atividade a : atividades) {
				if(a.getId() == id) {
					encontrado = true;
					ativ = a;
					break;
				}
			}
			
			if(encontrado) {
				ativ.print("\n----------------------------------------------\n",
						"----------------------------------------------\n", true);
				
				System.out.println("\nConfirma exclusão desta atividade do projeto?");
				
				System.out.println("\n(1) Confirmar exclusão");
				System.out.println("(2) Inserir outro ID de atividade");
				System.out.println("(3) Cancelar e voltar");
				
				switch(readOption(new int[] {1,2,3})) {
				case 1:
					p.removerAtividade(ativ);
					continue;
				case 2:
					continue;
				case 3:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("ID de atividade não encontrado!");
				
				System.out.println("\n(1) Inserir outro ID de atividade");
				System.out.println("(2) Cancelar e voltar");
				
				switch(readOption(new int[] {1,2})) {
				case 1:
					continue;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			}
		}	
	}
	
	private static void listarAtividadesProjeto(Projeto p) {
		System.out.println("\n== Lista de Atividades do projeto ==");
		
		System.out.println("----------------------------------------------");
		for(Atividade a : p.getAtividades()) {
			a.print("","----------------------------------------------\n", false);
		}
		
		return;
	}
	
	
	
	private static void modificarStatus(Projeto p) {
		// Imprime mensagens do menu
		System.out.println("\nAlterar Status de Projeto");
		System.out.println("Status atual: " + p.getStatusTexto());		
		
		switch(p.getStatus()) {
		case Projeto.STATUS_CRIACAO:
			boolean podeAlterarStatus = true;
			
			if(p.getCoordenador() == null) {
				podeAlterarStatus = false;
				System.out.println("Coordenador do projeto não definido.");
			}
		
			if(p.getDescricao() == null) {
				podeAlterarStatus = false;
				System.out.println("Descrição do projeto não definida.");
			}
			
			if(p.getInicio() == null) {
				podeAlterarStatus = false;
				System.out.println("Data de início do projeto não definida.");
			}
			
			if(p.getTermino() == null) {
				podeAlterarStatus = false;
				System.out.println("Data de término do projeto não definida.");
			}
			
			if(p.getProfissionais().size() == 0) {
				podeAlterarStatus = false;
				System.out.println("Projeto não possui profissionais associados.");
			}
		
			if(p.getAtividades().size() == 0) {
				podeAlterarStatus = false;
				System.out.println("Projeto não possui atividades associadas.");
			}
			
			if(podeAlterarStatus) {
				System.out.println("\nDeseja alterar o status do projeto para INICIADO?.");
				
				System.out.println("\n(1) Sim");
				System.out.println("(2) Não");
				
				switch(readOption(new int[] {1,2})) {
				case 1:
					if(p.getInicio().isBefore(LocalDateTime.now())) {
						p.setStatus(Projeto.STATUS_INICIADO);
						
						System.out.println("\nStatus alterado com sucesso!");
						return;
					} else {
						System.out.println("\nImpossível alterar status! Data de início anterior a data de hoje.");
					}
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("\nO status do projeto não pode ser alterado até que todas as pendências sejam resolvidas.");
				return;
			}
		case Projeto.STATUS_INICIADO:
			if(p.getInicio().isBefore(LocalDateTime.now())) {
				p.setStatus(Projeto.STATUS_ANDAMENTO);
				
				System.out.println("\nStatus alterado automaticamente para EM ANDAMENTO!");
				break;
			} else {
				System.out.println("\nAguarde a data de início do projeto. O sistema irá alterar o status automaticamente.");
			}
			return;
		case Projeto.STATUS_ANDAMENTO:
			System.out.println("\nDeseja alterar o status do projeto para CONCLUÍDO?.");
			
			System.out.println("\n(1) Sim");
			System.out.println("(2) Não");
			
			switch(readOption(new int[] {1,2})) {
			case 1:
				p.setStatus(Projeto.STATUS_CONCLUIDO);
				System.out.println("\nStatus alterado com sucesso!");
				break;
			case 2:
				System.out.println("\nOperação cancelada!");
			}
			
			return;
		case Projeto.STATUS_CONCLUIDO:
			System.out.println("\nO projeto já foi concluído. Impossível alterar status.");
			return;
		}
	}
  
	private static void telaUsuarios() {
		while(true) {
			System.out.println("\nUsuarios");
			System.out.println("(1) Cadastrar usuário");
			System.out.println("(2) Remover usuário");
			System.out.println("(3) Consultar usuário");
			System.out.println("(4) Voltar");
							
			switch(readOption(new int[]{1,2,3,4})) {
			case 1:
				cadastrarUsuario();
				break;
			case 2:
				removerUsuario();
				break;
			case 3:
				consultarUsuarios();
				break;
			case 4:
				System.out.println("");
				return;
			}
		}
	}
	
	private static void cadastrarUsuario() {
		Usuario novoUsuario = null;
		String nome;
		int idUser;
		
		System.out.println("\nCadastrar usuário");
		idUser = readId("Id (Número de Matrícula): ");
		System.out.print("Nome: ");
		nome = readLine();
		
		System.out.println("Categoria:");
		System.out.println("(1) Aluno de Graduação");
		System.out.println("(2) Aluno de Mestrado");
		System.out.println("(3) Aluno de Doutorado");
		System.out.println("(4) Professor");
		System.out.println("(5) Pesquisador");
		System.out.println("(6) Profissional");
		System.out.println("(7) Técnico");
		
		switch(readOption(new int[] {1,2,3,4,5,6,7})) {
		case Usuario.CAT_ALUNO_GRADUACAO:
			novoUsuario = new AlunoGraduacao(idUser, nome);
			break;
		case Usuario.CAT_ALUNO_MESTRADO:
			novoUsuario = new AlunoMestrado(idUser, nome);
			break;
		case Usuario.CAT_ALUNO_DOUTORADO:
			novoUsuario = new AlunoDoutorado(idUser, nome);
			break;
		case Usuario.CAT_PROFESSOR:
			novoUsuario = new Professor(idUser, nome);
			break;
		case Usuario.CAT_PESQUISADOR:
			novoUsuario = new Pesquisador(idUser, nome);
			break;
		case Usuario.CAT_PROFISSIONAL:
			novoUsuario = new Profissional(idUser, nome);
			break;
		case Usuario.CAT_TECNICO:
			novoUsuario = new Tecnico(idUser, nome);
			break;
		}
					
		System.out.println("\nAs informações estao corretas?");
		
		novoUsuario.print("\n----------------------------------------------\n",
				"----------------------------------------------\n\n");
		
		System.out.println("(1) Confirmar e cadastrar ");
		System.out.println("(2) Cancelar e voltar");
		
		// 
		switch(readOption(new int[] {1,2})) {
		case 1:
			for(Usuario u : usuarios) {
				if(u.getIdUser() == idUser) {
					System.out.println("\nId já cadastrado! Operação cancelada!");
					return;
				}
			}
			
			usuarios.add(novoUsuario);
			
			System.out.println("\nUsuário cadastrado com sucesso!");
		case 2:
			return;
		}
	}
	
	private static void removerUsuario() {
		if(usuarios.size() == 0) {
			System.out.println("Não há usuários para remover!\n");
			return;
		}	
		
		int idUser;
		
		System.out.println("\n== Remover usuário ==");
	    idUser = readId("Id");
		
		int index = Usuario.indiceEmLista(usuarios, idUser);
					
		if(index != -1) {
			System.out.println("\nDeseja remover o usuário?");
	
			usuarios.get(index).print("\n----------------------------------------------\n",
					"----------------------------------------------\n\n");
			
			System.out.println("(1) Remover e voltar");
			System.out.println("(2) Cancelar e voltar");
			
			switch(readOption(new int[] {1,2})) {
			case 1:
				usuarios.remove(index);
				System.out.println("\nUsuário removido com sucesso!");
				return;
			case 2:
				System.out.println("\nOperação cancelada!");
				return;
			}
		} 

		System.out.println("\nUsuário não encontrado!");
		return;
	}
	
	private static void consultarUsuarios() {
		if(usuarios.size() == 0) {
			System.out.println("Não há usuários para consultar!\n");
			return;
		}
		
		System.out.println("\n== Consultar usuários ==");
		
		System.out.println("(1) Lista de Usuários");
		System.out.println("(2) Buscar por id");
		
		switch(readOption(new int[] {1,2})) {
		case 1:
			System.out.println("\nLista de Usuários");
			System.out.println("----------------------------------------------");
			
			for(Usuario u : usuarios) {
				u.print("", "----------------------------------------------\n");
			}
			
			return;
		case 2:
			System.out.println("\nBuscar por id");
			int id = readId("Id");
			
			for(Usuario u : usuarios) {
				if(u.getIdUser() == id) {
					u.print("\n----------------------------------------------\n",
							"----------------------------------------------\n");
					return;
				}
			}
			
			System.out.println("\nUsuário não encontrado!");
			
			return;
		}
	}

	private static void telaAtividades() {
		while(true) {
			System.out.println("\nAtividades");
			System.out.println("(1) Adicionar");
			System.out.println("(2) Remover");
			System.out.println("(3) Modificar");
			System.out.println("(4) Consultar");
			System.out.println("(5) Voltar");
							
			switch(readOption(new int[]{1,2,3,4,5})) {
			case 1:
				adicionarAtividade();
				break;
			case 2:
				removerAtividade();
				break;
			case 3:
				modificarAtividade();
				break;
			case 4:
				consultarAtividades();
				break;
			case 5:
				System.out.println("");
				return;
			}
		}	
	}
	
	private static void adicionarAtividade() {
		Usuario responsavel = null;
		String descricao;
		LocalDateTime inicio;
		LocalDateTime termino;
		int idUser;
		boolean continuar = true;
		boolean encontrado;
		
		System.out.println("\nAdicionar Atividade");
		
		while(continuar) {
			idUser = readId("Id do Responsável");
			encontrado = false;
			
			for(Usuario u : usuarios) {
				if (u.getIdUser() == idUser) {
					encontrado = true;
					
					System.out.println("\nConfirma responsável pela atividade?");
					u.print("\n----------------------------------------------\n",
							"----------------------------------------------\n");
					
					System.out.println("\n(1) Sim");
					System.out.println("(2) Não");
					System.out.println("(3) Cancelar e voltar");
					
					switch(readOption(new int[] {1,2,3})) {
					case 1:
						responsavel = u;
						continuar = false;
						break;
					case 2:
						System.out.println("");
						break;
					case 3:
						System.out.println("\nOperação cancelada!");
						return;
					}
					
					break;
				}
			}
			
			if(continuar && !encontrado) {
				System.out.println("\nUsuário não encontrado!\n");
				System.out.println("(1) Ler outro id");
				System.out.println("(2) Cancelar e voltar");
				
				switch(readOption(new int[] {1,2})) {
				case 1:
					break;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			}
		}
		
		System.out.println("\nDescrição da atividade: ");
		descricao = readLine();
		
		while(true) {
			System.out.println("Data de inicio: ");
			inicio = readDate();
			
			System.out.println("Data de término: ");
			termino = readDate();
			
			if(termino.isAfter(inicio)) {
				break;
			} else {
				System.out.println("\nInício e término inválidos! A data de início é posterior à data de término.\n");
				continue;
			}
		}
		
		Atividade a = new Atividade(j++, descricao, inicio, termino, responsavel);

		System.out.println("\nOs dados da atividade estão corretos?");
		
		a.print("\n----------------------------------------------\n",
				"----------------------------------------------\n", false);
		
		System.out.println("\n(1) Confirmar e adicionar");
		System.out.println("(2) Cancelar e voltar");
		
		switch(readOption(new int[] {1,2})) {
		case 1:
			atividades.add(a);
			System.out.println("\nAtividade adicionada com sucesso!\n");
			
			break;
		case 2:
			System.out.println("\nOperação cancelada!");
			return;
		}
		
		while(true) {
			System.out.println("Deseja adicionar profissionais envolvidos na atividade?");
			System.out.println("\n(1) Sim");
			System.out.println("(2) Voltar");
			
			switch(readOption(new int[] {1,2})) {
			case 1:
				adicionarProfissionais(a);
				
				a.print("\n----------------------------------------------\n",
						"----------------------------------------------\n", false);
				
				break;
			case 2:
				System.out.println("\nOperação terminada!");
				return;
			}	
		}
	}
	
	private static void removerAtividade() {
		if(atividades.size() == 0) {
			System.out.println("Não há atividades para remover!\n");
			return;
		}		
		
		int id;		
		boolean encontrado;
		System.out.println("\nRemover Atividade");
		Atividade ativ = null;
		
		System.out.println("Atividades cadastradas:");
		System.out.println("----------------------------------------------");
		for(Atividade a : atividades) {
			a.print("", "----------------------------------------------\n", true);
		}
		
		while(true) {
			id = readId("ID da Atividade");
			
			encontrado = false;
			
			for(Atividade a : atividades) {
				if (a.getId() == id) {
					encontrado = true;
					ativ = a;
					break;
				}
			}
			
			if(encontrado) {
				System.out.println("\nDeseja remover a seguinte atividade?");

				ativ.print("\n----------------------------------------------\n", 
						"----------------------------------------------\n", true);
				
				
				System.out.println("\n(1) Confirmar e remover");
				System.out.println("(2) Cancelar e voltar");
				
				switch(readOption(new int[] {1,2})) {
				case 1:
					atividades.remove(ativ);
					System.out.println("\nAtividade removida com sucesso!");
					return;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("\nAtividade não encontrada!");					
				continue;
			}	
		}
	}
	
	private static void modificarAtividade() {
		if(atividades.size() == 0) {
			System.out.println("Não há atividades para modificar!\n");
			return;
		}
		
		int id;		
		boolean encontrado;
		System.out.println("\n== Modificar Atividade ==");
		Atividade ativ = null; 

		System.out.println("Atividades cadastradas:");
		System.out.println("----------------------------------------------");
		for(Atividade a : atividades) {
			a.print("", "----------------------------------------------\n", true);
		}
		
		while(true) {
			id = readId("ID da Atividade");
			
			encontrado = false;
			
			for(Atividade a : atividades) {
				if (a.getId() == id) {
					encontrado = true;
					ativ = a;
					break;
				}
			}
			
			if(encontrado) {
				System.out.println("\nDeseja modificar a seguinte atividade?");

				ativ.print("\n----------------------------------------------\n", 
						"----------------------------------------------\n", true);
				
				
				System.out.println("\n(1) Confirmar e modificar");
				System.out.println("(2) Cancelar e voltar");
				
				switch(readOption(new int[] {1,2})) {
				case 1:
					break;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("\nAtividade não encontrada!");					
				continue;
			}
			
			while(true) {
				System.out.println("\nO que você deseja modificar?");
				System.out.println("(1) Responsável");
				System.out.println("(2) Descrição");
				System.out.println("(3) Início");
				System.out.println("(4) Término");
				System.out.println("(5) Incluir usuários envolvidos");
				System.out.println("(6) Excluir usuários envolvidos");
				System.out.println("(7) Alterar tarefa de usuarios");
				System.out.println("(8) Voltar");
				
				switch(readOption(new int[] {1,2,3,4,5,6,7,8})) {
				case 1:
					modificarResponsavel(ativ);					
					continue;
				case 2:
					modificarDescricaoAtividade(ativ);
					continue;
				case 3:
					modificarInicioAtividade(ativ);
					continue;
				case 4:
					modificarTerminoAtividade(ativ);
					continue;
				case 5:
					adicionarProfissionais(ativ);					
					continue;
				case 6:
					removerProfissional(ativ);					
					continue;
				case 7:
					modificarTarefa(ativ);
					continue;
				case 8:
					return;
				}
			}			
		}
	}
	
	private static void consultarAtividades() {
		if(atividades.size() == 0) {
			System.out.println("Não há atividades para consultar!\n");
			return;
		}
				
		System.out.println("\nConsultar atividades");
		
		System.out.println("(1) Lista de Atividades");
		System.out.println("(2) Buscar por ID");
		
		switch(readOption(new int[] {1,2})) {
		case 1:
			System.out.println("\nLista de Atividades");
			System.out.println("----------------------------------------------");
			
			for(Atividade a : atividades) {
				a.print("", "----------------------------------------------\n", false);
			}
			
			return;
		case 2:
			System.out.println("\nBuscar por ID");
			int id = readId("ID");
			
			for(Atividade a : atividades) {
				if(a.getId() == id) {
					a.print("\n----------------------------------------------\n",
							"----------------------------------------------\n", false);
					return;
				}
			}
			
			System.out.println("\nAtividade não encontrada!");
			
			return;
		}
	}

	private static void modificarResponsavel(Atividade ativ) {
		System.out.println("\nModificar Responsável da Atividade");
		
		int idUser;
		boolean encontrado;
		
		idUser = readId("Id do Novo Responsável");

		if(ativ.getResponsavel().getIdUser() == idUser) {
			System.out.println("O id do Novo Responsável é idêntico ao id do Antigo Responsável!");
			System.out.println("\nOperação cancelada!");
			return;
		}
		
		encontrado = false;
		for(Usuario u : usuarios) {
			if(u.getIdUser() == idUser) {
				encontrado = true;
				
				System.out.print("\nVocê deseja substituir o responsável?");
				
				ativ.getResponsavel().print("\n----------------------------------------------\nAntigo Responsável:\n",
						"----------------------------------------------\n");
				
				u.print("\n----------------------------------------------\nNovo Responsável:\n",
						"----------------------------------------------\n");
				
				System.out.println("(1) Confirmar e substituir");
				System.out.println("(2) Cancelar e voltar");
				
				switch(readOption(new int[] {1,2})) {
				case 1:
					ativ.setResponsavel(u);
					System.out.println("\nResponsável alterado com sucesso!");
					return;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			}			
		}
		
		if(!encontrado){
			System.out.println("\nId não encontrado! Operação cancelada!");
		}		
	}
	
	private static void modificarDescricaoAtividade(Atividade ativ) {
		
		System.out.println("\n== Modificar Descrição da Atividade ==");
		
		String descricao;
		
		System.out.println("Nova descrição: ");
		descricao = readLine();
		
		System.out.println("\nDeseja substituir a descrição?");
		
		System.out.println("\nDescrição antiga: " + ativ.getDescricao());
		
		System.out.println("Nova descrição: " + descricao);
		
		System.out.println("\n(1) Confirmar e substituir");
		System.out.println("(2) Cancelar e voltar");
		
		switch(readOption(new int[] {1,2})) {
		case 1:
			ativ.setDescricao(descricao);
			System.out.println("\nDescrição alterada com sucesso!");
			return;
		case 2:
			System.out.println("\nOperação cancelada!");
			return;
		}
	}
	
	private static void modificarInicioAtividade(Atividade ativ) {
		System.out.println("\nModificar Data de Início da Atividade");
		
		LocalDateTime inicio;
		
		System.out.println("Nova data de inicio:");
		inicio = readDate();
		
		System.out.println("\nDeseja substituir a data de início?");
		
		System.out.println("\nData de início antigo: " + ativ.getInicio().toString());
		
		System.out.println("Nova data de início: " + inicio.toString());
		
		System.out.println("\n(1) Confirmar e substituir");
		System.out.println("(2) Cancelar e voltar");
		
		switch(readOption(new int[] {1,2})) {
		case 1:
			ativ.setInicio(inicio);
			System.out.println("\nData de início alterada com sucesso!");
			return;
		case 2:
			System.out.println("\nOperação cancelada!");
			return;
		}
	}
	
	private static void modificarTerminoAtividade(Atividade ativ) {
		
		System.out.println("\nModificar Data de Término da Atividade");
		
		LocalDateTime termino;
		
		System.out.println("Nova data de inicio:");
		termino = readDate();
		
		System.out.println("\nDeseja substituir a data de término?");
		
		System.out.println("\nData de término antigo: " + ativ.getTermino().toString());
		
		System.out.println("Nova data de término: " + termino.toString());
		
		System.out.println("\n(1) Confirmar e substituir");
		System.out.println("(2) Cancelar e voltar");
		
		switch(readOption(new int[] {1,2})) {
		case 1:
			ativ.setTermino(termino);
			System.out.println("\nData de término alterada com sucesso!");
			return;
		case 2:
			System.out.println("\nOperação cancelada!");
			return;
		}
	}
	
	private static void adicionarProfissionais(Atividade a) {
		System.out.println("\nAdicionar Profissionais à Atividade");
		int idUser;
		String tarefa;
		boolean encontrado;
		Usuario user = null; 
		
		while(true) {
			idUser = readId("Id");
			encontrado = false;

			for(Usuario u : usuarios) {
				if (u.getIdUser() == idUser) {
					// Usuario encontrado
					encontrado = true;
					user = u;
					break;
				}
			}
			
			if(encontrado) {
				if(a.getProfissionais().contains(user)) {
					System.out.println("\nUsuário já consta na lista de profissionais associados a esta atividade!");					
					continue;
				} else {
					user.print("\n----------------------------------------------\n", 
							"----------------------------------------------\n");					
					
					System.out.println("\nTarefa: ");
					tarefa = readLine();
					
					System.out.println("\nDeseja incluir o seguinte profissional e tarefa?");
					
					System.out.println("\n----------------------------------------------");
					System.out.println("Nome: " + user.getNome());
					System.out.println("Tarefa: " + tarefa);
					System.out.println("----------------------------------------------\n");
					
					System.out.println("(1) Confirmar e incluir");
					System.out.println("(2) Cancelar e voltar");
					
					switch(readOption(new int[] {1,2})) {
					case 1:
						a.adicionarProfissional(user, tarefa);
						System.out.println("\nProfissional incluído com sucesso!");
						return;
					case 2:
						System.out.println("\nOperação cancelada!");
						return;
					}
				}
			} else {
				System.out.println("\nUsuário não encontrado!");					
				continue;
			}
		}
	}
	
	private static void removerProfissional(Atividade ativ) {
		System.out.println("\nRemover Profissionais da Atividade");
		int idUser, index = -1;
		boolean encontrado;
		
		System.out.println("");
		ativ.printProfissionais();
		
		while(true) {
			idUser = readId("Id");
			
			encontrado = false;
			
			for(int i = 0; i < ativ.getProfissionais().size(); i++) {
				if (ativ.getProfissionais().get(i).getIdUser() == idUser) {
					encontrado = true;
					index = i;
					break;
				}
			}
			
			if(encontrado) {
				System.out.println("\nDeseja remover o seginte profissional e tarefa?");
				
				System.out.println("\n----------------------------------------------");
				System.out.println("Nome: " + ativ.getProfissionais().get(index).getNome());
				System.out.println("Tarefa: " + ativ.getTarefas().get(index));
				System.out.println("----------------------------------------------\n");
				
				System.out.println("(1) Confirmar e remover");
				System.out.println("(2) Cancelar e voltar");
				
				switch(readOption(new int[] {1,2})) {
				case 1:
					ativ.removerProfissional(index);
					System.out.println("\nProfissional removido com sucesso!");
					return;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("\nUsuário não encontrado!");					
				continue;
			}
		}
	}

	private static void modificarTarefa(Atividade ativ) {
		System.out.println("\nModificar Tarefa de Profissional da Atividade");
		int idUser, index;
		
		System.out.println("");
		ativ.printProfissionais();
		
		while(true) {
			idUser = readId("Id");
			
			index = Usuario.indiceEmLista(ativ.getProfissionais(), idUser);
			
			if(index != -1) {

				System.out.println("\nDeseja modificar a tarefa do seguinte profissional?");
				
				System.out.println("\n----------------------------------------------");
				System.out.println("Nome: " + ativ.getProfissionais().get(index).getNome());
				System.out.println("Tarefa: " + ativ.getTarefas().get(index));
				System.out.println("----------------------------------------------\n");
				
				System.out.println("(1) Confirmar e modificar");
				System.out.println("(2) Cancelar e voltar");
				
				switch(readOption(new int[] {1,2})) {
				case 1:
					substituirTarefa(ativ, index);
					return;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("\nUsuário não encontrado!");					
				continue;
			}
		}
	}
	
	private static void substituirTarefa(Atividade ativ, int index) {
		System.out.println("\nSubstituir Tarefas");
		
		String tarefa;
		
		System.out.println("Nova tarefa: ");
		tarefa = readLine();
		
		System.out.println("\nDeseja substituir a tarefa?");
		
		System.out.println("\n----------------------------------------------");
		System.out.println("Tarefa antiga: " + ativ.getTarefas().get(index));
		System.out.println("Nova tarefa: " + tarefa);
		System.out.println("----------------------------------------------\n");
		
		System.out.println("(1) Confirmar e substituir");
		System.out.println("(2) Cancelar e voltar");
		
		switch(readOption(new int[] {1,2})) {
		case 1:
			ativ.getTarefas().set(index, tarefa);
			return;
		case 2:
			System.out.println("\nOperação cancelada!");
			return;
		}
	
	}
	
	private static void telaRelatorios() {
		while(true) {
			System.out.println("\nRelatórios");
			System.out.println("(1) Projetos");
			System.out.println("(2) Atividades");
			System.out.println("(3) Voltar");
							
			switch(readOption(new int[]{1,2,3})) {
			case 1:
				relatorioDeProjetos();
				break;
			case 2:
				relatorioDeAtividades();
				break;
			case 3:
				System.out.println("");
				return;
			}
		}
	}
	
	private static void relatorioDeProjetos() {
		if(projetos.size() == 0) {
			System.out.println("Não há projetos para exibir relatórios!\n");
			return;
		}
		
		System.out.println("\n== Relatório de atividades");
		
		System.out.println("----------------------------------------------");
		for(Projeto p : projetos) {
			p.print("", "----------------------------------------------", false);
		}
		
		return;
	}
	
	private static void relatorioDeAtividades() {
		if(atividades.size() == 0) {
			System.out.println("Não há atividades para exibir relatórios!\n");
			return;
		}
		
		System.out.println("\n== Relatório de atividades");
		
		System.out.println("----------------------------------------------");
		for(Atividade a : atividades) {
			a.print("", "----------------------------------------------", false);
		}
		return;
	}
	
	public static void main(String [] args) {
		while(true) {
			switch(menuPrincipal()) {
			case 1:
				telaProjetos();
				break;
			case 2:
				telaUsuarios();
				break;
			case 3:
				telaAtividades();
				break;
			case 4:
				telaRelatorios();
				break;
			case 5:
				return;	
			}
		}
	}
}
