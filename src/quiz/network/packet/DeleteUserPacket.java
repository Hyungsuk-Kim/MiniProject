package quiz.network.packet;

import quiz.common.domain.User;
import quiz.mvc.interfaces.QuizModel;

public class DeleteUserPacket extends PacketObject{
	
	private static final long serialVersionUID = -8147068647099292971L;
	private User user = null;
	
	public DeleteUserPacket(User user) {
		this.user = user;
	}
	
	@Override
	public void execute(QuizModel model) {
		try {
			model.deleteUser(user);
		} catch (Exception e) {
			this.exception = e;
		}
	}

}
