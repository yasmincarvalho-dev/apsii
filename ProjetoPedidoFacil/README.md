# PedidoFácil — Padrão de Projeto Singleton & SOLID

## 1. Explicação do projeto

O **PedidoFácil** simula o sistema de gerenciamento de uma lanchonete, centralizando o fluxo financeiro, o controle de caixa e o registro de pedidos de forma segura e consistente. O projeto foi estruturado em equipe para demonstrar boas práticas de Orientação a Objetos em Java.

* **CaixaCentral (Singleton):** Garante uma única instância global de acesso para o caixa e controle financeiro da lanchonete, evitando inconsistências de dados.
* **Pedido (Modelo / Encapsulamento):** Gerencia os itens solicitados e os cálculos de valores do cliente, mantendo os dados protegidos.
* **Aplicação e Execução:** Coordena a interface com o usuário, fluxo de menus e chamadas do sistema (responsabilidade da equipe de integração).

## Como executar

Requisito: JDK 8 ou superior e uma IDE (Eclipse, IntelliJ, etc.).

1. Importe a pasta do projeto na IDE.
2. Localize a classe principal de execução da aplicação (responsável pelo `main` e pelo menu interativo).
3. Execute a classe (`Run`) para rodar a demonstração completa no console.

## 2. Estrutura do projeto

```text
ProjetoPedidoFacil
└── src
    └── modelo
        ├── CaixaCentral.java       (Singleton: gerencia o caixa e faturamento central)
        └── Pedido.java             (Encapsulamento: dados do pedido e itens)
```

## 3. Justificativa do padrão escolhido

### Por que o Singleton é o mais adequado?

O problema: Várias partes do sistema (pedidos, pagamentos, fechamento de caixa) precisam acessar e modificar o estado financeiro da lanchonete. Se permitíssemos múltiplas instâncias de caixas criadas livremente, perderíamos o controle do faturamento global.

* Com o Singleton (`CaixaCentral`):
  * Garantimos que existe **apenas um único objeto de caixa** ativo em toda a aplicação.
  * Fornecemos um ponto de acesso global e controlado (`getInstancia()`).
  * Protegemos os dados financeiros contra instâncias duplicadas ou corrompidas.

### Outros padrões que poderiam ser considerados

| Padrão / Alternativa | Serviria? | Comentário |
| :--- | :--- | :--- |
| **Strategy** | Sim, complementar | Poderia ser aplicado para alternar entre diferentes formas de pagamento ou descontos, mas o núcleo do projeto precisava estritamente de uma única instância de caixa global (Singleton). |
| **Monostate** | Sim | Compartilha o estado entre instâncias através de variáveis estáticas, mas ainda permite criar vários objetos desnecessários na memória. O Singleton é mais restritivo e seguro. |
| **Factory Method** | Não é o ideal | Foca na criação de famílias de objetos, mas não resolve o problema de unicidade e controle global do caixa financeiro. |
| **Facade** | Complementar | Poderia unificar o subsistema de pedidos, mas a centralização do caixa exige estritamente a restrição de uma única instância (Singleton). |

## 4. Como os princípios SOLID foram aplicados

| Princípio | Onde aparece no projeto |
| :--- | :--- |
| **SRP – Responsabilidade Única** | `CaixaCentral` gerencia exclusivamente o fluxo financeiro e o estado do caixa; `Pedido` cuida apenas dos dados e itens da compra. Cada classe tem uma única razão para mudar. |
| **OCP – Aberto/Fechado** | O núcleo do caixa está fechado para modificações indesejadas, mas aberto para extensões futuras de novas formas de pagamento ou tipos de pedidos através de interfaces. |
| **LSP – Substituição de Liskov** | As classes e estruturas de dados respeitam contratos consistentes, garantindo que componentes derivados possam substituir os genéricos sem quebrar o sistema. |
| **ISP – Segregação de Interfaces** | Métodos e contratos são enxutos, evitando que classes implementem comportamentos desnecessários para a regra de negócio da lanchonete. |
| **DIP – Inversão de Dependências** | Módulos de alto nível dependem de abstrações, desacoplando a lógica central de detalhes de implementação de baixo nível. |

### Prós e contras do Singleton (neste projeto)

* Respeita o controle estrito de instâncias globais para dados críticos de caixa.
* Evita concorrência e divergência nos valores financeiros computados.
* ⚠️ **Atenção ao escopo global:** O uso excessivo de Singletons pode dificultar testes unitários isolados se o estado global não for limpo adequadamente entre os testes.
