import java.io.Serializable;

public class Stanza implements Serializable
{
    int numeroStanza;
    boolean luce;   //FALSE = luce spenta    TRUE = luce accesa
    boolean letto;  //FALSE = non c'è un letto   TRUE = c'è un letto.
    boolean armadio;  //FALSE = non c'è un armadio   TRUE = c'è un letto.armadio.

    Oggetti oggetto;

    public Stanza (int numeroStanza, boolean luce, boolean letto, boolean armadio)
    {
        this.numeroStanza = numeroStanza;
        this.luce = luce;
        this. letto = letto;
        this.armadio = armadio;
    }

    public void inserisciOggettoStanza(Oggetti oggetto)
    {
        this.oggetto = oggetto;
    }
}
