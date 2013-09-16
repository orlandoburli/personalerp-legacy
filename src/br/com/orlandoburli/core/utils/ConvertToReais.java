package br.com.orlandoburli.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 
 * 
 * @author Rafael Fiume
 * @version 0.9
 * @see http://www.rgagnon.com/javadetails/java-0426.html
 */
public class ConvertToReais {
	
	/** O prefixo do valor positivo por extenso. */
	private String positivePrefix = "";
	
	/** O sufixo do valor positivo por extenso. */
	private String positiveSufix  = "";
	
	/** O prefixo do valor negativo por extenso. */
	private String negativePrefix = "";
	
	/** O sufixo do valor negativo por extenso. */
	private String negativeSufix  = "";
	
	/** Indica se o valor monet�rio � negativo ou positivo. */
	private boolean isPositive = true;
	
	/** cents pode assumir os valores: centavo, centavos e milhar de real. */
	private String cents = "";
	
	/** moeda pode assumir os valores: real e reais. */
	private String moeda  = "";
	
	private final String REAL = "real";
	
	private final String REAIS = "reais";
	
	private final String CENTAVO = "centavo";
	
	private final String CENTAVOS = "centavos";
	
	// Talvez no tempo do meu av� usassem essa express�o: "milhar de real"...
	// Mas, n�o use centavos aqui ou problemas ocorrer�o! Mesmo porque, n�o � o correto.
	private final String DECIMAL_3_CASAS_SINGULAR = "milhar de real";
	
	private final String DECIMAL_3_CASAS_PLURAL = "milhar de real";
	
	/* Representa grandes n�meros, por extenso e no singular. */
	private static final String[] numerosGrandes = {
		"",
		"mil ",
		"milh�o ",
		"bilh�o ",
		"trilh�o ",
		"quatrilh�o "
	};
	
	/* Representa grandes n�meros, por extenso e no plural. */
	private static final String[] grandeNumerosPlural = {
		"",
		"mil ",
		"milh�es ",
		"bilh�es ",
		"trilh�es ",
		"quatrilh�es "
	};
	
	/* Representando as centenas, por extenso. */
	private static final String[] centenas = {
		"",
		"cento e ",
		"duzentos ",
		"trezentos ",
		"quatrocentos ",
		"quinhentos ",
		"seissentos ",
		"setecentos ",
		"oitocentos ",
		"novecentos "
	};
	
	/* Representando as dezenas, por extenso. */
	private static final String[] dezenas = {
		"",
		"dez ",	   
		"vinte ",
		"trinta ",
		"quarenta ",
		"cinq�enta ",
		"sessenta ",
		"setenta ",
		"oitenta ",
		"noventa "
	};
	
	/* Representando os n�meros de 1 at� 19, por extenso. */
	private static final String[] numeros = {
		"",
		"um ",
		"dois ",
		"tr�s ",
		"quatro ",
		"cinco ",
		"seis ",
		"sete ",
		"oito ",
		"nove ",
		"dez ",
		"onze ",
		"doze ",
		"treze ",
		"quatorze ",
		"quinze ",
		"dezesseis ",
		"dezessete ",
		"dezoito ",
		"dezenove "
	};
	
	/**
	 * @return Returns the positivePrefix.
	 */
	public String getPositivePrefix() {
		return positivePrefix;
	}
	
	/**
	 * @param positivePrefix The positivePrefix to set.
	 */
	public void setPositivePrefix(String prefix) {
		this.positivePrefix = prefix;
	}

	/**
	 * @return Returns the positiveSufix.
	 */
	public String getPositiveSufix() {
		return positiveSufix;
	}
	
	/**
	 * @param positiveSufix The positiveSufix to set.
	 */
	public void setPositiveSufix(String sufix) {
		this.positiveSufix = sufix;
	}
	
	/**
	 * @return Returns the negativePrefix.
	 */
	public String getNegativePrefix() {
		return negativePrefix;
	} 	

	/**
	 * @param negativePrefix The negativePrefix to set.
	 */
	public void setNegativePrefix(String negativePrefix) {
		this.negativePrefix = negativePrefix;
	}

	/**
	 * @return Returns the negativeSufix.
	 */
	public String getNegativeSufix() {
		return negativeSufix;
	}
	
