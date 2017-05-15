package quiz.common.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.*;

import quiz.common.domain.Quiz;
import quiz.common.domain.User;
import quiz.common.helper.QuizException;
import quiz.mvc.interfaces.QuizController;
import quiz.mvc.interfaces.QuizModel;

public class QuizGUI implements ActionListener {
	private QuizModel model;
	private QuizController controller;
	
	Image backBoard2 = Toolkit.getDefaultToolkit().getImage("image/mark.gif");
	ImageIcon backBoard = new ImageIcon("image/blackboard.jpg");
	ImageIcon leftButtonPush_not = new ImageIcon("image/LeftButtonPush_not.jpg");
	ImageIcon leftButtonPush = new ImageIcon("image/LeftButtonPush.jpg");
	ImageIcon rightButtonPush_not = new ImageIcon("image/RightButtonPush_not.jpg");
	ImageIcon rightButtonPush = new ImageIcon("image/RightButtonPush.jpg");

	JFrame mainFrame = new JFrame("Quiz Show");
	
	JDialog conDialog;
	
	JPanel pnlMain = new JPanel();
	JPanel pnlCenter = new JPanel();
	JPanel pnlCenterQuizExample = new JPanel(){
		public void paintComponent(Graphics g) {
            Dimension d = getSize();
            g.drawImage(backBoard.getImage(), 0, 0, d.width, d.height, null);
            setOpaque(false); //그림을 표시하게 설정,투명하게 조절
            super.paintComponent(g);
		}
	};
	JPanel pnlCenterButton = new JPanel();
	JPanel pnlEast = new JPanel();
	JPanel pnlSouth = new JPanel();
	JPanel pnlWestSpace = new JPanel();

	JLabel lblQuiz = new JLabel("<html><font size='7'>Wellcome to Quiz Show !!!</font></html>");
	JLabel lblExample = new JLabel("");
	JLabel lblTimer = new JLabel("타이머");

	JButton btnBeforeQuiz = new JButton(leftButtonPush_not);
	JButton btnNextQuiz = new JButton(rightButtonPush_not);
	JButton btnNewQuiz = new JButton("새로풀기");
	JButton btnRankTable = new JButton("순위확인");
	JButton btnExit = new JButton("종료");
	JButton btnSendAnswer = new JButton("답안 전송");

	JTextField txtInputAnswer = new JTextField(30);
	
	// dialog
	JPanel userInfoPan, lblPan, tfPan, btnPan;
	JLabel lblTitle, lblNickname, lblPassword;
	JTextField tfNickname, tfPassword;
	JButton btnSignIn, btnCancel, btnRegister;

	public QuizGUI(QuizModel model) {
		this.model = model;
		buildDisplay();
	}

	public void addController(QuizController controller) {
		this.controller = controller;
	}
	
	private void buildDisplay() {
		
		// 레이아웃 설정
		mainFrame.setLayout(new BorderLayout());
		pnlMain.setLayout(new BorderLayout(20, 20));
		pnlCenter.setLayout(new BorderLayout());
		pnlCenterQuizExample.setLayout(new GridLayout(0, 1, 10, 10));
		pnlCenterButton.setLayout(new FlowLayout());
		pnlEast.setLayout(new GridLayout(0, 1));
		pnlSouth.setLayout(new FlowLayout());
		
		// 패널에 컴포넌트 배치
		pnlCenterQuizExample.add(lblQuiz);
		pnlCenterQuizExample.add(lblExample);
		pnlCenterButton.add(btnBeforeQuiz);
		pnlCenterButton.add(btnNextQuiz);
		pnlEast.add(lblTimer);
		pnlEast.add(btnNewQuiz);
		pnlEast.add(btnRankTable);
		pnlEast.add(btnExit);
		pnlSouth.add(txtInputAnswer);
		pnlSouth.add(btnSendAnswer);
		
		// 패널 배치
		mainFrame.add(pnlMain, BorderLayout.CENTER);
		pnlMain.add(pnlCenter, BorderLayout.CENTER);
		pnlMain.add(pnlEast, BorderLayout.EAST);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		pnlCenter.add(pnlCenterQuizExample, BorderLayout.CENTER);
		pnlCenter.add(pnlCenterButton, BorderLayout.SOUTH);
		
		
		// 컴포넌트 설정
		btnBeforeQuiz.setPressedIcon(leftButtonPush);
		btnBeforeQuiz.setBorder(null);
		btnNextQuiz.setPressedIcon(rightButtonPush);
		btnNextQuiz.setBorder(null);
		lblQuiz.setForeground(Color.WHITE);
		lblExample.setForeground(Color.WHITE);
		
		
		// 보더 설정
		pnlMain.setBorder(new EmptyBorder(10, 10, 10, 15));
		pnlCenterQuizExample.setBorder(new EmptyBorder(5, 20, 5, 20));
		
		
		// 메인 프레임 세팅
		mainFrame.setSize(820, 520);
		mainFrame.setVisible(true);
		mainFrame.setIconImage(backBoard2);
		
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 리스너 추가
        btnNewQuiz.addActionListener(this);
        btnRankTable.addActionListener(this);
        
        launchDialog();
		
	}
	
