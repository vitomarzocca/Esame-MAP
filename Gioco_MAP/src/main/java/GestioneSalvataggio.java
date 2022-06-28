import javax.xml.transform.Result;
import java.sql.*;
import java.util.Properties;

public class GestioneSalvataggio
{
    public Connection conn;

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
    public void connessioneDB()
    {
       Properties credenziali = new Properties();
       credenziali.setProperty("user", "user");
       credenziali.setProperty("password", "1234");

        try
        {
             conn = DriverManager.getConnection("jdbc:h2:file:/home/vito/Scrivania/Esame-MAP/Gioco_MAP/src/main/resources/dataBaseLocale", credenziali);
        }
        catch (SQLException e)
        {
            System.out.println("Si è verificato un errore nel collegamento con il DataBase ");
            System.out.println(e.getMessage());
        }

    }

    public void creazioneTabellaDB()
    {
         final String DROP_TABLE = "DROP TABLE IF EXISTS partita";
         final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS partita (nascosto BOOLEAN, stanzaCorrente INT, Vivo BOOLEAN," +
                "OggettoInvetario1 VARCHAR(20), OggettoInvetario2 VARCHAR(20), OggettoInvetario3 VARCHAR(20), OggettoInvetario4 VARCHAR(20)," +
                "OggettoInvetario5 VARCHAR(20), OggettoInvetario6 VARCHAR(20), StanzaVisitata1 BOOLEAN, StanzaVisitata2 BOOLEAN, StanzaVisitata3 BOOLEAN," +
                 "StanzaVisitata4 BOOLEAN, StanzaVisitata5 BOOLEAN, StanzaVisitata6 BOOLEAN, StanzaVisitata7 BOOLEAN, StanzaVisitata8 BOOLEAN, StanzaVisitata9 BOOLEAN," +
                 "StanzaVisitata10 BOOLEAN, StanzaVisitata11 BOOLEAN, StanzaVisitata12 BOOLEAN)";

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
            stm.executeUpdate(DROP_TABLE);
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

    public  void inserimentoSalvataggioInTabella(boolean nascosto, int stanzaCorrente, boolean vivo, Oggetti inventario[], boolean stanzaVisitata[])
    {

        PreparedStatement pstm = null;
        try
        {
          pstm = conn.prepareStatement("INSERT INTO partita VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
          pstm.setBoolean(1, nascosto);
          pstm.setInt(2, stanzaCorrente);
          pstm.setBoolean(3, vivo);
          pstm.setString(7, inventario[0].toString());
          pstm.setString(8, inventario[1].toString());
          pstm.setString(9, inventario[2].toString());
          pstm.setString(10, inventario[3].toString());
          pstm.setString(11, inventario[4].toString());
          pstm.setString(12, inventario[5].toString());
          pstm.setBoolean(13, stanzaVisitata[0]);
          pstm.setBoolean(14, stanzaVisitata[1]);
          pstm.setBoolean(15, stanzaVisitata[2]);
          pstm.setBoolean(16, stanzaVisitata[3]);
          pstm.setBoolean(17, stanzaVisitata[4]);
          pstm.setBoolean(18, stanzaVisitata[5]);
          pstm.setBoolean(19, stanzaVisitata[6]);
          pstm.setBoolean(20, stanzaVisitata[7]);
          pstm.setBoolean(21, stanzaVisitata[8]);
          pstm.setBoolean(22, stanzaVisitata[9]);
          pstm.setBoolean(23, stanzaVisitata[10]);
          pstm.setBoolean(24, stanzaVisitata[11]);
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

    public  GameManager caricaSalvataggio()
    {
        connessioneDB();

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
            rs = stm.executeQuery("SELECT nascosto, stanzaCorrente, Vivo," +
                    " OggettoInvetario1, OggettoInvetario2 , OggettoInvetario3 , OggettoInvetario4 ," +
                    " OggettoInvetario5, OggettoInvetario6, StanzaVisitata1, StanzaVisitata2, StanzaVisitata3 " +
                    " StanzaVisitata4, StanzaVisitata5, StanzaVisitata6 , StanzaVisitata7, StanzaVisitata8 , StanzaVisitata9 " +
                    " StanzaVisitata10, StanzaVisitata11, StanzaVisitata12 FROM partita");
        }
        catch(SQLException e)
        {
            System.out.println("Si è verificato un errore con la query SELECT 1");
        }

        try
        {
            salvataggio.nascosto = rs.getBoolean(1);
            salvataggio.stanzaCorrente = rs.getInt(2);
            salvataggio.vivo = rs.getBoolean(3);

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
            System.out.println("Si è verificato un errore con la query SELECT 2");
        }

        return salvataggio;
    }

}
