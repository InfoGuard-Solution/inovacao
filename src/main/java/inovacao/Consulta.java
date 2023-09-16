package inovacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Consulta {

    public List<Integer> dados() {

        List<Integer> lista = new ArrayList<>();
        String sql = "SELECT * FROM dados";

        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco. ***SELECT****
        ResultSet rset = null;


        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();


            Integer cpuTemperatura = 0;
            Integer cpuFrequencia = 0;
            Integer gpuTemperatura = 0;
            Integer gpuFrequencia = 0;
            Integer redeLatencia = 0;
            Integer redePacote = 0;

            while (rset.next()) {

                cpuTemperatura = rset.getInt("cpuTemp");
                cpuFrequencia = rset.getInt("cpuFreq");
                gpuTemperatura = rset.getInt("gpuTemp");
                gpuFrequencia = rset.getInt("gpuFreq");
                redeLatencia = rset.getInt("redeLatencia");
                redePacote = rset.getInt("redePack");
            }
            if (cpuFrequencia.equals(0) && gpuFrequencia.equals(0)) {
                cpuTemperatura = 0;
                cpuFrequencia = 0;
                gpuTemperatura = 0;
                gpuFrequencia = 0;
                redeLatencia = 0;
                redePacote = 0;

            }
            lista.add(cpuTemperatura);
            lista.add(cpuFrequencia);
            lista.add(gpuTemperatura);
            lista.add(gpuFrequencia);
            lista.add(redeLatencia);
            lista.add(redePacote);


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

    public LocalDateTime tempo() {

        String sql = "SELECT hora FROM dados";
        LocalDateTime hora = null;

        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco. ***SELECT****
        ResultSet rset = null;


        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();


            while (rset.next()) {

                hora = (LocalDateTime) rset.getObject("hora");
            }

            if (hora == null) {
                hora = LocalDateTime.now();

            }
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
        return hora;
    }

    public static void main(String[] args) {
        Consulta consulta = new Consulta();

        System.out.println(consulta.tempo());
    }
}