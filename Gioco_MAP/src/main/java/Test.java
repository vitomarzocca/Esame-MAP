import java.io.*;
import java.util.Iterator;

public class Test {
    public static void main(String[] args)
    {
        GameManager partita = new GameManager();

        Parser.parser("nasconditi sotto il letto" , partita.paroleDaCancellare, partita.paroleConcesse, partita);


    }
}