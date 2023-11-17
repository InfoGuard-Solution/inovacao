package registros;

import conexao.Conexao;
import conexao.ConexaoMySql;
import conexao.ConexaoServer;
import entities.DadosLooca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class InsertLooca {

    // conexão my sql
    private static final Conexao connectMy = new ConexaoMySql();

    // conexão sql server
    private static final Conexao connectserver = new ConexaoServer();

    public void InsertDados(DadosLooca dados) {
        CrudChamado consultaId = new CrudChamado();

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            /* Conexão my sql
                conn = connectMy.criarConexao(); */

            // conexao sql server//
            conn = connectserver.criarConexao();

            LocalDateTime hora = LocalDateTime.now();
            Integer num = ThreadLocalRandom.current().nextInt(4, 9);

            String sql = String.format("insert into tbMonitoramento (dataHora, fk_idComputador,cpuTemp,gpuTemp, cpuFreq,redeLatencia, disco, ram)\n" +
                    "VALUES ('%s', %d, %.0f, %.0f, %.0f, %d, %.0f, %.0f);", hora, consultaId.getIdPc(), dados.getTemperatura(), (dados.getTemperatura() + num), dados.getUso(), dados.latenciaRede(), dados.atividadeDisco(), dados.porcentualRam());


            pstm = conn.prepareStatement(sql);
            pstm.executeQuery();

        } catch (Exception ex) {
            // Tratamento de exceção genérica

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
