import java.io.*;
import java.util.Iterator;

public class Test {
    public static void main(String[] args)
    {
        GameManager partita = new GameManager();

        Parser.parser("accendi la luce nella stanza" , partita.paroleDaCancellare, partita.paroleConcesse, partita);


    }
}