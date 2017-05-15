package quiz.network.packet;

import quiz.common.domain.User;
import quiz.mvc.interfaces.QuizModel;

public class AddUserPacket extends PacketObject{
	
	private static final long serialVersionUID = 6349391204285804443L;
	private User user = null;
	
	public AddUserPacket(User user) {
		this.user = user;
	}
	
	@Override
	public void execute(QuizModel model) {
		try {
			model.addUser(user);
		} catch (Exception e) {
			this.exception = e;
		}
	}

}
