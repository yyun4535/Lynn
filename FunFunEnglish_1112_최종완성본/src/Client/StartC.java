package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class StartC {
	Socket client = null;

	StartC() {
		try {
			client = new Socket("127.0.0.1", 8888);// �������� ���ᱸ ������ֱ�
			new WithServer(client);// �ּҰ� �����ֱ� ȣ�η�

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
