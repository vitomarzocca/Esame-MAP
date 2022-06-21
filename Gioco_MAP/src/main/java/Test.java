import java.io.*;
import java.util.Iterator;

public class Test {
    public static void main(String[] args)
    {
        GameManager partita = new GameManager();

        Iterator<String> it = partita.paroleDaCancellare.iterator();

        while (it.hasNext() == true)
        {
            System.out.println(it.next());
        }
    }
}