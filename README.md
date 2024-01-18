# Challenge Tecnica

## Obiettivo
### Backend
È presente un endpoint `/api/v1/persons` che restituisce una lista paginata di Persone, ognuna delle quali all'interno del database ha una relazione One-To-Many con l'entità Documento.  
Ti chiediamo di implementare un nuovo endpoint che restituisca tutti i Documenti legati a una Persona.

### Frontend
È presente una tabella contenente la lista di Persone nella quale dovrai aggiungere un bottone che apre una modale di dettaglio.\
In questa sezione dovrai mostrare tutti i documenti legati alla persona attraverso una tabella.

### Attenzione
Nel ORM utilizzato da questo progetto sono stati introdotti dei bug, che scoprirai già nelle prime fasi dello sviluppo; sta a te scoprirli e correggerli in modo definitivo (ciò significa che dopo una nuova build tutto funzionerà correttamente).\
Potrebbe essere necessaria anche l'aggiunta di qualche configurazione specifica.

## Java
Il progetto di backend è basato su OpenLiberty e utilizza Java 8.

### Avviare il progetto
Per avviare la challenge su OpenLiberty ti basterà eseguire:
```sh
$ docker-compose up -d
```
Per comodità forniamo lo script in `scripts/restart` per riavviare il container dell'app, che eseguirà una nuova build del backend.  
Se vuoi entrare nel container provvediamo anche lo script in `scripts/shell` che puoi eseguire dopo l'avvio del container `app`.

Durante l'avvio del container, verrà sempre eseguito `/entrypoint.sh`, che come vedi installa gli artefatti, genera i source e impacchetta i vari progetti, successivamente esegue le migrazioni e avvia il server.  
Esponiamo i resource della challenge sulla porta `:9080`, mentre mappiamo anche la porta `:7777` in modo da poter attaccare il debugger.
L'attach del debugger è già configurato se utilizzi i principali IDE:
- Intellij IDEA come run configuration
- Visual Studio Code (installando l'estensione "Extension Pack for Java")  

In `/repository` troverai il volume mappato al `~/.m2/repository`.

## Angular
Il progetto di frontend è basato su Angular CLI 6.2.9.

### Avviare il progetto
Per avviare la challenge su Angular ti basterà eseguire, nella folder del progetto frontend, questo comando:
```sh
$ ng serve
```
e navigare su http://localhost:4200/. L'applicazione caricherà in automatico ogni tuo cambiamento nei file sorgenti.
