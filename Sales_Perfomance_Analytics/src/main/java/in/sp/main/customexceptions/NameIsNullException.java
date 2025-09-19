package in.sp.main.customexceptions;

public class NameIsNullException extends Exception {
	private static final long serialVersionUID = 1L;

	public NameIsNullException(String message)
	{
		
		super(message);
	}
}
