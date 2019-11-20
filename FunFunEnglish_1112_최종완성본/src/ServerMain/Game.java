package ServerMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DAO.DAO_Game;
import DTO.DTO_Game;
import Image.TextToImage;

public class Game extends JFrame implements ActionListener {

	private JPanel contentPane;
	ClientSocket cs = null;
	String id = null;

	Random r = new Random();
	DTO_Game gto = new DTO_Game();
	DAO_Game gao = DAO_Game.getInstance();
	// count를 위로 선언하기
	// count를 socket에다 저장

	public Game(ClientSocket ClientSocket, String id) {
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

		JLabel lblFunfunAddword = new JLabel("FunFun GameMenu");
		lblFunfunAddword.setForeground(Color.PINK);
		lblFunfunAddword.setFont(new Font("Edo SZ", Font.BOLD, 20));
		lblFunfunAddword.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunfunAddword.setBounds(69, 10, 183, 15);
		getContentPane().add(lblFunfunAddword);

		JButton btnNewButton = new JButton("GameStart");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game();
			}
		});

		btnNewButton.setBounds(119, 65, 97, 23);
		getContentPane().add(btnNewButton);

		// 많이 틀린 단어
		JButton btnWeaknessWord = new JButton("Weakness Word");
		btnWeaknessWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WeakWordList(ClientSocket, gao, id);
				setVf();
			}
		});
		btnWeaknessWord.setBounds(101, 153, 133, 23);
		contentPane.add(btnWeaknessWord);

		// Excel 파일로 넘기기
		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Export();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}

			}
		});
		btnExport.setBounds(119, 235, 97, 23);
		contentPane.add(btnExport);

		// 뒤로가기
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new English(ClientSocket, id);
				setVf();
			}
		});
		btnBack.setBounds(231, 299, 97, 23);
		contentPane.add(btnBack);

		this.setVisible(true);
		this.setResizable(false);

	}

	public void Export() throws Exception {
		// TODO Auto-generated method stub
		JLabel Excel = new JLabel("");
		Excel.setBounds(143, 268, 57, 15);
		contentPane.add(Excel);
		try {
			Export ex = new Export(cs);
			ex.export1();
			Excel.setText("Sucess");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Excel.setText("Fail");
		}

	}

	private void game() {
		// TODO Auto-generated method stub
		ArrayList<String> GameList = new ArrayList<>();
		ArrayList<String> AnswerList = new ArrayList<>();
		int chk[] = new int[cs.WordList.size()];
		// wordList 사이즈 check
		System.out.println("in game");
		if (cs.WordList.size() >= 10) {

			for (int i = 1; i < 11; i++) {
				int a = r.nextInt(cs.WordList.size());
				if (chk[a] == 1) {
					i--;
				} else {
					chk[a] = 1;
					String Eword = cs.WordList.get(a).getEn();
					String Kword = cs.WordList.get(a).getKr();
					AnswerList.add(Kword);
					System.out.println("Kword : " + Kword);
					GameList.add(Eword);
					System.out.println("Eword: " + Eword);
					try {

						TextToImage image = new TextToImage(GameList);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			GameRoom gr = new GameRoom(cs, GameList, AnswerList);
		} else {
			// sendDate("게임을 하기에 단어리스트가 부족합니다. 단어를 더 저장해주세요!");
			// cs.e.add();
			cs.startEnglish();
		}
	}

	public void setVf() {
		this.setVisible(!this.isVisible());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}