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

public class Login extends JFrame implements ActionListener {
	ClientSocket cs = null;
	private JPanel contentPane;
	private JTextField idt;
	private JTextField pwdt;
	String id;
	String pwd;

	boolean option = true;

	public Login(ClientSocket clientSocket) {
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

		JLabel lblWelcomToFunfunenglish = new JLabel("Welcom to FunFunEnglish");
		lblWelcomToFunfunenglish.setForeground(Color.PINK);
		lblWelcomToFunfunenglish.setFont(new Font("Edo SZ", Font.BOLD, 20));
		lblWelcomToFunfunenglish.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomToFunfunenglish.setBounds(11, 21, 317, 75);
		contentPane.add(lblWelcomToFunfunenglish);

		JLabel lblIdFailed = new JLabel();
		lblIdFailed.setBounds(65, 102, 211, 15);
		contentPane.add(lblIdFailed);

		JLabel lblPasswordFailed = new JLabel();
		lblPasswordFailed.setBounds(64, 167, 212, 15);
		contentPane.add(lblPasswordFailed);
		this.setResizable(false);
		this.setVisible(true);

		// 로그인시작
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id = idt.getText();
				pwd = pwdt.getText();

				boolean chk = true;

				while (chk) {

					if (cs.MemberList.size() == 0) {
						cs.startJoin();
						setVf();
					} else {
						int imsi = 0;
						System.out.println("imsi : " + imsi);
						for (int i = 0; i < cs.MemberList.size(); i++) {
							imsi = i;
							if ((cs.MemberList.get(i).getId().equals(id))
									&& (cs.MemberList.get(imsi).getPw().equals(pwd))) {

								System.out.println("로그인성공");
								chk = false;
								option = false;
								cs.listUpdateW();
								cs.listUpdateG();
								cs.startEnglish();
								setVf();
								break;
							}
						}

						if (cs.MemberList.get(imsi).getId().equals(id)) {
							if (cs.MemberList.get(imsi).getPw().equals(pwd)) {
							} else {
								System.out.println("password 실패");
								lblPasswordFailed.setText("password 실패");
								break;
							}
						} else {
							System.out.println("id 실패");
							lblIdFailed.setText("id 실패");
							break;
						}
					}
				}
			}
		});

		btnLogin.setBounds(209, 227, 63, 23);
		contentPane.add(btnLogin);

		JButton btnJoin = new JButton("Join");
		btnJoin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				cs.startJoin();
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
