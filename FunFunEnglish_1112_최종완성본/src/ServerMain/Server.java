package ServerMain;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	ArrayList<ClientSocket> box = new ArrayList<ClientSocket>();
	Socket serverclient = null;
	int num = 1;

	Server() {

		try {
			ServerSocket ss = new ServerSocket();
			ss.bind(new InetSocketAddress("127.0.0.1", 8888));
			while (true) {
				System.out.println("Server Waiting");
				serverclient = ss.accept();//대기중
				InetAddress ip = serverclient.getInetAddress();
				System.out.println("[" + (new String(ip + "")).substring(1) + "] 님 입장.");
				ClientSocket w = new ClientSocket(serverclient);
				box.add(w);
				w.setName("client" + num);
				num++;
				w.start();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
