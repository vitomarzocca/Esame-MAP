package outputUtente;

import logica.Oggetti;
import logica.Stanza;

public class Dialoghi {

    /**
     * Metodo statico che stampa un messaggio a schermo che norifica la presenza di una chiave
     * @param stanza
     */
    public static void messaggioPresenzaChiaveCantina(Stanza stanza)
    {
        if(stanza.oggetto == Oggetti.CHIAVE_CANTINA)
        {
            System.out.println("Sul tavolo e' presente una chiave. Prima non l'ho notata.");
            System.out.println("Potrebbe risualtare utile raccoglierla");
        }
    }

    /**
     * Metodo statico che stampa un messaggio a schermo che notifica la presenza di un accendino
     * @param stanza
     */
    public static void messaggioPresenzaAccendino(Stanza stanza)
    {
        if(stanza.oggetto == Oggetti.ACCENDINO)
        {
            System.out.println("Qualcuno ha dimenticato sul divano un accendino.");
            System.out.println("Meglio prenderselo."
                    + " Un accendino puo' essere sempre utile");
        }
    }

    /**
     * Metodo statico che stampa un messaggio a schermo che notifica la presenza di una padella,
     * ma solo se l'evento è stato attivato
     * @param stanza
     * @param eventoTipoCheCammina
     */
    public static void messaggioPresenzaPadella(Stanza stanza, boolean eventoTipoCheCammina)
    {
        if(stanza.oggetto == Oggetti.PADELLA && eventoTipoCheCammina)
        {
            System.out.println("Devo trovare un qualcosa da"
                    + " usare per difendermi da quel tipo");
            System.out.println("Questa padella dovrebbe andare bene");
        }
    }

    /**
     * Metodo statico che stampa un messaggio a schermo che notifica la presenza di una chiave
     * @param stanza
     */
    public static void messaggioPresenzaChiaveTesoro(Stanza stanza)
    {
        if (stanza.oggetto == Oggetti.CHIAVE_TESORO)
        {
            System.out.println("Si intravede una chiave appesa in una teca"
                    + " incastonata nel comodino sinistro ");

        }
    }

    /**
     * Metodo statico che stampa un messaggio a schermo che noifica l'inzio dell'evento
     */
    public static void messaggioInizioEvento()
    {
        System.out.println("\n");
        System.out.println("ATTENZIONE E' APPENA ARRIVATA UN AUTO");
        System.out.println("NON FARTI BECCARE");
    }

    /**
     * Metodo statico che stampa a schermo il prologo della storia
     */
    public static void prologo(){
        System.out.println("Prologo:");
        System.out.println("20 giorni prima");
        System.out.println("Una giornata tranquilla nella chiassosa Brooklyn, Johnny -o mafius- si sta" +
                " ritirando a casa da una serena cena con i colleghi mafiosi.");
        System.out.println("Johnny era sempre stato uno a cui piaceva apparire e piaceva" +
                " farlo con stile, ma questa cosa gli si ritorse contro.");
        System.out.println("Appena uscito dal ristorante del suo miglior amico e" +
                " collega Vivio Marzalma,si ritrovo' con le spalle al muro in un vicoletto li' vicino...");
        System.out.println("Una banda di scapestrati mandati da qualcuno l aveva gia'" +
                " accerchiato e sembrava fossero interessati solo al derubarlo anche a costo di non lasciarlo in vita.");
        System.out.println("-Davvero strano- penso' Johnny mentre guardava negli occhi" +
                " i suoi malfattori, -guardano tutti la mia fedora di al capone, saranno interessati davvero a quella?-");
        System.out.println("Non fece in tempo a pensare un alternativa" +
                " per scappare che senti' una botta e poi tutto nero.");
        }
    /**
     * Metodo statico che stampa a schermo l'obiettivo della missione
     */
    public static void obiettivo(){
        System.out.println("\n");
        System.out.println("Missione: Il Furto");
        System.out.println("Obiettivo: Recupera LA FEDORA");
    }

