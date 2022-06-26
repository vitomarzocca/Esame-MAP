import javax.xml.transform.Result;
import java.sql.*;
import java.util.Properties;

public class GestioneSalvataggio
{
    public static Connection conn;
    public static void connessioneDB()
    {
       Properties credenziali = new Properties();
       credenziali.setProperty("user", "user");
       credenziali.setProperty("password", "1234");


        try
        {
             conn = DriverManager.getConnection("jdbc:h2:/home/vito/Scrivania/Esame-MAP/Gioco_MAP/src/main/resources/dataBaseLocale", credenziali);
        }
        catch (SQLException e)
        {
            System.out.println("Si è verificato un errore nel collegamento con il DataBase");
        }

    }

    public static void creazioneTabellaDB()
    {
         final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS partita (nascosto BOOLEAN, stanzaCorrente INT, Vivo BOOLEAN, StanzaGuardia INT, EventoTipoCheCammina BOOLEAN, EventoTipoCheCamminaInPausa BOOLEAN" +
                "OggettoInvetario1 VARCHAR(20, OggettoInvetario2 VARCHAR(20, OggettoInvetario3 VARCHAR(20, OggettoInvetario4 VARCHAR(20,)" +
                "OggettoInvetario5 VARCHAR(20), OggettoInvetario6 VARCHAR(20), StanzaVisitata1 BOOLEAN, StanzaVisitata2 BOOLEAN), StanzaVisitata3 BOOLEAN," +
                 "StanzaVisitata4 BOOLEAN, StanzaVisitata5 BOOLEAN, StanzaVisitata6 BOOLEAN, StanzaVisitata7 BOOLEAN, StanzaVisitata8 BOOLEAN, StanzaVisitata9 BOOLEAN," +
                 "StanzaVisitata10 BOOLEAN, StanzaVisitata11 BOOLEAN, StanzaVisitata12 BOOLEAN)";

        Statement stm = null;

         GestioneSalvataggio.connessioneDB();

         try
         {
             stm = conn.createStatement();
         }
         catch(SQLException e)
         {
             System.out.println("Si è verificato un errore nella creazione dello statement");
         }

        try
        {
            stm.executeUpdate(CREATE_TABLE);
        }
        catch(SQLException e)
        {
            System.out.println("Si è verificato un errore nella creazione della tabella nel DB");
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

    public static void inserimentoSalvataggioInTabella(GameManager partita)
    {
        GestioneSalvataggio.connessioneDB();

        PreparedStatement pstm = null;
        try
        {
           pstm = conn.prepareStatement("INSERT INTO partita VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
           pstm.setBoolean(1, partita.nascosto);
           pstm.setInt(2, GameManager.stanzaCorrente);
           pstm.setBoolean(3, partita.vivo);
           pstm.setInt(4,GameManager.stanzaGuradia);
           pstm.setBoolean(5, GameManager.eventoTipoCheCammina);
           pstm.setBoolean(6, GameManager.eventoTipoCheCamminaInPausa);
           pstm.setString(7, partita.inventario[0].toString());
           pstm.setString(8, partita.inventario[1].toString());
           pstm.setString(9, partita.inventario[2].toString());
           pstm.setString(10, partita.inventario[3].toString());
           pstm.setString(11, partita.inventario[4].toString());
           pstm.setString(12, partita.inventario[5].toString());
           pstm.setBoolean(13, partita.stanzaVisitata[0]);
           pstm.setBoolean(14, partita.stanzaVisitata[1]);
           pstm.setBoolean(15, partita.stanzaVisitata[2]);
           pstm.setBoolean(16, partita.stanzaVisitata[3]);
           pstm.setBoolean(17, partita.stanzaVisitata[4]);
           pstm.setBoolean(18, partita.stanzaVisitata[5]);
           pstm.setBoolean(19, partita.stanzaVisitata[6]);
           pstm.setBoolean(20, partita.stanzaVisitata[7]);
           pstm.setBoolean(21, partita.stanzaVisitata[8]);
           pstm.setBoolean(22, partita.stanzaVisitata[9]);
           pstm.setBoolean(23, partita.stanzaVisitata[10]);
           pstm.setBoolean(24, partita.stanzaVisitata[11]);
        }
        catch (SQLException e)
        {
            System.out.println("Si è verificato un errore nel salvataggio 1 ");
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
    }

    public static GameManager caricaSalvataggio()
    {
        GestioneSalvataggio.connessioneDB();

        GameManager salvataggio = null;
        Statement stm = null;
        ResultSet rs = null;
        String stringaSupporto;

        try
        {
            stm = conn.createStatement();
        }
        catch(SQLException e)
        {
            System.out.println("Errore aperutra stateman");
        }

        try
        {
            rs = stm.executeQuery("SELECT nascosto, stanzaCorrente, Vivo, StanzaGuardia, EventoTipoCheCammina, EventoTipoCheCamminaInPausa, " +
                    "OggettoInvetario1, OggettoInvetario2, OggettoInvetario3, OggettoInvetario4, " +
                    "OggettoInvetario5, OggettoInvetario6 , StanzaVisitata1, StanzaVisitata2, StanzaVisitata3, " +
                    "StanzaVisitata4, StanzaVisitata5 , StanzaVisitata6 , StanzaVisitata7 , StanzaVisitata8 , StanzaVisitata9, " +
                    "StanzaVisitata10, StanzaVisitata11, StanzaVisitata12 FROM partita");
        }
        catch(SQLException e)
        {
            System.out.println("Si è verificato un errore con la query SELECT");
        }

        try
        {
            salvataggio.nascosto = rs.getBoolean(1);
            GameManager.stanzaCorrente = rs.getInt(2);
            salvataggio.vivo = rs.getBoolean(3);
            GameManager.stanzaGuradia = rs.getInt(4);
            GameManager.eventoTipoCheCammina = rs.getBoolean(5);
            GameManager.eventoTipoCheCamminaInPausa = rs.getBoolean(6);

            for(int i = 0; i<6; i++)
            {
                salvataggio.inventario[i] = Oggetti.valueOf(rs.getString(i+7));
            }

            for (int i = 0; i<12; i++)
            {
                salvataggio.stanzaVisitata[i] = rs.getBoolean(i+13);
            }
        }
        catch(SQLException e)
        {
            //errore
        }



        return salvataggio;
    }

}
