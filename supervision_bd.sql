Create database inovacao;

USE inovacao;

CREATE TABLE dados(
	hora datetime PRIMARY KEY,
    cpuTemp INT,
    cpuFreq int,
    gpuTemp INT,
    gpuFreq int,
    redeLatencia int,
    redePack int
);
truncate table dados;

-- Inserção de dados de exemplo
INSERT INTO dados (hora, cpuTemp, cpuFreq, gpuTemp, gpuFreq, redeLatencia, redePack)
VALUES
    ('2023-09-16 11:05:00', 30, 20, 25, 10, 20, 1);


INSERT INTO dados (hora, cpuTemp, cpuFreq, gpuTemp, gpuFreq, redeLatencia, redePack)
VALUES
    ('2023-09-16 11:10:00', 64, 68, 50,  69, 40, 1);
    
INSERT INTO dados (hora, cpuTemp, cpuFreq, gpuTemp, gpuFreq, redeLatencia, redePack)
VALUES
   ('2023-09-16 11:15:00', 80, 90, 100,  10, 200, 1);
    
select * from dados;


