package ServerMain;


import java.awt.Image;

import javax.swing.ImageIcon;

public class WordCenter {
	int x = -1;
	int y = -1;
	int speed = 500;
	// MyCanvas mc = null;
	private Image WordImg = null;
	int width = 0;
	int height = 0;
	String imgName = null;

	public WordCenter(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.imgName = name;

		WordImg = new ImageIcon(imgName).getImage();
		System.out.println("WordCenter ImageName = " + imgName);
		this.width = WordImg.getWidth(null);
		this.height = WordImg.getHeight(null);

	}

	public Image getMyMon() {
		return WordImg;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
}
