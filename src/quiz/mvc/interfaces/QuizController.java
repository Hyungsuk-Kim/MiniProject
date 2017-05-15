package quiz.mvc.interfaces;

import quiz.common.domain.User;
import quiz.common.helper.QuizException;

public interface QuizController {	
	
	/** 사용자의 사용자 추가 입력을 model에게 전달 */
	public abstract void handleAddUserGesture(User user) throws QuizException;
	/** 사용자의 사용자 정보 수정 입력을 model에게 전달 */
	public abstract void handleUpdateUserGesture(User user) throws QuizException;
	/** 사용자의 사용자 삭제 입력을 model에게 전달 */
	public abstract void handleDeleteUserGesture(User user) throws QuizException;
	/** 사용자의 로그인 입력을 model에게 전달 */
	public abstract void handleSignInGesture(User user) throws QuizException;
	/** 사용자의 로그아웃 입력을 model에게 전달 */
	public abstract void handleSignOutGesture(User user) throws QuizException;
	/** 로딩된 퀴즈 집합을 model에게 전달 */
	public abstract void handleQuizSetGesture() throws QuizException;
	/** 로딩된 퀴즈를 할당받은 사용자를 model에게 전달*/
	public abstract void handleQuizToUserGesture(User user) throws QuizException;	
	/** 사용자의 정답 체크 입력을 model에게 전달 */
	public abstract void handleCheckCorrectAnswerGesture(User user) throws QuizException;
	/** 사용자의 답안 작성 입력을 model에게 전달 */
	public abstract void handleMappingSolutionGesture(User user, int quizNum, String solution) throws QuizException;
	/** 매일 지정된 시간에 Rank board를 갱신하도록 model에게 지시 */
	public abstract void handleLoadRankBoardGesture() throws QuizException;
}
