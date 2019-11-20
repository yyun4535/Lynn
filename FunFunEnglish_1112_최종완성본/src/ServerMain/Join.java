package ServerMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DTO.DTO_Member;

public class Join extends JFrame implements ActionListener {
	ClientSocket cs = null;
	private JPanel contentPane;
	private JTextField idt;
	private JTextField pwdt;
	String id;
	String pwd;

	boolean option = true;

	public Join(ClientSocket clientSocket) {
		// TODO Auto-generated constructor stub
		this.cs = clientSocket;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 360);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		idt = new JTextField();
		idt.setBounds(64, 119, 212, 34);
		contentPane.add(idt);
		idt.setColumns(10);

		pwdt = new JPasswordField('*');
		pwdt.setColumns(10);
		pwdt.setBounds(64, 183, 212, 34);
		contentPane.add(pwdt);

		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(12, 128, 57, 15);
		contentPane.add(lblId);

		JLabel lblPwd = new JLabel("PWD");
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setBounds(12, 192, 57, 15);
		contentPane.add(lblPwd);

		JLabel lblWelcomToFunfunenglish = new JLabel("FUNFUN JOIN");
		lblWelcomToFunfunenglish.setForeground(Color.PINK);
		lblWelcomToFunfunenglish.setFont(new Font("Edo SZ", Font.BOLD, 20));
		lblWelcomToFunfunenglish.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomToFunfunenglish.setBounds(11, 21, 317, 75);
		contentPane.add(lblWelcomToFunfunenglish);

		JLabel lblIdFailed = new JLabel();
		lblIdFailed.setBounds(65, 102, 104, 15);
		contentPane.add(lblIdFailed);

		JLabel lblPasswordFailed = new JLabel();
		lblPasswordFailed.setBounds(64, 167, 132, 15);
		contentPane.add(lblPasswordFailed);

		this.setVisible(true);
		this.setResizable(false);

		// 로그인시작
		JButton btnLogin = new JButton("Join");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DTO_Member dtoM = new DTO_Member();
				id = idt.getText();
				pwd = pwdt.getText();

				boolean userID = true;

				for (int i = 0; i < cs.MemberList.size(); i++) {
					System.out.println("for문");
					if ((cs.MemberList.get(i).getId().equals(id))) {
						System.out.println("2if문");
						System.out.println("중복된 아이디입니다. 다시 입력해주세요.");
						lblIdFailed.setText("중복된 아이디입니다. 다시 입력해주세요.");
						userID = false;
					}
				}
				if (userID) {
					System.out.println("아이디&비밀번호 컴플리트");
					dtoM.setId(id);
					dtoM.setPw(pwd);
					cs.daoM.insert(dtoM);
					cs.listUpdateM();
					cs.startLogin();
				}
			}
		});

		btnLogin.setBounds(209, 227, 63, 23);
		contentPane.add(btnLogin);

		JButton btnJoin = new JButton("Back");
		btnJoin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				cs.startLogin();
				setVf();
			}

		});
		btnJoin.setBounds(133, 227, 63, 23);
		contentPane.add(btnJoin);

	}

	public void setVf() {
		this.setVisible(!this.isVisible());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
