package registros;

import conexao.Conexao;
import telas.Overlay;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudChamado {

    public void InsertChamado(String descricao, String hora) {

        String sql = String.format("INSERT INTO tbOcorrencia (descricao, fk_idComputador, hora, status)\n" +
                "VALUES ('%s', %d, '%s', 'Pendente');", descricao, getIdPc(), hora);
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            int rset = pstm.executeUpdate();
            StatusPc();
            JOptionPane.showMessageDialog(null, "Chamado feito com sucesso !");

        } catch (Exception ex) {
            // Tratamento de exceção genérica
            ex.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void StatusPc() {

        String sql = String.format("update tbComputador set status = 'bug' where idComputador = %d;", getIdPc());
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            int rset = pstm.executeUpdate();

        } catch (Exception ex) {
            // Tratamento de exceção genérica
            ex.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static boolean verificador = true;
    private static Integer idPc;

    public Integer getIdPc() {
        return idPc;
    }

    public Integer pegarIdPc() {
        Overlay apelido = new Overlay();

        String sql = String.format("SELECT idComputador FROM tbComputador WHERE apelidoComputador = '%s' AND fk_idEvento = (select e.idEvento from tbEvento e\n" +
                "inner join tbComputador c ON c.fk_idEvento = e.idEvento\n" +
                "where c.apelidoComputador = '%s' AND e.status = \"Em andamento\");", apelido.getApelido(), apelido.getApelido());

        Integer id = null; // Inicializado como null para indicar que não foi encontrado

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                id = rset.getInt("idComputador");
            }

            if (id == null) {
                // Se id não foi encontrado, exibe a mensagem para o usuário
                JOptionPane.showMessageDialog(null, "Apelido inexistente");
                System.exit(0);
            } else {

                if (verificador) {
                    JOptionPane.showMessageDialog(null, "Computador localizado");
                    verificador = false;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        idPc = id;
        return id;
    }

}
