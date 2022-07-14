package logica;

import outputUtente.Dialoghi;

import java.io.*;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;

public class GameManager {

    final private int DIMENSIONE_INVENTARIO = 6;
    final private int NUMERO_STANZE = 12;
    public boolean nascosto = false;
    public int stanzaCorrente;
    public boolean vivo;
    public Oggetti[] inventario = new Oggetti[DIMENSIONE_INVENTARIO];
    public boolean[] stanzaVisitata = new boolean[NUMERO_STANZE];
    private List<Stanza> casa = new ArrayList<>();
    public Set<String> paroleConcesse = new HashSet<>();
    public Set<String> paroleDaCancellare = new HashSet<>();
    Evento evento;
    GestioneSalvataggio salvataggio;

    /**
     * Costruttore della classe GameManager che inzializza le variabili di istanza
     */
    public GameManager()
    {
        stanzaCorrente = 1;
        inizializzaInventario();
        inizializzaStanzaVisitata();
        avvaloraParoleDaCancellare((String s) -> paroleDaCancellare.add(s));
        avvaloraParoleConcesse((String s) -> paroleConcesse.add(s));
        avvaloraCasa((Stanza s) -> casa.add(s));
        evento = new Evento();
        vivo = true;
        stanzaVisitata[0] = true;
    }

    /**
     * Iniziallizza l'array stanzaVisitata con valori FALSE
     */
    private void inizializzaStanzaVisitata()
    {
        for (int i = 0; i < 12; i++) {
            stanzaVisitata[i] = false;
        }
    }

    /**
     * Inizializza l'array invetario con valori Oggetti.VUOTO
     */
    private void inizializzaInventario()
    {
        for (int i = 0; i < 6; i++) {
            inventario[i] = Oggetti.VUOTO;

        }
    }

    /**
     * Avvalora l'insieme paroleDaCancellare attraverso il file paroleDaCancellare.txt
     * @param in
     */
    private void avvaloraParoleDaCancellare(Inseritore<String> in)
    {
        FileReader fr = null;
        boolean flag = false;
        String parolaLetta;

        try {
            fr = new FileReader("./Gioco_MAP/src/main/resources/paroleDaCancellare.txt");
        } catch (FileNotFoundException e) {
            Dialoghi.erroreGestioneFile();
        }

        BufferedReader bf = new BufferedReader(fr);

        try {

            do
            {
                parolaLetta = bf.readLine();

                if (parolaLetta != null)
                {
                    in.inserisci(parolaLetta);
                    flag = true;
                }
                else
                {
                    flag = false;
                }
            } while (flag);

        } catch (IOException e) {
            Dialoghi.erroreLetturaParoleDaCancellare();

        }

        try {
            bf.close();
        } catch (IOException e) {
            Dialoghi.erroreStream();
        }

        try {
            bf.close();
        } catch (IOException e) {
            Dialoghi.erroreStream();
        }
    }

    /**
     * Avvalora l'insieme paroleConcesse attraverso il file paroleConcesse.txt
     * @param in
     */
    private void avvaloraParoleConcesse(Inseritore<String> in)
    {
        FileReader fr = null;
        boolean flag = false;
        String parolaLetta;

        try {
            fr = new FileReader("./Gioco_MAP/src/main/resources/paroleConcesse.txt");
        } catch (FileNotFoundException e) {
            Dialoghi.erroreGestioneFile();
        }

        BufferedReader bf = new BufferedReader(fr);

        try
        {
            do
            {
                parolaLetta = bf.readLine();

                if (parolaLetta != null)
                {
                    in.inserisci(parolaLetta);
                    flag = true;
                }
                else
                {
                    flag = false;
                }
            }while(flag);


        } catch (IOException e) {
            Dialoghi.erroreLetturaParoleConcesse();

        }

        try {
            bf.close();
        } catch (IOException e) {
            Dialoghi.erroreStream();
        }

        try {
            bf.close();
        } catch (IOException e) {
            Dialoghi.erroreStream();
        }
    }

    /**
     * Avvalore l'insieme casa attraverso il file binario DescrizioneStanze
     * @param in
     */
    private void avvaloraCasa(Inseritore<Stanza> in)
    {
        Stanza stanza = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try
        {
            Class.forName("logica.Stanza");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("non worka");
        }


        try
        {
            fis = new FileInputStream("./Gioco_MAP/src/main/resources/DescrizioneStanze");
        }
        catch (IOException e)
        {
            Dialoghi.erroreLetturaDescrizioneStanze();
        }

        try
        {
            ois = new ObjectInputStream(fis);
        }
        catch (IOException e)
        {
            Dialoghi.erroreLetturaDescrizioneStanze();
        }

        try {
            for(int i = 0; i<12; i++)
            {
                stanza = (Stanza) ois.readObject();
                in.inserisci(stanza);
            }
        }
        catch (IOException e)
        {
            Dialoghi.errore();
        }
        catch (ClassNotFoundException k )
        {
            Dialoghi.errore();
        }

        try
        {
            fis.close();
        } catch (IOException e)
        {
            Dialoghi.erroreStream();
        }

        try
        {
            ois.close();
        }
        catch (IOException e)
        {
            Dialoghi.erroreStream();
        }

    }

