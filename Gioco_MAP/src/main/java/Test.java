import java.io.*;
import java.net.PasswordAuthentication;
import java.util.Iterator;
import java.util.Scanner;

public class Test {
    public static void main(String[] args)
    {
        String comando;


        GameManager partita = new GameManager();




        System.out.println("Ti trovi nella stanza: " +partita.stanzaCorrente);
        Dialoghi.prologo();

        Scanner input = new Scanner(System.in);

        while (partita.vivo == true)
        {

            comando = input.nextLine();
            Parser.parser(comando, partita);
        }

        System.out.println("Fine partita");
    }
}

