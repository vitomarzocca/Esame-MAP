import java.io.*;
import java.util.HashSet;
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

}
