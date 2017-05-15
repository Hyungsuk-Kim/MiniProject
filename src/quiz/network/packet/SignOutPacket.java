package quiz.network.packet;

import quiz.common.domain.User;
import quiz.mvc.interfaces.QuizModel;

public class SignOutPacket extends PacketObject{
	
	private static final long serialVersionUID = 3321341689728262245L;
	private User user = null;
	
	public SignOutPacket(User user) {
		this.user = user;
	}
	
	@Override
	public void execute(QuizModel model) {
		try {
			model.signOut(user);
		} catch (Exception e) {
			this.exception = e;
		}
	}

}
