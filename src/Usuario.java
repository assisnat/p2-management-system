import java.util.LinkedList;

public abstract class Usuario {
	
	public static final int CAT_ALUNO_GRADUACAO = 1; 	// Usuário da categoria aluno de graduação 
	public static final int CAT_ALUNO_MESTRADO = 2; 	// Usuário da categoria aluno de mestrado
	public static final int CAT_ALUNO_DOUTORADO = 3; 	// Usuário da categoria aluno de doutorado
	public static final int CAT_PROFESSOR= 4;		// Usuário da categoria professor
	public static final int CAT_PESQUISADOR = 5;		// Usuário da categoria pesquisador
	public static final int CAT_PROFISSIONAL = 6; 	// Usuário da categoria profissional (desenvolvedor, testador e analista)
	public static final int CAT_TECNICO = 7;		// Usuário da categoria técnico

	protected int idUser;
	protected String nome;
	
	// Construtor
	
	public Usuario(int idUser, String nome) {
		this.idUser = idUser;
		this.nome = nome;
	}

	// Métodos abstratos
	
	public abstract void print(String antes, String depois);
	
	public abstract String getCategoria();
	
	// Getters e Setters
	
	public int getIdUser() {
		return idUser;
	}

	public String getNome() {
		return nome;
	}

	public void setIdUser(int codUsuario) {
		this.idUser = codUsuario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static int indiceEmLista(LinkedList<Usuario> lista, int id) {
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getIdUser() == id) {
				return i;
			}
		}
		
		return -1;
	}
}

