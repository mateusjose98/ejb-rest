package com.dev.mateusjose98.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.dev.mateusjose98.entity.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<AgendamentoEmail> listarAgendamentos() {
		return entityManager.createQuery("select a from AgendamentoEmail a", 
				AgendamentoEmail.class).getResultList();
	}
	
	public void salvarAgendamento(AgendamentoEmail agendamento) {
		entityManager.persist(agendamento);
	}
	
	public List<AgendamentoEmail> listarAgendamentosPorEmail(String email) {
		TypedQuery<AgendamentoEmail> query = entityManager
				.createQuery("select a from AgendamentoEmail a "
						+ "where a.email = :email and a.enviado = false", AgendamentoEmail.class)
				.setParameter("email", email);
		return  query.getResultList();
	}
	
	public List<AgendamentoEmail> listarAgendamentosNaoEnviados() {
		TypedQuery<AgendamentoEmail> query = entityManager
				.createQuery("select a from AgendamentoEmail a "
						+ "where a.enviado = false", AgendamentoEmail.class);
		return  query.getResultList();
	}


}
