package com.dev.mateusjose98.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception{
	

	private List<String> mensagens;
	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super();
		mensagens = new ArrayList<String>();
	}
	
	public BusinessException(String msg) {
		super(msg);
		mensagens = new ArrayList<String>();
		mensagens.add(msg);
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	
	

}
