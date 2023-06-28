SELECT  tipo_fase.descricao AS tipo_fase, rota.descricao AS rota,  medidor.descricao AS medidor,
    poste.codigo AS poste,
    pessoa.nome AS nome,
    pessoa.cpf AS cpf,
    pessoa.cnpj AS cnpj,
    classe.descricao AS classe
    FROM
    tipo_fase
    INNER JOIN classe ON classe.id_tipo_fase = tipo_fase.id
    INNER JOIN contrato ON contrato.class_id = classe.id
    INNER JOIN medidor ON medidor.id = contrato.medidor_id
    INNER JOIN rota ON rota.id = medidor.roda_id
    INNER JOIN poste ON poste.id = medidor.poste_id
    INNER JOIN cliente ON cliente.id = contrato.cliente_id
    INNER JOIN pessoa ON pessoa.id = cliente.pessoa_id
    WHERE pessoa.id = 1;