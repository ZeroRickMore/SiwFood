# HEADER CUOCO


```
<!DOCTYPE html>
<head>
    <title>SiwFood - Aggiungi ingrediente</title>
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/index.css">
</head>
<body>

	<nav th:if="${userDetails!=null}">
	
		<div class="buttons">
    		<span class="button">
       	 		<a href="/logout">Esci</a>
    		</span>
            	
		</div>
        
		<div class="text_a">
    		Bentornato, <a th:href="@{'/showSelf'}" th:text="${userDetails.username}"></a> !
		</div>
	
	</nav>
	
	<nav th:if="${userDetails==null}">
	
		<div class="buttons">
			<span class="button">
				<a href="/register">Registrati</a>
			</span>
			<span class="button">
				<a href="/login">Accedi</a>
			</span>
		</div>
		        
		<div class="text_a">
			<a id="a_no_animations" th:href="@{'/register'}">Benvenuto in SiwFood !</a>
		</div>
		        
	</nav>
	
	<div id="header"><a th:href="@{/}">Siwfood</a></div>
	
	
```


<!DOCTYPE html>
<html>
    <head>
        <title>SiwFood Login</title>
        <link rel = "stylesheet" type = "text/css" href = "/css/stile.css">
    </head>
    <body>
        <h2>Login utente:</h2>
        <form th:action="@{/login}" method="POST"> 
            <label for="username">Username:</label><br>
            <input type = "text" id="username" name="username" required>
                <br><br>
            <label for="password">Password:</label><br>
            <input type = "password" id="password" name="password" required>
                <br><br>
            <div>
                <span th:if="${param.error != null}">
                    <span>Username o password errati!</span>
                </span>
            </div>
            <input type = "submit" value = "Accedi">
        </form>
    </body>
</html>

















<!DOCTYPE html>
<head>
    <title>SiwFood - Aggiungi ricetta</title>
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/index.css">
</head>
<body>

	<nav th:if="${userDetails!=null}">
	
		<div class="buttons">
    		<span class="button">
       	 		<a href="/logout">Esci</a>
    		</span>
            	
		</div>
        
		<div class="text_a">
    		Bentornato, <a th:href="@{'/showSelf'}" th:text="${userDetails.username}"></a> !
		</div>
	
	</nav>
	
	<nav th:if="${userDetails==null}">
	
		<div class="buttons">
			<span class="button">
				<a href="/register">Registrati</a>
			</span>
			<span class="button">
				<a href="/login">Accedi</a>
			</span>
		</div>
		
		<div class="text_a">
			<a th:href="@{'/register'}" text="Benvenuto in SiwFood !"></a>
		</div>
		        
	</nav>
	
	<div id="header"><a th:href="@{/}">Siwfood</a></div>

		<div id="menu_container">
        <div class="choice_menu">
                        
            <div class="titolo_choice_menu">
                            
                <a th:if="${param.error==null}" th:href="@{'/login'}">Accedi</a>
				<a th:if="${param.error!=null}" th:href="@{'/login'}">Credenziali errate</a>
				
            </div> 
                        
            <div class="split_half">     
                <div class="text_form">
                    <ul>
						<li>
							<form th:action="@{/login}" method="POST">
								
								<div>
									<br>
							        <span>Username*:<br></span>
									<input type="text" name="username" required>
									<br><br>
									<span>Password*:<br></span>
									<input type="password" name="password" required>
									<div><br></div>
								</div>
								
							    <div>
									<button class="submit" type="submit">Conferma</button>
								</div>
								
							</form>
						</li>
					</ul>
                </div>
            </div>
        </div>

</body>

</html>






































<!DOCTYPE html>
<html>
<head>
    <title>Siwvariant - Pagina nuova ricetta</title>
	<link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&display=swap" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/css/index.css">
