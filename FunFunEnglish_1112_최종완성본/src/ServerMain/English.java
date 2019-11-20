package ServerMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DTO.DTO_Word;

public class English extends JFrame implements ActionListener {
	Scanner in = new Scanner(System.in);
	ClientSocket cs = null;
	String id = null;
	private JPanel contentPane;

	public English(ClientSocket ClientSocket, String id) {
		System.out.println("시작!");
		this.cs = ClientSocket;
		this.id = id;
		getContentPane().setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 360);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFunfunMenu = new JLabel("FunFun Menu");
		lblFunfunMenu.setBounds(104, 22, 126, 24);
		lblFunfunMenu.setFont(new Font("Edo SZ", Font.BOLD, 20));
		lblFunfunMenu.setVerticalAlignment(SwingConstants.TOP);
		lblFunfunMenu.setForeground(Color.PINK);
		getContentPane().add(lblFunfunMenu);
		this.setVisible(true);
		this.setResizable(false);

		// 단어추가
		JButton btnNewButton = new JButton("AddWord");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WordAdd(cs, id);
				setVf();
			}
		});

		btnNewButton.setBounds(119, 76, 97, 23);
		getContentPane().add(btnNewButton);

		// wordList
		JButton btnWordlist = new JButton("WordList");
		btnWordlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WordList(ClientSocket, id);
				setVf();
			}
		});
		btnWordlist.setBounds(119, 120, 97, 23);
		getContentPane().add(btnWordlist);

		// 단어 수정
		JButton btnAlterword = new JButton("AlterWord");
		btnAlterword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AlterWord(ClientSocket, id);
				setVf();
			}

		});
		btnAlterword.setBounds(119, 166, 97, 23);
		getContentPane().add(btnAlterword);

		// DeleteWord
		JButton btnDeleteword = new JButton("DeleteWord");
		btnDeleteword.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DeleteWord(ClientSocket, id);
				setVf();
			}

		});
		btnDeleteword.setBounds(119, 209, 97, 23);
		getContentPane().add(btnDeleteword);

		// 게임 시작
		JButton btnWordgame = new JButton("WordGame");
		btnWordgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cs.startgame();
				setVf();
			}
		});
		btnWordgame.setBounds(119, 256, 97, 23);
		getContentPane().add(btnWordgame);
	}

	public void setVf() {
		this.setVisible(!this.isVisible());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
