package ServerMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DTO.DTO_Word;

public class AlterWord extends JFrame implements ActionListener {
	private JPanel contentPane;
	ClientSocket cs = null;
	String id;
	private JTextField Korea;

	public AlterWord(ClientSocket ClientSocket, String id) {
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

		JLabel lblFunfunAddword = new JLabel("FunFun AlterWord");
		lblFunfunAddword.setBounds(69, 10, 183, 15);
		lblFunfunAddword.setForeground(Color.PINK);
		lblFunfunAddword.setFont(new Font("Edo SZ", Font.BOLD, 20));
		lblFunfunAddword.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblFunfunAddword);

		this.setVisible(true);
		this.setResizable(false);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(196, 274, 97, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cs.startEnglish();
				setVf();
			}
		});
		getContentPane().add(btnBack);

		JLabel English = new JLabel();
		English.setForeground(Color.BLACK);
		English.setFont(new Font("굴림", Font.PLAIN, 12));
		English.setBounds(141, 179, 152, 15);
		contentPane.add(English);

		JLabel Unselected = new JLabel();
		Unselected.setFont(new Font("굴림", Font.BOLD, 12));
		Unselected.setHorizontalAlignment(SwingConstants.CENTER);
		Unselected.setBounds(141, 179, 135, 15);
		contentPane.add(Unselected);

		// List Setting
		Vector vecW;
		vecW = new Vector();
		JList Word = new JList();
		Word.setBounds(1, 1, 205, 114);
		Word.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for (int i = 0; i < cs.WordList.size(); i++) {
			vecW.addElement(cs.WordList.get(i).getEn() + " : " + cs.WordList.get(i).getKr());
			Word.setListData(vecW);
		}
		contentPane.add(Word);
		// List Select 값

		Word.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {
				String En = Word.getSelectedValue().toString();

				StringTokenizer option = new StringTokenizer(En, " : ", false);
				String english = option.nextToken();
				if (!arg0.getValueIsAdjusting()) {
					English.setText(english);
					Unselected.setText(" ");
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(Word);
		scrollPane.setBounds(69, 35, 207, 116);
		contentPane.add(scrollPane);

		Korea = new JTextField();
		Korea.setColumns(10);
		Korea.setBounds(150, 221, 116, 21);
		contentPane.add(Korea);

		JLabel lblEnglish = new JLabel("English");
		lblEnglish.setBounds(85, 179, 57, 15);
		contentPane.add(lblEnglish);

		JLabel lblKorea = new JLabel("Korea");
		lblKorea.setBounds(85, 224, 57, 15);
		contentPane.add(lblKorea);

		// 수정
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(85, 274, 97, 23);
		contentPane.add(btnSave);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DTO_Word dtoW = null;

				String Kr = Korea.getText();
				String En = English.getText();

				if (En.equals("")) {
					Unselected.setText("Unselected Word!!");
				} else {
					for (int i = 0; i < cs.WordList.size(); i++) {
						Korea.setText("");
						if (cs.WordList.get(i).getEn().equals(En)) {

							System.out.println("for문");
							System.out.println("fEn : " + En);
							System.out.println("fWen : " + cs.WordList.get(i).getEn());

							dtoW = cs.WordList.get(i);

							dtoW.setId(cs.WordList.get(i).getId());
							System.out.println("아이디");
							System.out.println(cs.WordList.get(i).getId());

							dtoW.setEn(En);
							System.out.println("영단어");
							System.out.println(English);

							dtoW.setKr(Kr);
							System.out.println("한글");
							System.out.println(Kr);
							break;
						} else {
							System.out.println("아닌듯 ㅋ");
						}

					}
					cs.daoW.updata(dtoW);
					cs.listUpdateW();
					System.out.println("리스트 업데이트 성공");
				}
			}

		});

	}

	public void setVf() {
		this.setVisible(!this.isVisible());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
