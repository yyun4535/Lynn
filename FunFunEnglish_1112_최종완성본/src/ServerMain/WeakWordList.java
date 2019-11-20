package ServerMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DAO.DAO_Game;

public class WeakWordList extends JFrame implements ActionListener {
	private JPanel contentPane;
	ClientSocket cs = null;
	DAO_Game game = null;
	String id;

	public WeakWordList(ClientSocket cs, DAO_Game game, String id) {
		setVf();
		this.cs = cs;
		this.game = game;
		this.id = id;
		getContentPane().setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 360);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFunfunAddword = new JLabel("FunFun WeakList");
		lblFunfunAddword.setForeground(Color.PINK);
		lblFunfunAddword.setFont(new Font("Edo SZ", Font.BOLD, 20));
		lblFunfunAddword.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunfunAddword.setBounds(69, 10, 183, 15);
		getContentPane().add(lblFunfunAddword);

		this.setVisible(true);
		this.setResizable(false);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cs.startEnglish();
				setVf();
			}
		});

		btnBack.setBounds(196, 274, 97, 23);
		getContentPane().add(btnBack);

		// List Setting
		// 영어 리스트
		Vector vecW;
		vecW = new Vector();
		JList Game = new JList();
		Game.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for (int i = 0; i < cs.GameList.size(); i++) {
			vecW.addElement(cs.GameList.get(i).getEn() + " : " + cs.GameList.get(i).getKr());
			Game.setListData(vecW);
		}
		Game.setBounds(57, 35, 207, 225);
		cs.listUpdateG();
		JScrollPane scrollPane = new JScrollPane(Game);
		scrollPane.setBounds(69, 35, 207, 228);
		contentPane.add(scrollPane);
	}

	public void setVf() {
		this.setVisible(!this.isVisible());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