    /**
     * Ricerca all'interno dell'insieme casa l'instanza di Stanza che ha l'attributo Stanza.numeroStanza
     * uguale al valore della variabile stanzaCorrente
     * @return
     */
    private Stanza ricercaStanzaCorrente()
    {
        Stanza stanza = null;


        Iterator<Stanza> it = casa.iterator();
        {
            while (it.hasNext()) {
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

    /**
     * Ricerca all'interno dell'insieme casa l'instanza di Stanza che l'attributo Stanza.numeroStanza
     * uguale al valore passato al metddo
     * @param numeroStanza
     * @return
     */
    private Stanza ricercaStanzaPerNumero(int numeroStanza)
    {
        Stanza nuovaStanza = null;


        Iterator<Stanza> it = casa.iterator();
        {
            while (it.hasNext()) {
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

    /**
     * Controlla se nell'array invetario è presente l'oggetto passato in input al metodo
     * @param oggetto
     * @return
     */
    private boolean controllaInvetario(Oggetti oggetto)
    {
        boolean trovato = false;

        if (oggetto == Oggetti.VUOTO) {
            return true;
        }

        for (int i = 0; i < 6; i++) {
            if (inventario[i] == oggetto) {
                trovato = true;
                break;
            }
        }

        return trovato;
    }

    /**
     * Insersice all'interno dell'array invetario l'oggetto indicato dalla stringa passato in input
     * @param oggetto
     */
    public void raccoltaOggetto(String oggetto)
    {
        Stanza stanza = ricercaStanzaCorrente();

        if(stanzaCorrente == 4 && oggetto.compareTo("chiave") == 0)
        {
            oggetto = "CHIAVE_CANTINA";
        }

        if(stanzaCorrente == 10 && oggetto.compareTo("chiave") == 0)
        {
            oggetto = "CHIAVE_TESORO";
        }

        if(stanzaCorrente == 3 && oggetto.compareTo("accendino") == 0)
        {
            oggetto = "ACCENDINO";
        }

        if (stanzaCorrente == 5 && oggetto.compareTo("padella") == 0)
        {
            oggetto = "PADELLA";
        }

        if (stanzaCorrente == 9 && oggetto.compareTo("fedora") == 0)
        {
            oggetto = "FEDORA";
            Dialoghi.fine();
            vivo = false;
        }


        if (!evento.eventoInPausa)
        {
            if (ricercaStanzaCorrente().oggetto.name().equals(oggetto))  //il .name() permette la conversione da enumerativo a stringa
            {
                System.out.print(stanza.oggetto.name());
                Dialoghi.aggiuntoInventario();

                for (int i = 0; i < 10; i++) {
                    if (inventario[i] == Oggetti.VUOTO) {
                        inventario[i] = stanza.oggetto;
                        casa.remove(stanza);
                        stanza.oggetto = Oggetti.VUOTO;
                        casa.add(stanza);
                        break;
                    }
                }

                if(stanzaCorrente == 9 && oggetto.compareTo("fedora")==0)
                {
                    Dialoghi.fine();
                    vivo = false;
                }
            }
            else
            {
                Dialoghi.oggettoNonPresente();
                System.out.print(oggetto);
            }
        }
        else
        {
            Dialoghi.catturato();
            vivo = false;
        }

    }

    /**
     * Imposta su TRUE il valore Stanza.luce dell'instaza di Stanza che rappresente la stanza corrente
     */
    public void accendiLuce()
    {
        Stanza stanza = ricercaStanzaCorrente();

        if (!evento.eventoInPausa)
        {
            if (stanza.luce)
            {
                Dialoghi.luceGiaAccesa();
            }
            else
            {
                if(stanzaCorrente == 11)
                {
                    Dialoghi.luceGuasta();
                }
                else
                {
                    casa.remove(stanza);
                    stanza.luce = true;
                    casa.add(stanza);
                    Dialoghi.luceAccesa();

                    switch (stanzaCorrente)
                    {
                        case 1: Dialoghi.stanza1();
                            break;

                        case 2: Dialoghi.stanza2();
                            break;

                        case 3: Dialoghi.stanza3();
                            break;

                        case 4: Dialoghi.stanza4();
                            break;

                        case 5: Dialoghi.stanza5();
                            break;

                        case 6: Dialoghi.stanza6();
                            break;

                        case 7: Dialoghi.stanza7();
                            break;

                        case 8: Dialoghi.stanza8();
                            break;

                        case 9: Dialoghi.stanza9();
                            break;

                        case 10: Dialoghi.stanza10();
                                 Dialoghi.messaggioPresenzaChiaveTesoro(stanza);
                            break;

                        case 11: Dialoghi.stanza11();
                            break;

                        case 12: Dialoghi.stanza12();
                            break;
                    }
                }

            }
        }
        else
        {
            Dialoghi.catturato();
            vivo = false;
        }


    }

    /**
     * Imposta du FALSE il valore Stanza.luce dell'instanza di Stanza che rappresenta la stanza corrente
     */
    public void spegniLuce()
    {
        Stanza stanza = ricercaStanzaCorrente();

        if (!evento.eventoInPausa)
        {
            if (stanzaCorrente == 11)
            {
                Dialoghi.luceGuasta();
            }
            else
            {
                if (!stanza.luce)
                {
                    Dialoghi.luceGiaSpenta();
                }
                else
                {
                    casa.remove(stanza);
                    stanza.luce = false;
                    casa.add(stanza);
                    Dialoghi.spegniLuce();
                }
            }
        }
        else
        {
            Dialoghi.catturato();
            vivo = false;
        }


    }

    /**
     * Stampa a schermo tutti gli oggetti presenti nell'inventario
     */
    public void apriInvetario()
    {
        for (Oggetti oggetto : inventario) {
            System.out.println(oggetto);
        }
    }

    /**
     * Se nella stanza attuale è presente un nascondiglio imposta su TRUE
     * l'attributo nascosto
     */
    public void nascondi()
    {
        Stanza stanza = ricercaStanzaCorrente();

        if (stanza.armadio) {
            nascosto = true;
            Dialoghi.nascostoArmadio();
        } else if (stanza.letto) {
            nascosto = true;
            Dialoghi.nascostoLetto();
        } else {
            Dialoghi.nessunNascondiglio();
        }
    }

    /**
     * Serve per usare un oggetto presente nell'inventario
     * @param oggetto
     */
    public void usa(String oggetto)
    {
        Oggetti ogg;

        try {
            ogg = Oggetti.valueOf(oggetto.toUpperCase());

            if (controllaInvetario(ogg))
            {
                if(stanzaCorrente == 11 && oggetto.compareTo("accendino") == 0)
                {
                    Dialoghi.stanza11();
                }

                else if(nascosto && oggetto.compareTo("padella") == 0)
                {
                    evento.interrupt();
                    Dialoghi.guardiaAbbattuta();
                }

                else
                {
                    Dialoghi.nonPuoiUsare();
                }
            }
            else
            {
                Dialoghi.oggettoNonPresenteInInventario();
            }
        }
        catch (Exception e)
        {
            Dialoghi.oggettoNonPresenteInInventario();
        }


    }

    /**
     * Permette di cambiare stanza in base ad una direzione indicata dalla stringa in input
     * @param direzione
     */
    public void muovi(String direzione)
    {
        Stanza stanza = ricercaStanzaCorrente();
        Stanza nuovaStanza;

        if (stanza.numeroStanza == 1 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(2);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(2);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza2();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);

            }
        }

        else if (stanza.numeroStanza == 1 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(3);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(3);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza3();
                    Dialoghi.messaggioPresenzaAccendino(nuovaStanza);
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }

        }

        else if (stanza.numeroStanza == 1 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(5);
                Dialoghi.messaggioPresenzaPadella(nuovaStanza, evento.eventoInFunzione);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza5();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 2 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(1);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(1);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza1();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 3 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(1);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(1);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza1();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 3 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(4);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(4);

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza4();
                    Dialoghi.messaggioPresenzaChiaveCantina(nuovaStanza);
                }

                if (!stanzaVisitata[3])
                {
                    Dialoghi.messaggioInizioEvento();
                    evento.eventoInFunzione = true;
                    evento.start();
                }

                stanzaVisitata[stanzaCorrente - 1] = true;

            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 4 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(7);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(7);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza7();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 4 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(5);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza5();
                    Dialoghi.messaggioPresenzaPadella(nuovaStanza, evento.eventoInFunzione);
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 4 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(3);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(3);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza3();
                    Dialoghi.messaggioPresenzaAccendino(nuovaStanza);
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(1);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(1);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza1();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(4);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(4);

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza4();
                    Dialoghi.messaggioPresenzaChiaveCantina(nuovaStanza);
                }

                if (!stanzaVisitata[3])
                {
                    Dialoghi.messaggioInizioEvento();
                    evento.eventoInFunzione = true;
                    evento.start();
                }

                stanzaVisitata[stanzaCorrente - 1] = true;

            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(6);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(6);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza6();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(12);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(12);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza12();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 6 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(5);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza5();
                    Dialoghi.messaggioPresenzaPadella(nuovaStanza, evento.eventoInFunzione);
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 6 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(7);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(7);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza7();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 6 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(11);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(11);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza11();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 7 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(6);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(6);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza6();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 7 && direzione.compareTo("sud") == 0) {
            nuovaStanza = ricercaStanzaPerNumero(4);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(4);
                Dialoghi.stanza4();

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza4();
                    Dialoghi.messaggioPresenzaChiaveCantina(nuovaStanza);
                }

                if (!stanzaVisitata[3])
                {
                    Dialoghi.messaggioInizioEvento();
                    evento.eventoInFunzione = true;
                    evento.start();
                }

                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 7 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(8);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(8);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza8();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 8 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(9);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(9);
                stanzaVisitata[stanzaCorrente - 1] = true;

                Dialoghi.portaSbloccata();


                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza9();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 8 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(10);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(10);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza10();
                    Dialoghi.messaggioPresenzaChiaveTesoro(nuovaStanza);
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if(stanzaCorrente == 8 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(7);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(7);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza7();
                }
            }
        }

