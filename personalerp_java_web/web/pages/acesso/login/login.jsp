<!DOCTYPE html>
<html>
<head>
	<title>Personaler ERP - Orlando Burli Projetos de TI Personalizados</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/web/assets/css/main.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/web/assets/css/login.css">
	<link rel="shortcut icon" type="image/png" href="/web/assets/img/logo.png">

	<!-- Plugin JQuery e JQuery UI-->
	<script src="<%=request.getContextPath() %>/web/assets/jquery-ui/js/jquery-1.9.1.js"></script>
	<script src="<%=request.getContextPath() %>/web/assets/jquery-ui/js/jquery-ui-1.10.3.custom.js"></script>
	<script src="<%=request.getContextPath() %>/web/assets/js/geral.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath() %>/web/assets/js/login.js" type="text/javascript"></script>
</head>
<body>
	<div id="content-all">

		<div id="content-logo">
			<div id="div-logo"></div>

			<div id="info-logo">
				<p>PersonalERP - Orlando Burli</p>
				<p>Projetos de TI Personalizados</p>
				<p>(65) 9946 - 3093</p>
				<p><a href="mailto:contato@orlandoburli.com.br">contato@orlandoburli.com.br</a></p>
			</div>			
		</div>

		<div id="form-login" class="form">
			<div class="form-header">LOGIN</div>
			<div class="form-content">
				<p>
					<label for="Login">Login:</label>
					<input id="Login" type="text" maxlength="20" autofocus="autofocus"/>
				</p>
				<p>
					<label for="Senha">Senha:</label>
					<input id="Senha" type="password" />
				</p>
			</div>
			<div class="form-bottom"></div>
			<div class="form-botoes">
				<button id="Entrar">Entrar</button>
			</div>
		</div>

		<div id="form-perfil" class="form">
			<div class="form-header">PERFIL</div>
			<div class="form-content">
				<p>
					<label for="Perfil">Perfil:</label>
					<select id="Perfil">
						<option value="">[SELECIONE UM PERFIL]</option>
						<option value="1">Administrador</option>
						<option value="2">Auxiliar Estoque</option>
						<option value="3">Auxiliar Financeiro</option>
						<option value="4">Gerente Loja</option>
					</select>
				</p>
			</div>
			<div class="form-bottom"></div>
			<div class="form-botoes">
				<button id="SelecionarPerfil">Avan&ccedil;ar</button>
				<button id="VoltarLogin">Voltar</button>
			</div>
		</div>

		<div id="form-empresa" class="form">
			<div class="form-header">EMPRESA</div>
			<div class="form-content">
				<p>
					<label for="Empresa">Empresa:</label>
					<select id="Empresa">
						<option value="">[SELECIONE UMA EMPRESA]</option>
						<option value="1">EMPRESA 01</option>
						<option value="2">EMPRESA 02</option>
					</select>
				</p>
			</div>
			<div class="form-bottom"></div>
			<div class="form-botoes">
				<button id="SelecionarEmpresa">Avan&ccedil;ar</button>
				<button id="VoltarPerfil">Voltar</button>
			</div>
		</div>

		<div id="form-loja" class="form">
			<div class="form-header">LOJA</div>
			<div class="form-content">
				<p>
					<label for="Loja">Loja:</label>
					<select id="Loja">
						<option value="">[SELECIONE UMA LOJA]</option>
						<option value="1">LOJA 01</option>
						<option value="2">LOJA 02</option>
					</select>
				</p>
			</div>
			<div class="form-bottom"></div>
			<div class="form-botoes">
				<button id="SelecionarLoja">Entrar</button>
				<button id="VoltarEmpresa">Voltar</button>
			</div>
		</div>

		<div id="form-loading" class="form">
			<div class="form-header"></div>
			<div class="form-content form-loading-content">
				<p>
					<label>Por favor aguarde, logando no sistema...</label>
					<div id="div-loading-bar"></div>
				</p>
			</div>
			<div class="form-bottom"></div>
		</div>

		<div id="form-mensagem" class="mensagem">

		</div>

	</div>
</body>
</html>