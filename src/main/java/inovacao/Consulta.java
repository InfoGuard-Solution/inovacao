package inovacao;

import telas.Overlay;
import telas.TelaChamado;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Consulta {

    public List<Integer> dados() {

        List<Integer> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbMonitoramento";

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
                redePacote = rset.getInt("redePacote");
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

        String sql = "SELECT dataHora FROM tbMonitoramento";
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

                hora = (LocalDateTime) rset.getObject("dataHora");
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

    private static boolean verificador = true;
    public Integer pegarId() {
        Overlay apelido = new Overlay();
        apelido.dispose();

        String sql = String.format("SELECT idComputador FROM tbComputador WHERE apelidoComputador = '%s' AND fk_idEvento = (SELECT idEvento FROM tbEvento WHERE status = 'Em andamento');", apelido.getApelido());

        Integer id = null; // Inicializado como null para indicar que não foi encontrado

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                id =  rset.getInt("idComputador");
            }

            if (id == null) {
                // Se id não foi encontrado, exibe a mensagem para o usuário
                JOptionPane.showMessageDialog(null, "Apelido inexistente");
                System.exit(0);
            }else{

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
        return id;
    }

}