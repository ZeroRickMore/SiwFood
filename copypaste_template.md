# HEADER CUOCO


```
<!DOCTYPE html>
<head>
    <title>SiwFood</title>
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
		<span th:if="${userDetails!=null}">
        	<div class="text_a">
            	Bentornato, <a th:href="@{'/showSelf'}" th:text="${userDetails.username}"></a> !
        	</div>
		</span>
		<span th:if="${userDetails==null}">
			<div class="text">
				Utente ospite
			</div>
		</span>
    </nav>

    <div id="header"><a th:href="@{/}">Siwfood</a></div>
	
	
```




    
		
			 <span th:text="${ricetta.descrizione}"></span>
         </div>
         <br>
         <div>
			 Immagini:
			 <ul>
			  <li th:each="pathImmagine : ${ricetta.tuttiPathDelleImmagini}">
			 	 <img th:src="'/' + ${pathImmagine}" width="20%">	 	
			  </li>
			 </ul>
         </div>
          <div>
			 Ingredienti:
			 <ul>
				 <li th:each="ingrediente : ${listaIngredienti}">
					 <a th:href="@{'/ingrediente' + '/' + ${ingrediente.id}}"
					 th:text="${ingrediente.nome} + ' : '">Nome ingrediente Generico</a>
					 <span th:text="${ricetta.ingrediente2quantity.get(ingrediente)} + ' ' + 
					 				${ingrediente.unitàDiMisura}">Quantità ingrediente generica</span>		 	
				 </li>
			 </ul>
         </div>



