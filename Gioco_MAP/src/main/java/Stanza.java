import java.io.Serializable;

public class Stanza implements Serializable
{
    public int numeroStanza;
    public String descrizione;
    public Oggetti oggettoRichiesto = Oggetti.VUOTO;  //ad esempio per entrare in una stanza serve la chiave
    public Oggetti oggetto = Oggetti.VUOTO;
    public boolean luce;   //FALSE = luce spenta    TRUE = luce accesa
    public boolean letto;  //FALSE = non c'è un letto   TRUE = c'è un letto.
    public boolean armadio;  //FALSE = non c'è un armadio   TRUE = c'è un letto.armadio.


    public Stanza (int numeroStanza, String descrizione, boolean luce, boolean letto, boolean armadio, int stanzaCollegata1, int stanzaCollegata2, int stanzaCollegata3, int stanzaCollegata4, Oggetti oggetto, Oggetti oggettoRichiesto) {
        this.numeroStanza = numeroStanza;
        this.descrizione = descrizione;
        this.luce = luce;
        this.letto = letto;
        this.armadio = armadio;
        this.oggetto = oggetto;
        this.oggettoRichiesto = oggettoRichiesto;
    }

}

