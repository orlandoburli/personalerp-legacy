var janelaAtiva = "";

$(function() {

	var tempo  = 250;
	var isLoading = false;

	// Funcao principal de Login
	function entrar() {
		if ($("#Login").val().length <= 0 || $("#Senha").val().length <= 0) {
			showMessage("Informe o usu&aacute;rio / senha");
			$("#Login").focus();
		} else {
			var params = {
				'login' : $("#Login").val(),
				'senha' : $("#Senha").val()
			};

			$.ajax({
				url : "login.login.erp",
				type : "POST",
				data : params,

				beforeSend : function(data) {
					console.log("loading...");
				},

				success : function(data) {
					var retorno = $.parseJSON(data);

					if (retorno.sucesso) {
						if (retorno.mensagem == "LOGIN_OK") {
							finalLogin();
						} else if (retorno.mensagem == "SELECIONAR_PERFIL") {
							exibirPerfil();
						} else if (retorno.mensagem == "SELECIONAR_EMPRESA") {
							exibirEmpresa();
						} else if (retorno.mensagem == "SELECIONAR_LOJA") {
							exibirLoja();
						}
					} else {
						showMessage(retorno.mensagem);
						$("#Login").focus();
					}
				},
				error : function(erro) {
					console.log("Erro no load ajax! " + erro);
				}
			});
		}
	}

	// Exibe seleção de perfil
	function exibirPerfil() {
		trocar("#form-login", "#form-perfil");
		focus("#Perfil");

		var params = {};

		// Carrega os perfis
		$.ajax({
			url : "login.perfis.erp",
			type : "POST",

			beforeSend : function(data) {
				console.log("loading...");
			},

			success : function(data) {
				var retorno = $.parseJSON(data);

				fillSelect("#Perfil", retorno, "CodigoEmpresa;CodigoLoja;CodigoPerfil", "NomePerfil", "");

				$("#Perfil").focus();
			},
			error : function(erro) {
				console.log("Erro no load ajax! " + erro);
			}
		});
	}

	// Selecao de perfil
	function selecionarPerfil() {
		var codigoEmpresaPerfil = $("#Perfil").val().split(";")[0];
		var codigoLojaPerfil    = $("#Perfil").val().split(";")[1];
		var codigoPerfil        = $("#Perfil").val().split(";")[2];
		
		var params = {
			'perfil.CodigoEmpresa' : codigoEmpresaPerfil,
			'perfil.CodigoLoja'    : codigoLojaPerfil,
			'perfil.CodigoPerfil'  : codigoPerfil
		};

		if ($("#Perfil").val().length <= 0) {
			showMessage("Selecione um perfil");
			focus("#Perfil");
		} else {
			$.ajax ({
				url : "login.perfil.erp",
				type : "POST",
				data : params,

				beforeSend : function(data) {
					console.log("loading...");
				},

				success : function(data) {
					console.log(data);

					var retorno = $.parseJSON(data);

					if (retorno.sucesso) {
						exibirEmpresa();
					} else {
						showMessage(retorno.mensagem);
					}
				},
				error : function(erro) {
					console.log("Erro no load ajax! " + erro);
				}
			});
		}
	}

	// Exibe seleção de empresas
	function exibirEmpresa() {
		trocar(janelaAtiva, "#form-empresa");
		focus("#Empresa");

		// Carrega as empresas
		$.ajax({
			url : "login.empresas.erp",
			type : "POST",

			beforeSend : function(data) {
				console.log("loading...");
			},

			success : function(data) {
				var retorno = $.parseJSON(data);

				fillSelect("#Empresa", retorno, "CodigoEmpresa", "RazaoSocialEmpresa", "");

				$("#Perfil").focus();
			},
			error : function(erro) {
				console.log("Erro no load ajax! " + erro);
			}
		});
	}

	// Selecao de empresa
	function selecionarEmpresa() {
		if ($("#Empresa").val().length <= 0) {
			showMessage("Selecione uma empresa");
			focus("#Empresa");
		} else {
			var params = {
				'empresa.CodigoEmpresa' : $("#Empresa").val()
			};

			$.ajax ({
				url : "login.empresa.erp",
				type : "POST",
				data : params,

				beforeSend : function(data) {
					console.log("loading...");
				},

				success : function(data) {
					console.log(data);

					var retorno = $.parseJSON(data);

					if (retorno.sucesso) {
						exibirLoja();
					} else {
						showMessage(retorno.mensagem);
					}
				},
				error : function(erro) {
					console.log("Erro no load ajax! " + erro);
				}
			});

		}
	}

	// Exibe seleção de lojas
	function exibirLoja() {
		trocar(janelaAtiva, "#form-loja");
		focus("#Loja");

		// Carrega as lojas
		$.ajax({
			url : "login.lojas.erp",
			type : "POST",

			beforeSend : function(data) {
				console.log("loading...");
			},
			success : function(data) {
				var retorno = $.parseJSON(data);

				fillSelect("#Loja", retorno, "CodigoEmpresa;CodigoLoja;", "NomeLoja", "");

				$("#Loja").focus();
			},
			error : function(erro) {
				console.log("Erro no load ajax! " + erro);
			}
		});
	}

	// Seleciona a loja
	function selecionarLoja() {
		if ($("#Empresa").val().length <= 0) {
			showMessage("Selecione uma loja");
			focus("#Empresa");
		} else {

			var params = {
				'loja.CodigoEmpresa' : $("#Loja").val().split(";")[0],
				'loja.CodigoLoja' : $("#Loja").val().split(";")[1]
			};

			$.ajax ({
				url : "login.loja.erp",
				type : "POST",
				data : params,

				beforeSend : function(data) {
					console.log("loading...");
				},

				success : function(data) {
					console.log(data);

					var retorno = $.parseJSON(data);

					if (retorno.sucesso) {
						finalLogin();
					} else {
						showMessage(retorno.mensagem);
						isLoading = false;
					}
				},
				error : function(erro) {
					console.log("Erro no load ajax! " + erro);
				}
			});

			// trocar("#form-empresa", "#form-loja");
			// focus("#Loja");
		}
	}

	function finalLogin() {
		trocar(janelaAtiva, "#form-loading");

		var params = {};

		$.ajax ({
			url : "login.load.erp",
			type : "POST",
			data : params,
			beforeSend : function(data) {
				console.log("loading...");
			},
			success : function(data) {
				console.log(data);

				var retorno = $.parseJSON(data);

				if (retorno.sucesso) {
					// TODO Redirect
					$(window).attr("location", "home.erp");
				} else {
					showMessage(retorno.mensagem);
					isLoading = false;
					trocar("#form-login");
				}
			},
			error : function(erro) {
				console.log("Erro no load ajax! " + erro);
			}
		});
	}

	// Volta para a tela de login
	function voltarLogin() {
		trocar(janelaAtiva, "#form-login");
		focus("#Login");
	}

	// Volta para a tela de perfil
	function voltarPerfil() {
		trocar(janelaAtiva, "#form-perfil");
		focus("#Perfil");
	}

	// Volta para a tela de empresa
	function voltarEmpresa() {
		trocar(janelaAtiva, "#form-empresa");
		focus("#Empresa");
	}

	// *************************************
	//                EVENTOS
	// *************************************

	// Clique do login - valida se preencheu usuario e senha
	$("#Entrar").click(function() {
		entrar();
	});

	$("#SelecionarPerfil").click(function() {
		selecionarPerfil();
	});

	// Voltar para a tela de login
	$("#VoltarLogin").click(function() {
		voltarLogin();
	});

	// Avancar para a tela de selecao de empresa
	$("#SelecionarEmpresa").click(function() {
		selecionarEmpresa();
	});

	// Volta para a selecao de perfil
	$("#VoltarPerfil").click(function() {
		voltarPerfil();
	});

	// Avancar para a selecao de loja
	$("#SelecionarLoja").click(function() {
		selecionarLoja();
	});

	// Volta para selecionar a empresa
	$("#VoltarEmpresa").click(function() {
		voltarEmpresa();
	});

	// Clique do botao entrar2 - Fim do login
	// $("#Entrar2").click(function() {
	// 	finalLogin();
	// });

	// Esconde uma janela e mostra outra
	function trocar(janelaHide, janelaShow) {
		$(janelaHide).fadeOut(tempo);
		
		setTimeout(function() {
			$(janelaShow).fadeIn(tempo);
			janelaAtiva = janelaShow;
		}, tempo);
	};

	// function trocar(janelaShow) {
	// 	trocar(janelaAtiva, janelaShow);
	// }

	// Exibe a mensagem de erro por 3 segundos
	function showMessage(messageText) {
		$("#form-mensagem").html(messageText);
		$("#form-mensagem").fadeIn(tempo);

		setTimeout(function() {
			$("#form-mensagem").fadeOut(tempo);
		}, 3000);
	}

	// Coloca o foco num componente, com um delay
	function focus(componenteId) {
		setTimeout(function() {
			$(componenteId).focus();
		}, tempo);
	}

	// 
	// Eventos de teclas dos inputs
	// 

	$("#Login").keydown(function(event) {
		if (event.which == 13) {
			$("#Senha").focus();
		}
	});

	$("#Senha").keydown(function(event) {
		if (event.which == 13) {
			$("#Entrar").click();
		}
	});

	$("#Perfil").keydown(function(event) {
		if (event.which == 13) {
			$("#SelecionarPerfil").click();
		} else if (event.which == 27) {
			$("#VoltarLogin").click();
		}
	});

	$("#Empresa").keydown(function(event) {
		if (event.which == 13) {
			$("#SelecionarEmpresa").click();
		} else if (event.which == 27) {
			$("#VoltarPerfil").click();
		}
	});

	$("#Loja").keydown(function(event) {
		if (!isLoading) {
			if (event.which == 13) {
				$("#SelecionarLoja").click();
				isLoading = true;
			} else if (event.which == 27) {
				$("#VoltarEmpresa").click();
			}
		}
	});

	$("#form-perfil").hide();
	$("#form-empresa").hide();
	$("#form-loja").hide();
	$("#form-loading").hide();
	$("#form-mensagem").hide();

	$("#Login").focus();
});