	/**
	 * @param negativeSufix The negativeSufix to set.
	 */
	public void setNegativeSufix(String negativeSufix) {
		this.negativeSufix = negativeSufix;
	}

	/*
	 * Retorna o valor monet�rio por extenso, a partir de uma representa��o
	 * textual de valor monet�rio.
	 * 
	 * @param currency O valor monet�rio.
	 * @returns O valor monet�rio por extenso.
	 * @throws ArithmeticException se a escala do n�mero for diferente de dois ou tr�s.
	 */
	public String porExtenso(String currency) {
		
		return porExtenso(new BigDecimal(currency));
	}
	
	/*
	 * Retorna o valor monet�rio por extenso.
	 * 
	 * @param currency O valor monet�rio.
	 * @returns O valor monet�rio por extenso.
	 * @throws ArithmeticException se a escala do n�mero for diferente de dois ou tr�s.
	 */
	public String porExtenso(BigDecimal currency) {
		
		int scale = currency.scale();
		
		if ((scale != 2) && (scale != 3)) {
			currency.setScale(2);
			///throw new ArithmeticException("a escala deve ser igual a dois ou tr�s.");
		}
		
		StringBuffer valorPorExtenso = new StringBuffer();
		StringBuffer inteiraPorExtenso;
		StringBuffer decimalPorExtenso;	
		
		BigDecimal[] result     = divideAndRemainder(currency);
		BigDecimal parteInteira = result[0];
		BigDecimal parteDecimal = result[1];
		
		setCents(result[1], scale);
		
		inteiraPorExtenso = convertInteger(parteInteira.intValue());
		decimalPorExtenso = convertCents(parteDecimal.intValue());
		
		if((!inteiraPorExtenso.toString().equals("")) && (!decimalPorExtenso.toString().equals(""))) {
			valorPorExtenso.append(inteiraPorExtenso)
			               .append(" e ")
						   .append(decimalPorExtenso);		
		} else {		
			valorPorExtenso.append(inteiraPorExtenso)
		 				   .append(decimalPorExtenso);
		}
		
		if (isPositive) {
			return valorPorExtenso.insert(0, positivePrefix)
								  .append(positiveSufix).toString();
		}
		
		return valorPorExtenso.insert(0, negativePrefix)
		  					  .append(negativeSufix).toString();
	}
		
	/*
	 * Separa a parte inteira do valor monet�rio, da sua
	 * parte decimal. 
	 * 
	 * @param currency O valor monet�rio que desej�-se separar suas partes
	 * 				   inteira e decimal.
	 * @return BigDecimal[2] com a parte inteira e a parte decimal.
	 * @author Eduardo Machado de Oliveira
	 */
	private BigDecimal[] divideAndRemainder(BigDecimal currency) {
				
        BigDecimal[] result = new BigDecimal[2];
        
        result[0] = currency.setScale(0, RoundingMode.DOWN);
        result[1] = currency.subtract(result[0]).movePointRight(currency.scale());
        return result;
	}
	
	/*
	 * Transforma a parte inteira do valor monet�rio em valor por extenso.
	 * 
	 * @param numero A parte inteira do valor monet�rio.
	 * @return O valor por extenso.
	 */
	private StringBuffer convertInteger(int numero) {
		
		if (numero == 0) {
			return new StringBuffer("");
		}
		
		if (numero < 0) {
			numero = -numero;
			isPositive = false;
			
		} else {
			isPositive = true;
		}
		
		StringBuffer intPorExtenso = new StringBuffer("");
		moeda = ((numero == 1) ? REAL : ((numero == 0) ? "" : REAIS));
		
		intPorExtenso = convertGreaterOrEqualThanOneThousand(numero);
		
		if(isSpecialCase(intPorExtenso)) {
			moeda = "de " + moeda;
		}
		
		return intPorExtenso.append(moeda);
	}
	
