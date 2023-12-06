-- Funcionario definition

CREATE TABLE Funcionario (
    
    -- Coluna "id" que será a chave primária autoincrementada (sempre soma 1 a cada registro incluído)
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    
    -- Coluna "nome" para armazenar o nome da tarefa (não pode ser nulo)
    nome TEXT NOT NULL,
    
    -- Coluna "cargo" para armazenar a descrição do cargo (não pode ser nulo)
    cargo TEXT NOT NULL,
    
    -- Coluna "funcao" para armazenar a descrição da funcao (não pode ser nulo)
    telefone TEXT,    
    
    -- Coluna "data_admissao" para armazenar a data de admissao (não pode ser nulo)
    data_admissao TEXT NOT NULL       
);

--Obs: SQLite não possui o tipo data. Logo, deve-se usar TEXT com 
--     tipo um padrão que favoreça a ordenação.: "20/09/2023 21:03:00".