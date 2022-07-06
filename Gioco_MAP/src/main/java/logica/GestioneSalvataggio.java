package logica;

import outputUtente.Dialoghi;

import java.sql.*;
import java.util.Properties;

public class GestioneSalvataggio
{
    public Connection conn;

    /**
     * Costruttore della classe GestioneSalvataggio
     */
    public GestioneSalvataggio()
    {
        conn = null;
        connessioneDB();
        creazioneTabellaDB();
        try
        {
            conn.close();
        }
        catch(SQLException e)
        {
            System.out.println("Errore nel costruttore");
        }

        try
        {
            conn.close();
        }
        catch(SQLException e)
        {
            System.out.println("Errore nella chiusura della connessione");
        }

    }

    /**
     * Permette di collegarsi al DB locale
     */
    private void connessioneDB()
    {
       Properties credenziali = new Properties();
       credenziali.setProperty("user", "user");
       credenziali.setProperty("password", "1234");

        try
        {
             conn = DriverManager.getConnection("jdbc:h2:file:./Gioco_MAP/src/main/resources/dataBaseLocale", credenziali);
        }
        catch (SQLException e)
        {
            System.out.println("Si è verificato un errore nel collegamento con il DataBase ");
            System.out.println(e.getMessage());
        }

    }

    /**
     * Crea una tabella nel DB se non è già presente, utile per il salvataggio
     */
    private void creazioneTabellaDB()
    {
         final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS partita\n" +
                 "     (\n" +
                 "       NASCOSTO BOOLEAN NOT NULL,\n" +
                 "       STANZACORRENTE INT NOT NULL,\n" +
                 "      VIVO BOOLEAN NOT NULL,\n" +
                 "      EVENTO BOOLEAN NOT NULL,\n" +
                 "OGGETTOINVETARIO1  VARCHAR(20) NOT NULL,\n" +
                 "OGGETTOINVETARIO2  VARCHAR(20) NOT NULL,\n" +
                 "OGGETTOINVETARIO3  VARCHAR(20) NOT NULL,\n" +
                 "OGGETTOINVETARIO4  VARCHAR(20) NOT NULL,\n" +
                 "OGGETTOINVETARIO5  VARCHAR(20) NOT NULL,\n" +
                 "OGGETTOINVETARIO6  VARCHAR(20) NOT NULL,\n" +
                 "STANZAVISITATA1  BOOLEAN NOT NULL,\n" +
                 "STANZAVISITATA2  BOOLEAN NOT NULL,\n" +
                 "STANZAVISITATA3  BOOLEAN NOT NULL,\n" +
                 "STANZAVISITATA4  BOOLEAN NOT NULL,\n" +
                 "STANZAVISITATA5  BOOLEAN NOT NULL,\n" +
                 "STANZAVISITATA6  BOOLEAN NOT NULL,\n" +
                 "STANZAVISITATA7  BOOLEAN NOT NULL,\n" +
                 "STANZAVISITATA8  BOOLEAN NOT NULL,\n" +
                 "STANZAVISITATA9  BOOLEAN NOT NULL,\n" +
                 "STANZAVISITATA10  BOOLEAN NOT NULL,\n" +
                 "STANZAVISITATA11  BOOLEAN NOT NULL,\n" +
                 "STANZAVISITATA12  BOOLEAN NOT NULL\n" +
                 "      );";

        Statement stm = null;

         try
         {
             stm = conn.createStatement();
         }
         catch(SQLException e)
         {
             System.out.println("Si è verificato un errore nella creazione dello statement");
             System.out.println(e.getMessage() +"\n\n");
         }

        try
        {
            stm.executeUpdate(CREATE_TABLE);
        }
        catch(SQLException e)
        {
            System.out.println("Si è verificato un errore nella creazione della tabella nel DB");
            System.out.println(e.getMessage() +"\n\n");
        }

        try
        {
            stm.close();
        }
        catch(SQLException e)
        {
            System.out.println("Si è verificato un errore nella chiusura dello statement");
        }


    }