</head>
<body>

    <nav>
        <div class="buttons">
            <span class="button">
                <a href="/register">Registrati</a>
            </span>
            <span class="button">
                <a href="/login">Accedi</a>
            </span>
        </div>
        <div class="text">
            Benvenuto, Rick!
        </div>
    </nav>

    <div id="header"><a th:href="@{/}">Siwfood</a></div>
    <h2>Aggiungi nuova ricetta</h2>
    <form th:action="@{/admin/aggiungiRicettaCompleta}" method="POST" th:object="${nuovaRicetta}">
		<div th:if="${#fields.hasGlobalErrors()}">
			<p th:each="err : ${#fields.globalErrors()}">
				<span th:text="${err}"></span>
					<span th:if="${err=='Ricetta già presente nel sistema:'}">
						<a th:href="@{'/ricetta' + '/' + ${vecchiaRicetta.id}}" th:text="' ' + ${vecchiaRicetta.nomeRicetta}"> bho?</a>
					</span>
			</p>
		</div>
		<div>
        	Nome*: <input required type="text" th:field="${nuovaRicetta.nomeRicetta}">
        	<span th:if="${#fields.hasErrors('nomeRicetta')}" th:errors="${nuovaRicetta.nomeRicetta}"></span>
			<br><br>
        	Descrizione: <input type="text" th:field="${nuovaRicetta.descrizione}">
        	<span th:if="${#fields.hasErrors('descrizione')}" th:errors="*{descrizione}"></span>
			<br><br>
			Percorso dei file immagine: <input type="text" th:field="${nuovaRicetta.tuttiPathDelleImmaginiString}">
			<br><br>
			Cuoco*:
			<select th:field="${cuoco.nome}">
				<option value="Nessun cuoco" text="Nessun cuoco">Nessun cuoco</option>
				<option th:each="line : ${elencoNomeCognomeData}" th:value="${line}" th:text="${line}">Nome - Cognome - DataNascita</option>
			</select>
			
        </div>
        <div>
			<span><button type="submit">Conferma</button></span>
		</div>
    </form>
</body>
</html>

				







	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<h2>Ingredienti nella ricetta</h2>
    <div>
        <div th:if="${allIngredientiMessi==null || allIngredientiMessi.isEmpty()}">
            Questa ricetta non ha nessun ingrediente
        </div>
        <div th:unless="${allIngredientiMessi==null || allIngredientiMessi.isEmpty()}">
        	<ul>
            	<li th:each="ingrediente : ${allIngredientiMessi}">
                	<a th:href="@{'/removeIngrediente' + '/' + ${ricettaId} + '/' + ${ingrediente.id}}" th:text="${ingrediente.nome} + ' [' + ${ricetta.ingrediente2quantity.get(ingrediente)} + ']'">Goku Ingrediente</a>
            	</li>
        	</ul>
        </div>
    </div>
	<h2>Ingredienti disponibili</h2>
	<div>
		<span th:if="${qtyError}">
			<span th:if="${qtyError == ''}">
				<h1>Inserire una quantità maggiore di zero!</h1>
			</span>
		</span>
	       <div th:if="${allIngredientiDisponibili==null || allIngredientiDisponibili.isEmpty()}">
	            Non ci sono più ingredienti disponibili
	        </div>
	        <div th:unless="${allIngredientiDisponibili==null || allIngredientiDisponibili.isEmpty()}">
	        	<ul>
	            	<li th:each="ingrediente : ${allIngredientiDisponibili}">
						
						<form th:action="@{'/addIngrediente' + '/' + ${ricettaId} + '/' + ${ingrediente.id}}" method="post">
							<span th:text="${ingrediente.nome}"></span>
						    <input type="number" name="ingredienteQuantity">
							
						    <button type="submit">Aggiungi alla ricetta</button>
						</form>
	            	</li>
	        	</ul>
	        </div>
	    </div>
</body>

</html>
















