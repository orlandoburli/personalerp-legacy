console.log("Carregando arquivo cadastro.js");

$(function() {

	// Carrega uma pagina
	function loadPage(pagina) {
		console.log(pagina);
		$("#conteudo-formularios").fadeOut(tempo);
		setTimeout(function() {
			$("#conteudo-formularios").load(pagina);
			setTimeout(function() {
				$("#conteudo-formularios").fadeIn(tempo);
			}, tempo);
		}, tempo);
	}

	// Carrega um arquivo .css
	function loadCSS(href) {
		setTimeout(function() {
			var cssLink = $("<link>");
			$("head").append(cssLink); // IE hack: append before setting href

			cssLink.attr({
				rel : "stylesheet",
				type : "text/css",
				href : href
			}, tempo * 4);
		});
	}

	// Carrega um arquivo .js
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

	$(".BotaoSalvar").click(function() {
		salvar();
	});

	$(".BotaoExcluir").click(function() {
		excluir();
	});

	$(".BotaoVoltar").click(function() {
		voltar();
	})

	// Funcao de salvar o registro
	function salvar() {
		var paginaCadastro = $(".FormularioCadastro").attr("data-page-cadastro");
		var paginaBase = paginaCadastro.split(".")[0];
		var operacao = $(".FormularioCadastro").attr("data-page-operacao");
		var paginaFinal = paginaBase + "." + operacao + "." + paginaCadastro.split(".")[1];

		var params = {
			'operacao' : operacao
		};

		// Loop nos input's do form para enviar
		$("input,select,textarea").each(function(index) {
			params[$(this).attr("id")] = $(this).val();
		});

		$.ajax({
			url : paginaFinal,
			type : 'POST',
			data : params,
			beforeSend : function(data) {
				// console.log("loading...");
			},
			success : function(data) {

				var retorno = $.parseJSON(data);

				if (retorno.sucesso) {
					// mensagemModal(retorno.mensagem, "Confirmação", function() {
					voltar();
					// });
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

	// Funcao de excluir o registro
	function excluir() {
		confirmacaoModal("Confirma a exclusão deste registro?", "Confirmação", excluirConfirmado);
	}

	function excluirConfirmado() {
		var paginaCadastro = $(".FormularioCadastro").attr("data-page-cadastro");
		var paginaBase = paginaCadastro.split(".")[0];
		var operacao = "excluir";
		var paginaFinal = paginaBase + "." + operacao + "." + paginaCadastro.split(".")[1];

		var params = {
			'operacao' : operacao
		};

		// Loop nos input's do form para enviar
		$("input,select").each(function(index) {
			params[$(this).attr("id")] = $(this).val();
		});

		$.ajax({
			url : paginaFinal,
			type : 'POST',
			data : params,
			beforeSend : function(data) {
				// console.log("loading...");
			},
			success : function(data) {

				var retorno = $.parseJSON(data);

				if (retorno.sucesso) {
					mensagemModal(retorno.mensagem, "Confirmação", function() {
						voltar();
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

	// Volta para a pagina de consulta
	function voltar() {
		var pageConsulta = $(".FormularioCadastro").attr("data-page-consulta");
		loadPage(pageConsulta);
		loadJs("web/assets/js/consulta.js");
	}

	// Funcao das teclas de cadastro
	var eventoTeclasCadastro = function(event) {
		
		switch (event.which) {
		
		case (KEY_ESC):
			voltar();
			break;

		case (KEY_S):
			if (event.ctrlKey) {
				event.preventDefault();
				salvar();
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

	// Seleciona os input's SELECT de acordo com a custom tag data-field-value
	$("select").each(function() {

		var val = $(this).attr("data-field-value");
		
		// console.log($(this).attr("id") + " = " + val);
		
		if (val != null && val != "") {
			$(this).val(val);
		}
	});

	// Adiciona um option vazio ao select do tipo autocomplete
	$( "select[data-field-type='autocomplete']" ).prepend("<option value=\"\">NENHUM</option>");

	// Transforma os input's select em combobox autocomplete
	$( "select[data-field-type='autocomplete']" ).combobox();

	// Funcao keydown para tratar as teclas de cadastro
	$(document).ready(function() {
		$(document).off("keydown");
		
		// Verifica se é uma tela de consulta
		if ($(".FormularioCadastro")) {
			$(document).on("keydown", eventoTeclasCadastro);
		}
	});

	// Funcao para input's numéricos - Formata e coloca a máscara
	$("input[data-field-type='number']").each(function() {
		var decimais = $(this).attr("data-field-precision");

		var valueNumber = parseFloat($(this).val());
		valueNumber = valueNumber * (Math.pow(10, decimais));

		$(this).val(valueNumber);

		$(this).priceFormat({
			clearPrefix: true,
		    prefix: '',
		    centsSeparator: ',',
		    thousandsSeparator: '',
		    centsLimit : parseInt(decimais)
		});
	});
	

	// Funcao ENTER funcionar como TAB em input's
	textboxes = $("input:visible, select:visible, textarea:visible");

	if (browser.mozilla) {
		$(textboxes).keypress(checkForEnter);
	} else {
		$(textboxes).keydown(checkForEnter);
	}

	function checkForEnter(event) {
		if (event.keyCode == 13) {
			currentBoxNumber = textboxes.index(this);

			if (textboxes[currentBoxNumber + 1] != null) {
				nextBox = textboxes[currentBoxNumber + 1]
				nextBox.focus();
				nextBox.select();
				event.preventDefault();
				return false;
			}
		}
	}
});