import java.io.Serializable;

public class Stanza implements Serializable
{
    int numeroStanza;
    boolean luce;
    boolean chiave;
    boolean letto;
    boolean armadio;

    public Stanza (boolean luce, boolean chiave, boolean letto, boolean armadio)
    {
        this.luce = luce;
        this.chiave = chiave;
        this. letto = letto;
        this.armadio = armadio;
    }
}
