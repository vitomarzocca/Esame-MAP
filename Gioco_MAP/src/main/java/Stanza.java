import java.io.Serializable;

public class Stanza implements Serializable
{
    int numeroStanza;

    int stanzaCollegata1 = -1;
    int stanzaCollegata2 = -1;   //il numero di stande è modificabile
    int stanzaCollegata3 = -1;
    boolean luce;   //FALSE = luce spenta    TRUE = luce accesa
    boolean letto;  //FALSE = non c'è un letto   TRUE = c'è un letto.
    boolean armadio;  //FALSE = non c'è un armadio   TRUE = c'è un letto.armadio.

    Oggetti oggetto;

    public Stanza (int numeroStanza, boolean luce, boolean letto, boolean armadio, int stanzaCollegata1, int stanzaCollegata2, int stanzaCollegata3)
    {
        this.numeroStanza = numeroStanza;
        this.luce = luce;
        this. letto = letto;
        this.armadio = armadio;
        this.stanzaCollegata1 = stanzaCollegata1;
        this.stanzaCollegata2 = stanzaCollegata2;
        this.stanzaCollegata3 = stanzaCollegata3;
    }

    public void inserisciOggettoStanza(Oggetti oggetto)
    {
        this.oggetto = oggetto;
    }
}
