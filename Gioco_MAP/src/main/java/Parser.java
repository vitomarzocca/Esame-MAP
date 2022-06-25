import java.util.Set;

public class Parser
{
    //L'idea è quella di prendere il comando e riscriverlo come <verbo> <oggetto>
    public static void parser(String comando,  GameManager partita)
    {
        int i = 0;

        String[] elementiComandoSporco = comando.split("\\s+");
        String[] elementiComandoPulito = new String [2];

        String comandopt1 = "/";
        String comandopt2 = "/";


        for (String parola:elementiComandoSporco)
        {
            if(partita.paroleDaCancellare.contains(parola) == false && i<2 && partita.paroleConcesse.contains(parola) == true)
            {
                elementiComandoPulito[i]=parola;
                i++;
            }
        }

        try
        {
            comandopt1 = elementiComandoPulito[0].toString();
            comandopt2 = elementiComandoPulito[1].toString();
        }
        catch(NullPointerException e)
        {

        }


        if (comandopt1.compareTo("entra") == 0 || comandopt1.compareTo("muovi") == 0 || comandopt1.compareTo("apri") == 0)
        {
            partita.muovi(elementiComandoPulito[1]);
        }

        else if (comandopt1.compareTo("raccogli") == 0 || comandopt1.compareTo("prendi") == 0)
        {
            partita.raccoltaOggetto(elementiComandoPulito[1]);
        }

        else if (comandopt1.compareTo("nasconditi") == 0)
        {
            partita.nascondi();
        }

        else if (comandopt1.compareTo("accendi") == 0 && comandopt2.compareTo("luce") == 0)
        {
            partita.accendiLuce();
        }

        else if (comandopt1.compareTo("spegni") == 0 &&comandopt2.compareTo("luce") == 0)
        {
            partita.spegniLuce();
        }

        else if (comandopt1.compareTo("usa") == 0)
        {
            partita.usa(elementiComandoPulito[1]);
        }

        else if ((comandopt1.compareTo("apri") == 0 && comandopt2.compareTo("inventario") == 0) || (comandopt1.compareTo("mostra") == 0 && comandopt2.compareTo("inventario") == 0))
        {
            partita.apriInvetario();
        }

        else if (comandopt1.compareTo("carica") + comandopt2.compareTo("salvataggio") == 0)
        {
            //bisgona ancora creare un metodo
        }

        else if (comandopt1.compareTo("/") == 0 || comandopt2.compareTo("/") == 0)
        {
            System.out.println("Comando non valido. Riprovare");
        }
    }
}
