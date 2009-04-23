package trptcolin.persistence;

import java.sql.*;

public class BoardScoreStorage
{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public BoardScoreStorage()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/ttt10?" + "user=root&password=");
            stmt = conn.createStatement();
        }
        catch(Exception ex)
        {
        }
    }

    public boolean containsKey(String key)
    {
        String query = "SELECT squares FROM scores WHERE squares='" + key + "' LIMIT 1";
        try
        {
            rs = stmt.executeQuery(query);
            if(rs.next())
            {
                if(rs.getString("squares") != null)
                    return true;
                else
                    return false;
            }
        }
        catch(SQLException ex)
        {
            logSQLException(ex);
            return false;
        }
        return false;
    }

    private void logSQLException(SQLException ex)
    {
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    }
      
    public void put(String key, int value)
    {
        String query = "INSERT INTO scores VALUES('" + key + "', " + value + ")";
        
        try
        {
            stmt.executeUpdate(query);
        }
        catch(SQLException ex)
        {
            logSQLException(ex);
        }
    }


    public int get(String key)
    {
        String query = "SELECT * FROM scores WHERE squares='" + key + "' LIMIT 1";
        try
        {
            rs = stmt.executeQuery(query);
            if(rs.next())
            {
                return rs.getInt("score");
            }
        }
        catch(SQLException ex)
        {
            logSQLException(ex);
        }

        return 0;
    }

    public void clear()
    {

    }

    public int size()
    {
        try
        {
            rs = stmt.executeQuery("SELECT * FROM scores");
            rs.last();
            int rowCount = rs.getRow();
            return rowCount;
        }
        catch(SQLException ex)
        {
            logSQLException(ex);
        }
        return 0;
    }
}

