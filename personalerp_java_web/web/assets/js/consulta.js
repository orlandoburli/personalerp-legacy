console.log("Carregando js da consulta");

// FUNCOES DA DATAGRID

var tempo = 250;

function loadJs(jsFile) {
	setTimeout(function() {
		console.log("Carregando arquivo " + jsFile);
		$.getScript(jsFile).done(function(script, textStatus) {
			console.log("Arquivo " + jsFile + " carregado com sucesso.");
		}).fail(function(jqxhr, settings, exception) {
			console.log("Erro ao carregar js file - " + exception);
		});
	}, tempo * 2);
}

// Funcao que limpa as linhas selecionadas da grid
function limparSelecao() {
	$(".DataGridConsulta > table > tbody > tr").each(function(index) {
		$(this).attr("data-selected", "false");
	});
}

// Retorna o id da linha selecionada
function getSelectedDataId() {
	var retorno = null;

	$(".DataGridConsulta > table > tbody > tr").each(function(index) {
		if ($(this).attr("data-selected") == "true") {
			console.log("achou");
			retorno = $(this).attr("data-id");
		}
	});
	return retorno;
}

function getPageSize() {
	return parseInt($(".DataGridConsulta").attr("data-page-size"));
}

// Retorna a linha selecionada
function getSelectedIndex() {
	var retorno = -1;

	$(".DataGridConsulta > table > tbody > tr").each(function(index) {
		if ($(this).attr("data-selected") == "true") {
			retorno = index;
		}
	});
	return retorno;
}

// Altera a linha selecionada
function setSelectedIndex(selectedIndex) {
	limparSelecao();

	$(".DataGridConsulta > table > tbody > tr").each(function(index) {
		if (index == selectedIndex) {
			$(this).attr("data-selected", "true");
		}
	});
}

function loadDataGrid() {
	var paginaGrid = $(".DataGridConsulta").attr("data-page");
	var pageSize = $(".DataGridConsulta").attr("data-page-size");
	var pageNumber = $(".DataGridConsulta").attr("data-page-number");

	if (!pageNumber) {
		pageNumber = 1;
	}

	var params = {
		'PageNumber' : pageNumber,
		'PageSize' : pageSize
	};

	// Loop nos input's do form para enviar
	$("input,select").each(function(index) {
		params[$(this).attr("id")] = $(this).val();
	});

	$.ajax({
		url : paginaGrid,
		type : 'POST',
		data : params,
		beforeSend : function(data) {
			// console.log("loading...");
		},
		success : function(data) {
			$(".DataGridConsulta").html(data);

			limparSelecao();

			setSelectedIndex(0);

			// FUNCOES DOS BOTOES DE NAVEGACAO

			// Selecao da grid
			$(".DataGridConsulta > table > tbody > tr").click(function() {
				// TODO - Funcionalidade default e de selecao unica, posso implementar
				// ainda a selecao mutipla
				// Varre a tabela para limpar as selecoes

				limparSelecao();

				$(this).attr("data-selected", "true");

			});
		},
		error : function(erro) {
			console.log("Erro no load ajax! " + erro);
		}
	});
}

// FIM DAS FUNCOES DA DATAGRID

$(".BotaoEditar").click(function() {
	editar();
});

$(".BotaoNovo").click(function() {
	novo();
});

$(".BotaoExcluir").click(function() {
	excluir();
});

// Load automatico
loadDataGrid();

$(".BotaoNavegacaoProximo").click(function() {
	proximaPagina();
});

$(".BotaoNavegacaoUltimo").click(function() {
	ultimaPagina();
});

$(".BotaoNavegacaoPrimeiro").click(function() {
	primeiraPagina();
});

$(".BotaoNavegacaoAnterior").click(function() {
	paginaAnterior();
});

$(".BotaoPesquisar").click(function() {
	loadDataGrid();
});

function novo() {
	// TODO Novo 
	// alert("Novo ainda nao implementado!");
	
	var paginaCadastro = $(".DataGridConsulta").attr("data-detail-page");
	var paginaBase = paginaCadastro.split(".")[0];
	var paginaVisualizar = paginaBase + ".visualizar." + paginaCadastro.split(".")[1];

	var params = {
		'operacao' : 'inserir'
	};

	$.ajax({
		url : paginaVisualizar,
		type : 'POST',
		data : params,
		beforeSend : function(data) {
			console.log("loading...");
		},
		success : function(data) {
			var tempo = 250;
			$("#conteudo-formularios").fadeOut(tempo);

			setTimeout(function() {
				$("#conteudo-formularios").html(data);
				$("#conteudo-formularios").fadeIn(tempo);

				// Carrega o js de cadastros
				loadJs("web/assets/js/cadastro.js");
			}, tempo);
		},
		error : function(erro) {
			console.log("Erro no load ajax! " + erro);
		}
	});
}

function editar() {
	var dados = getSelectedDataId();

	var paginaCadastro = $(".DataGridConsulta").attr("data-detail-page");
	var paginaBase = paginaCadastro.split(".")[0];
	var paginaVisualizar = paginaBase + ".visualizar." + paginaCadastro.split(".")[1];

	var params = {
		'operacao' : 'alterar'
	};

	var dadosSplit = dados.split("&");

	for ( var i = 0; i < dadosSplit.length; i++) {
		var campo = dadosSplit[i].split("=")[0];
		var valor = dadosSplit[i].split("=")[1];
		params[campo] = valor;
	}

	$.ajax({
		url : paginaVisualizar,
		type : 'POST',
		data : params,
		beforeSend : function(data) {
			console.log("loading...");
		},
		success : function(data) {
			var tempo = 250;
			$("#conteudo-formularios").fadeOut(tempo);

			setTimeout(function() {
				$("#conteudo-formularios").html(data);
				$("#conteudo-formularios").fadeIn(tempo);

				// Carrega o js de cadastros
				loadJs("web/assets/js/cadastro.js");
			}, tempo);
		},
		error : function(erro) {
			console.log("Erro no load ajax! " + erro);
		}
	});
}

