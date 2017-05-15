package quiz.network;

import quiz.mvc.implemented.*;
import quiz.mvc.interfaces.*;

public class StartClient {
	public static void main(String[] args) {
		int port = 6789;
		String host = "localhost";
		//String host = "220.67.115.225";
		try {
            if (args.length > 0) {
                host = args[0];
            }
            QuizClient client = new QuizClient(host, 6789);
            QuizModel model = new QuizModelNwImpl(client);
            QuizView view = new QuizViewImpl(model);
            QuizController con = new QuizControllerImpl(model, view);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
