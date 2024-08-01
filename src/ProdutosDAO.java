import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        try {
            prep = conn.prepareStatement("insert into produtos (nome, valor, status) values (?, ?, ?);");
            prep.setString(1, produto.getNome());  
            prep.setInt(2,produto.getValor());
            prep.setString(3, "A Venda");
            
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o Produto: " + erro.getMessage() );
        } finally {
            conectaDAO.closeConnection(conn,prep);
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){        
        return listagem;
    }   
}
