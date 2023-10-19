package registros;

import conexao.Conexao;
import entities.DadosLooca;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.TimerTask;

public class InsertLooca {

    public void InsertDados(DadosLooca dados) {
        CrudChamado consultaId = new CrudChamado();
        LocalDateTime hora = LocalDateTime.now();

        String sql = String.format("insert into tbMonitoramento (dataHora, fk_idComputador,cpuTemp,cpuFreq,redeLatencia, disco, ram)\n" +
                "VALUES ('%s', %d, %.0f, %.0f, %d, %.0f, %.0f);", hora, consultaId.getIdPc(), dados.getTemperatura(), dados.getUso(), dados.latenciaRede(), dados.espacoDisco(), dados.porcentualRam());
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
}
