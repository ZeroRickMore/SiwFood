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

    - Visualizzare l'elenco di Variant
    - Visualizzare una singola Variant
    - Ricerca Variant per Nome
    - Visualizzare l'elenco di Editori
    - Visualizzare un singolo Editore
    - Ricerca Editori per Nome
    - Ricerca Editori per Nazione
    - Visualuzzare l'elenco di Manga
    - Visualizzare un singolo Manga
    - Ricerca Manga per Titolo
    - Ricerca Manga per Autore


### Cosa può fare un utente registrato (Editore)?

    - Tutto quello che può fare l'utente generico
    - Ogni Editore ha un suo Elenco di Variant pubblicate
    - Può aggiungere una nuova Variant a suo nome
    - Può rimuovere una delle sue Variant dal sistema
    - Può assegnare un Manga ad una delle sue Variant

### Cosa può fare un admin?

    - Tutto quello che possono fare gli altri
    - Aggiungere un Editore
    - Rimuovere un Editore
    - Modificare quali Variant un Editore ha pubblicato
    - Modificare quali Variant sono associate a un Manga
