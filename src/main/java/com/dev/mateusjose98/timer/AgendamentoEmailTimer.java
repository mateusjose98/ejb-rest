package com.dev.mateusjose98.timer;

import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import com.dev.mateusjose98.business.AgendamentoEmailBusiness;



@Singleton
public class AgendamentoEmailTimer {
	
	private static Logger logger = Logger.getLogger(AgendamentoEmailTimer.class.getName());
	
	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;
	
	@Schedule(hour = "*", minute="*")
	public void enviarEmailAgendado() {
		logger.info("Rodando o job");
		agendamentoEmailBusiness.listarAgendamentosNaoEnviados().stream().forEach(ag -> {
			agendamentoEmailBusiness.enviarEmail(ag);
		});
	}

}
