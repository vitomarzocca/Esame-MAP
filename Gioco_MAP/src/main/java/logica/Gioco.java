package logica;

import inputUtente.Parser;
import outputUtente.Dialoghi;
import inputUtente.ParserException;

import java.util.Scanner;

public class Gioco {
    public static void main(String[] args)
    {
        String comando;


        GameManager partita = new GameManager();


        Dialoghi.prologo();
        Dialoghi.oggi();
        Dialoghi.piuTardi();
        Dialoghi.obiettivo();
        Dialoghi.stanza1();
        partita.stampaMappa();


        Scanner input = new Scanner(System.in);

        while (partita.vivo)
        {
            Dialoghi.attesaInput();
            comando = input.nextLine();

            try
            {
                Parser.parser(comando, partita);
            }
            catch(ParserException e)
            {
                System.out.println("Comando non valido");
            }

        }

        System.out.println("\nFINE DEL GIOCO");
    }
}

