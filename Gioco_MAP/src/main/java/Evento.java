import java.lang.Math;

public class Evento extends Thread      //EVENTO TIPO CHE CAMMINA PER LE STANZE
{

    public void run()
    {
        GameManager.stanzaGuradia = 1;

        while (GameManager.eventoTipoCheCammina == true ) {
            while (GameManager.eventoTipoCheCamminaInPausa == false) {

                if (GameManager.stanzaGuradia == 1) {
                    int random = (int) (Math.random() * 3);

                    switch (random) {
                        case 0:
                            GameManager.stanzaGuradia = 2;
                            break;

                        case 1:
                            GameManager.stanzaGuradia = 3;
                            break;

                        case 2:
                            GameManager.stanzaGuradia = 5;
                            break;
                    }

                } else if (GameManager.stanzaGuradia == 2) {
                    GameManager.stanzaGuradia = 1;
                } else if (GameManager.stanzaGuradia == 3) {
                    int random = (int) (Math.random() * 2);

                    switch (random) {
                        case 0:
                            GameManager.stanzaGuradia = 4;
                            break;

                        case 1:
                            GameManager.stanzaGuradia = 1;
                            break;

                    }
                } else if (GameManager.stanzaGuradia == 4) {
                    int random = (int) (Math.random() * 3);

                    switch (random) {
                        case 0:
                            GameManager.stanzaGuradia = 3;
                            break;

                        case 1:
                            GameManager.stanzaGuradia = 5;
                            break;

                        case 2:
                            GameManager.stanzaGuradia = 7;
                            break;
                    }
                } else if (GameManager.stanzaGuradia == 5) {
                    int random = (int) (Math.random() * 4);

                    switch (random) {
                        case 0:
                            GameManager.stanzaGuradia = 6;
                            break;

                        case 1:
                            GameManager.stanzaGuradia = 4;
                            break;

                        case 2:
                            GameManager.stanzaGuradia = 1;
                            break;

                        case 4:
                            GameManager.stanzaGuradia = 12;
                            break;
                    }
                } else if (GameManager.stanzaGuradia == 6) {
                    int random = (int) (Math.random() * 3);

                    switch (random) {
                        case 0:
                            GameManager.stanzaGuradia = 7;
                            break;

                        case 1:
                            GameManager.stanzaGuradia = 5;
                            break;

                        case 2:
                            GameManager.stanzaGuradia = 11;
                            break;
                    }
                } else if (GameManager.stanzaGuradia == 7) {
                    int random = (int) (Math.random() * 3);

                    switch (random) {
                        case 0:
                            GameManager.stanzaGuradia = 4;
                            break;

                        case 1:
                            GameManager.stanzaGuradia = 6;
                            break;

                        case 2:
                            GameManager.stanzaGuradia = 8;
                            break;
                    }
                } else if (GameManager.stanzaGuradia == 8) {
                    int random = (int) (Math.random() * 3);

                    switch (random) {
                        case 0:
                            GameManager.stanzaGuradia = 9;
                            break;

                        case 1:
                            GameManager.stanzaGuradia = 10;
                            break;

                        case 2:
                            GameManager.stanzaGuradia = 7;
                            break;
                    }
                } else if (GameManager.stanzaGuradia == 9) {
                    GameManager.stanzaGuradia = 8;
                } else if (GameManager.stanzaGuradia == 10) {
                    GameManager.stanzaGuradia = 8;
                } else if (GameManager.stanzaGuradia == 11) {
                    GameManager.stanzaGuradia = 6;
                } else if (GameManager.stanzaGuradia == 12) {
                    GameManager.stanzaGuradia = 5;
                    GameManager.eventoTipoCheCamminaInPausa = true;
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    //
                }


                if (GameManager.stanzaGuradia == GameManager.stanzaCorrente) {
                    System.out.println("ATTENZIONE SENTI DEI PASSI CHE SI STANNO AVVICINANDO VERSO DI TE");
                    System.out.println("NASCONDITI O CAMBIA STANZA");
                    GameManager.eventoTipoCheCamminaInPausa = true;
                }

                System.out.println("LA GUARDIA SI TROVA NELLA STANZA " + GameManager.stanzaGuradia);

            }
        }
    }

}

