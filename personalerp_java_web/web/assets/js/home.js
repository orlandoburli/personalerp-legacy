var tempo = 250;

console.log("Inicio load home.js");

function loadDataLink(dataLink) {
	if (dataLink == "null") {
		// console.log("Link nulo!");
	} else {
		if (dataLink != null && dataLink != "null" && dataLink != "") {
			
			loadPage(dataLink);
			
			if (dataLink.indexOf("cadastro") > 0) {
				loadJs("web/assets/js/cadastro.js");
			} else if (dataLink.indexOf("consulta") > 0) {
				loadJs("web/assets/js/consulta.js");
			}
		} else {
			clearPage();
		}
	}
}

// Carrega uma pagina no corpo
function loadPage(pagina) {
	console.log("Carregando página " + pagina);

	$("#conteudo-formularios").fadeOut(tempo);
	setTimeout(function() {
		$("#conteudo-formularios").load(pagina);
		setTimeout(function() {
			$("#conteudo-formularios").fadeIn(tempo);
		}, tempo);
	}, tempo);		
}

// Limpa conteudo do form
function clearPage(pagina) {
	$("#conteudo-formularios").fadeOut(tempo);
	setTimeout(function() {
		$("#conteudo-formularios").html("");
		setTimeout(function() {
			$("#conteudo-formularios").fadeIn(tempo);
		}, tempo);
	}, tempo);		
}

// Carrega um arquivo css
function loadCSS(href) {
	setTimeout(function() {
		var cssLink = $("<link>");
  	$("head").append(cssLink); //IE hack: append before setting href

  	cssLink.attr({
  		rel:  "stylesheet",
  		type: "text/css",
  		href: href
  	}, tempo * 4);
  });
}

// Carrega um arquivo js
function loadJs(jsFile) {
	setTimeout(function() {
		console.log("Carregando arquivo " + jsFile);
		$.getScript(jsFile)
		.done(function(script, textStatus) {
			console.log( "Arquivo " + jsFile + " carregado com sucesso.");
		})
		.fail(function(jqxhr, settings, exception) {
			console.log("Erro ao carregar js file - " + exception);
		});
	}, tempo * 2);
}

$(function() {
	

	// Funcoes de navegacao
	$("#menu-lateral li a").click(function() {
		var dataLink = $(this).attr("data-link");
		
		loadDataLink(dataLink);
	});

	textboxes = $("input, select, textarea");

	function checkForEnter (event) {

		if (event.keyCode == 13) {

			currentBoxNumber = textboxes.index(this);

			if (textboxes[currentBoxNumber + 1] != null) {
				nextBox = textboxes[currentBoxNumber + 1]
				nextBox.focus();
				event.preventDefault();
				return false;
			}
		}
	}

	if (browser.mozilla) {
		$(textboxes).keypress(checkForEnter);
	} else {
		$(textboxes).keydown(checkForEnter);
	}

	// Monta menu
	$("#menu-lateral").menu();
	
	// Auto-complete do acesso rapido
	$("#AcessoRapido").autocomplete({
      source: function( request, response ) {
        $.ajax({
          url: "home.buscamenu.erp",
          data: {
            term: request.term
          },
          success: function( data ) {
           	var parsed = $.parseJSON(data);
           	var newArray = new Array(parsed.length);
            var i = 0;

            parsed.forEach(function (entry) {
                var newObject = {
                    label: entry.NomeMenu,
                    nomeMenu : entry.NomeMenu,
                    nomeObjeto : entry.NomeObjeto
                };
                newArray[i] = newObject;
                i++;
            });
            response(newArray);
          }
        });
      },
      // minLength: 2,
      select: function( event, ui ) {
        loadDataLink(ui.item.nomeObjeto);
      },
      open: function() {
        $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
      },
      close: function() {
        $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
      }
    });

	console.log("Fim load home.js");
});