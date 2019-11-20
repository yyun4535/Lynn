package Client;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FileSave {

	byte buff[] = new byte[1024];
	String filename = "";
	int portNum = 0;
	String ip = "";

	FileOutputStream fo = null;
	BufferedOutputStream bw;
	Socket fileSocket = null;
	InputStream reMsg = null;
	OutputStream sendMsg = null;

	public FileSave(String filename, String portNum, String ip) {
		// TODO Auto-generated constructor stub
		this.filename = filename;
		this.portNum = Integer.valueOf(portNum);
		this.ip = ip;
		setting();
		saveFile();
	}

	private void setting() {
		// TODO Auto-generated method stub
		try {
			fileSocket = new Socket(ip, portNum);
			System.out.println("FileName : " + filename);
			fo = new FileOutputStream(filename);
			bw = new BufferedOutputStream(fo);
			sendMsg = fileSocket.getOutputStream();
			reMsg = fileSocket.getInputStream();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void saveFile() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						byte[] reBuf = new byte[1024];
						int size = reMsg.read(reBuf);
						fo.write(reBuf, 0, size);
						fo.flush();
						//System.out.println("now size : " + size);
					} catch (Exception e) {

					}
				}
			}

		}).start();
	}
}
