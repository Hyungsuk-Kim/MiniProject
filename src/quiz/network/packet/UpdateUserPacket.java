package quiz.network.packet;

import quiz.common.domain.User;
import quiz.mvc.interfaces.QuizModel;

public class UpdateUserPacket extends PacketObject{
	
	private static final long serialVersionUID = 6773956584137985205L;
	private User user = null;
	
	public UpdateUserPacket(User user) {
		this.user = user;
	}
	
	@Override
	public void execute(QuizModel model) {
		try {
			model.updateUser(user);
		} catch (Exception e) {
			this.exception = e;
		}
	}

}