        else if (stanza.numeroStanza == 9 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(8);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(8);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza9();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if (stanza.numeroStanza == 10 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(8);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(8);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza8();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if(stanza.numeroStanza == 11 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(6);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(6);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza6();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else if(stanza.numeroStanza == 12 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto))
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(5);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (!nuovaStanza.luce)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza5();
                }
            }

            else
            {
                Dialoghi.necessitiDi(nuovaStanza);
            }
        }

        else
        {
            Dialoghi.portaInesistente();
        }

        if(nascosto)
        {
            nascosto = false;
        }

        stampaMappa();

        if (evento.eventoInFunzione && evento.eventoInPausa)
        {
            evento.eventoInPausa=false;
        }




    }

    /**
     * Stampa a schermo la posizione attuale attraverso una mappa
     */
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

    /**
     * Permette di riprendere la partita dal punto dove si è salvato il gioco
     */
    public void caricaSalvataggio()
    {
        GameManager nuovaPartita;
        GestioneSalvataggio salvataggio = new GestioneSalvataggio();

        nuovaPartita = salvataggio.caricaSalvataggio();

        System.arraycopy(nuovaPartita.stanzaVisitata, 0, stanzaVisitata, 0, 12);

        System.arraycopy(nuovaPartita.inventario, 0, inventario, 0, 6);

        nascosto = nuovaPartita.nascosto;
        vivo = nuovaPartita.vivo;
        stanzaCorrente = nuovaPartita.stanzaCorrente;

        if (nuovaPartita.stanzaVisitata[3])
        {
            if(!evento.eventoInFunzione)
            {
                evento.eventoInFunzione = true;
                evento.start();
            }

        }

        switch (stanzaCorrente)
        {
            case 1: Dialoghi.stanza1();
            break;

            case 2: Dialoghi.stanza2();
                break;

            case 3: Dialoghi.stanza3();
                break;

            case 4: Dialoghi.stanza4();
                break;

            case 5: Dialoghi.stanza5();
                break;

            case 6: Dialoghi.stanza6();
                break;

            case 7: Dialoghi.stanza7();
                break;

            case 8: Dialoghi.stanza9();
                break;

            case 10: Dialoghi.stanza11();
                break;

            case 11: Dialoghi.stanza11();
                break;

            case 12: Dialoghi.stanza12();
                break;
        }

        stampaMappa();
    }

    /**
     * Permette di salvare lo stato attuale della partita in un DB locale
     */
    public void salvaPartita()
    {
        salvataggio = new GestioneSalvataggio();
        File fileDaCancellare = new File ("./Gioco_MAP/src/main/resources/DescrizioneStanze");

        salvataggio.inserimentoSalvataggioInTabella(nascosto, stanzaCorrente, vivo, inventario, stanzaVisitata, evento.eventoInFunzione);

        if (fileDaCancellare.exists())
        {
            fileDaCancellare.delete();

            creaNuovoFileDescrizioneStaza();
        }
    }

    /**
     * Crea un nuovo file DescrizioneStanza aggiornato durante l'operaizone di salvataggio
     */
    private void creaNuovoFileDescrizioneStaza()
    {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        Iterator<Stanza> it ;

        try
        {
            fos = new FileOutputStream("./Gioco_MAP/src/main/resources/DescrizioneStanze");
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
            System.out.println("Si è verificato un errore nell salvataggio 1");
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

