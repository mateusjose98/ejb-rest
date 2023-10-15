package com.dev.mateusjose98.business;


import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import com.dev.mateusjose98.dao.AgendamentoEmailDao;
import com.dev.mateusjose98.entity.AgendamentoEmail;
import com.dev.mateusjose98.exceptions.BusinessException;
import com.dev.mateusjose98.interceptor.Logger;

@Stateless
@Logger
public class AgendamentoEmailBusiness {
	
	@Inject
	private AgendamentoEmailDao agendamentoEmailDao;
	
	@Resource(lookup = "java:jboss/mail/AgendamentoMailSession")
	private Session sessaoEmail;
	private static String EMAIL_FROM = "mail.address";
	private static String EMAIL_USER = "mail.smtp.user";
	private static String EMAIL_PASSWORD = "mail.smtp.pass";
	
	public List<AgendamentoEmail> listarAgendamentosEmail() {
		List<AgendamentoEmail> emails = agendamentoEmailDao.listarAgendamentos();
		
		return emails;
	}
	
	public void criarNovo(@Valid AgendamentoEmail agendamento) throws BusinessException {
		if(!agendamentoEmailDao.listarAgendamentosPorEmail(agendamento.getEmail()).isEmpty()) {
			throw new BusinessException("Email já possui agendamento");
		}
		agendamento.setEnviado(false);
		agendamentoEmailDao.salvarAgendamento(agendamento);
	}
	
	public List<AgendamentoEmail> listarAgendamentosNaoEnviados() {
		return agendamentoEmailDao.listarAgendamentosNaoEnviados();
	}
	
	public void enviarEmail(AgendamentoEmail agendamento) {
		try {
		    MimeMessage mensagem = new MimeMessage(sessaoEmail);
		    mensagem.setFrom(sessaoEmail.getProperty(EMAIL_FROM));
		    mensagem.setRecipients(Message.RecipientType.TO, agendamento.getEmail());
		    mensagem.setSubject(agendamento.getAssunto());
		    mensagem.setText(Optional.ofNullable(agendamento.getMensagem()).orElse(""));
		    Transport.send(mensagem,
		    sessaoEmail.getProperty(EMAIL_USER),
		    sessaoEmail.getProperty(EMAIL_PASSWORD));
		} catch (MessagingException e) {
		    throw new RuntimeException(e);
		}
	}

}
