# Desafio GitHub
Por Alan Camargo

## Resumo
O projeto é um desafio técnico para listar numa tela os usuários do GitHub e, ao clicar ou buscar 
por um usuário específico, exibir seus detalhes e seus repositórios públicos numa outra tela.

## Arquitetura
A arquitetura escolhida foi a MVVM, também usando MVI, clean architecture e os princípios SOLID.

O app está modularizado por funcionalidade.

### Módulos
- app
  Módulo principal do app. Somente executa e gerencia os demais.

- core
  Ferramentas essenciais para o funcionamento do app como logger, provedor de API, provedor de
  banco de dados, etc.

- core-design
  Recursos e ferramentas de UI utilizados no app inteiro.

- core-test
  Ferramentas de teste unitário e de UI utilizadas no app inteiro.

- data
  Entidades da camada de dados utilizadas (ou potencialmente utilizadas) por um ou mais módulos.
  Contém também mapeamentos para seus equivalentes na camada de domínio.

- domain
  Entidades da camada de domínio utilizadas (ou potencialmente utilizadas) por um ou mais módulo.
  Este módulo não deve conter nenhuma dependência externa.

- feature-users
  Funcionalidade de exibição de usuários e suas dependências internas.

- navigation
  Interfaces de navegação entre telas.

## Testes

### Testes unitários
Todas as camadas do aplicativo estão cobertas por testes unitários validando diferentes cenários.

### Testes de UI
Todas as telas (activities e fragments) do módulo `feature-users` estão cobertas por testes de UI 
cobrindo diferentes cenários. Os testes estão estruturados de acordo com o padrão Robots, o que 
facilita na leitura, manutenção e mitigação de possíveis problemas.
