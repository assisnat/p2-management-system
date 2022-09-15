

public class Tecnico extends Usuario{

	public Tecnico(int idUser, String nome) {
		super(idUser, nome);
	}

	public void print(String antes, String depois) {
		System.out.print(antes);
		System.out.println("Id: " + idUser);
		System.out.println("Nome: " + nome);
		System.out.println("Categoria: Técnico");
		System.out.print(depois);
		
	}
	
	public String getCategoria() {
		return "Técnico";
	}
	
	
}
