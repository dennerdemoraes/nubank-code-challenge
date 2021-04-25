Nubank Code Challenge: Autorizador
===================================

## O que foi feito:
- Utilizei o Guice do Google para gerenciar a injeção de dependências das classes;
- Organizei o código em 3 pastas;
    * application - é um lugar para interfaces funcionais, helpers e configurações, no caso usei somente para separa o arquivo de JsonParser;
    * core - é onde agrupei as regras de negócio, use cases e as entidades que criei pra solução;
    * entrypoint - é onde separei a classe que faz a leitura do arquivo e que escolhe qual use case vai ser executado dependendo do tipo do evento;
- No EventFileReader eu optei por separar o evento por tipo (account e transaction) e pra isso fiz um iterator dos tipos que são esperados por essa aplicação;
- Criei dois use cases, um pra cada tipo de evento que fazem o tratamento adequado e aplicam as regras de acordo com o tipo do evento;
- As regras de negócio foram criadas respeitando uma interface sendo possível acrescentar novas regras se necessário;

## Linguagem
* Java 11

## Build e Run
**Linux e MacOS**
```bash
# build
$ ./gradlew build

# run
$ java -jar app/build/libs/authorize.jar {file}
```
