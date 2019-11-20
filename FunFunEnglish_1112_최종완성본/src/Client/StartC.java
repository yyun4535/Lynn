package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class StartC {
	Socket client = null;

	StartC() {
		try {
			client = new Socket("127.0.0.1", 8888);// 소켓으로 연결구 만들어주기
			new WithServer(client);// 주소값 던져주기 호로록

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
