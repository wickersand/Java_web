package br.com.dextra.treinamento.model.service.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.com.dextra.treinamento.model.domain.Post;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/minhaFila")
})
public class ConsumidorFila implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		
		//TextMessage mensagem = (TextMessage) msg;
		ObjectMessage mensagemPost = (ObjectMessage) msg;
		try {
			//String texto = mensagem.getText();
			
			Post post = (Post) mensagemPost.getObject();
			System.out.println("MENSAGEM:" + post.toString());
			
			//System.out.println("MENSAGEM:" + texto);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
