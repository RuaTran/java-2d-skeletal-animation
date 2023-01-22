package maps;

import game.Game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import lib.Reference;

import javax.imageio.ImageIO;

public class Level
{
	BufferedImage levelImage;
	public Level(String filePath)
	{
		File file = new File(Reference.BACKGROUND_LOCATION + filePath);
		try
		{
			levelImage = ImageIO.read(file);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void render( Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		AffineTransform at = new AffineTransform();
		double scaleX = Game.WIDTH / (double) levelImage.getHeight();
		double scaleY = Game.HEIGHT / (double) levelImage.getHeight();
		at.scale(scaleX, scaleY);
		g2.drawImage(levelImage,at,null);
	}
}
