package entities.exceptions;

//Classe personalizada para tratar as excessões do código
public class DomainExceptions extends Exception{
	
	private static final long serialVersionUID = 1L;

	public DomainExceptions(String msg) {
		super(msg);
	}
}