<!DOCTYPE html>
<head>
    <title>SiwFood - Aggiungi ingrediente</title>
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/index.css">
</head>
<body>

	<nav th:if="${userDetails!=null}">
	
		<div class="buttons">
    		<span class="button">
       	 		<a href="/logout">Esci</a>
    		</span>
            	
		</div>
        
		<div class="text_a">
    		Bentornato, admin <a th:href="@{'/showSelf'}" th:text="${userDetails.username}"></a> !
		</div>
	
	</nav>
	
	<div id="header"><a th:href="@{/}">Siwfood</a></div>
	
	
	<div id="menu_container">
	        <div class="choice_menu">
	                        
	            <div class="titolo_choice_menu">
	                            
	                <a th:href="@{'/aggiungiIngrediente'}">Rimuovi ingrediente</a>

	            </div>
	            <div class="split_half">
	                <img src="/css/fruit_basket_upscayl.jpg" width="20%"> 
	            </div>
	                        
	            <div class="split_half">     
	                <div class="text_form">
	                    <ul>
							<li>
								<form th:action="@{/admin/rimuoviIngrediente}" method="POST" th:object="${ingredienteDaRimuovere}">
										<div th:if="${#fields.hasGlobalErrors()}">
											<p th:each="err : ${#fields.globalErrors()}">
												<span th:text="${err}"></span>
											</p>
										</div>
										</div>
										<div>
											<br>
								        	<span class="match_input_offset"> Nome*:<br></span>
											<input required type="text" th:field="${ingredienteDaRimuovere.nome}">
											<span th:if="${#fields.hasErrors('nome')}" th:errors="${ingredienteDaRimuovere.nome}"></span>
											<br><br>
											<span class="match_input_offset">Unità di misura*:</span>
											<select th:field="${ingredienteDaRimuovere.unitàDiMisura}">
												<option th:each="unitàDiMisuraType : ${elencoUnitàDiMisura}" th:value="${unitàDiMisuraType}" th:text="${unitàDiMisuraType}">Unità di misura</option>
											</select>
											<div><br></div>
										</div>
							
							
								        <div>
											<span><button class="submit" type="submit">Conferma</button></span>
										</div>
								    </form>
							</li>
								
						</ul>
	                </div>
	            </div>
	        </div>
	    

	</body>

	</html>














    
	
	
	
	
	
	
	
	
	
	
	
	
    <h2>Rimuovi ingrediente esistente</h2>
    <form th:action="@{/admin/rimuoviIngrediente}" method="POST" th:object="${ingredienteDaRimuovere}">
		<div th:if="${#fields.hasGlobalErrors()}">
			<p th:each="err : ${#fields.globalErrors()}">
				<span th:text="${err}"></span>
			</p>
		</div>
		<div>
        	Nome*: <input required type="text" th:field="${ingredienteDaRimuovere.nome}">
        	<span th:if="${#fields.hasErrors('nome')}" th:errors="*{nome}"></span>
			<span th:if="${#fields.hasErrors('nome')}" th:errors="*{nome}"></span>
			<br><br>
			Unità di misura*:
			<select th:field="${ingredienteDaRimuovere.unitàDiMisura}">
				<option th:each="unitàDiMisuraType : ${elencoUnitàDiMisura}" th:value="${unitàDiMisuraType}" th:text="${unitàDiMisuraType}">Unità di misura</option>
			</select>
        </div>
        <div>
			<span><button type="submit">Conferma</button></span>
		</div>
    </form>
</body>
</html>

























	
	
	
	
	
	
    <h2>Rimuovi ricetta esistente</h2>
    <form th:action="@{/admin/rimuoviRicetta}" method="POST" th:object="${ricettaDaRimuovere}">
		<div th:if="${#fields.hasGlobalErrors()}">
			<p th:each="err : ${#fields.globalErrors()}">
				<span th:text="${err}"></span>
			</p>
		</div>
		<div>
        	Nome*: <input required type="text" th:field="${ricettaDaRimuovere.nomeRicetta}">
        	<span th:if="${#fields.hasErrors('nomeRicetta')}" th:errors="${ricettaDaRimuovere.nomeRicetta}"></span>
			<br><br>
			<h2>Informazioni cuoco</h2>
			Cuoco:
			<select th:field="${cuoco.nome}">
				<option value="Nessun cuoco" text="Nessun cuoco">Nessun cuoco</option>
				<option th:each="line : ${elencoNomeCognomeData}" th:value="${line}" th:text="${line}">Nome - Cognome - DataNascita</option>
			</select>
        </div>
        <div>
			<span><button type="submit">Conferma</button></span>
		</div>
    </form>
