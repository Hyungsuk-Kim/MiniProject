package quiz.mvc.interfaces;

import quiz.common.helper.QuizException;

public interface QuizView {
	/** 발생한 사용자 입력을 model에게 전달하기 위한 controller를 view에 등록 */
	public abstract void addController(QuizController controller) throws QuizException;
	/** view의 상태 변경을 유발하는 사용자 입력을 view에 반영하는 메서드 (Controller가 사용) */
	public abstract void showDisplay(Object display) throws QuizException;
	/** model의 상태 변경을 유발하는 사용자 입력을 view에 반영하는 메서드 (Model이 사용) */
	public abstract void handleModelChange(Object display) throws QuizException;
}
