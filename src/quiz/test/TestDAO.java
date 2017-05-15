package quiz.test;


import org.junit.Before;
import org.junit.Test;

import quiz.common.domain.Quiz;
import quiz.common.helper.QuizException;
import quiz.database.QuizDAO;
import quiz.database.QuizDAOImpl;

public class TestDAO {
	QuizDAO dao = null;
	
	@Before
	public void before() throws QuizException{
		this.dao = new QuizDAOImpl();
		System.out.println("DAO Created");
	}
	
	/*@Test
	public void getQuizSetAsLevel() throws QuizException {
		Quiz[] quizzes = dao.getQuizSetAsLevel(1);
		for (Quiz quiz : quizzes) {
			System.out.println(quiz.getQuiz() + " - " + quiz.getAnswer());
		}
	}*/
	
	
	
}
