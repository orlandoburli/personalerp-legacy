package br.com.orlandoburli.core.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.vo.manutencao.HistoricoTrocaManutencaoVO;
import br.com.orlandoburli.core.vo.manutencao.TrocaManutencaoVO;

public class SendMail {
	
	@SuppressWarnings("deprecation")
	public void sendEmailHistoricoTroca(TrocaManutencaoVO troca, HistoricoTrocaManutencaoVO historico) throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.chillibeansmt.com.br"); // o servidor SMTP para envio do e-mail
		email.addTo(troca.getEmailClienteTrocaManutencao(), troca.getNomeClienteTrocaManutencao()); //destinat�rio
		
		if (troca.getEmail2TrocaManutencao() != null && !troca.getEmail2TrocaManutencao().trim().equals("")) {
			email.addBcc(troca.getEmail2TrocaManutencao());
		}
		
		if (troca.getEmail3TrocaManutencao() != null && !troca.getEmail3TrocaManutencao().trim().equals("")) {
			email.addBcc(troca.getEmail3TrocaManutencao());
		}
		
		email.setFrom("sac@chillibeansmt.com.br", "Chilli Beans MT"); // remetente
		email.setSubject("Garantia - Chilli Beans MT"); // assunto do e-mail
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>Olá Sr(a), <strong>" + troca.getNomeClienteTrocaManutencao() + "</strong></p>");
		sb.append("<p>Referência: <b>" + troca.getReferenciaTrocaManutencao() + "</b></p>");
		sb.append("<p>Status: <b>" + historico.getDescricaoSituacaoTrocaManutencao() + "</b></p>");
		sb.append("<p>" + historico.getDescricaoHistoricoTrocaManutencao() + "</p>");
		
		String corpo = getEmailBase();
		
		corpo = corpo.replace("[CORPO_EMAIL]", sb.toString());
		corpo = corpo.replace("[TITULO_EMAIL]", "");
		
		email.setHtmlMsg(corpo); // Texto html
		
		email.setAuthentication("sac@chillibeansmt.com.br", "carol1408");
		email.setSmtpPort(587);
		email.setSSL(false);
		
		try {
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getEmailBase() {
		try {
			FileReader fis = new FileReader(SystemManager.getProperty("email.body"));
			BufferedReader reader = new BufferedReader(fis);
			String linha = reader.readLine();
			
			StringBuffer sb = new StringBuffer(linha);
			
			while (linha != null) {
				linha = reader.readLine();
				if (linha != null) {
					sb.append(linha);
				}
			}
			
			reader.close();
			
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}