	/*
	 * Transforma a parte decimal (centavos) do valor monet�rio em valor por extenso.
	 * 
	 * @param decimal A parte decimal do valor monet�rio.
	 * @return O valor por extenso.
	 */
	private StringBuffer convertCents(int decimal) {		
		
		if (decimal == 0) {
			return new StringBuffer("");
		}
		
		if (decimal < 0) {
			decimal = -decimal;
			isPositive = false;
			
		} else {
			isPositive = true;
		}
		
		StringBuffer centsPorExtenso = new StringBuffer("");		
		centsPorExtenso = convertGreaterOrEqualThanOneThousand(decimal);
		
		return centsPorExtenso.append(cents);
	}
	
	/*
	 * Deixa por extenso n�meros maiores ou iguais a 1000. 
	 * 
	 * @param numero N�mero maior ou igual a 1000.
	 * @return O n�mero inferior a 1000, por extenso.
	 * @throws IllegalArgumentException se numero for menor que zero.
	 */
	private StringBuffer convertGreaterOrEqualThanOneThousand(int numero) {
		
		StringBuffer numPorExtenso = new StringBuffer("");		
		
		if (numero < 0) {
			throw new IllegalArgumentException("numero deve ser maior ou igual a zero.");			
		}
		
		int casa_milhar = 0;
		
		do {	        
			int n = numero % 1000;
			
			if (n != 0) {	            
				StringBuffer s = convertLessThanOneThousand(n);
				
				if (isSingular(n)) {
					numPorExtenso = s.append(numerosGrandes[casa_milhar]).append(numPorExtenso);
					
				} else {
					numPorExtenso = s.append(grandeNumerosPlural[casa_milhar]).append(numPorExtenso);
				}
			}
			
			casa_milhar++;
			numero /= 1000;	       	        
			
		} while (numero > 0);
		
		return numPorExtenso;
	}
		
	/*
	 * Deixa por extenso n�meros menores que 1000. 
	 * 
	 * @param numero N�mero menor que 1000.
	 * @return O n�mero inferior a 1000, por extenso.
	 * @throws IllegalArgumentException se numero for menor que zero.
	 */
	private StringBuffer convertLessThanOneThousand(int numero) {
		
		StringBuffer numPorExtenso = new StringBuffer("");
		
		if (numero < 0) {
			throw new IllegalArgumentException("numero deve ser maior ou igual a zero.");			
		}
		
		if (numero == 0) 
			return numPorExtenso;
		
		if (numero == 100) {
			return numPorExtenso.append("cem ");
		}
		
		if (numero % 100 < 20){
			numPorExtenso.append(numeros[numero % 100]);
			numero /= 100;
			
		} else {
			numPorExtenso.append(numeros[numero % 10]);
			
			if ((numero % 10) != 0) {	
				numPorExtenso.insert(0, "e ");
			}
			
			numero /= 10;
			
			numPorExtenso.insert(0, dezenas[numero % 10]);
			numero /= 10;
		}    
		
		if((numero > 0) && ((numero != 1))) {
			numPorExtenso.insert(0, "e ");
		}
		
		return numPorExtenso.insert(0, centenas[numero]);
	}
	
	/*
	 * Ajusta corretamente o valor por extenso da parte monet�ria
	 * correspondente aos decimais, que atualmente podem ser:
	 * centavo / centavos /milhar de real.
	 * 
	 * A dificuldade adicinal em determinar o valor por extenso da
	 * parte monet�ria decimal est� no fato de que � preciso conhecer
	 * a escala desse valor. Se a escala for 2, essa parte decimal
	 * corresponde aos centavos; se for 3, corresponde ao milhar de real.
	 * 
	 * Esse m�todo considera que a escala dos n�meros � <strong>sempre</strong>
	 * igual a dois ou tr�s, por isso n�o verifica se a escala do valor passado
	 * pelo argumento � diferente do esperado. A verifica��o da escala
	 * correta deve ser feitra anteriormente, evitando assim redund�ncia com a
	 * programa��o excessivamente defensiva.
	 * 
	 * @param currency O valor monet�rio.
	 */
	private void setCents(BigDecimal decimal, int scale) {
		
		if (scale == 3) {
			cents  = ((decimal.intValue() == 1) ? 
					DECIMAL_3_CASAS_SINGULAR : ((decimal.intValue() == 0) ? "" : DECIMAL_3_CASAS_PLURAL));			
			
			return;
		} 
		
		cents  = ((decimal.intValue() == 1) ? 
				CENTAVO : ((decimal.intValue() == 0) ? "" : CENTAVOS));		
	}
	
