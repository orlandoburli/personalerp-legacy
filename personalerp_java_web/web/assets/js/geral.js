var browser = {
	chrome: false,
	mozilla: false,
	opera: false,
	msie: false,
	safari: false
};

$(function() {
	var link_sair_visivel = false;
	
	$(".link-sair").click(function() {
		if (link_sair_visivel) {
			$(".div-link-sair").fadeOut();
			link_sair_visivel = false;
		} else {
			$(".div-link-sair").fadeIn();
			link_sair_visivel = true;
		}
	});

	$(".div-link-sair").hide();	

	var sBrowser, sUsrAg = navigator.userAgent;
	if(sUsrAg.indexOf("Chrome") > -1) {
		browser.chrome = true;
	} else if (sUsrAg.indexOf("Safari") > -1) {
		browser.safari = true;
	} else if (sUsrAg.indexOf("Opera") > -1) {
		browser.opera = true;
	} else if (sUsrAg.indexOf("Firefox") > -1) {
		browser.mozilla = true;
	} else if (sUsrAg.indexOf("MSIE") > -1) {
		browser.msie = true;
	}
	
  $( document ).tooltip();
});

function mensagemModal(mensagem, titulo) {
  mensagemModal(mensagem, titulo, null);
}

function mensagemModal(mensagem, titulo, funcaoRetorno) {

	$("#div-message").attr("title", titulo);

	$("#div-message").html( mensagem);

  $( "#div-message" ).dialog({
    modal: true,
    buttons: {
      Ok: function() {
        $( this ).dialog( "close" );

        if (funcaoRetorno) {
         funcaoRetorno();
       }

     }
   }
 });
}

function confirmacaoModal(mensagem, titulo, funcaoSim) {
  confirmacaoModal(mensagem, titulo, funcaoSim, null);
}

function confirmacaoModal(mensagem, titulo, funcaoSim, funcaoNao) {

  $("#div-message").attr("title", titulo);

  $("#div-message").html( mensagem);

  $( "#div-message" ).dialog({
    modal: true,
    buttons: {
      "Sim": function() {
        $( this ).dialog( "close" );

        if (funcaoSim) {
          funcaoSim();
        }

      },
      "Não" : function() {
        $( this ).dialog( "close" );

        if (funcaoNao) {
          funcaoNao();
        }
      }
    }
  });
}

function fillSelect(selectElement, values, valueKey, textKey, defaultValue) {
  if (typeof (selectElement) == "string") {
    selectElement = $(selectElement);
  }

  selectElement.empty();

  if (typeof (values) == 'object') {
    if (values.length) {

      var type = typeof (values[0]);
      var html = "";

      if (type == 'object') {
                // values is array of hashes
                var optionElement = null;

                $.each(values, function () {
                  var value = "";
                  var row = this;

                  if (valueKey.indexOf(";") >= 0) {
                    var keys = valueKey.split(";");

                    $.each(keys, function() {

                      if (row[this] != null) {
                        value += row[this] + ";";
                      } else {
                        value += "";
                      }
                    });

                  } else {
                    value = this[valueKey] == null ? "" :this[valueKey];
                  }

                  html += '<option value="' + value + '">' + this[textKey] + '</option>';                  
                });

              } else {
                // array of strings
                $.each(values, function () {
                  var value = this.toString();
                  html += '<option value="' + value + '">' + value + '</option>';                    
                });
              }
              selectElement.append(html);
            }

        // select the defaultValue is one was passed in
        if (typeof defaultValue != 'undefined') {
          selectElement.children('option[value="' + defaultValue + '"]').attr('selected', 'selected');
        }
      }
      return false;
    }


    // Widget combobox auto-complete jQuery UI
    (function( $ ) {
      $.widget( "custom.combobox", {
        _create: function() {
          this.wrapper = $( "<span>" )
          .addClass( "custom-combobox" )
          .insertAfter( this.element );

          this.element.hide();
          this._createAutocomplete();
          this._createShowAllButton();
        },

        _createAutocomplete: function() {
          var selected = this.element.children( ":selected" ),
          value = selected.val() ? selected.text() : "";

          this.input = $( "<input>" )
          .appendTo( this.wrapper )
          .val( value )
          .attr("id", this.element.attr("id") + "_cb")
          .attr( "title", "" )
          .addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
          .autocomplete({
            delay: 0,
            minLength: 0,
            source: $.proxy( this, "_source" )
          })
          .tooltip({
            tooltipClass: "ui-state-highlight"
          });

          this._on( this.input, {
            autocompleteselect: function( event, ui ) {
              ui.item.option.selected = true;
              this._trigger( "select", event, {
                item: ui.item.option
              });
            },

            autocompletechange: "_removeIfInvalid"
          });
        },

        _createShowAllButton: function() {
          var input = this.input,
          wasOpen = false;

          $( "<a>" )
          .attr( "tabIndex", -1 )
          .attr( "title", "Mostrar todos os itens" )
          .tooltip()
          .appendTo( this.wrapper )
          .button({
            icons: {
              primary: "ui-icon-triangle-1-s"
            },
            text: false
          })
          .removeClass( "ui-corner-all" )
          .addClass( "custom-combobox-toggle ui-corner-right" )
          .mousedown(function() {
            wasOpen = input.autocomplete( "widget" ).is( ":visible" );
          })
          .click(function() {
            input.focus();

            // Close if already visible
            if ( wasOpen ) {
              return;
            }

            // Pass empty string as value to search for, displaying all results
            input.autocomplete( "search", "" );
          });
        },

        _source: function( request, response ) {
          var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
          response( this.element.children( "option" ).map(function() {
            var text = $( this ).text();
            if ( this.value && ( !request.term || matcher.test(text) ) )
              return {
                label: text,
                value: text,
                option: this
              };
            }) );
        },

        _removeIfInvalid: function( event, ui ) {

        // Selected an item, nothing to do
        if ( ui.item ) {
          return;
        }

        // Search for a match (case-insensitive)
        var value = this.input.val(),
        valueLowerCase = value.toLowerCase(),
        valid = false;
        this.element.children( "option" ).each(function() {
          if ( $( this ).text().toLowerCase() === valueLowerCase ) {
            this.selected = valid = true;
            return false;
          }
        });

        // Found a match, nothing to do
        if ( valid ) {
          return;
        }

        // Remove invalid value
        this.input
        .val( "" )
        .attr( "title", value + " Nenhum item encontrado com o filtro informado" )
        .tooltip( "open" );
        this.element.val( "" );
        this._delay(function() {
          this.input.tooltip( "close" ).attr( "title", "" );
        }, 2500 );
        this.input.data( "ui-autocomplete" ).term = "";
      },

      _destroy: function() {
        this.wrapper.remove();
        this.element.show();
      }
    });
})( jQuery );

// Constantes de teclas de funcao

var KEY_UP = 38;
var KEY_DOWN = 40;
var KEY_NEXT = 39;
var KEY_PREVIOUS = 37;
var KEY_ENTER = 13;

var KEY_F3 = 114;
var KEY_F4 = 115;
var KEY_F5 = 116;
var KEY_F6 = 117;
var KEY_F7 = 118;
var KEY_F8 = 119;

var KEY_E = 69;
var KEY_N = 78;
var KEY_S = 83;
var KEY_DEL = 46;
var KEY_ESC = 27;

var KEY_END = 35;
var KEY_HOME = 36;
var KEY_PAGEUP = 33;
var KEY_PAGEDOWN = 34;

