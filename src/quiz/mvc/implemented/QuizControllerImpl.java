package quiz.mvc.implemented;

import quiz.common.domain.Quiz;
import quiz.common.domain.User;
import quiz.common.helper.QuizException;
import quiz.mvc.interfaces.*;

public class QuizControllerImpl implements QuizController {
	
	private QuizModel quizModel;
	private QuizView quizView;

	public QuizControllerImpl(QuizModel model, QuizView view) {
				
		try {			
			quizModel = model;
			quizView = view;
			view.addController(this);			
		} catch (QuizException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleAddUserGesture(User user) throws QuizException {
		
		try {			
			quizModel.addUser(user);			
		} catch (QuizException e) {			
			throw new QuizException("QuizControllerImpl.handleAddUserGesture()", e);			
		}
		
	}

	@Override
	public void handleUpdateUserGesture(User user) throws QuizException {
		
		try {			
			quizModel.updateUser(user);
		} catch (QuizException e) {			
			throw new QuizException("QuizControllerImpl.handleUpdateUserGesture()", e);			
		}
		
	}

	@Override
	public void handleDeleteUserGesture(User user) throws QuizException {

		try {			
			quizModel.deleteUser(user);		
		} catch (QuizException e) {			
			throw new QuizException("QuizControllerImpl.handleDeleteUserGesture()", e);			
		}
	}

	@Override
	public void handleSignInGesture(User user) throws QuizException {
		
		try {			
			quizModel.signIn(user);	
		} catch (QuizException e) {			
			throw new QuizException("QuizControllerImpl.handleSignInGesture()", e);			
		}
	}

	@Override
	public void handleSignOutGesture(User user) throws QuizException {
		
		try {			
			quizModel.signOut(user);			
		} catch (QuizException e) {			
			throw new QuizException("QuizControllerImpl.handleSignOutGesture()", e);			
		}
	}

	@Override
	public void handleCheckCorrectAnswerGesture(User user) throws QuizException {
		
		try {			
			quizModel.checkCorrectAnswer(user);			
		} catch (QuizException e) {			
			throw new QuizException("QuizControllerImpl.handleCheckCorrectAnswerGesture()", e);			
		}
	}

	@Override
	public void handleLoadRankBoardGesture() throws QuizException {
				
		try {			
			int limit = 10;
			
			User[] users = quizModel.loadRankBoard(limit);
			
			quizView.showDisplay(users);
		} catch (QuizException e) {			
			throw new QuizException("QuizControllerImpl.handleLoadRankBoardGesture()", e);			
		}
	}

	@Override
	public void handleQuizSetGesture() throws QuizException {
		 
		try {
			System.out.println("QuizController (104 line)");
			Quiz[] quizs = quizModel.loadQuizSet();
			System.out.println("QuizController (105 line)");
			
			quizView.showDisplay(quizs);
			System.out.println("QuizController (108 line)");
		} catch (QuizException e) {			
			throw new QuizException("QuizControllerImpl.handleQuizSetGesture()", e);
		}
	}

	@Override
	public void handleQuizToUserGesture(User user) throws QuizException {
				
		try {			
			quizModel.allocateQuizToUser(user);			
		} catch (QuizException e) {			
			throw new QuizException("QuizControllerImpl.handleQuizToUserGesture()", e);			
		}
	}

	@Override
	public void handleMappingSolutionGesture(User user, int quizNum, String solution) throws QuizException {
		
		try {			
			quizModel.mappingSolution(user, quizNum, solution);			
		} catch (QuizException e) {			
			throw new QuizException("QuizControllerImpl.handleMappingSolutionGesture()", e);			
		}
	}

}
