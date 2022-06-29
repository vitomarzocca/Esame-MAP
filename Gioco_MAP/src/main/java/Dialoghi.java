
public class Dialoghi {

    public static void messaggioPresenzaChiaveCantina(boolean visitata)
    {
        if(visitata == true)
        {
            System.out.println("Sul tavolo e' presente una chiave. Prima non l'ho notata.");
            System.out.println("Potrebbe risualtare utile raccoglierla");
        }
    }
    public static void messaggioPresenzaAccendino(boolean visitata)
    {
        if(visitata == false)
        {
            System.out.println("Qualcuno ha dimenticato sul divano un accendino.");
            System.out.println("Meglio prenderselo."
                    + " Un accendino puo' essere sempre utile");
        }
    }
    public static void messaggioPresenzaPadella(boolean visitata, boolean eventoTipoCheCammina)
    {
        if(visitata == false && eventoTipoCheCammina == true)
        {
            System.out.println("Devo trovare un qualcosa da"
                    + " usare per difermi da quel tipo");
            System.out.println("Questa padella dovrebbe andare bene");
        }
    }
    public static void messaggioPresenzaChiaveTesoro(boolean visitata)
    {
        if (visitata == false)
        {
            System.out.println("C'e'� qualcosa che luccica");
            System.out.println("Questa chiave e'� piu' sfarzosa"
                    + " del solito. Forse serve per aprire la stanza dei tesori del boss");
        }
    }
    public static void messaggioInizioEvento()
    {
        System.out.println("ATTENZIONE E' APPENA ARRIVATA UN AUTO");
        System.out.println("NON FARTI BECCARE");
    }
    public static void prologo() {
            System.out.println("Prologo:");
            System.out.println("20 giorni prima");
            System.out.println("Una giornata tranquilla nella chiassosa Brooklyn, Johnny -o mafius- si sta ritirando a casa da una serena cena con i colleghi mafiosi.");
            System.out.println("Johnny era sempre stato uno a cui piaceva apparire e piaceva farlo con stile, ma questa cosa gli si ritorse contro.");
            System.out.println("Appena uscito dal ristorante del suo miglior amico e collega Vivio Marzalma,si ritrovo' con le spalle al muro in un vicoletto li' vicino...");
            System.out.println("Una banda di scapestrati mandati da qualcuno l aveva gia' accerchiato e sembrava fossero interessati solo al derubarlo anche a costo di non lasciarlo in vita.");
            System.out.println("-Davvero strano- penso' Johnny mentre guardava negli occhi i suoi malfattori, -guardano tutti la mia fedora di al capone, saranno interessati davvero a quella?-");
            System.out.println("Non fece in tempo a pensare un alternativa per scappare che senti' una botta e poi tutto nero.");
        }
    public static void obiettivo(){
            System.out.println("Missione: Il Furto");
            System.out.println("Obiettivo: Recupera LA FEDORA");
        }
    public static void oggi()
    {
            System.out.println("\n");
            System.out.println("Oggi:");
            System.out.println("Johnny si e' svegliato con una chiamata di Vivio: -Johnny ma che cazzo stai facendo!?!-");
            System.out.println("-Hey Vivio mi sono appena svegliato,sto nel letto-");
            System.out.println("-Coglione sveglia non c'e' tempo da perdere!!!-");
            System.out.println("-Dimmi che e' quello che penso...- disse Johnny con gli occhi di fuoco.");
            System.out.println("-Paparino sta tornando!-");
        }
    public static void fine(){
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
    public static void attesaInput(){
            System.out.println("\nCOSA VUOI FARE :");
        }
    public static void stanza1(){
            System.out.println("\n");
            System.out.println("L' INGRESSO");
            System.out.println("L' ingresso e' alquanto illuminato dalla luna"
                    + " che frange le vetrate con immagini della madonna.");

            System.out.println("E' presente una porta a: Nord, Est, Ovest.");         
        }
    public static void stanza2(){
            System.out.println("\n");
            System.out.println("LO SGABUZZINO");
            System.out.println("Si intravedono delle"
                    + " scope ed un lavello sporco di sangue."
                    + "Che puzza esci subito da quiii!!");
            System.out.println("E' presente una porta a: Ovest.");
        }
    public static void stanza3(){
            System.out.println("IL SALOTTO");
            System.out.println("Un salotto rustico con un enorme "
                    + "set di divani di pelle"
                    + "marrone trapuntati e la testa di un orso sul camino"
                    + "in pietra.");
            System.out.println("E' presente un armadio");
            System.out.println("E' presente una porta a: Nord, Ovest.");
        }
    public static void stanza4(){
            System.out.println("LA SALA DA PRANZO");
            System.out.println("Una sala da pranzo stile a meta' tra il moderno"
                    + " ed il classico, si vede che ci ha speso un capitale.");
            System.out.println("E' presente una porta a: Nord, Sud, Est.");
        }
    public static void stanza5(){
            System.out.println("LA CUCINA");
            System.out.println("Una cucina scavolini di quelle sponsorizzate"
                    + " da Cracco.");
            System.out.println("E' presente una porta a: Nord, Sud, Est, Ovest.");
        }
    public static void stanza6(){
            System.out.println("IL BAGNO");
            System.out.println("Si intravede uno specchio e dei sanitari"
                    + " completamente d'oro tipico da boss della mala o"
                    + "dei capi rom.");
            System.out.println("E' presente una porta a: Sud, Est, Ovest.");
        }
    public static void stanza7(){
            System.out.println("LE SCALE");
            System.out.println("Le scale che portano al piano di sopra"
                    + " sono illuminate da un bellissimo quadro incorniciato"
                    + "e illuminato di John Dillinger.");
                    System.out.println("E' presente una porta a: Ovest");
        }
    public static void stanza8(){
            System.out.println("CORRIDOIO 1o PIANO");
            System.out.println("Uno spazioso corridioio che da alle stanze da letto");
            System.out.println("E' presente una porta a: Sud, Est, Ovest.");
        }
    public static void stanza9(){
            System.out.println("LA STANZA DEI TESORI DEL BOSS");
            System.out.println("Ed Eccolo...");
            System.out.println("Li...");
            System.out.println("Immobile...");
            System.out.println("Quanto mi sei mancato mia vecchia amica...");
            
        }        
    public static void stanza10(){
            System.out.println("LA STANZA DEL BOSS");
            System.out.println("Una camera da letto super-lussuosa "
                    + "con luce soffusa "
                    + "e con tutti gli accorgimenti possibili ed immaginabili."
                    + "Si intravede una chiave appesa in una teca incastonata "
                    + "nel comodino sinistro");
            System.out.println("E' presente una porta a: Nord.");
        }
    public static void stanza11(){
            System.out.println("LA STIVA DEI ''GIOCATTOLI''");
            System.out.println("WOW!!! Non avevo mai visto una quantita'"
                    + " del genere di"
                    + "droga e armi tutte insieme...");
            System.out.println("Chissa' se mi prestera' qualcosa. *sghignazza*");
            System.out.println("E' presente una porta a: Sud."); 
        }
    public static void stanza12(){
            System.out.println("DISPENSA");
            System.out.println("C'e' un sacco di cibo in scatola ed acqua."
                    + "Sembra come se questo boss sia pronto a tutto...");
            System.out.println("E' presente una porta a: Sud.");
        }
    public static void messaggioSalvataggioTerminato(){
        System.out.println("La partita e' stata salvata correttamente.");
    }
    public static void luceGuasta(){
        System.out.println("La luce e' guasta. Hai qualcosa per illuminare?");
    }
    public static void oggettoNecessario(){
        System.out.println("Accidenti... questa porta e' chiusa,"
      +  "credo che servira' una chiave per aprirla.");
      System.out.println("Probabilmente si troverà in un altra stanza della casa");
    }
    public static void nascostoStordimento(){
        System.out.println("Bravo,ti sei nascosto senza farti sentire!");
        System.out.println("Guarda!!! E' di spalle, sfrutta"
                + " l' occasione per colpirlo con la padella!!!");
    }
    public static void piuTardi(){
        System.out.println("--Partenza per la casa del boss--");
        System.out.println("Johnny e' pronto per riprendersi...");
        System.out.println("tutt' chell che e' o soi");
    } 
}
