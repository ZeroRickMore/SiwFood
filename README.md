# SiwFood
 Sistema informativo su web per gestione informazioni relative a ricette, ingredienti e cuochi

## Informazioni di dominio ed utilizzo:

### Cos'è un Cuoco?

    Nel sistema, una persona che crea ricette e aggiunge ingredienti.

### Cos'è un Ingrediente?

    Nel sistema, una componente di una Ricetta.

### Cos'è una Ricetta?

    Nel sistema, un insieme di Ingredienti, fatta da uno chef o nessuno
    
### Chi utilizza il sistema?

    Il sistema è composto da tre tipi di utenti:  

    - L'utente generico (non loggato),  
    - Il cuoco (utente registrato, corrisponde a un oggetto "Cuoco" nel sistema),  

    Nota: Il Cuoco, quando si registra, inserisce anche le credenziali del Cuoco che "Rappresenta".  
    L'idea è che un Utente sia un ponte verso l'oggetto Cuoco, di cui si hanno Nome, Cognome, Data di Nascita.  
    Se il Cuoco esiste già (in quanto creato dall'Admin), inserire Nome, Cognome e Data di Nascita di quel Cuoco lo associa DIRETTAMENTE al nuovo utente, senza crearne un doppione o lanciando errori.  
    Se il Cuoco tuttavia è già associato a un Utente, il sistema nega la Registrazione dicendo di Inserire un altro cuoco  

    - L'amministratore  

### Cosa può fare un utente generico?

    - Visualizzare l'elenco di Ingredienti  
    - Visualizzare un singolo Ingrediente  
    - Ricerca Ingrediente per Nome  
    - Visualizzare l'elenco di Ricette  
    - Visualizzare una singola Ricetta  
    - Ricerca Ricetta per Nome  
    - Ricerca Ricette per Ingrediente  
    - Visualuzzare l'elenco di Cuochi  
    - Visualizzare un singolo Cuoco  
    - Ricerca Cuoco per Nome  
    - Ricerca Cuoco per Cognome  


### Cosa può fare un utente registrato (Cuoco)?

    - Tutto quello che può fare l'utente generico  
    - Ogni Editore ha un suo Elenco di Ricette ideate  
    - Può aggiungere una nuova Ricetta a suo nome  
    - Può aggiungere un Ingrediente
    - Può rimuovere una delle sue Ricette dal sistema  
    - Può modificare gli Ingredienti delle sue Ricette

### Cosa può fare un admin?

    - Tutto quello che possono fare gli altri
    - Aggiungere un Cuoco
    - Rimuovere un Cuoco
    - Modificare quali Ricette un Cuoco ha ideato, in caso anche assegnare "Nessun cuoco"
    - Modificare quali Ingredienti compongono una Ricetta, di qualsiasi Cuoco
    - Aggiungere una nuova Ricetta a nome di "Nessun Cuoco" o un altro Cuoco nel sistema
    - Rimuovere Ingredienti dal sistema

### General Features:

    - Controlli per accesso a risorse protette in ogni "step", e non solo sul primo
        Ad esempio, far si che un Cuoco possa vedere solo l'elenco delle proprie ricette per modificarle, non nega che il Cuoco possa giocare con gli URL e fare le giuste richieste per arrivare a risorse protette di altri cuochi senza interfaccia.
    - Diagnostica di errori con BindingResult nella maggior parte delle Form, tranne quando vi era una soluzione più pratica e funzionale
    - CSS e impaginazione di tutte le form
    - REST APIs
    - Transazioni SQL
    - Mapping molto importante per lo /error
    - Controlli backend su tutto, anche sui menu a tendina
        (L'ingrediente si inserisce tramite menu a tendina, ma nulla vieta di modificare la POST. Per dimostrare il funzionamento, modificare la POST nel campo unitàDiMisura e controllare che nella risposta venga stampato "Unità di misura non valida".)
    - Tanti, tanti, tanti <a href> con funzionalità comode all'interno delle pagine
    - Username sempre in alto a destra. Se cliccato, mostra l'elenco delle proprie Ricette
    - Se si prova ad aggiungere qualcosa di già presente nel sistema, viene mostrato un errore tramite BindingResult che linka a quel qualcosa nel sito.

### Known bug:

    In modo abbastanza casuale, ogni tanto, al primo Login quando si inseriscono le credenziali, logga senza problemi, ma redirecta su un json status 999, error none...

### Considerazioni sul progetto:

    Un progetto divertente ed impegnativo, in linea con il corso.
    Spring Boot è uno strumento molto potente, la stretta sintassi aiuta molto nello sviluppo, e Java è un linguaggio che sicuramente rende il tutto più facile e comprensibile.

    La stesura di due progetti, uno individuale ed uno di gruppo introduce ridondanza, tuttavia aiuta a prendere la mano con strumenti come GitHub qualora qualcuno non l'avesse mai utilizzato. 
    Nel mio caso già lo utilizzavo, ma non ho mai avuto l'occasione di fare un vero progetto con qualcuno, quindi è stato sicuramente istruttivo.

    Dal mio punto di vista, è stato molto interessante ed entusisasmante, dopotutto, una volta che si ha la possibilità di creare un proprio sito web, come resistere alla tentazione di metterci qualcosa di simpatico, e nell'implementare quelle cose simpatiche, imparare ancora?

    Personalmente mi sono trovato estremamente bene con il corso, e non avrei particolari annotazioni di modifiche ad esso.

    
