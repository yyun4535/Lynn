package ServerMain;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import DAO.DAO_Game;
import DAO.DAO_Member;
import DAO.DAO_Word;
import DTO.DTO_Game;
import DTO.DTO_Member;
import DTO.DTO_Word;

public class ClientSocket extends Thread {
	// 파일보내기 변수들

	Socket myclient = null;
	Scanner in = new Scanner(System.in);
	InputStream reMsg = null;
	OutputStream sendMsg = null;
	DTO_Member dtoM = new DTO_Member(); // 현재 접속자 정보
	DAO_Member daoM = null;
	DAO_Word daoW = null;
	DTO_Word dtoW = null;
	DAO_Game daoG = null;
	DTO_Game dtoG = new DTO_Game();

	ArrayList<DTO_Member> MemberList = null;
	ArrayList<DTO_Word> WordList = null;
	ArrayList<DTO_Game> GameList = null;

	Game g = null;
	English e = null;
	Login l = null;
	Join J = null;
	Export ex = null;

	String send1 = "";
	String msg = "";

	byte[] rebuf = new byte[1024];

	public ClientSocket(Socket server) {
		// TODO Auto-generated constructor stub
		this.myclient = server;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		setting();
		streamset();
		receiveDate();
		listUpdateW();
		listUpdateM();
		listUpdateG();

	}

	private void setting() {
		// TODO Auto-generated method stub
		daoM = daoM.getInstance();
		daoW = daoW.getInstance();
		daoG = daoG.getInstance();
	}

	public void listUpdateG() {
		GameList = daoG.getList();
		if (GameList == null) {
			GameList = new ArrayList<DTO_Game>();
		}
		System.out.println("GameList : " + GameList.size());
	}

	public void listUpdateW() {
		WordList = daoW.getList(l.id);
		System.out.println("listUpdateW id : " + l.id);
		if (WordList == null) {
			WordList = new ArrayList<DTO_Word>();
		}
		System.out.println("WordList : " + WordList.size());
	}

	public void listUpdateM() {
		MemberList = daoM.getList();
		if (MemberList == null) {
			MemberList = new ArrayList<DTO_Member>();

		}
		System.out.println("MemberList : " + MemberList.size());
	}

	public void startLogin() {
		System.out.println("startLoin");
		l = new Login(this);
	}

	public void startJoin() {
		System.out.println("startJoin");
		J = new Join(this);
	}

	public void startExport() {
		try {
			ex = new Export(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startEnglish() {
		e = new English(this, l.id);
		System.out.println("English id : " + l.id);
	}

	public void startgame() {
		g = new Game(this, l.id);
		System.out.println("Game id : " + l.id);
	}

	public String receive() {
		byte[] rebuf1 = new byte[1024];
		try {
			reMsg.read(rebuf1);
			String send3 = new String(rebuf1).trim();
			// this.send1 = send;
			return send3;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String imsi = "오류";
			return imsi;
		} // InputStream을 읽어와야한다.
	}

	private void receiveDate() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					send1 = receive();
					startEnglish();
					startgame();
					System.out.println(send1);
				}
			}

		}).start();

	}

	public void sendDate(String send) {
		// TODO Auto-generated method stub

		try {
			if (sendMsg != null) {
				sendMsg.write(send.getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void sendDate() {
		new Thread(new Runnable() {
			// TODO Auto-generated method stub
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while (true) {
						String send = in.nextLine();
						sendMsg.write(send.getBytes());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	}

	private void streamset() {
		// TODO Auto-generated method stub
		try {
			System.out.println("streamset");
			sendMsg = myclient.getOutputStream();
			// String a = " [Welcome to Fun!Fun!Word] \r\n 시작하시려면 START를 입력해주세요!";
			// sendMsg.write(a.getBytes());
			reMsg = myclient.getInputStream();
			startLogin();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
