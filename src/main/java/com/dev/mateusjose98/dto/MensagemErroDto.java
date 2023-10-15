package com.dev.mateusjose98.dto;

import java.util.Date;
import java.util.List;

public class MensagemErroDto {
	
	private List<String> mensagens;
	private Date dataErro;
	
	
	public static MensagemErroDto build(List<String> mensagens) {
		return new MensagemErroDto(mensagens, new Date());
	}
	
	
	public MensagemErroDto(List<String> mensagens, Date dataErro) {
		super();
		this.mensagens = mensagens;
		this.dataErro = dataErro;
	}
	public List<String> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	public Date getDataErro() {
		return dataErro;
	}
	public void setDataErro(Date dataErro) {
		this.dataErro = dataErro;
	}
	
	

}
