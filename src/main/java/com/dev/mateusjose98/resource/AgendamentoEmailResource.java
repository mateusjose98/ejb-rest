package com.dev.mateusjose98.resource;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dev.mateusjose98.business.AgendamentoEmailBusiness;
import com.dev.mateusjose98.entity.AgendamentoEmail;
import com.dev.mateusjose98.exceptions.BusinessException;

@Path("agendamentoemail")
public class AgendamentoEmailResource {
	
	private static Logger logger = Logger.getLogger(AgendamentoEmailResource.class.getName());

	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarAgendamentoEmail() {
		List<AgendamentoEmail> emails = agendamentoEmailBusiness
				.listarAgendamentosEmail();
		
		return Response.ok(emails).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response criarNovo(AgendamentoEmail agendamento) throws BusinessException {
		logger.info("Criando um agendamento!");
			agendamentoEmailBusiness.criarNovo(agendamento);
			return Response.status(201).build();
	}
}
