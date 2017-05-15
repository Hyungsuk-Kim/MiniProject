package quiz.network;

import java.util.ArrayList;
import java.util.List;

import quiz.common.domain.Quiz;
import quiz.common.domain.User;
import quiz.common.helper.QuizException;
import quiz.mvc.interfaces.QuizModel;
import quiz.mvc.interfaces.QuizView;
import quiz.network.packet.*;

public class QuizModelNwImpl implements QuizModel{
	private List<QuizView> modelChangeListeners = new ArrayList<QuizView>();
	private QuizClient client = null;
	private User curUser = null;
	
	public QuizModelNwImpl(QuizClient client) {
		this.client = client;
	}
	
	private void fireModelChangeEvent(Object display) {
        for (QuizView view : modelChangeListeners) {
            try {
                view.handleModelChange(display);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } 
	
	@Override
	public void addChangeModelListener(QuizView view) throws QuizException {
		this.modelChangeListeners.add(view);
	}

	@Override
	public void addUser(User user) throws QuizException {
		PacketObject packet = null;
		Object result = null;
		try {
			packet = new AddUserPacket(user);
			client.send(packet);
			packet = (PacketObject) client.receive();
			result = packet.getResult();
			
			this.fireModelChangeEvent(user);
		} catch (Exception e) {
			throw new QuizException(e.getMessage(), e);
		}
	}

	@Override
	public void deleteUser(User user) throws QuizException {
		PacketObject packet = null;
		Object result = null;
		try {
			packet = new DeleteUserPacket(user);
			client.send(packet);
			packet = (PacketObject) client.receive();
			result = packet.getResult();
			
			this.fireModelChangeEvent(user);
		} catch (Exception e) {
			throw new QuizException(e.getMessage(), e);
		}
	}

	@Override
	public void updateUser(User user) throws QuizException {
		PacketObject packet = null;
		Object result = null;
		try {
			packet = new UpdateUserPacket(user);
			client.send(packet);
			packet = (PacketObject) client.receive();
			result = packet.getResult();
			
			this.fireModelChangeEvent(user);
		} catch (Exception e) {
			throw new QuizException(e.getMessage(), e);
		}		
	}

	@Override
	public User signIn(User user) throws QuizException {
		User userInfo = null;
		PacketObject packet = null;
		Object result = null;
		try {
			packet = new SignInPacket(user);
			client.send(packet);
			packet = (PacketObject) client.receive();
			result = packet.getResult();
			this.curUser = user;
			userInfo = user;
			this.fireModelChangeEvent(user);
		} catch (Exception e) {
			throw new QuizException(e.getMessage(), e);
		}
		return userInfo;
	}

	@Override
	public void signOut(User user) throws QuizException {
		PacketObject packet = null;
		Object result = null;
		try {
			packet = new SignOutPacket(user);
			client.send(packet);
			packet = (PacketObject) client.receive();
			result = packet.getResult();
			
			this.fireModelChangeEvent(user);
		} catch (Exception e) {
			throw new QuizException(e.getMessage(), e);
		}
	}

	@Override
	public Quiz[] loadQuizSet() throws QuizException {
		PacketObject packet = null;
		Object result = null;
		Quiz[] quizSet = null;
		User user = null;
		try {
			packet = new AllocatingQuizPacket(curUser);
			client.send(packet);
			packet = (PacketObject) client.receive();
			result = packet.getResult();
			user = (User) result;
			quizSet = user.getAllocatedQuiz();
			//this.fireModelChangeEvent(quizSet);
		} catch (Exception e) {
			throw new QuizException(e.getMessage(), e);
		}
		return quizSet;
	}

	@Override
	public void allocateQuizToUser(User user) throws QuizException {
		try {
			user.setAllocatdQuiz(this.loadQuizSet());
		} catch (QuizException e) {
			throw new QuizException(e.getMessage(), e);
		}
	}

	@Override
	public void checkCorrectAnswer(User user) throws QuizException {
		String[] solutions = user.getSolutions();
		Quiz[] quizSet = user.getAllocatedQuiz();
		int score = 0;
		for (int i = 0; i < quizSet.length; i++) {
			if (quizSet[i].getAnswer().equals(solutions[i])){
				score = quizSet[i].getLevel();
			}
		}
		user.setScore(score);
		if (score > user.getBestScore()) {
			user.setBestScore(score);
		}
		fireModelChangeEvent(user);
	}

	@Override
	public void mappingSolution(User user, int quizNum, String solution) throws QuizException {
		String[] solutions =  user.getSolutions();
		if (solution.trim() != null) {
			solutions[quizNum] = solution.trim();
		}
		user.setSolutions(solutions);
		fireModelChangeEvent(user);
	}

	@Override
	public User[] loadRankBoard(int limit) throws QuizException {
		PacketObject packet = null;
		Object result = null;
		User[] users = null;
		try {
			packet = new LoadRankingPacket(limit);
			client.send(packet);
			packet = (PacketObject) client.receive();
			result = packet.getResult();
			
			users = (User[]) result;
			//this.fireModelChangeEvent(users);
		} catch (Exception e) {
			throw new QuizException(e.getMessage(), e);
		}
		return users;
	}

}
