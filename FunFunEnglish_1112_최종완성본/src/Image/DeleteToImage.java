package Image;

import java.io.File;

public class DeleteToImage {

	DeleteToImage(String title) {
		File file = new File("./img/" + title);

		if (file.exists()) {
			if (file.delete()) {
				System.out.println(title + " : ���� ����");
			} else {
				System.out.println(title + " : ���� ����");
			}
		}else {
			System.out.println(title + " : �������� �ʽ��ϴ�.");
		}

	}

}
