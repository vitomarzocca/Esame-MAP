import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.io.FileReader;
import java.io.BufferedReader;

public class GameManager {
    public boolean nascosto = false;
    public int stanzaCorrente;
    public boolean vivo;
    public Oggetti[] inventario = new Oggetti[6];
    public boolean[] stanzaVisitata = new boolean[12];
    public Set<Stanza> casa = new HashSet();
    public Set<String> paroleConcesse = new HashSet<>();
    public Set<String> paroleDaCancellare = new HashSet<>();

    Evento evento;

    GestioneSalvataggio salvataggio;


    public GameManager()
    {
        stanzaCorrente = 1;
        inizializzaInventario();
        inizializzaStanzaVisitata();
        avvaloraParoleDaCancellare();
        avvaloraParoleConcesse();
        avvaloraCasa();
        evento = new Evento();
        vivo = true;
    }

    private void inizializzaStanzaVisitata()
    {
        for (int i = 0; i < 12; i++) {
            stanzaVisitata[i] = false;
        }
    }

    private void inizializzaInventario()
    {
        for (int i = 0; i < 6; i++) {
            inventario[i] = Oggetti.VUOTO;

        }
    }

    private void avvaloraParoleDaCancellare()
    {
        FileReader fr = null;
        boolean flag = false;
        String parolaLetta;

        try {
            fr = new FileReader("/home/vito/Scrivania/Esame-MAP/Gioco_MAP/src/main/resources/paroleDaCancellare.txt");
        } catch (FileNotFoundException e) {
            System.out.println("si è verificato un errore con la gestione del file");
        }

        BufferedReader bf = new BufferedReader(fr);

        try {
            parolaLetta = bf.readLine();

            if (parolaLetta != null) {
                paroleDaCancellare.add(parolaLetta);
                flag = true;
            }

        } catch (IOException e) {
            System.out.println("Si è verificato un errore nella lettura del file paroleDaCancellare");

        }

        try {
            while (flag == true) {
                parolaLetta = bf.readLine();

                if (parolaLetta != null) {
                    paroleDaCancellare.add(parolaLetta);
                    flag = true;
                } else {
                    flag = false;
                }
            }
        } catch (IOException e) {
            System.out.println("Si è verificato un errore nella lettura del file paroleDaCancellare");
        }
        try {
            bf.close();
        } catch (IOException e) {
            System.out.println("Si è verificato un errore nella chiusura dello stream");
        }

        try {
            bf.close();
        } catch (IOException e) {
            System.out.println("Si è verificato un errore nella chiusura dello stream");
        }
    }

    private void avvaloraParoleConcesse()
    {
        FileReader fr = null;
        boolean flag = false;
        String parolaLetta;

        try {
            fr = new FileReader("/home/vito/Scrivania/Esame-MAP/Gioco_MAP/src/main/resources/paroleConcesse.txt");
        } catch (FileNotFoundException e) {
            System.out.println("si è verificato un errore con la gestione del file");
        }

        BufferedReader bf = new BufferedReader(fr);

        try {
            parolaLetta = bf.readLine();

            if (parolaLetta != null) {
                paroleConcesse.add(parolaLetta);
                flag = true;
            }

        } catch (IOException e) {
            System.out.println("Si è verificato un errore nella lettura del file paroleConcesse");

        }

        try {
            while (flag == true) {
                parolaLetta = bf.readLine();

                if (parolaLetta != null) {
                    paroleConcesse.add(parolaLetta);
                    flag = true;
                } else {
                    flag = false;
                }
            }
        } catch (IOException e) {
            System.out.println("Si è verificato un errore nella lettura del file paroleConcesse");
        }
        try {
            bf.close();
        } catch (IOException e) {
            System.out.println("Si è verificato un errore nella chiusura dello stream");
        }

        try {
            bf.close();
        } catch (IOException e) {
            System.out.println("Si è verificato un errore nella chiusura dello stream");
        }
    }

