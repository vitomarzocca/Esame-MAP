import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.io.FileReader;
import java.io.BufferedReader;

public class GameManager
{
    public boolean nascosto = false;
    public int stanzaCorrente;
    public Oggetti[] inventario = new Oggetti[10];

    public Set<Stanza> casa = new HashSet();
    public Set<String> paroleConcesse = new HashSet<>();
    public Set<String> paroleDaCancellare = new HashSet<>();

    int tempoDiGioco;

    public GameManager()
    {
        stanzaCorrente = 1;
        tempoDiGioco = 0;
        inizializzaInventario();
        avvaloraParoleDaCancellare();
        avvaloraParoleConcesse();
        avvaloraCasa();
    }

    private void inizializzaInventario()
    {
        for(int i = 0; i<10; i++)
        {
            inventario[i] = Oggetti.VUOTO;

        }
    }

    private void avvaloraParoleDaCancellare()
    {
        FileReader fr = null;
        boolean flag = false;
        String parolaLetta;

        try
        {
            fr = new FileReader("/home/vito/Scrivania/Esame-MAP/Gioco_MAP/src/main/resources/paroleDaCancellare.txt");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("si è verificato un errore con la gestione del file");
        }

        BufferedReader bf = new BufferedReader(fr);

        try
        {
            parolaLetta = bf.readLine();

            if(parolaLetta != null)
            {
                paroleDaCancellare.add(parolaLetta);
                flag = true;
            }

        }
        catch (IOException e )
        {
            System.out.println("Si è verificato un errore nella lettura del file paroleDaCancellare");

        }

        try
        {
            while(flag == true)
            {
                parolaLetta = bf.readLine();

                if(parolaLetta != null)
                {
                    paroleDaCancellare.add(parolaLetta);
                    flag = true;
                }
                else
                {
                    flag = false;
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Si è verificato un errore nella lettura del file paroleDaCancellare");
        }
        try
        {
            bf.close();
        }
        catch (IOException e )
        {
            System.out.println("Si è verificato un errore nella chiusura dello stream");
        }

        try
        {
            bf.close();
        }
        catch (IOException e )
        {
            System.out.println("Si è verificato un errore nella chiusura dello stream");
        }
    }

    private void avvaloraParoleConcesse()
    {
        FileReader fr = null;
        boolean flag = false;
        String parolaLetta;

        try
        {
            fr = new FileReader("/home/vito/Scrivania/Esame-MAP/Gioco_MAP/src/main/resources/paroleConcesse.txt");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("si è verificato un errore con la gestione del file");
        }

        BufferedReader bf = new BufferedReader(fr);

        try
        {
            parolaLetta = bf.readLine();

            if(parolaLetta != null)
            {
                paroleConcesse.add(parolaLetta);
                flag = true;
            }

        }
        catch (IOException e )
        {
            System.out.println("Si è verificato un errore nella lettura del file paroleConcesse");

        }

        try
        {
            while(flag == true)
            {
                parolaLetta = bf.readLine();

                if(parolaLetta != null)
                {
                    paroleConcesse.add(parolaLetta);
                    flag = true;
                }
                else
                {
                    flag = false;
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Si è verificato un errore nella lettura del file paroleConcesse");
        }
        try
        {
            bf.close();
        }
        catch (IOException e )
        {
            System.out.println("Si è verificato un errore nella chiusura dello stream");
        }

        try
        {
            bf.close();
        }
        catch (IOException e )
        {
            System.out.println("Si è verificato un errore nella chiusura dello stream");
        }
    }

    private void avvaloraCasa()
    {
        Stanza stanza = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        boolean flag = false;

        try
        {
            fis = new FileInputStream("/home/vito/Scrivania/Esame-MAP/Gioco_MAP/src/main/resources/descrizioneStanze.txt");
        }
        catch (IOException e)
        {
            System.out.println("Si è verificato un errore con il file \"descizioneStanze.txt\" 1");
        }

        try
        {
            ois = new ObjectInputStream(fis);
        }
        catch (IOException e)
        {
            System.out.println("Si è verificato un errore con il file \"descizioneStanze.txt\" 2");
        }

        try
        {
            stanza = (Stanza) ois.readObject();
            casa.add(stanza);
            stanza = (Stanza) ois.readObject();
            casa.add(stanza);
            stanza = (Stanza) ois.readObject();
            casa.add(stanza);
            stanza = (Stanza) ois.readObject();
            casa.add(stanza);
            stanza = (Stanza) ois.readObject();
            casa.add(stanza);
            stanza = (Stanza) ois.readObject();
            casa.add(stanza);
            stanza = (Stanza) ois.readObject();
            casa.add(stanza);
            stanza = (Stanza) ois.readObject();
            casa.add(stanza);
            stanza = (Stanza) ois.readObject();
            casa.add(stanza);
            stanza = (Stanza) ois.readObject();
            casa.add(stanza);
            stanza = (Stanza) ois.readObject();
            casa.add(stanza);
            stanza = (Stanza) ois.readObject();
            casa.add(stanza);
        }
        catch(IOException e)
        {
            System.out.println("Si è verificato un errore");
        }
        catch(ClassNotFoundException k)
        {
            System.out.println("Si è verificato un errore");
        }

        try
        {
            fis.close();
        }
        catch (IOException e)
        {
            System.out.println("Si è verificato un errore nella chiusura dello stream");
        }

        try
        {
            ois.close();
        }
        catch (IOException e)
        {
            System.out.println("Si è verificato un errore nella chiusura dello stream");
        }

    }

    private Stanza ricercaStanzaCorrente()
    {
        Stanza stanza = null;


        Iterator<Stanza> it = casa.iterator();
        {
            while(it.hasNext() == true)
            {
                stanza = it.next();

                if (stanza.numeroStanza != stanzaCorrente)
                {
                    stanza = null;
                }

                else
                {
                    break;
                }
            }
        }

        return stanza;
    }

    private Stanza ricercaStanzaPerNumero(int numeroStanza)
    {
        Stanza nuovaStanza = null;


        Iterator<Stanza> it = casa.iterator();
        {
            while (it.hasNext() == true)
            {
                nuovaStanza = it.next();

                if (nuovaStanza.numeroStanza != numeroStanza)
                {
                    nuovaStanza = null;
                }
                else
                {
                    break;
                }

            }
        }
        return nuovaStanza;
    }

    private boolean controllaInvetario (Oggetti oggetto)
    {
        boolean trovato = false;

        for (int i = 0; i<10; i++)
        {
            if(inventario[i] == oggetto)
            {
                trovato = true;
            }
        }

        if(trovato == true)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    public void raccoltaOggetto(String oggetto)
    {
        Stanza stanza = ricercaStanzaCorrente();

        if(ricercaStanzaCorrente().oggetto.name().equals(oggetto) )  //il .name() permette la conversione da enumerativo a stringa
        {
            System.out.println(stanza.oggetto.name() + "è stato aggiunto all'invetario");

            for (int i = 0; i<10; i++)
            {
                if(inventario[i] == Oggetti.VUOTO)
                {
                    inventario[i] = stanza.oggetto;
                }
            }
        }



    }

    public void accendiLuce()
    {
        Stanza stanza = ricercaStanzaCorrente();

        if (stanza.luce == true)
        {
            System.out.println("In questa stanza la luce è già accesa");
        }

        else
        {
            casa.remove(stanza);
            stanza.luce = true;
            casa.add(stanza);
        }
    }

    public void spegniLuce()
    {
        Stanza stanza = ricercaStanzaCorrente();

        if (stanza.luce == false)
        {
            System.out.println("In questa stanza la luce è già spenta");
        }

        else
        {
            casa.remove(stanza);
            stanza.luce = false;
            casa.add(stanza);
        }
    }

    public void apriInvetario()
    {
        for (Oggetti oggetto : inventario)
        {
            System.out.println(oggetto);
        }
    }

    public void nascondi()
    {
        Stanza stanza = ricercaStanzaCorrente();

        if (stanza.armadio == true)
        {
            nascosto = true;
            System.out.println("Ti sei nascosto nell'aramdio");
        }

        else if (stanza.letto == true)
        {
            nascosto = true;
            System.out.println("Ti sei nascosto sotto il letto");
        }

        else
        {
            System.out.println("Non c'è nessun nascodiglio in questa stanza");
        }
    }

    public void usa(String oggetto) //metodo non definitivo è na cosa provissoria
    {
        Oggetti ogg ;

        try
        {
            ogg = Oggetti.valueOf(oggetto);

            if(controllaInvetario(ogg))
            {
                //da finire
            }

            else
            {
                System.out.print("L' oggetto che vuoi usare non è presente nel tuo inventario");
            }
        }
        catch(Exception e )
        {
            System.out.println("Oggetto che vuoi usare non è presente nell'inventario");
        }


    }

    public void muovi(String direzione)
    {
        Stanza stanza = ricercaStanzaCorrente();
        Stanza nuovaStanza = null;

        if (stanza.numeroStanza == 1 && direzione == "est")
        {
            nuovaStanza = ricercaStanzaPerNumero(2);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 1 && direzione == "ovest")
        {
            nuovaStanza = ricercaStanzaPerNumero(3);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }

        }

        else if (stanza.numeroStanza == 1 && direzione == "nord" )
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 2 && direzione == "ovest")
        {
            nuovaStanza = ricercaStanzaPerNumero(1);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 3 && direzione == "est")
        {
            nuovaStanza = ricercaStanzaPerNumero(1);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 3 && direzione == "nord" )
        {
            nuovaStanza = ricercaStanzaPerNumero(1);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 4 && direzione == "nord")
        {
            nuovaStanza = ricercaStanzaPerNumero(7);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 4 && direzione == "est")
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 4 && direzione == "sud")
        {
            nuovaStanza = ricercaStanzaPerNumero(3);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione == "sud")
        {
            nuovaStanza = ricercaStanzaPerNumero(1);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione == "ovest")
        {
            nuovaStanza = ricercaStanzaPerNumero(4);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione == "nord")
        {
            nuovaStanza = ricercaStanzaPerNumero(6);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione == "est")
        {
            nuovaStanza = ricercaStanzaPerNumero(12);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 6 && direzione == "sud")
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 6 && direzione == "ovest")
        {
            nuovaStanza = ricercaStanzaPerNumero(7);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 6 && direzione == "est")
        {
            nuovaStanza = ricercaStanzaPerNumero(11);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 7 && direzione == "est")
        {
            nuovaStanza = ricercaStanzaPerNumero(6);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 7 && direzione == "sud")
        {
            nuovaStanza = ricercaStanzaPerNumero(4);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 7 && direzione == "nord")
        {
            nuovaStanza = ricercaStanzaPerNumero(8);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 8 && direzione == "est")
        {
            nuovaStanza = ricercaStanzaPerNumero(9);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 8 && direzione == "sud")
        {
            nuovaStanza = ricercaStanzaPerNumero(10);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 9 && direzione == "ovest")
        {
            nuovaStanza = ricercaStanzaPerNumero(8);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 10 && direzione == "nord")
        {
            nuovaStanza = ricercaStanzaPerNumero(8);

            if(controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
            }
            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else
        {
            System.out.println("Non esiste nessuna porta in questa direzione");
        }



    }


}
