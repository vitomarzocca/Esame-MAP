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
         final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS partita (nascosto BOOLEAN, stanzaCorrente INT, " +
                "OggettoInvetario1 VARCHAR(20, OggettoInvetario2 VARCHAR(20, OggettoInvetario3 VARCHAR(20, OggettoInvetario4 VARCHAR(20,)" +
                "OggettoInvetario5 VARCHAR(20), OggettoInvetario6 VARCHAR(20), OggettoInvetario7 VARCHAR(20), OggettoInvetario8 VARCHAR(20)," +
                "OggettoInvetario9 VARCHAR(20), OggettoInvetario1 VARCHAR(20)";

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

    }

    public static void inserimentoSalvataggioInTabella(GameManager partita)
    {
        PreparedStatement pstm = null;
        try
        {
           pstm = conn.prepareStatement("INSERT INTO store VALUES (?, ?, ?, ?)");
        }
        catch (SQLException e)
        {
            //messaggio di errore
        }

        try
        {
            pstm.setBoolean(1, partita.nascosto);
            pstm.setInt(2, partita.stanzaCorrente);

        }
        catch (SQLException e)
        {
            //messaggio di errore
        }


    }

}
