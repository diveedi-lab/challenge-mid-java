# Challenge Java

### Obiettivo
Questo progetto è basato su OpenLiberty e utilizza Java 8.  
Esponiamo un endpoint `/api/v1/persons` che restituisce una lista paginata di Persone, ognuna
delle quali all'interno del database ha una relazione One-To-Many con l'entità Documento.  
In essenza, quello che ti chiediamo è di implementare un nuovo endpoint che restituisca tutti
i Documenti legati ad una Persona. Inoltre nel progetto frontend è presente una tabella contenente la lista di Persone, nella quale dovrai aggiungere un bottone che apre una pagina di dettaglio, con una tabella nella quale mostrare tutti i documenti legati alla persona.  
Attenzione però, nell'ORM utilizzato da questo progetto sono stati introdotti dei bug, che scoprirai già nelle prime fasi dello sviluppo; sta a te scoprirli e correggerli in modo definitivo (ciò significa che dopo una nuova build tutto funzionerà correttamente).

### Avviare il progetto

Per avviare challenge su OpenLibery ti basterà eseguire:
```sh
$ docker-compose up -d
```

Durante l'avvio del container, verrà sempre eseguito `/entrypoint.sh`, che come vedi installa gli artefatti, genera i source e impacchetta i vari progetti, successivamente esegue le migrazioni e avvia il server.  
Esponiamo i resource della challenge sulla porta `:9080`, mentre mappiamo anche la porta `:7777` in modo da poter attaccare il debugger (già configurato se utilizzi Visual Studio Code con "Extension Pack for Java").  

In `/repository` troverai il volume mappato al `~/.m2/repository`.
