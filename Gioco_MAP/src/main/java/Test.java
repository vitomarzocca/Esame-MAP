import java.io.*;
import java.util.Iterator;

public class Test {
    public static void main(String[] args)
    {
        String comando = "a b c d e f g h i l m o p q ";
        String[] vettoreStringa = comando.split("\\s+");

        for (String parola : vettoreStringa)
        {
            System.out.println(parola);
        }
    }
}