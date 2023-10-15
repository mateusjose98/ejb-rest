package com.dev.mateusjose98;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.dev.mateusjose98.dto.MensagemErroDto;
import com.dev.mateusjose98.exceptions.BusinessException;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException>{

	@Override
	public Response toResponse(BusinessException e) {
	
	    return Response
	            .status(Response.Status.BAD_REQUEST)
	            .entity( MensagemErroDto.build(
	                   e.getMensagens())).build();
	}

}
