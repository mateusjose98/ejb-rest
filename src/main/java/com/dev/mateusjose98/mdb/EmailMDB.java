package com.dev.mateusjose98.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MessageListener;

import com.dev.mateusjose98.business.AgendamentoEmailBusiness;
import com.dev.mateusjose98.entity.AgendamentoEmail;
import com.dev.mateusjose98.interceptor.Logger;

@Logger
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:/jms/queue/EmailQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class EmailMDB implements MessageListener {
	
	
    
	@Inject
    private AgendamentoEmailBusiness agendamentoEmailBusiness;
    
	@Override
    public void onMessage(javax.jms.Message message) {
    	System.out.println("Lendo msg");
		try {
			AgendamentoEmail ag = message.getBody(AgendamentoEmail.class);
			
			agendamentoEmailBusiness.enviarEmail(ag);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		
    }
}