# Guia de Contribuição

Obrigado por considerar contribuir com o projeto **kafka-sample**! Este documento descreve o fluxo de trabalho recomendado para propor mudanças de código, documentação e testes.

## Workflow de branches

O projeto utiliza branches de curta duração, sempre baseadas em `master`:

- `feature/<descrição-curta>` — novas funcionalidades
- `fix/<descrição-curta>` — correções de bugs
- `chore/<descrição-curta>` — tarefas de manutenção, configuração, CI/CD
- `docs/<descrição-curta>` — alterações apenas em documentação

Fluxo básico:

1. Criar uma branch a partir de `master` atualizada.
2. Implementar a mudança acompanhada de testes.
3. Abrir um Pull Request (PR) para `master`.
4. Obter pelo menos **1 aprovação** e aguardar o CI passar.
5. Fazer merge via GitHub (preferencialmente *squash merge*).

## Conventional Commits

Todas as mensagens de commit devem seguir o padrão [Conventional Commits](https://www.conventionalcommits.org/pt-br/v1.0.0/):

- `feat:` — nova funcionalidade
- `fix:` — correção de bug
- `chore:` — tarefas de manutenção
- `docs:` — alterações em documentação
- `test:` — adição ou ajuste de testes
- `refactor:` — refatoração sem mudança de comportamento
- `ci:` — alterações em pipelines de CI/CD

Exemplos:

```
feat(producer): adicionar endpoint de envio em lote
fix(consumer): tratar mensagens nulas no callback
chore: atualizar dependências do Spring Boot
```

## Processo de code review

- PRs devem ter no mínimo **1 aprovação** antes do merge.
- Revisores devem verificar: cobertura de testes, aderência ao style guide, impacto em documentação e compatibilidade com Java 1.8.
- Use *conversations* do GitHub para discutir pontos específicos do código; evite discussões longas fora do PR.

## Como criar PRs

1. Use título descritivo seguindo Conventional Commits (ex.: `feat: adicionar endpoint /healthz`).
2. Preencha o template de PR (`.github/pull_request_template.md`).
3. Vincule a issue relacionada usando `Closes #<número>`.
4. Garanta que `mvn -B verify` passa localmente antes de subir.
5. Inclua screenshots ou logs quando a mudança for visível/observável.

## Testes

- Os testes unitários ficam em `src/test/java/br/com/sample/kafka/` e usam **JUnit 5** + **Mockito**.
- Cobertura é coletada pelo **JaCoCo** durante `mvn verify`.
- Evite testes que dependam de broker Kafka real; prefira mocks (`KafkaTemplate`, `KafkaConsumer`) ou `@EmbeddedKafka` quando for realmente necessário.

## Lint & Formatação

- `make lint` roda Checkstyle e SpotBugs.
- `.editorconfig` define regras básicas (4 espaços, UTF-8, LF).
- O padrão de estilo segue uma versão simplificada do [Google Java Style](https://google.github.io/styleguide/javaguide.html).

## Branch protection (instruções para o mantenedor)

No GitHub, acesse **Settings → Branches → Add rule** para `master` e habilite:

- *Require pull request reviews before merging* (mínimo **1 approver**).
- *Require status checks to pass before merging* (selecionar o check `CI / build`).
- *Require branches to be up to date before merging*.
- *Include administrators*.

Essas configurações garantem que nenhuma mudança entre em `master` sem revisão e sem passar no CI.
