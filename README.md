# SaúdePets

Projeto Java de domínio para uma clínica veterinária. O código representa
usuários e seus papéis, clientes, pacientes, serviços, atendimentos, prontuários
e agendamentos. A camada `services` oferece cadastro, busca, listagem,
cancelamento e persistência em CSV dos agendamentos.

## Requisitos

- JDK 11 ou superior
- Maven 3.8 ou superior

## Compilar e executar os testes

Na raiz do projeto, execute:

```bash
mvn test
```

O comando compila as classes em `src/` e executa os testes JUnit 5 em `test/`.
Para gerar o pacote compilado, use `mvn package`. Como o projeto ainda não tem
uma interface de linha de comando ou tela, ele é uma biblioteca de domínio e
não possui um comando para iniciar uma aplicação interativa.

## Uso básico

```java
Servico consulta = new Servico(1, "Consulta", "Consulta veterinária", 30);
Agendamento agendamento = new Agendamento(
    1, LocalDate.of(2026, 12, 1), LocalTime.of(9, 0), "Confirmado", consulta);

AgendamentoService agenda = new AgendamentoService();
agenda.cadastrar(agendamento);
agenda.buscar(1);

ArquivoAgendamentoRepository arquivo =
    new ArquivoAgendamentoRepository(Path.of("dados/agendamentos.csv"));
arquivo.salvar(agenda.listar());
```

Importe as classes com `models.*` e `services.*` e os tipos de data com
`java.time.*`. O repositório cria a pasta de destino quando necessário. Um
arquivo inexistente é tratado como uma agenda vazia.

## Organização

- `src/models`: entidades do domínio.
- `src/services`: regras de gestão e persistência de agendamentos.
- `src/exceptions`: exceções específicas e validações reutilizáveis.
- `test`: testes unitários JUnit 5.

## Exceções

`ValidacaoException` descreve dados ausentes ou inválidos;
`AgendamentoException` descreve operações incompatíveis ou conflitos de
agenda; `PersistenciaException` informa erros ao ler ou gravar o CSV e mantém a
causa original para diagnóstico. As mensagens indicam o campo ou operação que
precisa ser corrigida.
