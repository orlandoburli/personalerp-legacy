<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>Personal ERP - Orlando Burli Projetos de TI Personalizados</title>
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/web/assets/css/main.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/web/assets/css/home.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/web/assets/css/formularios.css"/>
	<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath() %>/web/assets/img/logo.png"/>

	<!-- Plugin JQuery e JQuery UI-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/web/assets/jquery-ui/css/redmond/jquery-ui-1.10.3.custom.css"/>
	
	<script src="<%=request.getContextPath() %>/web/assets/jquery-ui/js/jquery-1.9.1.js"></script>
	<script src="<%=request.getContextPath() %>/web/assets/jquery-ui/js/jquery-ui-1.10.3.custom.js"></script>
	<script src="<%=request.getContextPath() %>/web/assets/jquery-ui/js/jquery.price_format.1.8.min.js"></script>
	<script src="<%=request.getContextPath() %>/web/assets/js/geral.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath() %>/web/assets/js/home.js" type="text/javascript"></script>
	
</head>
<body>
	
	<header>
		<div id="content-logo">
			<div id="div-logo"></div>

			<div id="info-logo">
				<p>PersonalERP - Orlando Burli</p>
				<p>Projetos de TI Personalizados</p>
				<p>(65) 9946 - 3093</p>
				<p><a href="mailto:contato@orlandoburli.com.br">contato@orlandoburli.com.br</a></p>
			</div>
		</div>

		<div>
			<a href="#" class="link-sair"></a>
			<div class="div-link-sair">
				<nav>
					<li><a data-value="trocar-loja" href="#">Trocar Loja</a></li>
					<li><a data-value="sair" href="#">Sair</a></li>
				</nav>
			</div>
		</div>
	
		<div id="box-acesso-rapido">
			<label for="AcessoRapido" accesskey="r">Acesso R&aacute;pido</label>
			<input type="text" id="AcessoRapido"/>
			<button id="ButtonAcessoRapido">Ir</button>
		</div>
	</header>
	
	<nav id="menu-lateral">
		${menuString}
	</nav>
	
	<section id="conteudo-formularios">
		<!-- Secao de conteudo / formularios -->
	</section>

	<section>
		<div id="div-message"></div>
	</section>
	
	<footer>
		<hr/>
		<span id="NomeUsuario">${usuariosessao.nomeUsuario}</span>
		<span>| Perfil:</span>
		<span id="NomePerfil">${perfilsessao.nomePerfil }</span>
		<span>| Empresa: </span>
		<span id="NomeEmpresa">${empresasessao.razaoSocialEmpresa}</span>
		<span>| Loja: </span>
		<span id="NomeLoja">${lojasessao.nomeLoja}</span>
		<span>| Vers&atilde;o: </span>
		<span id="VersaoSistema">${versao.numeroVersao} - <fmt:formatDate value="${versao.dataVersao}" type="both" pattern="dd/MM/yyyy"/></span>
	</footer>
	
</body>
</html>