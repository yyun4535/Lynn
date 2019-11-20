package ServerMain;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FileSend {

	private String src = "";
	private int portNum = 0;
	private String ip = "";
	private FileInputStream fn = null;
	private BufferedInputStream br = null;

	private ClientSocket cs = null;

	private ServerSocket serverS = null;
	private Socket serverClient = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;

	FileSend(String fileName, String portNum, String ip) {
		this.src = fileName;
		this.portNum = Integer.valueOf(portNum);
		setSever();
	}

	private void setSever() {
		try {
			serverS = new ServerSocket();
			serverS.bind(new InetSocketAddress(ip, portNum));
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("FileServer Ready");
					try {
						serverClient = serverS.accept();
						System.out.println("ServerClient");
						reMsg = serverClient.getInputStream();
						System.out.println("reMsg");
						sendMsg = serverClient.getOutputStream();
						System.out.println("FileServer - Accept OK");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean fileTransper() {
		setting();
		fileSend();
		return true;
	}

	public void setting() {
		try {
			fn = new FileInputStream(src);
			br = new BufferedInputStream(fn);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fileSend() {
		int n = 0;
		try {
			byte buff[] = new byte[1024];
			while ((n = fn.read(buff)) != -1) {
				sendMsg.write(buff, 0, n);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("FileServer - Send success OK");
		}
	}

}
