package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lib.Reference;

public class SpriteSheet
{
	private String path;
	private BufferedImage image;

	public SpriteSheet(String path)
	{
		this.path = path;
		load();
	}

	public void load()
	{
		File file = null;
		try
		{
			file = new File(Reference.GRAPHICS_LOCATION + path);
			image = ImageIO.read(file);
		}
		catch (Exception e)
		{
			System.err.println("File " + path + " not found in directory.");
			e.printStackTrace();
			;
		}
	}
	
	public void drawSprite(double xTranslation, double yTranslation, double xScale, double yScale, int xImagePos, int yImagePos, int imageWidth, int imageHeight, int xOrigin, int yOrigin, double angle, int flipFlag, Graphics g)
	{
		if (imageWidth <= 0 || imageHeight <= 0 || xScale == 0 || yScale == 0)
			return;
		
		Graphics2D g2 = (Graphics2D) g;

		// /////////////////////////////// rotation
		AffineTransform at = new AffineTransform();
		at.translate(xTranslation + xOrigin, yTranslation + yOrigin);
		at.rotate(Math.toRadians(angle));
		at.translate(-xOrigin, -yOrigin);
		// /////////////////////////////// scaling
		at.scale(xScale/1000, yScale/1000);
		at.translate(0, ((double)imageHeight*(1.0-(yScale/1000.0))));
			 	
		// /////////////////////////////// image flipping
		BufferedImage renderImage = new BufferedImage ( imageWidth, imageHeight	,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = renderImage.createGraphics();
		if (flipFlag == 0)
		{
			g2d.drawImage(image.getSubimage(xImagePos, yImagePos, imageWidth, imageHeight), 0, 0, (int) imageWidth, (int)  imageHeight, null);
		}
		else
		{
			g2d.drawImage(image.getSubimage(xImagePos, yImagePos, imageWidth, imageHeight), 0, 0, (int)  imageWidth, (int)  imageHeight, (int)  imageWidth, 0, 0, (int)  imageHeight, null);
		}
		g2d.dispose();
		g2.drawImage(renderImage, at, null);
	}
	
	public void drawSprite(double xTranslation, double yTranslation, double xScale, double yScale, int xImagePos, int yImagePos, int imageWidth, int imageHeight, int xOrigin, int yOrigin, double angle, int flipFlag, Graphics g, int direction)
	{
		if (imageWidth <= 0 || imageHeight <= 0 || xScale == 0 || yScale == 0)
			return;
		
		Graphics2D g2 = (Graphics2D) g;

		// /////////////////////////////// rotation
		AffineTransform at = new AffineTransform();
		at.translate(xTranslation + xOrigin, yTranslation + yOrigin);
		at.rotate(Math.toRadians(angle));
		at.translate(-xOrigin, -yOrigin);
		// /////////////////////////////// scaling
		at.scale(xScale/1000, yScale/1000);
		at.translate(0, (imageHeight*(1-(yScale/1000))));
			 	
		// /////////////////////////////// image flipping
		BufferedImage renderImage = new BufferedImage ( imageWidth, imageHeight	,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = renderImage.createGraphics();
		if (flipFlag == 0)
		{
			g2d.drawImage(image.getSubimage(xImagePos, yImagePos, imageWidth, imageHeight), 0, 0, (int) imageWidth, (int)  imageHeight, null);
		}
		else
		{
			g2d.drawImage(image.getSubimage(xImagePos, yImagePos, imageWidth, imageHeight), 0, 0, (int)  imageWidth, (int)  imageHeight, (int)  imageWidth, 0, 0, (int)  imageHeight, null);
		}
		g2d.dispose();
		g2.drawImage(renderImage, at, null);
	}
	
}
