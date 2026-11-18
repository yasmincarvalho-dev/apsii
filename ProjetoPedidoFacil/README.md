# PedidoFácil — Padrão de Projeto Singleton & SOLID

## 1. Explicação do projeto

O **PedidoFácil** simula o sistema de gerenciamento de uma lanchonete, centralizando o fluxo financeiro, o controle de caixa, o cadastro de produtos e o registro de pedidos de forma segura e consistente. O projeto foi estruturado em equipe para demonstrar boas práticas de Orientação a Objetos em Java.

* **CaixaCentral (Singleton):** Garante uma única instância global de acesso para o caixa e controle financeiro da lanchonete, evitando inconsistências de dados e duplicidade de saldo.
* **Pedido (Modelo / Encapsulamento):** Gerencia os itens solicitados e os cálculos de valores do cliente, mantendo os dados protegidos.
* **Produto (Modelo / Encapsulamento):** Representa os itens comercializados na lanchonete com seus respectivos atributos (nome e preço) devidamente encapsulados.
* **Principal (Aplicação e Execução):** Coordena a interface via console, a entrada de dados e o fluxo principal de execução do sistema.

## Como executar

Requisito: JDK 8 ou superior e uma IDE (Eclipse, IntelliJ, etc.).

1. Importe a pasta do projeto na IDE.
2. Localize a classe principal de execução da aplicação (`Principal.java` dentro do pacote `Produto`).
3. Execute a classe (`Run`) para rodar a demonstração interativa no console.

## 2. Estrutura do projeto

```text
ProjetoPedidoFacil
└── src
    ├── modelo
    │   ├── CaixaCentral.java       (Singleton: gerencia o caixa e faturamento central)
    │   └── Pedido.java             (Encapsulamento: dados do pedido e itens)
    └── Produto
        ├── Produto.java            (Modelo: dados e encapsulamento dos produtos)
        └── Principal.java          (Aplicação: ponto de entrada main e execução via console)
```

## 3. Justificativa do padrão escolhido

### Por que o Singleton é o mais adequado?

O problema: Várias partes do sistema (registro de pedidos, fechamento de contas e consultas financeiras) precisam acessar e modificar o estado financeiro da lanchonete. Se permitíssemos múltiplas instâncias de caixas criadas livremente, perderíamos o controle do faturamento global.

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
| **SRP – Responsabilidade Única** | `CaixaCentral` gerencia exclusivamente o fluxo financeiro; `Pedido` gerencia os itens solicitados; `Produto` guarda apenas os dados do item. Cada classe tem uma única responsabilidade. |
| **OCP – Aberto/Fechado** | O núcleo do caixa está fechado para modificações indesejadas, mas aberto para extensões futuras de novas regras de negócio ou meios de pagamento sem quebrar o código existente. |
| **LSP – Substituição de Liskov** | As classes de modelo respeitam contratos consistentes de estado e comportamento, permitindo extensões seguras no futuro. |
| **ISP – Segregação de Interfaces** | Os métodos e contratos das classes são enxutos e diretos, evitando que componentes dependam de funcionalidades que não utilizam. |
| **DIP – Inversão de Dependências** | A camada de execução (`Principal`) interage com os modelos através de métodos públicos bem definidos, desacoplando a interface de console da regra de negócio interna. |

### Prós e contras do Singleton (neste projeto)

* Respeita o controle estrito de instâncias globais para dados críticos de caixa.
* Evita concorrência e divergência nos valores financeiros computados.
   **Atenção ao escopo global:** O uso excessivo de Singletons pode dificultar testes unitários isolados se o estado global não for limpo adequadamente entre os testes.
