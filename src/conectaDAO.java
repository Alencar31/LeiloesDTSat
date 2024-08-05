import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    public Connection connectDB() throws ClassNotFoundException{
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leiloes","root","Tigresa15@");            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }     
    
    public static void closeConnection(Connection conn) {
        try {
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(conectaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt) {
        closeConnection(conn);
        try {
            if(stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(conectaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {
        closeConnection(conn,stmt);
        try {
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(conectaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
}