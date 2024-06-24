## Using `Map<>` with Jakarta Persistence

    ```java
    @ElementCollection
    @CollectionTable(name = "my_entity_map", joinColumns = @JoinColumn(name = "my_entity_id"))
    @MapKeyColumn(name = "map_key")
    @Column(name = "map_value")
    private Map<String, Integer> myMap;

    Esempio:
        	@ElementCollection
	        @CollectionTable(name = "RicettaIngrediente2Quantità", joinColumns = @JoinColumn(name = "ricetta_id"))
            @MapKeyColumn(name = "ingrediente")
            @Column(name = "quantità")
	        private Map<String, Integer> ingrediente2quantity;
            ```
            Questo codice genera una nuova tabella RicettaIngrediente2Quantità, con identificatore ricetta_id 
            (identificatore della classe in cui mi trovo) + ingrediente(chiave di Map<>), e un'ultima colonna quantità.