    private void avvaloraCasa()
    {
        Stanza stanza = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        boolean flag = false;

        try {
            fis = new FileInputStream("/home/vito/Scrivania/Esame-MAP/Gioco_MAP/src/main/resources/descrizioneStanze.txt");
        } catch (IOException e) {
            System.out.println("Si è verificato un errore con il file \"descizioneStanze.txt\" 1");
        }

        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            System.out.println("Si è verificato un errore con il file \"descizioneStanze.txt\" 2");
        }

        try {
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
        } catch (IOException e) {
            System.out.println("Si è verificato un errore");
        } catch (ClassNotFoundException k) {
            System.out.println("Si è verificato un errore");
        }

        try {
            fis.close();
        } catch (IOException e) {
            System.out.println("Si è verificato un errore nella chiusura dello stream");
        }

        try {
            ois.close();
        } catch (IOException e) {
            System.out.println("Si è verificato un errore nella chiusura dello stream");
        }

    }

    public Stanza ricercaStanzaCorrente()
    {
        Stanza stanza = null;


        Iterator<Stanza> it = casa.iterator();
        {
            while (it.hasNext() == true) {
                stanza = it.next();

                if (stanza.numeroStanza != stanzaCorrente) {
                    stanza = null;
                } else {
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
            while (it.hasNext() == true) {
                nuovaStanza = it.next();

                if (nuovaStanza.numeroStanza != numeroStanza) {
                    nuovaStanza = null;
                } else {
                    break;
                }

            }
        }
        return nuovaStanza;
    }

    private boolean controllaInvetario(Oggetti oggetto)
    {
        boolean trovato = false;

        if (oggetto == Oggetti.VUOTO) {
            return true;
        }

        for (int i = 0; i < 6; i++) {
            if (inventario[i] == oggetto) {
                trovato = true;
            }
        }

        if (trovato == true) {
            return true;
        } else {
            return false;
        }
    }

    public void raccoltaOggetto(String oggetto)
    {
        Stanza stanza = ricercaStanzaCorrente();

        if (evento.eventoInPausa == false) {
            if (ricercaStanzaCorrente().oggetto.name().equals(oggetto))  //il .name() permette la conversione da enumerativo a stringa
            {
                System.out.println(stanza.oggetto.name() + " è stato aggiunto all'invetario");

                for (int i = 0; i < 10; i++) {
                    if (inventario[i] == Oggetti.VUOTO) {
                        inventario[i] = stanza.oggetto;
                        casa.remove(stanza);
                        stanza.oggetto = Oggetti.VUOTO;
                        casa.add(stanza);
                        break;
                    }
                }
            } else {
                System.out.println("In questa stanza non è presente l'oggetto " + oggetto);
            }
        } else {
            System.out.println("Sei stato catturato dalla guardia");
            vivo = false;
        }

    }

    public void accendiLuce()
    {
        Stanza stanza = ricercaStanzaCorrente();

        if (evento.eventoInPausa == false)
        {
            if (stanza.luce == true)
            {
                System.out.println("In questa stanza la luce è già accesa");
            }
            else
            {
                if(stanzaCorrente == 11)
                {
                    //QUI VA IL MESSAGIO CHE LA LUCE E' ROTTA
                }
                else
                {
                    casa.remove(stanza);
                    stanza.luce = true;
                    casa.add(stanza);
                    System.out.println("Hai acceso la luce");
                }

            }
        }
        else
        {
            System.out.println("Sei stato catturato dalla guardia");
            vivo = false;
        }


    }

    public void spegniLuce()
    {
        Stanza stanza = ricercaStanzaCorrente();

        if (evento.eventoInPausa == false) {
            if (stanza.luce == false) {
                System.out.println("In questa stanza la luce è già spenta");
            } else {
                casa.remove(stanza);
                stanza.luce = false;
                casa.add(stanza);
                System.out.println("Hai spento la luce");
            }
        } else {
            System.out.println("Sei stato catturato dalla guardia");
            vivo = false;
        }


    }

    public void apriInvetario()
    {
        for (Oggetti oggetto : inventario) {
            System.out.println(oggetto);
        }
    }

    public void nascondi()
    {
        Stanza stanza = ricercaStanzaCorrente();

        if (stanza.armadio == true) {
            nascosto = true;
            System.out.println("Ti sei nascosto nell'aramdio");
        } else if (stanza.letto == true) {
            nascosto = true;
            System.out.println("Ti sei nascosto sotto il letto");
        } else {
            System.out.println("Non c'è nessun nascodiglio in questa stanza");
        }
    }

    public void usa(String oggetto)
    {
        Oggetti ogg;

        try {
            ogg = Oggetti.valueOf(oggetto);

            if (controllaInvetario(ogg))
            {
                if(stanzaCorrente == 11 && oggetto.compareTo("accendino") == 0)
                {
                    accendiLuce();
                }

                else
                {
                    System.out.println("Non ha senso usare questo oggetto qui");
                }

                if(nascosto = true && oggetto.compareTo("accendino") == 0)
                {
                    evento.eventoInFunzione = false;
                    System.out.println("La guradi è sistemata. Adesso non ci saranno più intoppi");
                }
            }
            else
            {
                System.out.print("L' oggetto che vuoi usare non è presente nel tuo inventario");
            }
        }
        catch (Exception e)
        {
            System.out.println("Oggetto che vuoi usare non è presente nell'inventario");
        }


    }

    public void muovi(String direzione)
    {
        Stanza stanza = ricercaStanzaCorrente();
        Stanza nuovaStanza = null;


        if (stanza.numeroStanza == 1 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(2);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(2);
                Dialoghi.stanza2();
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 1 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(3);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(3);
                Dialoghi.stanza3();
                Dialoghi.messaggioPresenzaAccendino(stanzaVisitata[2]);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }

        }

        else if (stanza.numeroStanza == 1 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(5);
                Dialoghi.stanza5();
                Dialoghi.messaggioPresenzaPentola(stanzaVisitata[4], evento.eventoInFunzione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 2 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(1);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(1);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 3 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(1);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(1);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 3 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(4);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(4);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);

                if (stanzaVisitata[3] == false)
                {
                    Dialoghi.messaggioInizionEvento();
                    evento.eventoInFunzione = true;
                    evento.start();
                }

                Dialoghi.messaggioPresenzaChivaCantina(stanzaVisitata[3]);
                stanzaVisitata[stanzaCorrente - 1] = true;

            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 4 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(7);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(7);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 4 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(5);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                Dialoghi.messaggioPresenzaPentola(stanzaVisitata[4], evento.eventoInFunzione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 4 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(3);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(3);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                Dialoghi.messaggioPresenzaAccendino(stanzaVisitata[2]);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(1);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(1);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(4);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(4);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);

                if (stanzaVisitata[3] == false)
                {
                    Dialoghi.messaggioInizionEvento();
                    evento.eventoInFunzione = true;
                    evento.start();
                }

                Dialoghi.messaggioPresenzaChivaCantina(stanzaVisitata[3]);
                stanzaVisitata[stanzaCorrente - 1] = true;

            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(6);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(6);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(12);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(12);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 6 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(5);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                Dialoghi.messaggioPresenzaPentola(stanzaVisitata[4], evento.eventoInFunzione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 6 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(7);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(7);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 6 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(11);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(11);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 7 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(6);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(6);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 7 && direzione.compareTo("sud") == 0) {
            nuovaStanza = ricercaStanzaPerNumero(4);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(4);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);

                if (stanzaVisitata[3] == false)
                {
                    Dialoghi.messaggioInizionEvento();
                    evento.eventoInFunzione = true;
                    evento.start();
                }

                Dialoghi.messaggioPresenzaChivaCantina(stanzaVisitata[3]);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 7 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(8);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(8);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 8 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(9);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(9);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 8 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(10);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(10);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                Dialoghi.messaggioPresenzaChiaveTesoro(stanzaVisitata[9]);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if(stanzaCorrente == 8 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(7);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(7);
                System.out.println("Sei entrato nella stanza: " + stanzaCorrente);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }
        }

        else if (stanza.numeroStanza == 9 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(8);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(8);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 10 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(8);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(8);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if(stanza.numeroStanza == 11 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(6);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(6);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                System.out.println("Per accedere a questa stanza hai bisogno di :" + nuovaStanza.oggettoRichiesto);
            }
        }

        else if(stanza.numeroStanza == 12 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(5);
                System.out.println("Sei entrato nella stanza: " + nuovaStanza.descrizione);
                stanzaVisitata[stanzaCorrente - 1] = true;
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

        stampaMappa();

        if (evento.eventoInFunzione == true && evento.eventoInPausa == true)
        {
            evento.eventoInPausa=false;
        }




    }

    public void stampaMappa()
    {
        if (stanzaCorrente == 1) {
            System.out.println("  ___ ___           ___ ___\n" +
                    " |   |   |         |   |   |\n" +
                    " |___|___|         |___|___|\n" +
                    " |   |   |         |   |\n" +
                    " |___|___|___      |___|\n" +
                    " |   |***|   |      ___    ___\n" +
                    " |___|***|___|     |   |  |   |\n" +
                    "                   |___|  |___|");
        } else if (stanzaCorrente == 2) {
            System.out.println("  ___ ___           ___ ___\n" +
                    " |   |   |         |   |   |\n" +
                    " |___|___|         |___|___|\n" +
                    " |   |   |         |   |\n" +
                    " |___|___|___      |___|\n" +
                    " |   |   |***|      ___    ___\n" +
                    " |___|___|***|     |   |  |   |\n" +
                    "                   |___|  |___|");
        } else if (stanzaCorrente == 3) {
            System.out.println("  ___ ___           ___ ___\n" +
                    " |   |   |         |   |   |\n" +
                    " |___|___|         |___|___|\n" +
                    " |   |   |         |   |\n" +
                    " |___|___|___      |___|\n" +
                    " |***|   |   |      ___    ___\n" +
                    " |***|___|___|     |   |  |   |\n" +
                    "                   |___|  |___|");
        } else if (stanzaCorrente == 4) {
            System.out.println("  ___ ___           ___ ___\n" +
                    " |   |   |         |   |   |\n" +
                    " |___|___|         |___|___|\n" +
                    " |***|   |         |   |\n" +
                    " |***|___|___      |___|\n" +
                    " |---|   |   |      ___    ___\n" +
                    " |___|___|___|     |   |  |   |\n" +
                    "                   |___|  |___|");
        } else if (stanzaCorrente == 5) {
            System.out.println("  ___ ___           ___ ___\n" +
                    " |   |   |         |   |   |\n" +
                    " |___|___|         |___|___|\n" +
                    " |   |***|         |   |\n" +
                    " |___|***|___      |___|\n" +
                    " |   |---|   |      ___    ___\n" +
                    " |___|___|___|     |   |  |   |\n" +
                    "                   |___|  |___|\n");
        } else if (stanzaCorrente == 6) {
            System.out.println("  ___ ___           ___ ___\n" +
                    " |   |***|         |   |   |\n" +
                    " |___|***|         |___|___|\n" +
                    " |   |---|         |   |\n" +
                    " |___|___|___      |___|\n" +
                    " |   |   |   |      ___    ___\n" +
                    " |___|___|___|     |   |  |   |\n" +
                    "                   |___|  |___|");
        } else if (stanzaCorrente == 7) {
            System.out.println(" ___ ___           ___ ___\n" +
                    " |***|   |         |   |   |\n" +
                    " |***|___|         |___|___|\n" +
                    " |---|   |         |   |\n" +
                    " |___|___|___      |___|\n" +
                    " |   |   |   |      ___    ___\n" +
                    " |___|___|___|     |   |  |   |\n" +
                    "                   |___|  |___|");
        } else if (stanzaCorrente == 8) {
            System.out.println(" ___ ___           ___ ___\n" +
                    " |   |   |         |***|   |\n" +
                    " |___|___|         |***|___|\n" +
                    " |   |   |         |---|\n" +
                    " |___|___|___      |___|\n" +
                    " |   |   |   |      ___    ___\n" +
                    " |___|___|___|     |   |  |   |\n" +
                    "                   |___|  |___|");
        } else if (stanzaCorrente == 9) {
            System.out.println(" ___ ___           ___ ___\n" +
                    " |   |   |         |   |***|\n" +
                    " |___|___|         |___|***|\n" +
                    " |   |   |         |   |---\n" +
                    " |___|___|___      |___|\n" +
                    " |   |   |   |      ___    ___\n" +
                    " |___|___|___|     |   |  |   |\n" +
                    "                   |___|  |___|");
        } else if (stanzaCorrente == 10) {
            System.out.println(" ___ ___           ___ ___\n" +
                    " |   |   |         |   |   |\n" +
                    " |___|___|         |___|___|\n" +
                    " |   |   |         |***|\n" +
                    " |___|___|___      |***|\n" +
                    " |   |   |   |      ___    ___\n" +
                    " |___|___|___|     |   |  |   |\n" +
                    "                   |___|  |___|\n");
        } else if (stanzaCorrente == 11) {
            System.out.println(" ___ ___           ___ ___\n" +
                    " |   |   |         |   |   |\n" +
                    " |___|___|         |___|___|\n" +
                    " |   |   |         |   |\n" +
                    " |___|___|___      |___|\n" +
                    " |   |   |   |      ___    ___\n" +
                    " |___|___|___|     |***|  |   |\n" +
                    "                   |***|  |___|");
        } else if (stanzaCorrente == 12) {
            System.out.println("\n" +
                    "  ___ ___           ___ ___\n" +
                    " |   |   |         |   |   |\n" +
                    " |___|___|         |___|___|\n" +
                    " |   |   |         |   |\n" +
                    " |___|___|___      |___|\n" +
                    " |   |   |   |      ___    ___\n" +
                    " |___|___|___|     |   |  |***|\n" +
                    "                   |___|  |***|");
        }
    }

    public void caricaSalvataggio()
    {
        GameManager nuovaPartita = null;

        nuovaPartita = salvataggio.caricaSalvataggio();

        for (int i = 0; i < 12; i++) {
            stanzaVisitata[i] = nuovaPartita.stanzaVisitata[i];
        }

        for (int i = 0; i < 6; i++) {
            inventario[i] = nuovaPartita.inventario[i];
        }

        nascosto = nuovaPartita.nascosto;
        vivo = nuovaPartita.vivo;
        stanzaCorrente = nuovaPartita.stanzaCorrente;

        if (nuovaPartita.stanzaVisitata[3] == true)
        {
            evento.start();
        }
    }

    public void salvaPartita()
    {
        salvataggio = new GestioneSalvataggio();
        File fileDaCancellare = new File ("/home/vito/Scrivania/Esame-MAP/Gioco_MAP/src/main/resources/descrizioneStanze.txt");

        salvataggio.inserimentoSalvataggioInTabella(nascosto, stanzaCorrente, vivo, inventario, stanzaVisitata);

        if (fileDaCancellare.exists() == true)
        {
            fileDaCancellare.delete();

            creaNuovoFileDescrizioneStaza();
        }
    }

    private void creaNuovoFileDescrizioneStaza()
    {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        Iterator<Stanza> it ;

        try
        {
            fos = new FileOutputStream("/home/vito/Scrivania/Esame-MAP/Gioco_MAP/src/main/resources/descrizioneStanze.txt");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File non trovato");
        }

        try
        {
            oos = new ObjectOutputStream(fos);
        }
        catch (IOException e)
        {
            System.out.println("Si è verofocato un errore nell salvataggio 1");
        }

        it = casa.iterator();

        while (it.hasNext())
        {
            try
            {
                oos.writeObject(it.next());
            }
            catch(IOException e)
            {
                System.out.println("Si è verificato un errore nel salvataggio 2");
            }
        }

    }
}

