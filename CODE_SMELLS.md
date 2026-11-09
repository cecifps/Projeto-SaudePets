# Análise de code smells

## Escopo e critérios

Análise estática dos modelos Java atualmente presentes em `src/models`. A priorização considera impacto funcional, risco de inconsistência e facilidade de correção. Como não há contexto de execução, persistência ou requisitos de negócio disponível nesta análise, observações condicionais estão identificadas como tal.

**Prioridades:** P1 = tratar antes de depender da funcionalidade em produção; P2 = corrigir antes de ampliar o domínio; P3 = melhoria de manutenção, integração ou testabilidade.

## Achados priorizados

| Prioridade | Achado | Evidências |
|---|---|---|
| P1 | **Operações públicas sem implementação.** Vários métodos que aparentam executar ações do domínio não fazem nada; `consultarAgenda()` devolve sempre uma lista vazia. Chamadores podem receber uma falsa impressão de sucesso sem que os dados sejam registrados, alterados ou consultados. Se essas classes forem apenas um esqueleto didático, deixe essa condição explícita e evite expor os métodos como operações concluídas. | [Administrador.java](./src/models/Administrador.java#L9), [Atendente.java](./src/models/Atendente.java#L9), [Atendimento.java](./src/models/Atendimento.java#L31), [Profissional.java](./src/models/Profissional.java#L23) |
| P1 | **Verificação de conflito com resultado constante.** `verificarConflito()` sempre retorna `false`, sem consultar data, horário, duração, profissional ou outros agendamentos. Assim, a regra aparenta estar validada, mas nunca detecta conflito. Implementar a consulta com os dados necessários ou não disponibilizar a verificação até ela estar pronta. | [Agendamento.java](./src/models/Agendamento.java#L49) |
| P1 | **Senha mantida e comparada diretamente como texto.** `Usuario` guarda a senha recebida e `login()` faz comparação direta. Isso não demonstra armazenamento seguro de credenciais; se o valor vier de uma senha em texto puro ou for persistido assim, uma exposição do objeto ou da base compromete as senhas. Usar um fluxo de autenticação que armazene hashes apropriados e nunca exponha a senha por getter. A análise não encontrou código de persistência para confirmar como os dados são armazenados. | [Usuario.java](./src/models/Usuario.java#L7), [Usuario.java](./src/models/Usuario.java#L13), [Usuario.java](./src/models/Usuario.java#L16), [Usuario.java](./src/models/Usuario.java#L31) |
| P2 | **Estado do agendamento como texto livre.** `status` aceita qualquer `String`, e as transições usam literais como `"Cancelado"` e `"Remarcado"`. Erros de digitação, valores divergentes e transições inválidas não são impedidos. Substituir por um `enum` e centralizar/validar as transições permitidas. | [Agendamento.java](./src/models/Agendamento.java#L15), [Agendamento.java](./src/models/Agendamento.java#L33), [Agendamento.java](./src/models/Agendamento.java#L43) |
| P2 | **Coleções internas mutáveis expostas.** `consultarHistorico()` devolve a lista interna do prontuário, e `getDiasFolga()`/`setDiasFolga()` expõem ou armazenam diretamente a lista recebida. Código externo pode alterar o estado sem passar por regras do modelo, ou modificar a lista original depois de atribuí-la. Retornar cópias imutáveis e copiar a entrada no setter (ou usar operações específicas para alterar as coleções). | [Prontuario.java](./src/models/Prontuario.java#L29), [Profissional.java](./src/models/Profissional.java#L34), [Profissional.java](./src/models/Profissional.java#L35) |
| P2 | **Objetos aceitam dados sem validação.** Os construtores atribuem valores diretamente, sem validar campos obrigatórios, intervalos ou coerência. Por exemplo, não se impede duração negativa de serviço nem horário final anterior ao inicial; esses estados inválidos podem propagar-se para agendamentos e regras futuras. Definir invariantes do domínio e validá-las na criação/alteração. | [Servico.java](./src/models/Servico.java#L9), [Profissional.java](./src/models/Profissional.java#L14), [Agendamento.java](./src/models/Agendamento.java#L19) |
| P3 | **Modelo acoplado à saída do console.** `logout()` imprime diretamente uma mensagem. Isso mistura a regra de domínio com apresentação, dificulta testes e impede que interfaces diferentes controlem a mensagem. Deixar a apresentação a cargo da camada de interface e manter o método focado na operação de domínio, se houver estado de sessão a alterar. | [Usuario.java](./src/models/Usuario.java#L21) |
| P3 | **Acesso ao histórico por cadeia de navegação.** `Cliente.consultarHistorico()` atravessa `Paciente` e `Prontuario` para obter o histórico. Isso acopla o cliente à estrutura interna desses objetos; uma mudança nessa estrutura exige alterar a delegação. Considerar expor uma operação de consulta mais direta no objeto responsável pelo prontuário, se esse fluxo for realmente necessário para `Cliente`. | [Cliente.java](./src/models/Cliente.java#L18) |

## Ordem sugerida

1. Implementar ou retirar as operações sem comportamento e corrigir a verificação de conflito (P1).
2. Revisar o ciclo de vida da senha antes de conectar autenticação a persistência ou uso real (P1).
3. Definir estados válidos e proteger invariantes e coleções do domínio (P2).
4. Separar saída de console e reduzir o acoplamento da consulta de histórico (P3).

## Limitações

Este documento não afirma que os achados foram explorados ou que exista persistência insegura: somente os arquivos de modelo disponíveis foram examinados. Não foram analisados requisitos funcionais, camadas de interface/persistência, testes ou execução do sistema.
