package ServerMain;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DAO.DAO_Game;
import DTO.DTO_Game;

public class GameRoom extends JFrame implements ActionListener {
	// GUI
	MyCanvas mc = new MyCanvas();
	JLabel title = new JLabel("FunFunEnglishGame");
	JTextField textField = new JTextField();

	// int myairNum = 3;

	// int score = 0;
	int monTimer = 500;

	ArrayList<String> gameList;
	ArrayList<String> answerList;
	ClientSocket cs;

	ArrayList<WordCenter> WordList = new ArrayList<WordCenter>();

	GameRoom(ClientSocket cs, ArrayList<String> gameList, ArrayList<String> answerList) {
		System.out.println("GameRoom");
		this.gameList = gameList;
		this.answerList = answerList;
		this.cs = cs;

		createWord();
		// gui

		this.setBounds(170, 100, 400, 500);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mc, "Center");
		getContentPane().add(title, "South");
		getContentPane().add(textField, "South");

		this.setResizable(false);
		this.textField.requestFocus();
		this.textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textField.setText("");
				textField.requestFocus();
				String word = e.getActionCommand();
				System.out.println("keyboard word : " + word);
				for (int i = 0; i < WordList.size(); i++) {
					if (answerList.get(i).equals(word)) {
						System.out.println("remove : " + WordList.get(i));
						System.out.println("answer : " + answerList.get(i));
						WordList.remove(i);
						answerList.remove(i);
						gameList.remove(i);
						break;
					}
				}
			}

		});

		this.setVisible(true);
	}

	private void createWord() {
		System.out.println("몬스터제작!");

		new Thread(new Runnable() {
			@Override
			public void run() {
				Random r = new Random();
				Word m = null;
				String word = null;
				int i = 0;
				while (true) {
					try {
						Thread.sleep(monTimer);
						if (i < 9) {
							word = gameList.get(i);
							int x = r.nextInt(350) + 10;
							int y = r.nextInt(20);
							m = new Word(x, y, word);
							System.out.println("Word사이즈" + WordList.size());
							System.out.println("Game사이즈" + gameList.size());
							WordList.add(m);
							System.out.println("count : " + i);
							i++;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mc.repaint();
					System.out.println("thread");
				}
			}
		}).start();
	}

	class MyCanvas extends Canvas {

		MyCanvas() {

			this.setSize(400, 500);
			this.setBackground(Color.WHITE);
		}

		@Override
		public void paint(Graphics g) {
			Image WordIMG = null;
			DTO_Game gto = new DTO_Game();
			DAO_Game gao = DAO_Game.getInstance();

			System.out.println("단어 사이즈 : " + WordList.size());
			for (int i = 0; i < WordList.size(); i++) {

				WordIMG = WordList.get(i).getMyMon();

				if (WordList.get(i).getY() > 400) {
					System.out.println("죽음의 단어들");
					gto.setEn(gameList.get(i));
					System.out.println("영어 : " + gameList.get(i));
					gto.setKr(answerList.get(i));
					System.out.println("한글 : " + answerList.get(i));
					gao.list(gameList.get(i), gto);
					cs.listUpdateG();
					gameList.remove(i);
					WordList.remove(i);
					answerList.remove(i);
				} else {
					g.drawImage(WordIMG, WordList.get(i).getX(), WordList.get(i).getY(), this);

				}

			}
			if (WordList.size() == 0) {
				g.setFont(new Font("궁서", Font.BOLD, 30)); // 폰트설정
				g.setColor(Color.red);
				g.drawString("GameOver", 120, 140);
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
