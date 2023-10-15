package com.dev.mateusjose98.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dev.mateusjose98.business.AgendamentoEmailBusiness;
import com.dev.mateusjose98.entity.AgendamentoEmail;

@Path("agendamentoemail")
public class AgendamentoEmailResource {

	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarAgendamentoEmail() {
		List<AgendamentoEmail> emails = agendamentoEmailBusiness
				.listarAgendamentosEmail();
		
		return Response.ok(emails).build();
	}
}
