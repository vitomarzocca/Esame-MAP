import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.io.FileReader;
import java.io.BufferedReader;

public class GameManager
{
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
        //avvaloraCasa();
    }

    private void inizializzaInventario()
    {
        for(int i = 0; i<10; i++)
        {
            inventario[i] = Oggetti.VUOTO;

        }
        System.out.println("Inventario caricato");
    }

    private void avvaloraParoleDaCancellare()
    {
        FileReader fr = null;
        boolean flag = false;
        String parolaLetta;

        try
        {
            fr = new FileReader("/home/vito/Scrivania/Esame-MAP/Gioco_MAP/src/main/resources/paroleDaCancellare.txt");
            System.out.println("file trovato");
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
            System.out.println("file trovato");
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

    //metodo ancora da testare
    private void avvaloraCasa()
    {
        Stanza stanza;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try
        {
            fis = new FileInputStream("./descrizioneStanze.txt");
        }
        catch (IOException e)
        {
            System.out.println("Si è verificato un errore con il file \"descizioneStanze.txt\"");
        }

        try
        {
            ois = new ObjectInputStream(fis);
        }
        catch (IOException e)
        {
            System.out.println("Si è verificato un errore con il file \"descizioneStanze.txt\"");
        }

        while (true)
        {
            try
            {
                ois.readObject();
            }
            catch (ClassNotFoundException e)
            {
                break;
            }
            catch (IOException e)
            {
                break;
            }
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


}
