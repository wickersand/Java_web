package br.com.dextra.treinamento.model.service.timer;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import br.com.dextra.treinamento.model.domain.Post;
import br.com.dextra.treinamento.model.service.jpa.PostServiceLocal;

@Stateless
public class TesteTimerImpl implements TesteTimerLocal {
	
	@Resource
	private SessionContext ctx;
	
	private static final String NOME_TIMER = "TesteTimerImpl";
	
	@EJB
	private PostServiceLocal postServiceLocal;

	@Override
	public void agendar(long inicio, long intervalo) {
		TimerService timerService = ctx.getTimerService();
		timerService.createTimer(inicio, intervalo, NOME_TIMER);
	}
	
	@Timeout
	public void excecutar(Timer timer) {
		List<Post> posts = postServiceLocal.listar();
		for(Post post: posts) {
			System.out.println(post.toString());
		}
	}

	@Override
	public void cancelar() {
		TimerService timerService = ctx.getTimerService();
		Collection<?> timers = timerService.getTimers();
		for(Object t: timers) {
			Timer timer = (Timer) t;
			if(timer.getInfo().equals(NOME_TIMER)) {
				timer.cancel();
			}
		}
	}

}
