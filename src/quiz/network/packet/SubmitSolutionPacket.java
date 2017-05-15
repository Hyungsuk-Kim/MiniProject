package quiz.network.packet;

import quiz.common.domain.User;
import quiz.common.helper.QuizException;
import quiz.mvc.interfaces.QuizModel;

public class SubmitSolutionPacket extends PacketObject {

	private static final long serialVersionUID = 6749605507788476554L;
	private User user = null;
	private int quizNum = 0;
	private String solution = null;
	
	public SubmitSolutionPacket(User user, int quizNum, String solution) {
		this.user = user;
		this.quizNum = quizNum;
		this.solution = solution;
	}

	@Override
	public void execute(QuizModel model) {
		try {
			model.mappingSolution(user, quizNum, solution);
		} catch (QuizException e) {
			this.exception = e;
		}
	}

}
