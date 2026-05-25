Diferente do serviço principal que utiliza a Arquitetura Hexagonal (Ports & Adapters) para isolar regras complexas, para este serviço de auditoria escolhi a Arquitetura em Camadas (Layered Architecture / MVC adaptado para eventos)**.


Simplicidade e Manutenibilidade
Este serviço possui uma única responsabilidade muito bem definida: ler de uma fila e salvar no banco. Aplicar a complexidade de múltiplos pacotes, portas, adaptadores e mappers de uma Arquitetura Hexagonal aqui resultaria em overengineering (desenvolvimento sobredimensionado)

O fluxo de dados é unidirecional e linear

Baixa Variabilidade e Coesão
   
   É improvável que as tecnologias deste serviço mudem.
   
   Ao manter as definições de mapeamento de banco de dados (JPA) e de consumo SQS próximas, reduzimos a quantidade de classes repetitivas (DTO > BO > Entity) para apenas o necessário, economizando processamento e código.

Arquitetura Baseada em Recursos (Resource-based)
   A triagem de severidade é uma regra puramente associada ao próprio recurso de auditoria (`AuditMessage`), estando encapsulada em seu método fábrica (`buildFromPayload`). Assim, a lógica de negócio permanece coesa no modelo sem espalhar-se pela aplicação.

Deve Retornar
{
  "errorId": "id_servico",
  "queueName": "T03N_eduardo_rubel",
  "payload": "{}",
  "timestamp": "2026-05-25T12:00:00Z",
  "status": "PENDING_ANALYSIS",
  "severity": "LOW"
}
