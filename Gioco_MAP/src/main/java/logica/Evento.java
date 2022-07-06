package logica;

import java.lang.Math;

public class Evento extends Thread
{
    public boolean eventoInFunzione;
    public boolean eventoInPausa;
    public int stanzaGuardia;
    public int stanzaCorrente;



    public Evento()
    {
        eventoInPausa = false;
        eventoInFunzione = false;
        stanzaGuardia = 1;
        stanzaCorrente = 4;
    }

    public void cambiaStanzaCorrente(int numeroStanza)
    {
        stanzaCorrente = numeroStanza;
    }

    public void run()
    {
        while (eventoInFunzione == true ) {
            while (eventoInPausa == false) {

                if (stanzaGuardia == 1)
                {
                    int random = (int) (Math.random() * 3);

                    switch (random) {
                        case 0:
                            stanzaGuardia = 2;
                            break;

                        case 1:
                            stanzaGuardia = 3;
                            break;

                        case 2:
                            stanzaGuardia = 5;
                            break;
                    }

                }
                else if (stanzaGuardia == 2)
                {
                    stanzaGuardia = 1;
                }

                else if (stanzaGuardia == 3)
                {
                    int random = (int) (Math.random() * 2);

                    switch (random) {
                        case 0:
                            stanzaGuardia = 4;
                            break;

                        case 1:
                            stanzaGuardia = 1;
                            break;

                    }
                }

                else if (stanzaGuardia == 4)
                {
                    int random = (int) (Math.random() * 3);

                    switch (random) {
                        case 0:
                            stanzaGuardia = 3;
                            break;

                        case 1:
                            stanzaGuardia = 5;
                            break;

                        case 2:
                            stanzaGuardia = 7;
                            break;
                    }
                }

                else if (stanzaGuardia == 5)
                {
                    int random = (int) (Math.random() * 4);

                    switch (random) {
                        case 0:
                            stanzaGuardia = 6;
                            break;

                        case 1:
                            stanzaGuardia = 4;
                            break;

                        case 2:
                            stanzaGuardia = 1;
                            break;

                        case 3:
                            stanzaGuardia = 12;
                            break;
                    }
                } else if (stanzaGuardia == 6) {
                    int random = (int) (Math.random() * 3);

                    switch (random) {
                        case 0:
                            stanzaGuardia = 7;
                            break;

                        case 1:
                            stanzaGuardia = 5;
                            break;

                        case 2:
                            stanzaGuardia = 11;
                            break;
                    }
                } else if (stanzaGuardia == 7) {
                    int random = (int) (Math.random() * 3);

                    switch (random) {
                        case 0:
                            stanzaGuardia = 4;
                            break;

                        case 1:
                            stanzaGuardia = 6;
                            break;

                        case 2:
                            stanzaGuardia = 8;
                            break;
                    }
                } else if (stanzaGuardia == 8) {
                    int random = (int) (Math.random() * 3);

                    switch (random) {
                        case 0:
                            stanzaGuardia = 9;
                            break;

                        case 1:
                            stanzaGuardia = 10;
                            break;

                        case 2:
                            stanzaGuardia = 7;
                            break;
                    }
                } else if (stanzaGuardia == 9) {
                    stanzaGuardia = 8;
                } else if (stanzaGuardia == 10) {
                    stanzaGuardia = 8;
                } else if (stanzaGuardia == 11) {
                    stanzaGuardia = 6;
                } else if (stanzaGuardia == 12) {
                    stanzaGuardia = 5;
                }


                if (stanzaGuardia == stanzaCorrente) {
                    System.out.println("ATTENZIONE SENTI DEI PASSI CHE SI STANNO AVVICINANDO VERSO DI TE");
                    System.out.println("NASCONDITI O CAMBIA STANZA");
                    eventoInPausa = true;
                }

                try
                {
                    Thread.sleep(400);
                }
                catch (InterruptedException e)
                {
                    System.out.println("Il thread si è interrotto");
                }
            }
        }
    }

    public void interrupt()
    {
        eventoInFunzione = false;
    }

}