    /**
     * Stampa a schermo il prologo della storia
     */
    public static void oggi(){
            System.out.println("\n");
            System.out.println("Oggi:");
            System.out.println("Johnny si e' svegliato con una chiamata di Vivio: -Johnny ma che cazzo stai facendo!?!-");
            System.out.println("-Hey Vivio mi sono appena svegliato,sto nel letto-");
            System.out.println("-Coglione sveglia non c'e' tempo da perdere!!!-");
            System.out.println("-Dimmi che e' quello che penso...- disse Johnny con gli occhi di fuoco.");
            System.out.println("-Paparino sta tornando!-");
    }

    /**
     * Stampa il continuo del prologo della storia
     */
    public static void piuTardi(){
        System.out.println("--Partenza per la casa del boss--");
        System.out.println("Johnny e' pronto per riprendersi...");
        System.out.println("tutt' chell che e' o soi");
    }

    /**
     * Stampa l'epilogo delle storia
     */
    public static void fine()
    {
          System.out.println("Fine:");  
          System.out.println("Johnny rivide il suo cappello e si mise a piangere"
                  + "dalla gioia,era cosi felice che si fece"
                  + " sentire dalla guardia del boss, che lo trovo' e gli sparo'"
                  + "dritto nel cuore.");
          System.out.println("Questa era la storia di un cappello,"
                  + " di mafia e di amicizia");
          System.out.println("Ma quando la seconda e' di mezzo,"
                  + "ci si dovrebbe aspettare un morto. Eh."
                  + "Non tutte le storie finiscono bene.");
        }

