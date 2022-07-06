package logica;

import outputUtente.Dialoghi;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.io.FileReader;
import java.io.BufferedReader;

public class GameManager {

    final int DIMENSIONE_INVENTARIO = 6;
    final int NUMERO_STANZE = 12;
    public boolean nascosto = false;
    public int stanzaCorrente;
    public boolean vivo;
    public Oggetti[] inventario = new Oggetti[DIMENSIONE_INVENTARIO];
    public boolean[] stanzaVisitata = new boolean[NUMERO_STANZE];
    public Set<Stanza> casa = new HashSet<>();
    public Set<String> paroleConcesse = new HashSet<>();
    public Set<String> paroleDaCancellare = new HashSet<>();
    Evento evento;
    GestioneSalvataggio salvataggio;


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
            parolaLetta = bf.readLine();

            if (parolaLetta != null) {
               in.inserisci(parolaLetta);
                flag = true;
            }

        } catch (IOException e) {
            Dialoghi.erroreLetturaParoleDaCancellare();

        }

        try {
            while (flag == true) {
                parolaLetta = bf.readLine();

                if (parolaLetta != null) {
                    in.inserisci(parolaLetta);
                    flag = true;
                } else {
                    flag = false;
                }
            }
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

        try {
            parolaLetta = bf.readLine();

            if (parolaLetta != null) {
                in.inserisci(parolaLetta);
                flag = true;
            }

        } catch (IOException e) {
            Dialoghi.erroreLetturaParoleConcesse();

        }

        try {
            while (flag == true) {
                parolaLetta = bf.readLine();

                if (parolaLetta != null) {
                    in.inserisci(parolaLetta);
                    flag = true;
                } else {
                    flag = false;
                }
            }
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

    private void avvaloraCasa(Inseritore<Stanza> in)
    {
        Stanza stanza = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        boolean flag = false;

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
        catch (ClassNotFoundException k)
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

    private Stanza ricercaStanzaCorrente()
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


        if (evento.eventoInPausa == false)
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

    public void accendiLuce()
    {
        Stanza stanza = ricercaStanzaCorrente();

        if (evento.eventoInPausa == false)
        {
            if (stanza.luce == true)
            {
                Dialoghi.luceGiaAccesa();
            }
            else
            {
                if(stanzaCorrente == 11 && stanza.luce == false)
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

    public void spegniLuce()
    {
        Stanza stanza = ricercaStanzaCorrente();

        if (evento.eventoInPausa == false)
        {
            if (stanzaCorrente == 11)
            {
                Dialoghi.luceGuasta();
            }
            else
            {
                if (stanza.luce == false)
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
            Dialoghi.nascostoArmadio();
        } else if (stanza.letto == true) {
            nascosto = true;
            Dialoghi.nascostoLetto();
        } else {
            Dialoghi.nessunNascondiglio();
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
                    Dialoghi.stanza11();
                }

                else if ((stanzaCorrente != 11 && (oggetto.compareTo("accendino") == 0) || oggetto.compareTo("padella") == 0))
                {
                    Dialoghi.nonPuoiUsare();
                }

                else if(nascosto = true && oggetto.compareTo("padella") == 0)
                {
                    evento.interrupt();
                    Dialoghi.guardiaAbbattuta();
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
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 1 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(3);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(3);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }

        }

        else if (stanza.numeroStanza == 1 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(5);
                Dialoghi.messaggioPresenzaPadella(nuovaStanza, evento.eventoInFunzione);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 2 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(1);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(1);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 3 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(1);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(1);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 3 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(4);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(4);

                if (nuovaStanza.luce == false)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza4();
                    Dialoghi.messaggioPresenzaChiaveCantina(nuovaStanza);
                }

                if (stanzaVisitata[3] == false)
                {
                    Dialoghi.messaggioInizioEvento();
                    evento.eventoInFunzione = true;
                    evento.start();
                }

                stanzaVisitata[stanzaCorrente - 1] = true;

            }

            else
            {
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 4 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(7);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(7);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 4 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(5);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 4 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(3);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(3);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(1);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(1);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(4);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(4);

                if (nuovaStanza.luce == false)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza4();
                    Dialoghi.messaggioPresenzaChiaveCantina(nuovaStanza);
                }

                if (stanzaVisitata[3] == false)
                {
                    Dialoghi.messaggioInizioEvento();
                    evento.eventoInFunzione = true;
                    evento.start();
                }

                stanzaVisitata[stanzaCorrente - 1] = true;

            }

            else
            {
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(6);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(6);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 5 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(12);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(12);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 6 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(5);
                Dialoghi.messaggioPresenzaPadella(nuovaStanza, evento.eventoInFunzione);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 6 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(7);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(7);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 6 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(11);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(11);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 7 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(6);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(6);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 7 && direzione.compareTo("sud") == 0) {
            nuovaStanza = ricercaStanzaPerNumero(4);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(4);
                Dialoghi.stanza4();

                if (nuovaStanza.luce == false)
                {
                    Dialoghi.luceSpenta();
                }

                else
                {
                    Dialoghi.stanza4();
                    Dialoghi.messaggioPresenzaChiaveCantina(nuovaStanza);
                }

                if (stanzaVisitata[3] == false)
                {
                    Dialoghi.messaggioInizioEvento();
                    evento.eventoInFunzione = true;
                    evento.start();
                }

                stanzaVisitata[stanzaCorrente - 1] = true;
            }

            else
            {
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 7 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(8);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(8);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 8 && direzione.compareTo("est") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(9);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(9);
                stanzaVisitata[stanzaCorrente - 1] = true;

                Dialoghi.portaSbloccata();


                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 8 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(10);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(10);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if(stanzaCorrente == 8 && direzione.compareTo("ovest") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(7);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(7);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(8);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if (stanza.numeroStanza == 10 && direzione.compareTo("nord") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(8);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(8);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if(stanza.numeroStanza == 11 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(6);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(6);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else if(stanza.numeroStanza == 12 && direzione.compareTo("sud") == 0)
        {
            nuovaStanza = ricercaStanzaPerNumero(5);

            if (controllaInvetario(nuovaStanza.oggettoRichiesto) == true)
            {
                stanzaCorrente = nuovaStanza.numeroStanza;
                evento.cambiaStanzaCorrente(5);
                stanzaVisitata[stanzaCorrente - 1] = true;

                if (nuovaStanza.luce == false)
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
                Dialoghi.necessitiDi();
                System.out.print(nuovaStanza.oggettoRichiesto);
            }
        }

        else
        {
            Dialoghi.portaInesistente();
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
        GameManager nuovaPartita;
        GestioneSalvataggio salvataggio = new GestioneSalvataggio();

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
            if(evento.eventoInFunzione == false)
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

    public void salvaPartita()
    {
        salvataggio = new GestioneSalvataggio();
        File fileDaCancellare = new File ("/home/vito/Scrivania/Esame-MAP/Gioco_MAP/src/main/resources/descrizioneStanze.txt");

        salvataggio.inserimentoSalvataggioInTabella(nascosto, stanzaCorrente, vivo, inventario, stanzaVisitata, evento.eventoInFunzione);

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

