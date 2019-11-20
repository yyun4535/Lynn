package ServerMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
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

public class DeleteWord extends JFrame implements ActionListener {
	private JPanel contentPane;
	ClientSocket cs = null;
	String id;

	public DeleteWord(ClientSocket ClientSocket, String id) {
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
		English.setBounds(159, 179, 93, 15);
		contentPane.add(English);

		JLabel Unselected = new JLabel();
		Unselected.setHorizontalAlignment(SwingConstants.CENTER);
		Unselected.setBounds(113, 307, 139, 15);
		contentPane.add(Unselected);

		// List Setting
		Vector vecW;
		vecW = new Vector();
		JList Word = new JList();
		Word.setBounds(36, 0, 205, 114);
		Word.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for (int i = 0; i < cs.WordList.size(); i++) {
			vecW.addElement(cs.WordList.get(i).getEn() + " : " + cs.WordList.get(i).getKr());
			Word.setListData(vecW);
		}
		contentPane.add(Word);
		// List Select °ª

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
		scrollPane.setBounds(69, 35, 207, 218);
		contentPane.add(scrollPane);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String En = English.getText();
				if (En.equals("")) {
					Unselected.setText("!!Unselected Word!!");
				} else {
					DTO_Word dtoW = new DTO_Word();
					if (dtoW != null) {
						dtoW.setEn(En);
						cs.daoW.del(En);
						cs.listUpdateW();
					}
				}
			}
		});
		btnDelete.setBounds(69, 274, 97, 23);
		contentPane.add(btnDelete);

	}

	public void setVf() {
		this.setVisible(!this.isVisible());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
