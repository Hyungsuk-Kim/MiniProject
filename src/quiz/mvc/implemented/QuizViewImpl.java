package quiz.mvc.implemented;

import quiz.common.gui.QuizGUI;
import quiz.common.helper.QuizException;
import quiz.mvc.interfaces.*;

public class QuizViewImpl implements QuizView {
	
	private QuizGUI gui;
	private QuizController quizController;
	private QuizModel quizModel;
	
	public QuizViewImpl(QuizModel model) {
        System.out.println("Creating QuizViewImpl");
        try {
        	quizModel = model;
        	quizModel.addChangeModelListener(this);
        } catch (Exception e) {
            System.out.println("quizModel constructor " + e);
        }
        gui = new QuizGUI(quizModel);

    }

	@Override
	public void addController(QuizController controller) throws QuizException {
		quizController = controller;
		
		gui.addController(quizController);
		
	}

	@Override
	public void showDisplay(Object display) throws QuizException {
		gui.displayObject(display);
	}

	@Override
	public void handleModelChange(Object object) throws QuizException {
		gui.displayObject(object);
	}

}
