package Image;

import java.io.File;

public class DeleteToImage {

	DeleteToImage(String title) {
		File file = new File("./img/" + title);

		if (file.exists()) {
			if (file.delete()) {
				System.out.println(title + " : 삭제 성공");
			} else {
				System.out.println(title + " : 삭제 실패");
			}
		}else {
			System.out.println(title + " : 존재하지 않습니다.");
		}

	}

}