</body>
</html>












<h2>Aggiungi nuovo cuoco</h2>
    <form th:action="@{/admin/aggiungiCuoco}" method="POST" th:object="${nuovoCuoco}">
		<div th:if="${#fields.hasGlobalErrors()}">
			<p th:each="err : ${#fields.globalErrors()}">
				<span th:text="${err}"></span>
					<span th:if="${err=='Cuoco già presente nel sistema:'}">
						<a th:href="@{'/cuoco' + '/' + ${vecchioCuoco.id}}" th:text="' ' + ${vecchioCuoco.nome} + ' ' + ${vecchioCuoco.cognome}"> bho?</a>
					</span>
			</p>
		</div>
		<div>
        	Nome*: <input required type="text" th:field="${nuovoCuoco.nome}">
        	<span th:if="${#fields.hasErrors('nome')}" th:errors="*{nome}"></span>
			<br><br>
        	Cognome*: <input required type="text" th:field="${nuovoCuoco.cognome}">
        	<span th:if="${#fields.hasErrors('cognome')}" th:errors="*{cognome}"></span>
			<br><br>
			Data di nascita*: <input required type="date" th:field="${nuovoCuoco.dataNascita}">
			<span th:if="${#fields.hasErrors('dataNascita')}" th:errors="*{dataNascita}"></span>
			<br><br>
			Percorso file immagine: <input type="text" th:field="${nuovoCuoco.fotografia_path}">
        </div>
        <div>
			<span><button type="submit">Conferma</button></span>
		</div>
    </form>
</body>
</html>
















<!DOCTYPE html>
<head>
    <title>SiwFood - Aggiungi ingrediente</title>
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/index.css">
</head>
<body>

	<nav th:if="${userDetails!=null}">
	
		<div class="buttons">
    		<span class="button">
       	 		<a href="/logout">Esci</a>
    		</span>
            	
		</div>
        
		<div class="text_a">
    		Bentornato, admin <a th:href="@{'/showSelf'}" th:text="${userDetails.username}"></a> !
		</div>
	
	</nav>
	
	<div id="header"><a th:href="@{/}">Siwfood</a></div>
	
	
	
	
	
    <div>
        <img src="https://static.bandainamcoent.eu/high/dragon-ball/dragonball-xenoverse-2/00-page-setup/dbxv2_game-thumbnail.jpg" width="20%" />
    </div>
	<h2>Ricette del cuoco <a th:href="@{'/cuoco' + '/' + ${cuoco.id}}" th:text="${cuoco.nome} + ' ' + ${cuoco.cognome}"></a></h2>
    <div>
        <div th:if="${allRicetteMesse==null || allRicetteMesse.isEmpty()}">
            Questo cuoco non ha ideato nessuna ricetta
        </div>
        <div th:unless="${allRicetteMesse==null || allRicetteMesse.isEmpty()}">
        	<ul>
            	<li th:each="ricetta : ${allRicetteMesse}">
                	<a th:href="@{'/admin/removeRicetta' + '/' + ${cuoco.id} + '/' + ${ricetta.id}}" th:text="${ricetta.nomeRicetta}">Goku Ricetta</a>
            	</li>
        	</ul>
        </div>
    </div>
	<h2>Ricette disponibili</h2>
	<div>
	       <div th:if="${allRicetteDisponibili==null || allRicetteDisponibili.isEmpty()}">
	            Questo cuoco ha ideato tutte le ricette nel sistema!
	        </div>
	        <div th:unless="${allRicetteDisponibili==null || allRicetteDisponibili.isEmpty()}">
	        	<ul>
	            	<li th:each="ricetta : ${allRicetteDisponibili}">
	                	<a th:href="@{'/admin/addRicetta' + '/' + ${cuoco.id} + '/' + ${ricetta.id}}" th:text="${ricetta.nomeRicetta}">Goku Ricetta</a>
	            	</li>
	        	</ul>
	        </div>
	    </div>
</body>

</html>