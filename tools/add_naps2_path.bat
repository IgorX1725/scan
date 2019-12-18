ECHO registrando a variavel de ambiente... 
ECHO este bat adicionara o diretorio onde o naps2 foi instalado (C:\Program Files (x86)\NAPS2) na variavel de ambiente path, antes de modifica-la, sera feita uma copia do conteudo da variavel path para um arquivo txt chamado "path_backup.txt"localizado na pasta raiz do usuario que esta logado no momento da execucao do mesmo(c:\users\NomeDoUsuario). Caso haja algum problema com a variavel path, sera possivel retornar os valores anteriores acessando as variaveisde ambiente copiando os dados do arquivo path_backup.txt e acessando a variaveil path pela configuracao do sistema. Link de apoio:https://docs.microsoft.com/en-us/previous-versions/windows/it-pro/windows-server-2003/cc736637(v=ws.10)?redirectedfrom=MSDN
@ECHO off
IF EXIST "%HOMEPATH%\path_backup.txt" (echo "[%date% %time%]-%path%" >> "%HOMEPATH%\path_backup.txt") else echo [%date% %time%]-%path% > "%HOMEPATH%\path_backup.txt"

REG ADD "HKLM\SYSTEM\CurrentControlSet\Control\Session Manager\Environment" /v path /t REG_EXPAND_SZ /d "%path%;C:\Program Files (x86)\NAPS2"

pause