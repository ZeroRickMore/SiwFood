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




<div id="header"><a th:href="@{/}">Siwfood</a></div>
    <h2>Aggiungi nuovo ingrediente</h2>
	
	
    <form th:action="@{/aggiungiIngrediente}" method="POST" th:object="${nuovoIngrediente}">
		<div th:if="${#fields.hasGlobalErrors()}">
			<p th:each="err : ${#fields.globalErrors()}">
				<span th:text="${err}"></span>
					<span th:if="${err=='Ingrediente già presente nel sistema:'}">
						<a th:href="@{'/ingrediente' + '/' + ${vecchioIngrediente.id}}" th:text="' ' + ${vecchioIngrediente.nome}"> bho?</a>
					</span>
			</p>
		</div>
		<div>
        	Nome*: <input required type="text" th:field="${nuovoIngrediente.nome}">
        	<span th:if="${#fields.hasErrors('nome')}" th:errors="*{nome}"></span>
			<br><br>
			Unità di misura*:
			<select th:field="${nuovoIngrediente.unitàDiMisura}">
				<option th:each="unitàDiMisuraType : ${elencoUnitàDiMisura}" th:value="${unitàDiMisuraType}" th:text="${unitàDiMisuraType}">Unità di misura</option>
			</select>
			<span th:if="${#fields.hasErrors('nome')}" th:errors="*{nome}"></span>
			<br><br>
			Percorso file immagine: <input type="text" th:field="${nuovoIngrediente.image_path}">
        </div>
        <div>
			<span><button type="submit">Conferma</button></span>
		</div>
    </form>


