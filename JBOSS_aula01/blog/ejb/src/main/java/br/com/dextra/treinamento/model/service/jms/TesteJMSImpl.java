package br.com.dextra.treinamento.model.service.jms;

import javax.ejb.Stateless;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.dextra.treinamento.model.domain.Post;

@Stateless
public class TesteJMSImpl implements TesteJMSLocal {

	@Override
	public void enviarMensagemQueue() {
		ConnectionFactory factory = null;
		Connection conn = null;
		Session session = null;
		MessageProducer publisher = null;

		try {
			Context ctx = new InitialContext();
			factory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
			conn = factory.createConnection();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Queue queue = (Queue) ctx.lookup("queue/minhaFila");
			publisher = session.createProducer(queue);

//			 TextMessage msg =
//			 session.createTextMessage("Esta eh a mensagem enviada para fila");
//			 publisher.send(msg);
			 
			 Post post = new Post("titulo jms", "descricao jms");
			 ObjectMessage msgObj = session.createObjectMessage(post);
			 publisher.send(msgObj);
			 

		} catch (JMSException e) {
			// Tratar excecao
		} catch (NamingException e) {
			// Tratar excecao
			e.printStackTrace();
		} finally {
			try {
				session.close();
				conn.close();
				publisher.close();
			} catch (JMSException e) {
				throw new RuntimeException(e);
			}
		}
	}



}