	/*
	 * Utilizado para manter a concord�ncia nominal da frase. 
	 * Ex: um milh�o, ao inv�s de um milh�es; dois bilh�es, ao inv�s de dois bilh�o.
	 * 
	 * @param numero O n�mero que desej�-se verificar se a sua representa��o por
	 * 				 extenso � singular ou plural.
	 * @return true, caso a concord�ncia esteja no singular; false, caso esteja no plural.
	 */
	private boolean isSingular(int numero) {
		
		String num = String.valueOf(numero);
		
		if ((numero / 1000) > 0 ) {			
			return num.endsWith("001");			
		}
		
		if (numero < 11) {
			return num.endsWith("1");
		}
		
		return false;
	}
	
	/*
	 * Garante que n�meros como 1.000.000, 2.000.000, 5.000.000.000 tenham 
	 * a preposi��o "de" antes da moeda: um milh�o <strong>de</strong> reais,
	 * dois milh�es <strong>de</strong> reais, 
	 * cinco bilh�es <strong>de</strong> reais e assim por diante.
	 * 
	 * @param numPorExtenso N�mero o qual desej�-se verificar se � necess�ria
	 * 						a coloca��o da preposi��o "de".
	 * @return true, se a preposi��o "de" for necess�ria; false, caso contr�rio.
	 */
	private boolean isSpecialCase(StringBuffer numPorExtenso) {
		
		String numeroPorExtenso = numPorExtenso.toString();
		
		for(int a = 1; a < numerosGrandes.length; a++) {			
			if(numeroPorExtenso.endsWith(numerosGrandes[a])) {
				return true;
			}
		}
		
		for(int b = 1; b < numerosGrandes.length; b++) {			
			if(numeroPorExtenso.endsWith(grandeNumerosPlural[b])) {
				return true;
			}
		}
		
		return false;
	}
	
	// Exclusvamente para testes r�pidos e demonstra��o.
	public static void main(String[] args) {		
		ConvertToReais converter = new ConvertToReais();		
		
		System.out.println(converter.porExtenso(new BigDecimal(-1).setScale(2, RoundingMode.HALF_DOWN)));
		System.out.println(converter.porExtenso(new BigDecimal(896558475).setScale(2, RoundingMode.HALF_DOWN)));		
		System.out.println(converter.porExtenso(new BigDecimal(0.01).setScale(2, RoundingMode.HALF_DOWN)));		
		System.out.println(converter.porExtenso(new BigDecimal(30).setScale(2, RoundingMode.HALF_DOWN)));
		System.out.println(converter.porExtenso(new BigDecimal(0.001).setScale(3, RoundingMode.HALF_DOWN)));
		System.out.println(converter.porExtenso(new BigDecimal(-100.04).setScale(2, RoundingMode.HALF_DOWN)));
		System.out.println(converter.porExtenso(new BigDecimal(120.04).setScale(2, RoundingMode.HALF_DOWN)));
		System.out.println(converter.porExtenso("100101.87"));
		System.out.println(converter.porExtenso("1001000000.991"));
		System.out.println(converter.porExtenso(new BigDecimal(12687.013).setScale(3, RoundingMode.HALF_DOWN)));
		
		converter.setNegativePrefix("(");
		converter.setNegativeSufix(") - Negativo?!! haha! Se mata v�io...");
		converter.setPositivePrefix("");
		converter.setPositiveSufix(" - Fala amig�o!! ");
		System.out.println(converter.porExtenso(new BigDecimal(100000000).setScale(2, RoundingMode.HALF_DOWN)));		
		System.out.println(converter.porExtenso(new BigDecimal(-2000000000).setScale(2, RoundingMode.HALF_DOWN)));
		
		// Pau aqui!!! Escala diferente de dois ou tr�s.
		//System.out.println(converter.porExtenso(new BigDecimal(0.3).setScale(1, RoundingMode.HALF_DOWN)));
		//System.out.println(converter.porExtenso(new BigDecimal(1413.1411).setScale(4, RoundingMode.HALF_DOWN))); 
	}
	
}