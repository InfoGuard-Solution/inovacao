package entities;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import entities.util.Converter;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;


public class DadosLooca {

    Looca looca = new Looca();
    File disc = new File("C:");
    Processador processador = new Processador();
    Temperatura temperatura = new Temperatura();
    DiscoGrupo disco = new DiscoGrupo();


    public Double getUso() {
        return looca.getProcessador().getUso();
    }

    public Double getTemperatura() {
        return temperatura.getTemperatura();
    }

    public Double porcentualRam() {
        Double porcentual = ((Converter.formater(looca.getMemoria().getEmUso()) / Converter.formater(looca.getMemoria().getTotal())) * 100);

        return porcentual;
    }

    public Double atividadeDisco() throws InterruptedException {
        // Obtém informações do sistema
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        HWDiskStore disk = hardware.getDiskStores().get(0); // Pega o primeiro disco, você pode adaptar conforme necessário

        // Registra o tempo inicial
        long initialTime = System.currentTimeMillis();
        long initialTransferTime = disk.getTransferTime();

        Thread.sleep(5000); // Aguarda 5 segundos(Tirar essa parte quebra o resultado)

        // Atualiza os atributos do disco para obter os dados mais recentes
        disk.updateAttributes();

        // Registra o tempo final
        long finalTime = System.currentTimeMillis();
        long finalTransferTime = disk.getTransferTime();

        // Calcula a porcentagem de tempo de atividade
        double activeTime = finalTransferTime - initialTransferTime;
        double totalTime = finalTime - initialTime;

        double activityPercentage = (activeTime / totalTime) * 100;

        return activityPercentage;
    }

    public Integer latenciaRede() {
        //Informações da Rede
        String host = "www.google.com";
        Integer latencia = 0;

        try {
            long tempoInicio = System.currentTimeMillis();
            InetAddress inetAddress = InetAddress.getByName(host);
            boolean isReachable = inetAddress.isReachable(1000);

            if (isReachable) {
                long tempoFim = System.currentTimeMillis();
                long lat = tempoFim - tempoInicio;
                latencia = Math.toIntExact(lat);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return latencia;
    }

    @Override
    public String toString() {
        return "DadosLooca{" +
                "looca=" + looca +
                ", disc=" + disc +
                ", processador=" + processador +
                ", temperatura=" + temperatura +
                ", disco=" + disco +
                '}';
    }
}