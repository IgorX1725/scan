package entities.exceptions;

//Classe personalizada para tratar as excess�es do c�digo
public class DomainExceptions extends Exception{
	
	private static final long serialVersionUID = 1L;

	public DomainExceptions(String msg) {
		super(msg);
	}
}
