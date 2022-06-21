import java.util.Set;

public class Parser
{
    //L'idea è quella di prendere il comando e riscriverlo come <verbo> <oggetto>
    public static void parser(String comando, Set<String> paroleDaCancellare, Set<String> paroleConcesse)
    {
        int i = 0;

        String[] elementiComandoSporco = comando.split("\\s+");
        String[] elementiComandoPulito = new String [1];

        for (String parola:elementiComandoPulito)
        {
            if(paroleDaCancellare.contains(parola) == false && i<2 && paroleConcesse.contains(parola) == true)
            {
                elementiComandoPulito[i]=parola;
                i++;
            }
        }

        if (elementiComandoPulito[0] == "entra" || elementiComandoPulito[0] == "sali" || elementiComandoPulito[0] == "scendi")
        {
            //invocazione comando per cambiare stanza
        }

        else if (elementiComandoPulito[0] == "raccogli" || elementiComandoPulito[0] == "prendi")
        {
            //invocazione comando per raccogliere oggetto
        }

        else if (elementiComandoPulito[0] == "nasconditi")
        {
            //invocazione comando per nascondersi
        }

        else if (elementiComandoPulito[0] == "accendi")
        {
            //invocazione comando per accendere la luce in una stanza
        }

        else if (elementiComandoPulito[0] == "spegni")
        {
            //invocazione comando per spegnere la luce
        }

        else if (elementiComandoPulito[0] == "usa")
        {
            //invocazione comando per usare un oggetto nell'inventario
        }

        else if (elementiComandoPulito[0] == "apri" && elementiComandoPulito[1] == "inventario")
        {
            //comando per visualizzare l'inventario
        }
    }
}
