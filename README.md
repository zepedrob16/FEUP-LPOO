# Ad Hunter

## Setup/Instalação
Esta app comporta-se como uma aplicação normal, não necessitando de um conjunto de passos complexos para o seu correto funcionamento.  
É possível testar a app através da transferência e **instalação do .apk** no dispositivo Android pretendido ou através da abertura do **projeto de Android Studio**, sua compilação e sucessiva execução, recorrendo a um emulador.  
Caso requisitado, será possível publicar a aplicação da Play Store, facilitando assim o processo.  
Visto Ad Hunter aceder a Google Play Services e funções de vibração e acelerómetro, poderá resultar em comportamentos inesperados após a execução em desktop, pelo é fortemente desaconselhada optar por esta plataforma.

## Diagrama UML

## Design Patterns
Ao longo do desenvolvimento da aplicação, os seguintes design patterns foram utilizados:
* **Singleton** - é criada uma instância com um ponto de acesso global para facilitar a leitura da lógica do jogo pela parte gráfica, sem propriamente uma instanciação privada.
* **Builder** - os pop-ups são compostos por um background e um foreground, cada um tendo instanciações diferentes, pelo que se implementou uma interface MenuPopup e múltiplas subclasses para cada um dos botões que implementam, da sua forma, estes conceitos.

Referência ainda ao **Decorator** que momentaneamente foi implementado, porém descartado, visto não resultar numa maior facilidade de manutenção do projeto, dado às limitações do libGDX.

## Dificuldades
De longe, a principal dificuldade terá sido a aprendizagem do libGDX e correta integração com o Android Studio. O que outrora seria relativamente simples de implementar (como os serviços da Google Play e JUnit), tornou-se exclusivamente complicado por causa de métodos adicionais de compatibilidade requerida pelo libGDX. Múltiplas horas terão sido gastas exclusivamente na implementação destas funcionalidades.

## Lições aprendidas
