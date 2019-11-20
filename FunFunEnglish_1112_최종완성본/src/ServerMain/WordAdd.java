package ServerMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DTO.DTO_Word;

public class WordAdd extends JFrame implements ActionListener {
	private JPanel contentPane;
	ClientSocket cs = null;
	String id;
	private JTextField En;
	private JTextField Kr;

	public WordAdd(ClientSocket ClientSocket, String id) {
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

		JLabel lblFunfunAddword = new JLabel("FunFun AddWord");
		lblFunfunAddword.setForeground(Color.PINK);
		lblFunfunAddword.setFont(new Font("Edo SZ", Font.BOLD, 20));
		lblFunfunAddword.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunfunAddword.setBounds(69, 10, 183, 15);
		getContentPane().add(lblFunfunAddword);

		JLabel lblEnglish = new JLabel("English");
		lblEnglish.setBounds(69, 97, 57, 15);
		getContentPane().add(lblEnglish);

		JLabel lblKorea = new JLabel("Korea");
		lblKorea.setBounds(69, 154, 57, 15);
		getContentPane().add(lblKorea);

		En = new JTextField();
		En.setBounds(122, 94, 116, 21);
		getContentPane().add(En);
		En.setColumns(10);

		Kr = new JTextField();
		Kr.setColumns(10);
		Kr.setBounds(122, 151, 116, 21);
		getContentPane().add(Kr);

		JLabel duplication = new JLabel();
		duplication.setFont(new Font("굴림", Font.BOLD, 12));
		duplication.setForeground(new Color(255, 200, 0));
		duplication.setHorizontalAlignment(SwingConstants.CENTER);
		duplication.setBounds(69, 122, 169, 15);
		getContentPane().add(duplication);

		JLabel Sucess = new JLabel();
		Sucess.setFont(new Font("굴림", Font.BOLD, 12));
		Sucess.setHorizontalAlignment(SwingConstants.CENTER);
		Sucess.setForeground(Color.ORANGE);
		Sucess.setBounds(69, 267, 169, 15);
		getContentPane().add(Sucess);

		this.setVisible(true);
		this.setResizable(false);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cs.startEnglish();
				setVf();
			}
		});
		btnBack.setBounds(51, 217, 97, 23);
		getContentPane().add(btnBack);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean wchk = true;
				String English = En.getText();
				String Korea = Kr.getText();

				while (wchk) {
					Sucess.setText(" ");
					duplication.setText(" ");
					En.setText(" ");
					Kr.setText(" ");
					DTO_Word dtoW = new DTO_Word();
					for (int i = 0; i < cs.WordList.size(); i++) {
						if ((cs.WordList.get(i).getEn().equals(English))
								|| (cs.WordList.get(i).getKr().equals(Korea))) {
							duplication.setText("!!Duplicated Word!!");
							// sendDate("!!추가했던 단어 입니다. 다시 입력해주세요.!!");
							wchk = false;
						}
					}
					if (wchk) {
						dtoW.setId(id);
						System.out.println("id : " + id);
						dtoW.setEn(English);
						System.out.println("영문 추가" + English);
						dtoW.setKr(Korea);
						System.out.println("한글 추가" + Korea);
						cs.daoW.insert(dtoW);
						System.out.println(cs.daoW);
						cs.listUpdateW();
						System.out.println("단어추가완료");
						Sucess.setText("Save Successfully");
						wchk = false;
					}
				}

			}

		});
		btnSave.setBounds(177, 217, 97, 23);
		getContentPane().add(btnSave);
	}

	public void setVf() {
		this.setVisible(!this.isVisible());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
