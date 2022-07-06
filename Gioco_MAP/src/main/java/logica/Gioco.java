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

        //INSERIRE DIALOGO PRIMA DI ENTRARE IN CASA

        Dialoghi.stanza1();
        partita.stampaMappa();


        Scanner input = new Scanner(System.in);

        while (partita.vivo == true)
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

        System.out.println("FINE DEL GIOCO");
    }
}

