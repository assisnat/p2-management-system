public class Professor extends Usuario{

	public Professor(int idUser, String nome) {
		super(idUser, nome);
	}

	public void print(String antes, String depois) {
		System.out.print(antes);
		System.out.println("Id: " + idUser);
		System.out.println("Nome: " + nome);
		System.out.println("Categoria: Professor");
		System.out.print(depois);
		
	}
	
	public String getCategoria() {
		return "Professor";
	}
	
	
}
