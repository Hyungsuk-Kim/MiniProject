package quiz.test;

import org.junit.Before;
import org.junit.Test;

import quiz.common.domain.Quiz;
import quiz.common.helper.QuizException;
import quiz.database.QuizDAO;
import quiz.database.QuizDAOImpl;
import quiz.mvc.implemented.QuizModelImpl;
import quiz.mvc.interfaces.QuizModel;

public class TestModelImpl {
	QuizModel model = null;
	QuizDAO dao = null;
	
	@Before
	public void before() throws QuizException {
		this.dao = new QuizDAOImpl();
		this.model = new QuizModelImpl(dao);
	}
	
	/*@Test
	public void TestLoadQuizSet() throws QuizException {
		Quiz[] quizzes = model.loadQuizSet();
		for (Quiz quiz : quizzes) {
			System.out.print(quiz.getQuiz());
			System.out.println("Level : " + quiz.getLevel());
		}
	}*/
}
