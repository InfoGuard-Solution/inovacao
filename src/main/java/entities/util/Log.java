package entities.util;

import java.time.LocalDateTime;
import java.util.Date;

public class Log {
    private LocalDateTime dateTime;
    private String mensagem;
    private String porcentagem;

    private LogLevel logLevel;
    private HardwareType hardwareType;

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public HardwareType getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(HardwareType hardwareType) {
        this.hardwareType = hardwareType;
    }

    public LocalDateTime getDateTime(){
        return this.dateTime;
    }
    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }

    public String getMensagem(){
        return this.mensagem;
    }
    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }

    public String getPorcentagem(){
        return this.porcentagem;
    }
    public void setPorcentagem(String porcentagem){
        this.porcentagem = porcentagem;
    }

    @Override
    public String toString() {
        return dateTime+
                ", mensagem='" + mensagem + '\'' +
                ", porcentagem='" + porcentagem + '\'' +
                ", logLevel=" + logLevel +
                ", hardwareType=" + hardwareType +
                '}';
    }
}


