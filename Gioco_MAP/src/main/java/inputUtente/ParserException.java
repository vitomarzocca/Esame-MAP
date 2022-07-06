package inputUtente;

public class ParserException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Comando non valido";
    }
}
