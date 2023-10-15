package com.dev.mateusjose98.business;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.dev.mateusjose98.dao.AgendamentoEmailDao;
import com.dev.mateusjose98.entity.AgendamentoEmail;

@Stateless
public class AgendamentoEmailBusiness {
	
	@Inject
	private AgendamentoEmailDao agendamentoEmailDao;
	
	public List<AgendamentoEmail> listarAgendamentosEmail() {
		List<AgendamentoEmail> emails = agendamentoEmailDao.listarAgendamentos();
		
		return emails;
	}

}
