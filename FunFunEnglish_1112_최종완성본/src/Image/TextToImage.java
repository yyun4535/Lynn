package Image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ServerMain.WordCenter;

public class TextToImage {
	public TextToImage(ArrayList<String> list) throws IOException {
		String word = null;
		for (int i = 0; i < list.size(); i++) {
			word = list.get(i);
		}

		String fileName = word;
		File file = new File("./img/" + fileName + ".png");
		Font font = new Font("Tahoma", Font.PLAIN, 15);
		FontRenderContext frc = new FontRenderContext(null, true, true);
		Rectangle2D bounds = font.getStringBounds(word, frc);
		int w = (int) bounds.getWidth();
		int h = (int) bounds.getHeight();

		BufferedImage image = 
	    new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		
		
		Graphics2D g = image.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g.drawString(word, (float) bounds.getX(), (float) -bounds.getY());
		g.dispose();
		ImageIO.write(image, "png", file);
		System.out.println("이미지 생성 완료");
	}
}
