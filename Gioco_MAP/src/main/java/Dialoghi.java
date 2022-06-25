
public class Dialoghi {

    
        public static void prologo() {
        System.out.println("PROLOGO");
        System.out.println("20 giorni prima");
        System.out.println("Una giornata tranquilla nella chiassosa Brooklyn, Johnny �o mafius� si sta ritirando a casa da una serena cena con i colleghi mafiosi.");
        System.out.println("Johnny era sempre stato uno a cui piaceva apparire e piaceva farlo con stile, ma questa cosa li si ritorse contro.");
        System.out.println("Appena uscito dal ristorante del suo miglior amico e collega Vivio Marzalma,si ritrov� con le spalle al muro in un vicoletto li vicino�");
        System.out.println("Una banda di scapestrati mandati da qualcuno l aveva gi� accerchiato e sembrava fossero interessati solo al derubarlo anche a costo di non lasciarlo in vita.");
        System.out.println("�Davvero strano� pens� Johnny mentre guardava negli occhi i suoi malfattori, �guardano tutti la mia fedora di al capone, saranno interessati davvero a quella?�");
        System.out.println("Non fece in tempo a pensare un alternativa per scappare che senti una botta e poi tutto nero.");
    }

    
        public static void oggi() {
        System.out.println("OGGI:");
        System.out.println("Johnny si � sveglia con una chiamata di Vivio:�Johnny ma che cazzo stai facendo!?!�");
        System.out.println("�Hey Vivio mi sono appena svegliato,sto nel letto�");
        System.out.println("�Coglione sveglia non c� tempo da perdere!!!�");
        System.out.println("�Dimmi che � quello che penso��disse Johnny con gli occhi di fuoco.");
        System.out.println("�Paparino sta tornando!�");
    }

    public static void messaggioPresenzaChivaCantina(boolean visitata)
    {
        if(visitata == true)
        {
            System.out.println("Sul tavolo è presente una chiave. Prima non l'ho notata.");
            System.out.println("Potrebbe risualtare utile raccoglierla");
        }
    }

    public static void messaggioPresenzaAccendino(boolean visitata)
    {
        if(visitata == false)
        {
            System.out.println("Qualcuno ha dimenticato sul divano un accendino.");
            System.out.println("Meglio prenderselo. Un accendino può essere sempre utile");
        }
    }

    public static void messaggioPresenzaPentola(boolean visitata, boolean eventoTipoCheCammina)
    {
        if(visitata == false && eventoTipoCheCammina == true)
        {
            System.out.println("Devo trovare un qualcosa da unsare per difermi da qul tipo");
            System.out.println("Questa padella dovrebbe fandare bene");
        }
    }

    public static void messaggioPresenzaChiaveTesoto(boolean visitata)
    {
        if (visitata == false)
        {
            System.out.println("C'è qualcosa che luccica");
            System.out.println("Questa chiave è più sfarzosa dela solito. Forse serve per aprire la stanza dei tesori del boss");
        }
    }

    public static void  messaggioInizionEvento()
    {
        System.out.println("ATTENZIONE E' APPENA ARRIVATA UN AUTO");
        System.out.println("NON FARTI BECCARE");
    }
}
