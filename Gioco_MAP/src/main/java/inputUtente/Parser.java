package inputUtente;

import logica.GameManager;

public class Parser
{
    /**
     * Metodo che riceve in input una stringa, stabilisce qual'è il soggetto della stringa e qual'è loggetto e invoca un metodo di GameManager.
     * Se viene passata una stringa che non rappresenta nussun comando viene lanciata un eccezione
     * @param comando
     * @param partita
     * @throws ParserException
     */
    public static void parser(String comando,  GameManager partita) throws ParserException
    {
        int i = 0;

        String[] elementiComandoSporco = comando.split("\\s+");
        String[] elementiComandoPulito = new String [2];

        elementiComandoPulito[0] = "/";
        elementiComandoPulito[1]= "/";


        for (String parola:elementiComandoSporco)
        {
            if(!partita.paroleDaCancellare.contains(parola) && i<2 && partita.paroleConcesse.contains(parola))
            {
                elementiComandoPulito[i]=parola;
                i++;
            }
        }
        
        
        if (elementiComandoPulito[0].compareTo("entra") == 0 || elementiComandoPulito[0].compareTo("muoviti") == 0 || elementiComandoPulito[0].compareTo("apri") == 0)
        {
            partita.muovi(elementiComandoPulito[1]);
        }

        else if (elementiComandoPulito[0].compareTo("raccogli") == 0 || elementiComandoPulito[0].compareTo("prendi") == 0)
        {
            partita.raccoltaOggetto(elementiComandoPulito[1]);
        }

        else if (elementiComandoPulito[0].compareTo("nasconditi") == 0)
        {
            partita.nascondi();
        }

        else if (elementiComandoPulito[0].compareTo("accendi") == 0 && elementiComandoPulito[1].compareTo("luce") == 0)
        {
            partita.accendiLuce();
        }

        else if (elementiComandoPulito[0].compareTo("spegni") == 0 &&elementiComandoPulito[1].compareTo("luce") == 0)
        {
            partita.spegniLuce();
        }

        else if (elementiComandoPulito[0].compareTo("usa") == 0)
        {
            partita.usa(elementiComandoPulito[1]);
        }

        else if (elementiComandoPulito[0].compareTo("mostra") == 0 && elementiComandoPulito[1].compareTo("inventario") == 0)
        {
            partita.apriInvetario();
        }

        else if (elementiComandoPulito[0].compareTo("carica") + elementiComandoPulito[1].compareTo("salvataggio") == 0 || elementiComandoPulito[0].compareTo("carica") + elementiComandoPulito[1].compareTo("partita") == 0)
        {
            partita.caricaSalvataggio();
        }

        else if (elementiComandoPulito[0].compareTo("salva") + elementiComandoPulito[1].compareTo("partita") == 0)
        {
            partita.salvaPartita();
        }

        else
        {
            throw new ParserException();
        }
    }
}
