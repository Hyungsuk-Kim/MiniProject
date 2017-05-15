package quiz.network.packet;

import quiz.mvc.interfaces.QuizModel;

public class LoadRankingPacket extends PacketObject {

	private static final long serialVersionUID = 5490960223815742500L;
	private int limit= 0;
	
	public LoadRankingPacket(int limit) {
		this.limit = limit;
	}
	
	@Override
	public void execute(QuizModel model) {
		try {
			model.loadRankBoard(limit);
		} catch (Exception e) {
			this.exception = e;
		}
	}

}
