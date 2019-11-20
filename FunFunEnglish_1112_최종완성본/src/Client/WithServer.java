package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class WithServer {
	Scanner in = new Scanner(System.in);
	Socket socket = null;
	InputStream reMsg = null;
	OutputStream send = null;
	String id = null;
	String s = null;
	String ip = "";
	byte[] buff = new byte[1024];

	public WithServer(Socket client) {
		// TODO Auto-generated constructor stub
		this.socket = client;
		streamSet();
		Input();
		Output();
	}

	private void Output() {// º¸³»±â
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while (true) {
						s = in.nextLine();
						send = socket.getOutputStream();
						send.write(s.getBytes());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}

	private void Input() {// ¹Þ´Â°Í
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				FileSave fs = null;
				int mode = 0;
				try {
					while (true) {
						byte[] receive = new byte[1024];
						int size = reMsg.read(receive);
						String a = new String(receive).trim();
						System.out.println(a);

						if (a.indexOf("/FilePort") != -1) {
							StringTokenizer stMsg = new StringTokenizer(a);
							stMsg.nextToken();
							String filename = "newWordList.xls";
							System.out.println("FileName : " + filename);
							String portNum = stMsg.nextToken();
							System.out.println("PortNum : " + portNum);

							mode = 1;
							System.out.println("FileStart!!");
							fs = new FileSave(filename, portNum, ip);
							if (fs != null) {
								String re = "File";
								send.write(re.getBytes());
							}
						} else if ((mode == 1) && (a.indexOf("End") != -1)) {
							mode = 0;
							System.out.println("File end");
							fs = null;
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	private void streamSet() {
		// TODO Auto-generated method stub
		try {
			// Clientï¿½ï¿½ï¿½ï¿½ ï¿½Þ¾Æ¿ï¿½ï¿½ï¿½
			reMsg = socket.getInputStream();
			reMsg.read(buff);
			String msg = new String(buff).trim();
			System.out.println(msg);
			// Serverï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
			id = in.nextLine();
			send = socket.getOutputStream();
			send.write(id.getBytes());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
