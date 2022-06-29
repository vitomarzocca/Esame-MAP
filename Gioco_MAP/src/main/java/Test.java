import java.io.*;
import java.net.PasswordAuthentication;
import java.util.Iterator;
import java.util.Scanner;

public class Test {
    public static void main(String[] args)
    {
        String comando;


        GameManager partita = new GameManager();


        Dialoghi.prologo();
        Dialoghi.oggi();

        //INSERIRE DIALOGO PRIMA DI ENTRARE IN CASA

        Dialoghi.stanza1();
        partita.stampaMappa();


        Scanner input = new Scanner(System.in);

        while (partita.vivo == true)
        {
            Dialoghi.attesaInput();
            comando = input.nextLine();
            Parser.parser(comando, partita);
        }

        System.out.println("FINE DEL GIOCO");
    }
}

