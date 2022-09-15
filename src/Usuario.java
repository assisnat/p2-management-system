import java.util.LinkedList;

public abstract class Usuario {
	
	public static final int CAT_ALUNO_GRADUACAO = 1;
	public static final int CAT_ALUNO_MESTRADO = 2; 
	public static final int CAT_ALUNO_DOUTORADO = 3; 
	public static final int CAT_PROFESSOR= 4;	
	public static final int CAT_PESQUISADOR = 5;		
	public static final int CAT_PROFISSIONAL = 6; 
	public static final int CAT_TECNICO = 7;		

	protected int idUser;
	protected String nome;
	
	public Usuario(int idUser, String nome) {
		this.idUser = idUser;
		this.nome = nome;
	}
	
	public abstract void print(String antes, String depois);
	
	public abstract String getCategoria();
	
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

