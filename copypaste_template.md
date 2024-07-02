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





























<input type="text" th:field="${credentials.username}" required>
										<span class="match_input_offset_err" th:if="${#fields.hasErrors('username')}" th:errors="${credentials.username}"></span>
										<br><br>
										<span class="match_input_offset"> Password*:<br></span>
										<input type="password" th:field="${credentials.password}" required>
										<span class="match_input_offset_err" th:if="${#fields.hasErrors('password')}" th:errors="${credentials.password}"></span>
										<br><br>
										<span class="match_input_offset"> Nome*:<br></span>
										<input required type="text" th:field="${cuoco.nome}">
										<span class="match_input_offset_err" th:if="${cuocoErrors != null and cuocoErrors.hasFieldErrors('nome')}" th:errors="${cuoco.nome}"></span>
										<br><br>
										<span class="match_input_offset"> Cognome*:<br></span>
										<input required type="text" th:field="${cuoco.cognome}">
										<span class="match_input_offset_err" th:if="${cuocoErrors != null and cuocoErrors.hasFieldErrors('cognome')}" th:errors="${cuoco.cognome}"></span>
										<br><br>
										<span class="match_input_offset"> Data di nascita*:</span>
										<input required type="date" th:field="${cuoco.dataNascita}">
										<span class="match_input_offset_err" th:if="${cuocoErrors != null and cuocoErrors.hasFieldErrors('dataNascita')}" th:errors="${cuoco.dataNascita}"></span>
										<br><br>
										<span class="match_input_offset"> Percorso file immagine*:<br></span>
										<input type="text" th:field="${cuoco.fotografia_path}">