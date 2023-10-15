package com.dev.mateusjose98.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dev.mateusjose98.entity.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<AgendamentoEmail> listarAgendamentos() {
		return entityManager.createQuery("select a from AgendamentoEmail", 
				AgendamentoEmail.class).getResultList();
	}


}
