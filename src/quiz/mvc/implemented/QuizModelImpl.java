package quiz.mvc.implemented;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import quiz.common.domain.*;
import quiz.common.helper.QuizException;
import quiz.database.QuizDAO;
import quiz.mvc.interfaces.*;

public class QuizModelImpl implements QuizModel {
	// Variables
	private List<QuizView> changeModelListeners = null;
	private QuizDAO dao = null;
	
	// Constructor
	public QuizModelImpl(QuizDAO dao) {
		this.changeModelListeners = new ArrayList<QuizView>();
		this.dao = dao;
	}
	
	// Methods
	private void fireModelChangeEvent(Object display) {
        for (QuizView view : changeModelListeners) {
            try {
                view.handleModelChange(display);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } 
	
	protected int randomIntInRange(int minimun, int maximun) {
		return (int) (Math.random() * (maximun - minimun + 1)) + minimun;
	}
	
	@Override
	public void addChangeModelListener(QuizView view) throws QuizException {
		if (!(this.changeModelListeners.add(view))) {
			throw new QuizException("QuizModelImpl.addChangeModelListener()");
		}
	}

	@Override
	public void addUser(User user) throws QuizException {
		try {
			this.dao.createUser(user);
			fireModelChangeEvent(user);
		} catch (QuizException e) {
			throw new QuizException("QuizModelImpl.addUser()", e);
		}
	}

	@Override
	public void deleteUser(User user) throws QuizException {
		try {
			this.dao.removeUser(user);
			fireModelChangeEvent(user);
		} catch (QuizException e) {
			throw new QuizException("QuizModelImpl.deleteUser()", e);
		}
	}

	@Override
	public void updateUser(User user) throws QuizException {
		try {
			this.dao.changeUserInfo(user);
			fireModelChangeEvent(user);
		} catch (QuizException e) {
			throw new QuizException("QuizModelImpl.updateUser()", e);
		}
	}

	@Override
	public User signIn(User user) throws QuizException {
		User userConfirm = null; // 임시 저장될 user 변수
		User confirmedUser = null; // 리턴 될 user 변수
		try {
			userConfirm = this.dao.getUser(user);
			if ((userConfirm.getPassword()).equals(user.getPassword())) {
				userConfirm.setRecentAccess(new Date());
				this.dao.changeUserInfo(userConfirm);
				confirmedUser = userConfirm;
			} else {
				throw new QuizException("You have wrong password.");
			}
			fireModelChangeEvent(confirmedUser);
		} catch (QuizException e) {
			throw new QuizException("QuizModelImpl.signIn()", e);
		}
		return confirmedUser;
	}

	@Override
	public void signOut(User user) throws QuizException {
		try {
			this.dao.changeUserInfo(user);
			fireModelChangeEvent(user);
		} catch (QuizException e) {
			throw new QuizException("QuizModelImpl.signOut()", e);
		}
	}

	@Override
	public Quiz[] loadQuizSet() throws QuizException {
		List<Quiz> qList = new ArrayList<Quiz>();
		try {
			Quiz[] tempQuizSetAsLevel = null;
			int maxCountQuizPerLevel = (Quiz.MAX_COUNT_QUIZ) / (Quiz.LEVEL_LIST.length);
			
			for (int level : Quiz.LEVEL_LIST) {
				tempQuizSetAsLevel = this.dao.getQuizSetAsLevel(level);
				/*for (Quiz quiz : tempQuizSetAsLevel) {
					System.out.println(quiz.getQuiz() + " - " + quiz.getAnswer());
				}*/
				for (int i = 0; i < maxCountQuizPerLevel; i++) {
					int curIndex = this.randomIntInRange(0, tempQuizSetAsLevel.length);
					int prevIndex = 0;
					if(curIndex == prevIndex) {
						curIndex = this.randomIntInRange(0, tempQuizSetAsLevel.length);
					} else if (curIndex != prevIndex){
						qList.add(tempQuizSetAsLevel[curIndex]);
						prevIndex = curIndex;
					}
				}
			}
		} catch (QuizException e) {
			throw new QuizException("QuizModelImpl.loadQuizSet()", e);
		}
		return qList.toArray(new Quiz[0]);
	}

	@Override
	public User[] loadRankBoard(int limit) throws QuizException {
		try {
			return this.dao.getUsersByScore(limit);
		} catch (QuizException e) {
			throw new QuizException("QuizModelImpl.loadQuizSet()", e);
		}
	}

	@Override
	public void allocateQuizToUser(User user) throws QuizException {
		try {
			user.setAllocatdQuiz(this.loadQuizSet());
		} catch (QuizException e) {
			throw new QuizException("QuizModelImpl.allocateQuizToUser()", e);
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

}