    /**
     * Stampa a schermo un messaggio per chiedere al giocatore cosa vuole fare
     */
    public static void attesaInput(){
            System.out.println("\nCOSA VUOI FARE :");
        }
    /**
     * Metodo statico che stampa a schermo la descrizione della stanza
     */
    public static void stanza1()
    {
        System.out.println("\n");
        System.out.println("L' INGRESSO");
        System.out.println("L' ingresso e' alquanto illuminato dalla luna"
                    + " che frange le vetrate con immagini della madonna.");

        System.out.println("E' presente una porta a: Nord, Est, Ovest.");
        }
    /**
     * Metodo statico che stampa a schermo la descrizione della stanza
     */
    public static void stanza2()
    {
        System.out.println("\n");
        System.out.println("LO SGABUZZINO");
        System.out.println("Si intravedono delle"
                + " scope ed un lavello sporco di sangue."
                + "Che puzza esci subito da quiii!!");
        System.out.println("E' presente una porta a: Ovest.");
        }
    /**
     * Metodo statico che stampa a schermo la descrizione della stanza
     */
    public static void stanza3()
    {
        System.out.println("IL SALOTTO");
        System.out.println("Un salotto rustico con un enorme "
                + "set di divani di pelle"
                + "marrone trapuntati e la testa di un orso sul camino"
                + "in pietra.");
        System.out.println("E' presente un armadio");
        System.out.println("E' presente una porta a: Nord, Ovest.");
        }
    /**
     * Metodo statico che stampa a schermo la descrizione della stanza
     */
    public static void stanza4()
    {
            System.out.println("LA SALA DA PRANZO");
            System.out.println("Una sala da pranzo stile a meta' tra il moderno"
                    + " ed il classico, si vede che ci ha speso un capitale.");
            System.out.println("E' presente una porta a: Nord, Sud, Est.");
        }
    /**
     * Metodo statico che stampa a schermo la descrizione della stanza
     */
    public static void stanza5(){
            System.out.println("LA CUCINA");
            System.out.println("Una cucina scavolini di quelle sponsorizzate"
                    + " da Cracco.");
            System.out.println("E' presente un armadio");
            System.out.println("E' presente una porta a: Nord, Sud, Est, Ovest.");
        }
    /**
     * Metodo statico che stampa a schermo la descrizione della stanza
     */
    public static void stanza6(){
            System.out.println("IL BAGNO");
            System.out.println("Si intravede uno specchio e dei sanitari"
                    + " completamente d'oro tipico da boss della mala o"
                    + "dei capi rom.");
            System.out.println("E' presente un armadio");
            System.out.println("E' presente una porta a: Sud, Est, Ovest.");
        }
    /**
     * Metodo statico che stampa a schermo la descrizione della stanza
     */
    public static void stanza7(){
            System.out.println("LE SCALE");
            System.out.println("Le scale che portano al piano di sopra"
                    + " sono illuminate da un bellissimo quadro incorniciato"
                    + "e illuminato di John Dillinger.");
                    System.out.println("E' presente una porta a: Ovest, Est e Sud");
        }
    /**
     * Metodo statico che stampa a schermo la descrizione della stanza
     */
    public static void stanza8(){
            System.out.println("CORRIDOIO 1o PIANO");
            System.out.println("Uno spazioso corridioio che da alle stanze da letto");
            System.out.println("E' presente un armadio");
            System.out.println("E' presente una porta a: Sud, Est, Ovest.");
        }
    /**
     * Metodo statico che stampa a schermo la descrizione della stanza
     */
    public static void stanza9(){
            System.out.println("LA STANZA DEI TESORI DEL BOSS");
            System.out.println("-- Nella stanza è presente la tanto cara fedora di Johnny--");
            System.out.println("Ed Eccolo...");
            System.out.println("Li...");
            System.out.println("Immobile...");
            System.out.println("Quanto mi sei mancato mia vecchia amica...");
            
        }
    /**
     * Metodo statico che stampa a schermo la descrizione della stanza
     */
    public static void stanza10(){
            System.out.println("LA STANZA DEL BOSS");
            System.out.println("Una camera da letto super-lussuosa "
                    + "con luce soffusa "
                    + "e con tutti gli accorgimenti possibili ed immaginabili.");

            System.out.println("E' presente una porta a: Nord.");
        }
    /**
     * Metodo statico che stampa a schermo la descrizione della stanza
     */
    public static void stanza11(){
            System.out.println("LA STIVA DEI ''GIOCATTOLI''");
            System.out.println("WOW!!! Non avevo mai visto una quantita'"
                    + " del genere di"
                    + "droga e armi tutte insieme...");
            System.out.println("Chissa' se mi prestera' qualcosa. *sghignazza*");
            System.out.println("E' presente una porta a: Sud."); 
        }
    /**
     * Metodo statico che stampa a schermo la descrizione della stanza
     */
    public static void stanza12(){
        System.out.println("DISPENSA");
        System.out.println("C'e' un sacco di cibo in scatola ed acqua."
                + "Sembra come se questo boss sia pronto a tutto...");
        System.out.println("E' presente una porta a: Sud.");
        }
    /**
     * Metodo statico che stampa a schermo il messaggio di salvataggio avvenuto
     */
    public static void messaggioSalvataggioTerminato(){
        System.out.println("LA PARTITA E' STATA SALVATA CORRETTAMENTE.");
    }
    /**
     * Metodo statico che stampa il messaggio della luce guasta
     */
    public static void luceGuasta(){
        System.out.println("La luce e' guasta. Devo usare qualcosa par fare luce");
    }
    /**
     * Metodo statico che stampa il messaggio dove la luce e' spenta
     */
    public static void luceSpenta(){
        System.out.println("Accidenti la luce e' spenta. Non si vede niente");
    }
    /**
     * Metodo statico che stampa il messaggio di errore
     */
    public static void erroreGestioneFile(){
        System.out.println("Si e' verificato un errore con la gestione del file");
    }
    /**
     * Metodo statico che stampa il messaggio di errore
     */
    public static void errore(){
        System.out.println("Si e' verificato un errore");
    }
    /**
     * Metodo statico che stampa il messaggio di errore
     */
    public static void erroreLetturaParoleDaCancellare(){
        System.out.println("Si e' verificato un errore nella"
                + " lettura del file paroleDaCancellare");
    }
    /**
     * Metodo statico che stampa il messaggio di errore
     */
    public static void erroreLetturaParoleConcesse(){
        System.out.println("Si e' verificato un errore nella"
                + " lettura del file paroleConcesse");
    }
    /**
     * Metodo statico che stampa il messaggio di errore
     */
    public static void erroreLetturaDescrizioneStanze(){
        System.out.println("Si e' verificato un"
                + " errore con il file DescrizioneStanze");
    }
    /**
     * Metodo statico che stampa il messaggio di errore
     */
    public static void erroreStream(){
        System.out.println("Si e' verificato un errore nella chiusura dello stream");
    }
    /**
     * Metodo statico il cuale messaggio avvisa la nostra cattura
     */
    public static void catturato(){
        System.out.println("Sei stato catturato dalla guardia");
    }
    /**
     * Metodo statico che stampa il messaggio di errore
     */
    public static void luceAccesa(){
        System.out.println("Hai acceso la luce");
    }
    /**
     * Metodo statico che stampa il messaggio l oggetto non e' presente nella stanza
     */
    public static void oggettoNonPresente(){
        System.out.println("In questa stanza non e' presente l'oggetto ");
    }
    /**
     * Metodo statico che stampa il messaggio l oggetto non e' presente nell'inventario
     */
    public static void oggettoNonPresenteInInventario(){
        System.out.println("L' oggetto che vuoi usare non e' presente nel tuo inventario ");
    }
    /**
     * Metodo statico che stampa il messaggio luce gia' accesa
     */
    public static void luceGiaAccesa(){
        System.out.println("La luce e' gia' accesa");
    }
    /**
     * Metodo statico che stampa il messaggio luce gia' spenta
     */
    public static void luceGiaSpenta(){
        System.out.println("La luce e' gia' spenta");
    }
    /**
     * Metodo statico che stampa l oggetto richiesto
     */
    public static void necessitiDi(Stanza stanza){
        System.out.println("Per accedere a questa stanza hai bisogno di: " + stanza.oggettoRichiesto + "\n");    
    }
    /**
     * Metodo statico che avvisa il
     * giocatore che non � presente nessuna porta nella direzione scelta
     */
    public static void portaInesistente(){
        System.out.println("Non esiste nessuna porta in questa direzione");
    }
    /**
     * Metodo statico che avvisa il corretto uso di una chiave su una porta
     */
    public static void portaSbloccata(){
        System.out.println("La porta sembra chiusa a chiave");
        System.out.println("-- Jhonny usa la chiave --");
    }
    /**
     * Metodo statico che avvisa il corretto nascondimento del giocatore nell armadio
     */
    public static void aggiuntoInventario(){
        System.out.println(" E' stato aggiunto all'invetario");
    }
    /**
     * Metodo statico che avvisa lo spegnimento della luce
     */
    public static void spegniLuce(){
        System.out.println("Hai spento la luce");
    }
    /**
     * Metodo statico che avvisa il giocatore
     * che non puo' usare quell oggetto in quella circosatanza
     */
    public static void nonPuoiUsare(){
        System.out.println("Non ha senso usare questo oggetto qui");
    }
    /**
     * Metodo statico che avvisa il corretto abbattimento della guardia
     */
    public static void guardiaAbbattuta(){
        System.out.println("La guardia e' sistemata. Adesso non ci saranno piu' intoppi");
    }
    /**
     * Metodo statico che avvisa il corretto nascondimento del giocatore nell armadio
     */
    public static void nascostoArmadio(){
        System.out.println("Ti sei nascosto nell'armadio");
    }
    /**
     * Metodo statico che avvisa il corretto nascondimento del giocatore sotto il letto
     */
    public static void nascostoLetto(){
        System.out.println("Ti sei nascosto sotto il letto");
    }
    /**
     * Metodo statico che avvisa il giocatore che non c'e' nessun nascondiglio
     */
    public static void nessunNascondiglio(){
        System.out.println("Non c'e' nessun nascodiglio in questa stanza");
    }
}
