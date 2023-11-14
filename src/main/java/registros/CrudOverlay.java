package registros;

import conexao.Conexao;
import conexao.ConexaoServer;
import telas.Overlay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CrudOverlay {

    public List<Integer> dados() {

        CrudChamado consultaId = new CrudChamado();
        List<Integer> lista = new ArrayList<>();
        String sql = String.format("SELECT * FROM tbMonitoramento where fk_idComputador = %d;", consultaId.getIdPc());

        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco. ***SELECT****
        ResultSet rset = null;


        try {
             /* Conexão my sql
                conn = Conexao.createConnectionToMySQL(); */

            // conexao sql server//
            conn = ConexaoServer.createConnectionToSqlServer();

            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            Integer cpuTemperatura = 0;
            Integer cpuFrequencia = 0;
            Integer gpuTemperatura = 0;
            Integer gpuFrequencia = 0;
            Integer redeLatencia = 0;
            Integer ram = 0;
            Integer disco = 0;

            while (rset.next()) {

                cpuTemperatura = rset.getInt("cpuTemp");
                cpuFrequencia = rset.getInt("cpuFreq");
                gpuTemperatura = rset.getInt("gpuTemp");
                gpuFrequencia = rset.getInt("gpuFreq");
                redeLatencia = rset.getInt("redeLatencia");
                ram = rset.getInt("ram");
                disco = rset.getInt("disco");
            }
            if (cpuFrequencia.equals(0) && gpuFrequencia.equals(0)) {
                cpuTemperatura = 0;
                cpuFrequencia = 0;
                gpuTemperatura = 0;
                gpuFrequencia = 0;
                redeLatencia = 0;
                ram = 0;
                disco = 0;

            }
            lista.add(cpuTemperatura);
            lista.add(cpuFrequencia);
            lista.add(gpuTemperatura);
            lista.add(gpuFrequencia);
            lista.add(redeLatencia);
            lista.add(ram);
            lista.add(disco);


        } catch (Exception e) {

            e.printStackTrace();

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
        return lista;
    }
}