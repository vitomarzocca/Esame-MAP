package logica;

import java.io.Serializable;

public class Stanza implements Serializable
{
    public int numeroStanza;
    public String descrizione;
    public Oggetti oggettoRichiesto;
    public Oggetti oggetto;
    public boolean luce;   //FALSE = luce spenta    TRUE = luce accesa
    public boolean letto;  //FALSE = non c'è un letto   TRUE = c'è un letto.
    public boolean armadio;  //FALSE = non c'è un armadio   TRUE = c'è un letto.armadio.


    /**
     * Costruttore della classe Stanza
     * @param numeroStanza
     * @param descrizione
     * @param luce
     * @param letto
     * @param armadio
     * @param oggetto
     * @param oggettoRichiesto
     */
    public Stanza (int numeroStanza, String descrizione, boolean luce, boolean letto, boolean armadio, Oggetti oggetto, Oggetti oggettoRichiesto) {
        this.numeroStanza = numeroStanza;
        this.descrizione = descrizione;
        this.luce = luce;
        this.letto = letto;
        this.armadio = armadio;
        this.oggetto = oggetto;
        this.oggettoRichiesto = oggettoRichiesto;
    }

}

