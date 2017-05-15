package quiz.common.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import quiz.common.domain.User;
import quiz.common.helper.QuizException;
import quiz.database.QuizDAO;
import quiz.database.QuizDAOImpl;
import quiz.mvc.implemented.QuizModelImpl;
import quiz.mvc.interfaces.QuizController;
import quiz.mvc.interfaces.QuizModel;

public class SignInGUI {
	private QuizModel model;
	private QuizController controller;
	
	private JFrame mainFrame;
	private JPanel userInfoPan, lblPan, tfPan, btnPan;
	private JLabel lblTitle, lblNickname, lblPassword;
	private JTextField tfNickname, tfPassword;
	private JButton btnSignIn, btnCancel, btnRegister;
	
	public SignInGUI(QuizModel model) {
		this.model = model;
		buildDisplay();
	}
	
	public SignInGUI() {
		buildDisplay();
	}

	private void buildDisplay() {
		mainFrame = new JFrame("Sign-in");
		userInfoPan = new JPanel(new BorderLayout());
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
		lblPan.setPreferredSize(new Dimension(100, 200));
		lblPan.add(lblNickname);
		lblPan.add(lblPassword);
		
		tfPan = new JPanel(new GridLayout(2, 0));
		tfPan.add(tfNickname);
		tfPan.add(tfPassword);
		
		btnPan = new JPanel(new BorderLayout());
		//btnPan.add(btnSignIn);
		//btnPan.add(btnCancel);
		btnPan.add(btnRegister);
		
		userInfoPan.add(lblPan, BorderLayout.WEST);
		userInfoPan.add(tfPan, BorderLayout.CENTER);
		
		mainFrame.add(lblTitle, BorderLayout.NORTH);
		mainFrame.add(userInfoPan, BorderLayout.CENTER);
		mainFrame.add(btnPan, BorderLayout.SOUTH);
		mainFrame.add(new JPanel(), BorderLayout.WEST);
		mainFrame.add(btnSignIn, BorderLayout.EAST);
		
		mainFrame.setSize(350, 180);
		mainFrame.setVisible(true);
		
		/*System.out.println("tfNickname : " + tfNickname.getText().trim());
		System.out.println("tfPassword : " + tfPassword.getText().trim());
		System.out.println("n" +tfNickname.getText().trim().equals(""));*/
	}
	
	public void addEventHandler() {
		
		ActionListener buttonHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				User user = null;
				
				if (source == btnSignIn) {
					String nickname = tfNickname.getText().trim();
					String password = tfPassword.getText().trim();
					if (!(nickname.equals("") && password.equals(""))) {
						user = new User(nickname, password);
						try {
							controller.handleSignInGesture(user);
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
		};
		btnSignIn.addActionListener(buttonHandler);
		btnCancel.addActionListener(buttonHandler);
		
		KeyListener keyHandler = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_ENTER) {
					String nickname = tfNickname.getText().trim();
					String password = tfPassword.getText().trim();
					if (!(nickname.equals("") && password.equals(""))) {
						User user = new User(nickname, password);
						try {
							controller.handleSignInGesture(user);
						} catch (QuizException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		};
		
	}
	
	public static void main(String[] args) {
		SignInGUI gui = new SignInGUI();
	}
}
