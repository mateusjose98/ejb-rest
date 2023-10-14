package com.dev.mateusjose98.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class AgendamentoEmailBusiness {
	
	public List<String> listarAgendamentosEmail() {
		List<String> emails = new ArrayList<>();
		
		emails.add("email1@gmail.com");
		
		emails.add("email2@hotmail.com");
		
		return emails;
	}

}
