# Projeto-SaudePets
# Projeto SaúdePets

## Tratamento de exceções

As validações dos modelos lançam `exceptions.ValidacaoException` (subclasse de
`IllegalArgumentException`) quando um dado obrigatório está ausente ou inválido.
Operações incompatíveis com o estado de um agendamento lançam
`exceptions.AgendamentoException` (subclasse de `IllegalStateException`).
As mensagens identificam o campo ou a operação que precisa ser corrigida.

Chamadores podem capturar as exceções específicas para apresentar mensagens na
interface e deixar exceções não previstas seguirem para o tratamento global da
aplicação. Falhas esperadas não são silenciosamente ignoradas.
