package in.sp.main.customexceptions;

public class EmailIsNullException extends Exception  {

	private static final long serialVersionUID = 1L;

	public EmailIsNullException(String message )
	{
		
		super(message);
	}
}