    /**
     * Metodo che permettere di inserire durante un salvataggio i dati nella tabella presente in tabella
     * @param nascosto
     * @param stanzaCorrente
     * @param vivo
     * @param inventario
     * @param stanzaVisitata
     * @param statoEvento
     */
    public  void inserimentoSalvataggioInTabella(boolean nascosto, int stanzaCorrente, boolean vivo, Oggetti[] inventario, boolean[] stanzaVisitata, boolean statoEvento)
    {
        connessioneDB();

        final String DROP_TABLE = "DROP TABLE IF EXISTS partita";

        Statement stm;
        PreparedStatement pstm = null;
        connessioneDB();

        try
        {
            stm = conn.createStatement();
            stm.executeUpdate(DROP_TABLE);
            creazioneTabellaDB();
        }
        catch(SQLException e)
        {
            System.out.println("Errore nella cancellazione della tabella");
        }

        try
        {

          pstm = conn.prepareStatement("INSERT INTO partita VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        }
        catch (SQLException e)
        {

            System.out.println("Si è verificato un errore nel salvataggio 1 ");
        }

        try
        {
            pstm.setBoolean(1, nascosto);
            pstm.setInt(2, stanzaCorrente);
            pstm.setBoolean(3, vivo);
            pstm.setBoolean(4, statoEvento);
            pstm.setString(5, inventario[0].toString());
            pstm.setString(6, inventario[1].toString());
            pstm.setString(7, inventario[2].toString());
            pstm.setString(8, inventario[3].toString());
            pstm.setString(9, inventario[4].toString());
            pstm.setString(10, inventario[5].toString());
            pstm.setBoolean(11, stanzaVisitata[0]);
            pstm.setBoolean(12, stanzaVisitata[1]);
            pstm.setBoolean(13, stanzaVisitata[2]);
            pstm.setBoolean(14, stanzaVisitata[3]);
            pstm.setBoolean(15, stanzaVisitata[4]);
            pstm.setBoolean(16, stanzaVisitata[5]);
            pstm.setBoolean(17, stanzaVisitata[6]);
            pstm.setBoolean(18, stanzaVisitata[7]);
            pstm.setBoolean(19, stanzaVisitata[8]);
            pstm.setBoolean(20, stanzaVisitata[9]);
            pstm.setBoolean(21, stanzaVisitata[10]);
            pstm.setBoolean(22, stanzaVisitata[11]);
        }
        catch(SQLException e)
        {

            System.out.println("Si è verificato un errore nel salvataggio 1.5 ");
        }
        catch(NullPointerException k)
        {

            System.out.println("Si è verificato un errore nel salvataggio 1.6 ");
        }

        try
        {
            pstm.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Si è verificato un errore nel salvataggio 2");
        }

        try
        {
            pstm.close();
        }
        catch (SQLException e)
        {
            System.out.println("Si è verificato un errore nella chiusura dello statement");
        }

        try
        {
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println("Si è verificato un errore con la chiusura della connession al DB");
        }

        Dialoghi.messaggioSalvataggioTerminato();
    }

    /**
     * Permette di leggere i dati dalla tabella del DB
     * @return
     */
    public GameManager caricaSalvataggio()
    {
        connessioneDB();

        GameManager salvataggio = new GameManager();
        Statement stm = null;
        ResultSet rs = null;

        try
        {
            stm = conn.createStatement();

        }
        catch (SQLException e)
        {
            System.out.println("Errore aperutra stateman");
        }

        try {
            rs = stm.executeQuery("SELECT\n" +
                    "NASCOSTO,\n" +
                    "STANZACORRENTE,\n" +
                    "VIVO,\n" +
                    "EVENTO, \n" +
                    "OGGETTOINVETARIO1,  \n" +
                    "OGGETTOINVETARIO2,\n" +
                    "OGGETTOINVETARIO3, \n" +
                    "OGGETTOINVETARIO4, \n" +
                    "OGGETTOINVETARIO5, \n" +
                    "OGGETTOINVETARIO6,\n" +
                    "STANZAVISITATA1,  \n" +
                    "STANZAVISITATA2,  \n" +
                    "STANZAVISITATA3 ,\n" +
                    "STANZAVISITATA4, \n" +
                    "STANZAVISITATA5,  \n" +
                    "STANZAVISITATA6,  \n" +
                    "STANZAVISITATA7,  \n" +
                    "STANZAVISITATA8,  \n" +
                    "STANZAVISITATA9 ,\n" +
                    "STANZAVISITATA10 , \n" +
                    "STANZAVISITATA11 , \n" +
                    "STANZAVISITATA12   " +
                    "FROM partita");

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Si è verificato un errore con la query SELECT 1");
        }

        try
        {
            if (rs.next())
             {
                 salvataggio.nascosto = rs.getBoolean(1);
                 salvataggio.stanzaCorrente = rs.getInt(2);
                 salvataggio.vivo = rs.getBoolean(3);
                 salvataggio.evento.eventoInFunzione =rs.getBoolean(4);

                 for (int i = 0; i < 6; i++)
                 {
                     salvataggio.inventario[i] = Oggetti.valueOf(rs.getString(i + 5));
                 }

                 for (int i = 0; i < 12; i++)
                 {
                     salvataggio.stanzaVisitata[i] = rs.getBoolean(i + 11);
                 }
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Si è verificato un errore con la query SELECT 2");
        }

        return salvataggio;



    }
}
