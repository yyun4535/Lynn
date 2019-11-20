package ServerMain;

import java.io.File;
import java.io.IOException;

import DAO.DAO_Game;
import DTO.DTO_Game;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Export {
	ClientSocket cs = null;
	DTO_Game gto = new DTO_Game();
	DAO_Game gao = DAO_Game.getInstance();
	String ip = "127.0.0.1";
	FileSend ft = null;

	public Export(ClientSocket ClientSocket) throws IOException {
		this.cs = ClientSocket;
		try {
			export1();
			fileTransfer("WordList.xls",
					"9" + (int) (Math.random() * 10) + (int) (Math.random() * 10) + (int) (Math.random() * 10), ip);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void export1() throws Exception {
		// TODO Auto-generated method stub

		File file = new File(
				"D:\\myData\\java-neon\\eclipse\\java_src\\eclipse-workspace\\JavaProject0724FUNFUN_0811_renew\\WordList.xls");

		if (file.exists()) {
			file.createNewFile();
		}

		WritableWorkbook workbook = Workbook.createWorkbook(file);
		WritableSheet sheet = workbook.createSheet("WordList", 1);

		Label label;
		Label label2;
		Label label3;

		Label title;
		Label title2;
		Label title3;

		int a = 0;
		int b = 1;
		int c = 2;

		title = new Label(a, a, String.valueOf("단어"));
		sheet.addCell(title);
		title2 = new Label(b, a, String.valueOf("뜻"));
		sheet.addCell(title2);
		title3 = new Label(c, a, String.valueOf("횟수"));
		sheet.addCell(title3);
		label = null;
		int o = 0;
		int t = 1;
		int th = 2;
		if (cs.GameList != null) {
			for (int z = 0; z < cs.GameList.size(); z++) {
				label = new Label(o, z + 1, String.valueOf(cs.GameList.get(z).getEn()));
				sheet.addCell(label);

				label2 = new Label(t, z + 1, String.valueOf(cs.GameList.get(z).getKr()));
				sheet.addCell(label2);

				label3 = new Label(th, z + 1, String.valueOf(cs.GameList.get(z).getCnt()));
				sheet.addCell(label3);

			}
		}
		workbook.write();
		workbook.close();
		System.out.println("export 성공했습니다.");

	}

	public void fileTransfer(String filename, String portNum, String ip) {
		ft = new FileSend(filename, portNum, ip);
		if (ft != null) {
			cs.sendDate("/FilePort" + filename + " " + portNum);
			System.out.println(filename);
			System.out.println(portNum);
			System.out.println("FilePort : " + "/fileport");
		}
	}

}
