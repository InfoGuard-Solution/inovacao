package registros;

import conexao.Conexao;
import entities.DadosLooca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class InsertLooca {

    public void InsertDados(DadosLooca dados) throws InterruptedException {
        CrudChamado consultaId = new CrudChamado();
        LocalDateTime hora = LocalDateTime.now();
        Integer num = ThreadLocalRandom.current().nextInt(4, 9);

        String sql = String.format("insert into tbMonitoramento (dataHora, fk_idComputador,cpuTemp,gpuTemp, cpuFreq,redeLatencia, disco, ram)\n" +
                "VALUES ('%s', %d, %.0f, %.0f, %.0f, %d, %.0f, %.0f);", hora, consultaId.getIdPc(), dados.getTemperatura(), (dados.getTemperatura() + num), dados.getUso(), dados.latenciaRede(), dados.atividadeDisco(), dados.porcentualRam());
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
