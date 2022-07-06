package inputUtente;

/**
 * Eccezione lanciata dal metodo parser della classe Parser
 */
public class ParserException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Comando non valido";
    }
}
