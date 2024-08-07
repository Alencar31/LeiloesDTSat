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
        
        try {
            conn = new conectaDAO().connectDB();
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
        
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("select * from produtos");
            resultset = prep.executeQuery();
            while (resultset.next()) {
                
                ProdutosDTO produtoDto = new ProdutosDTO();
                
                produtoDto.setId(resultset.getInt("id"));
                produtoDto.setNome(resultset.getString("nome"));
                produtoDto.setValor(resultset.getInt("valor"));
                produtoDto.setStatus(resultset.getString("status"));
                
                listagem.add(produtoDto);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
        } finally {
            conectaDAO.closeConnection(conn, prep, resultset);
        }
        return listagem;
    }   
    
    public void venderProduto(int idProd) {
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("Update produtos set status = 'Vendido' where id = " + idProd + ";");
            
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Venda efetuada com sucesso");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar a venda: " + erro.getMessage() );
        } finally {
            conectaDAO.closeConnection(conn,prep);
        }
    }
    
    public boolean verificaStatus(int idProd) {
        boolean vendido = false;
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("select status from produtos where id = " + idProd + ";");
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                if (resultset.getString("status").equalsIgnoreCase("Vendido")) {
                    vendido = true;
                } else {
                    vendido = false;
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar produtos: " + erro.getMessage());
        } finally {
            conectaDAO.closeConnection(conn, prep, resultset);
        }
        return vendido;        
    }
    
    public boolean verificaProduto(int idProd) {
        boolean existe = false;
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("select * from produtos where id = " + idProd + ";");
            resultset = prep.executeQuery();
            
            if (resultset.isBeforeFirst()){
                existe = true;
            } else {
                existe = false;
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar produtos: " + erro.getMessage());
        } finally {
            conectaDAO.closeConnection(conn, prep, resultset);
        }    
        return existe;
    }
}
