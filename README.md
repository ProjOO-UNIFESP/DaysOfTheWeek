# Comportamento por Dia da Semana

Programa orientado a objetos que identifica o dia atual da semana e **delega** a
execução de uma ação a uma estratégia específica daquele dia, aplicando os
padrões de projeto **Strategy** e **Null Object**.

> Disciplina: Projeto Orientado a Objetos — Unifesp (2026/1).
> O código (classes, métodos, comentários) está em inglês; as mensagens
> exibidas ao usuário estão em português, conforme o exemplo do enunciado.

## Funcionalidades

- Identifica o **dia atual** a partir da data do sistema (`java.time.LocalDate`).
- Associa **uma estratégia para cada dia** da semana.
- Cada estratégia recebe uma **informação adicional** do usuário (tarefa/meta).
- Permite **consultar manualmente** qualquer dia, sem depender da data atual.
- Trata **dias inválidos com segurança**, sem interromper a execução.
- Cada estratégia informa uma **prioridade**: ALTA, MÉDIA ou BAIXA.

## Como executar

### No VS Code (recomendado)

1. Instale a extensão **Extension Pack for Java** (Microsoft).
2. Abra a pasta do projeto (`DaysOfTheWeek`) no VS Code.
3. Abra `src/daysoftheweek/Main.java` e clique em **Run** (▶) acima do `main`.
4. Responda às perguntas no painel **Terminal** integrado.

### Pelo terminal

```bash
# a partir da raiz do projeto
javac -d bin src/daysoftheweek/*.java
java -cp bin daysoftheweek.Main
```

> Requer JDK 8 ou superior. Não há dependências externas.

## Estrutura das estratégias

| Componente | Arquivo(s) | Papel |
| --- | --- | --- |
| Abstração da estratégia | `DayStrategy.java` | Interface comum a todos os comportamentos. |
| Estratégias concretas | `MondayStrategy` … `SundayStrategy` | Um comportamento por dia da semana. |
| Estratégia nula | `UndefinedStrategy.java` | **Null Object**: ausência segura de comportamento. |
| Seletor | `StrategySelector.java` | Mapeia o dia → estratégia (sem cadeia de `if`). |
| Apoio | `Weekday`, `Priority`, `Behavior` | Dias, prioridades e o resultado (mensagem + prioridade). |

**Fluxo:** `Main` obtém o dia (atual ou digitado) → `StrategySelector` devolve a
estratégia correspondente (ou a `UndefinedStrategy`) → a estratégia produz um
`Behavior` (mensagem + prioridade) → `Main` exibe o resultado.

Não há `if`/`switch` escolhendo a mensagem de cada dia: a seleção é feita por um
`EnumMap<Weekday, DayStrategy>`. Para adicionar ou alterar um comportamento basta
criar/editar uma classe de estratégia e registrá-la no `StrategySelector` —
o restante do programa não muda (princípio aberto/fechado).

## Exemplos de execução

> O bloco ">> Comportamento sugerido para hoje" depende da data em que o
> programa é executado. As consultas manuais (`quarta`, `feriado`, ...) são
> determinísticas.

### 1. Entrada válida

```
=== Comportamento por Dia da Semana ===
Informe seu nome: Ana
Informe uma informação adicional (tarefa pendente ou meta semanal): Implementar relatório

>> Comportamento sugerido para hoje:
Usuário: Ana
Dia consultado: sexta-feira
Prioridade: MÉDIA
Mensagem: Fechamento da semana: registre o que foi concluído sobre "Implementar relatório".

Consultar outro dia? Digite o nome do dia (ou 'sair' para encerrar): quarta
Usuário: Ana
Dia consultado: quarta-feira
Prioridade: MÉDIA
Mensagem: Dia de revisão: verifique o andamento da atividade "Implementar relatório".

Consultar outro dia? Digite o nome do dia (ou 'sair' para encerrar): sair

Encerrado. Bons estudos!
```

### 2. Entrada inválida / sem estratégia associada

```
=== Comportamento por Dia da Semana ===
Informe seu nome: João
Informe uma informação adicional (tarefa pendente ou meta semanal): Estudar para a prova

>> Comportamento sugerido para hoje:
Usuário: João
Dia consultado: sexta-feira
Prioridade: MÉDIA
Mensagem: Fechamento da semana: registre o que foi concluído sobre "Estudar para a prova".

Consultar outro dia? Digite o nome do dia (ou 'sair' para encerrar): feriado
Usuário: João
Dia consultado: feriado
Prioridade: BAIXA
Mensagem: Não há estratégia associada ao dia informado. Nenhuma ação será executada para "Estudar para a prova".

Consultar outro dia? Digite o nome do dia (ou 'sair' para encerrar): sair

Encerrado. Bons estudos!
```

## Questões de reflexão

**1. Como evitar verificações repetidas de valores nulos no código principal?**

O seletor (`StrategySelector`) **nunca devolve `null`**. Quando não existe
estratégia para o dia informado, ele retorna uma instância de
`UndefinedStrategy`, que implementa a mesma interface `DayStrategy`. Assim, o
código principal sempre recebe um objeto válido e pode chamar `execute(...)`
diretamente, sem espalhar `if (estrategia != null)` pelo programa.

**2. Qual padrão de projeto pode ser utilizado para representar a ausência de
uma estratégia de forma segura?**

O padrão **Null Object** (Objeto Nulo). Ele complementa o **Strategy**: além das
estratégias concretas, existe uma estratégia "vazia" que representa, de forma
polimórfica e segura, a ausência de comportamento.

**3. Explique brevemente como esse padrão seria incorporado à solução.**

Cria-se a classe `UndefinedStrategy`, que implementa `DayStrategy` e, em vez de
executar uma ação real, devolve um `Behavior` informativo (mensagem segura +
prioridade `BAIXA`). O `StrategySelector` usa essa instância como valor padrão
(`getOrDefault(dia, fallback)` e `orElse(fallback)`), de modo que dias inválidos
ou sem estratégia associada são tratados sem ramificações condicionais e sem
risco de `NullPointerException`.

## Autores

- _(preencher nome — RA)_
- _(preencher nome — RA)_
