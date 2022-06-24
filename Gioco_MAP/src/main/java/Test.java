import java.io.*;
import java.util.Iterator;

public class Test {
    public static void main(String[] args)
    {
        GameManager partita = new GameManager();

        partita.muovi("sud");

        System.out.println(partita.stanzaCorrente);


    }
}

