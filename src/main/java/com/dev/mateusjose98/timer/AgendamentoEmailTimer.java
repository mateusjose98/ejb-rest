package com.dev.mateusjose98.timer;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import com.dev.mateusjose98.business.AgendamentoEmailBusiness;



@Singleton
public class AgendamentoEmailTimer {
	
	private static Logger logger = Logger.getLogger(AgendamentoEmailTimer.class.getName());
	
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;
	
	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;
	
	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;
	
	@Schedule(hour = "*", minute="*")
	public void enviarEmailAgendado() {
		logger.info("Rodando o job");
		agendamentoEmailBusiness.listarAgendamentosNaoEnviados().stream().forEach(ag -> {
			agendamentoEmailBusiness.marcarEnviadas(ag);
			context.createProducer().send(queue, ag);
		});
	}

}
