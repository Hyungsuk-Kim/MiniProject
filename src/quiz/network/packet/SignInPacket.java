package quiz.network.packet;

import quiz.common.domain.User;
import quiz.mvc.interfaces.QuizModel;

public class SignInPacket extends PacketObject{
	
	private static final long serialVersionUID = -4810841557038961017L;
	private User user = null; // 로그인 요청할 때 보낼 user 객체
	
	public SignInPacket(User user) {
		this.user = user;
	}
	
	@Override
	public void execute(QuizModel model) {
		try {
			result = model.signIn(user);
		} catch (Exception e) {
			this.exception = e;
		}
	}

}