function excluir() {
	confirmacaoModal("Confirma a exclusão deste registro?", "Confirmação", excluirConfirmado);
}

function excluirConfirmado() {

	var dados = getSelectedDataId();

	var paginaCadastro = $(".DataGridConsulta").attr("data-detail-page");
	var paginaBase = paginaCadastro.split(".")[0];
	var paginaExcluir = paginaBase + ".excluir." + paginaCadastro.split(".")[1];

	var params = {
		'operacao' : "excluir"
	};

	// Loop nos input's do form para enviar
	var dadosSplit = dados.split("&");

	for ( var i = 0; i < dadosSplit.length; i++) {
		var campo = dadosSplit[i].split("=")[0];
		var valor = dadosSplit[i].split("=")[1];
		params[campo] = valor;
	}

	$.ajax({
		url : paginaExcluir,
		type : 'POST',
		data : params,
		beforeSend : function(data) {
			// console.log("loading...");
		},
		success : function(data) {

			var retorno = $.parseJSON(data);

			if (retorno.sucesso) {
				mensagemModal(retorno.mensagem, "Confirmação", function() {
					loadDataGrid();
				});
			} else {
				mensagemModal(retorno.mensagem, "Erro", function() {
					$("#" + retorno.fieldFocus).focus();
					$("#" + retorno.fieldFocus + "_cb").focus();
				});
			}
		},
		error : function(erro) {
			console.log("Erro no load ajax! " + erro);
		}
	});

}

function primeiraPagina() {
	var pageNumber = $(".DataGridConsulta").attr("data-page-number");

	pageNumber = 1;

	$(".DataGridConsulta").attr("data-page-number", pageNumber);

	loadDataGrid();
}

function proximaPagina() {
	var pageNumber = $(".DataGridConsulta").attr("data-page-number");
	var pageCount = $(".DataGridConsulta table").attr("data-page-count");

	if (!$.isNumeric(pageCount)) {
		pageCount = 1;
	}

	if (!$.isNumeric(pageNumber)) {
		pageNumber = 1;
	}

	if (parseInt(pageNumber) < parseInt(pageCount)) {
		pageNumber = parseInt(pageNumber) + 1;

		$(".DataGridConsulta").attr("data-page-number", pageNumber);

		loadDataGrid();
	}
}

function ultimaPagina() {
	var pageNumber = $(".DataGridConsulta").attr("data-page-number");
	var pageCount = $(".DataGridConsulta table").attr("data-page-count");

	if (!$.isNumeric(pageCount)) {
		pageCount = 1;
	}

	if (!$.isNumeric(pageNumber)) {
		pageNumber = 1;
	}

	if (parseInt(pageNumber) < parseInt(pageCount)) {
		pageNumber = parseInt(pageCount);

		$(".DataGridConsulta").attr("data-page-number", pageNumber);

		loadDataGrid();
	}
}

function paginaAnterior() {
	var pageNumber = $(".DataGridConsulta").attr("data-page-number");
	var pageCount = $(".DataGridConsulta table").attr("data-page-count");

	if (!$.isNumeric(pageCount)) {
		pageCount = 1;
	}

	if (!$.isNumeric(pageNumber)) {
		pageNumber = 1;
	}

	if (parseInt(pageNumber) > 1) {
		pageNumber = parseInt(pageNumber) - 1;

		$(".DataGridConsulta").attr("data-page-number", pageNumber);

		loadDataGrid();
	}
}

function itemAnterior() {
	var index = getSelectedIndex();

	if (index > 0) {
		index = parseInt(index) - 1;
		setSelectedIndex(index);
	} else {
		paginaAnterior();
	}
}

function proximoItem() {
	var index = getSelectedIndex();

	if (index < (getPageSize() - 1)) {
		index = parseInt(index) + 1;
		setSelectedIndex(index);
	} else {
		proximaPagina();
	}
}

var eventoTeclasConsulta = function(event) {
	switch (event.which) {

	case (KEY_NEXT):
		proximaPagina();
		break;

	case (KEY_PAGEDOWN):
		proximaPagina();
		break;

	case (KEY_PREVIOUS):
		paginaAnterior();
		break;

	case (KEY_PAGEUP):
		paginaAnterior();
		break;

	case (KEY_UP):
		itemAnterior();
		break;

	case (KEY_DOWN):
		proximoItem();
		break;

	case (KEY_ENTER):
		primeiraPagina();
		break;
	
	case (KEY_HOME):
		primeiraPagina();
		break;

	case (KEY_END):
		ultimaPagina();
		break;

	case (KEY_N):
		if (event.ctrlKey) {
			event.preventDefault();
			novo();
		}
		break;
		
	case (KEY_E):
		if (event.ctrlKey) {
			event.preventDefault();
			editar();
		}
		break;
	case (KEY_DEL):
		if (event.ctrlKey) {
			event.preventDefault();
			excluir();
		}
		break;
	}
};

$(document).ready(function() {

	$(document).off("keydown");
	
	// Verifica se é uma tela de consulta
	if ($(".DataGridConsulta")) {
		$(document).on("keydown", eventoTeclasConsulta);
	}
});