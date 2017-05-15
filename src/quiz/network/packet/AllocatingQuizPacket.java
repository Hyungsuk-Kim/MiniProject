package quiz.network.packet;

import quiz.common.domain.User;
import quiz.mvc.interfaces.QuizModel;

public class AllocatingQuizPacket extends PacketObject{

	private static final long serialVersionUID = -8079260745937343971L;
	private User user = null;

	public AllocatingQuizPacket(User user) {
		this.user = user;
	}
	
	@Override
	public void execute(QuizModel model) {
		try {
			this.user.setAllocatdQuiz(model.loadQuizSet());
			result = this.user;
		} catch (Exception e) {
			this.exception = e;
		}
	}
}
