package uniandes.cupi2.karaoke.mundo;

public class NoHayCancionesException extends Exception
{

	public NoHayCancionesException(String causa)
	{
		super (causa);
	}
}
