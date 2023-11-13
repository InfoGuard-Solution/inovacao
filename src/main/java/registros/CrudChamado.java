package registros;

import conexao.Conexao;
import entities.Chamado;
import integracao.Slack;
import org.json.JSONObject;
import telas.Overlay;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrudChamado {

    // Verifica se já tem chamados encaminhados para determinado computador
    public Integer procurarChamado() {

        String sql = String.format("select idComputador from tbComputador where idComputador = %d AND status != \"bom\";", getIdPc());

        Integer id = null;

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
        return id;
    }

    // Faz uma inserção na table chamados
    public void InsertChamado(Chamado chamado) {

        JSONObject json = new JSONObject();
        String textoAlerta;

        String sql = String.format("INSERT INTO tbOcorrencia (descricao, fk_idComputador, hora, status)\n" +
                "VALUES ('%s', %d, '%s', 'Pendente');", chamado.getProblema(), getIdPc(), chamado.getHora());
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            if (procurarChamado() == null) {
                conn = Conexao.createConnectionToMySQL();
                pstm = conn.prepareStatement(sql);
                StatusPc();

                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date data = new Date();

                textoAlerta = String.format("""
                        Foi aberto um chamado!
                        Descrição do problema: %s,
                        Pc com problema: %s,
                        Hora da abertura do chamado: %s
                        """, chamado.getProblema(), new Overlay().getApelido(), formato.format(data));

                json.put("text", textoAlerta);
                Slack.sendMessage(json);

                int rset = pstm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Chamado feito com sucesso !");
            } else {
                JOptionPane.showMessageDialog(null, "Um chamado já foi encaminhado");
            }
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

    // atualiza o status do pc quando um chamado é feito

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


    // Pega o id do pc a partir do apelido fornecido
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