	public void launchDialog() {
		conDialog = new JDialog(mainFrame, "로그인");
		
		userInfoPan = new JPanel(new GridLayout(0, 2));
		lblTitle = new JLabel("Sign-in", JLabel.CENTER);
		lblTitle.setOpaque(true);
		lblTitle.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 32));
		lblTitle.setBackground(Color.orange);
		lblNickname = new JLabel("Nickname", JLabel.CENTER);
		lblNickname.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 18));
		lblPassword = new JLabel("Password", JLabel.CENTER);
		lblPassword.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 18));
		tfNickname = new JTextField(20);
		tfPassword = new JPasswordField(20);
		btnSignIn = new JButton("Sign-in");
		btnCancel = new JButton("Cancel");
		btnRegister = new JButton("Register");
		
		lblPan = new JPanel(new GridLayout(2, 0));
		lblPan.add(lblNickname);
		lblPan.add(lblPassword);
		
		tfPan = new JPanel(new GridLayout(2, 0));
		tfPan.add(tfNickname);
		tfPan.add(tfPassword);
		
		btnPan = new JPanel();
		btnPan.add(btnSignIn);
		btnPan.add(btnCancel);
		
		userInfoPan.add(lblPan);
		userInfoPan.add(tfPan);
		
		conDialog.add(lblTitle, BorderLayout.NORTH);
		conDialog.add(userInfoPan, BorderLayout.CENTER);
		conDialog.add(btnPan, BorderLayout.SOUTH);
		conDialog.add(new JPanel(), BorderLayout.WEST);
		conDialog.add(new JPanel(), BorderLayout.EAST);
		
		conDialog.setSize(350, 180);
		conDialog.setVisible(true);

	

		conDialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				conDialog.setVisible(false);
			}
		});
		
		btnSignIn.addActionListener(this);
		btnCancel.addActionListener(this);

		Point point = mainFrame.getLocation();  //Point class 는 x,y좌표를 설정
		conDialog.setLocation(point.x+130, point.y+120);

		conDialog.pack();
		conDialog.setVisible(true);
		tfNickname.requestFocus();
	}
	
	public static void main(String[] args) {
		QuizModel model = null;
		QuizGUI gui = new QuizGUI(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if(source == btnNewQuiz){
			try {
				controller.handleQuizSetGesture();
			} catch (QuizException e1) {
				e1.printStackTrace();
			}
		/*	String text = "5;다중 처리기 운영체제 구성에서 주/종(Master/Slave) 처리기 에대한 설명으로 옳지 않은 것은? ;1.주프로세서에 고장날 경우에도 전체 시스템은 작동한다.;2.비대칭 구조를 갖는다.;3.종 프로세서 입출력 발생시 주 프로세서에게 서비스를 요청한다.;4.주프로세서는 운영체제를 수행한다.;1";
			String[] textString;
			StringBuilder text2 = new StringBuilder("<html>");
			if(text.length() > 60){
				textString = new String[text.length()/60+1];
				for(int index = 0; index < textString.length; index++){
					//textString[index] = text.substring(10, 11*index);
					if(60+index*60+1 < text.length()){
						text2.append(text.substring(0 + index*60+1, 60 + index * 60 + 1) + "<br>" );
					}else{
						text2.append(text.substring(index * 60 + 1, text.length()-1));
					}
				}
				text2.append("</html>");
				System.out.println(text2);
				System.out.println(text2.toString());
				lblQuiz.setText(text2.toString());
			}
			*/
		}else if(source == btnRankTable){
			try {
				//controller.handleLoadRankBoardGesture();
				controller.handleSignInGesture(new User("adsdf","12234"));
			} catch (QuizException e1) {
				e1.printStackTrace();
			}
		}else if(source == btnBeforeQuiz){
			
		}else if(source == btnNextQuiz){
			
		}else if(source == btnSendAnswer){
			
		}
		User user = null;
		
		if (source == btnSignIn) {
			String nickname = tfNickname.getText().trim();
			String password = tfPassword.getText().trim();
			if (!(nickname.equals("") && password.equals(""))) {
				user = new User(nickname, password);
				try {
					controller.handleSignInGesture(user);
					conDialog.setVisible(false);
				} catch (QuizException e1) {
					e1.printStackTrace();
				}
			} else if (nickname.equals("")) {
				
			} else if (password.equals("")) {
				
			} else {
				
			}
		} else if (source == btnCancel) {
			tfNickname.setText("");
			tfPassword.setText("");
		}
		
	}

	public void displayObject(Object object) {
		if(object instanceof Quiz[]){
			Quiz[] quiz = (Quiz[]) object;
			lblQuiz.setText("dd");
		}
	}

}
