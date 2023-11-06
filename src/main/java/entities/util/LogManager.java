package entities.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogManager {
    private Log log;

    public LogManager(){}
    public void setLog(String mensagem, LogLevel logLevel, HardwareType hardwareType, String porcentagem) {
        this.log = new Log();
        this.log.setMensagem(mensagem);
        this.log.setLogLevel(logLevel);
        this.log.setHardwareType(hardwareType);
        this.log.setPorcentagem(porcentagem);
        this.log.setDateTime(LocalDateTime.now());
        this.salvarLog();
    }

    private void salvarLog() {
       try {
           String userDirectory = new File("").getAbsolutePath();
           File fileStream = new File(userDirectory+"/logs.txt");
           fileStream.createNewFile();
           FileWriter writeLog = new FileWriter(userDirectory+"/logs.txt");
           writeLog.write(log.toString());
           writeLog.close();
       }
       catch (IOException ex){
           System.out.println(ex.getMessage());
       }

    }
}
