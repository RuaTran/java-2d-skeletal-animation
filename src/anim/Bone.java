package anim;

import graphics.SpriteSheet;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class Bone implements Comparable
{
	private ArrayList<SpriteSheet> ssl;

	private float xTranslation;
	private float yTranslation;
	private float xScale;
	private float yScale;
	private int xOrigin;
	private int yOrigin;
	private float angle;
	private int flipFlag;
	private int xImagePos;
	private int yImagePos;
	private int imageWidth;
	private int imageHeight;
	private int layer;
	private int imageIndex;

	public Bone(int xTranslation, int yTranslation, int xScale, int yScale, int xOrigin, int yOrigin, int angle, int flipFlag, int xImagePos, int yImagePos, int imageWidth, int imageHeight, int layer, ArrayList<SpriteSheet> ssl)
	{
		this.xTranslation = xTranslation; // 1
		this.yTranslation = yTranslation; // 2
		this.xScale = xScale; // 6
		this.yScale = yScale; // 7
		this.xOrigin = xOrigin;// 17
		this.yOrigin = yOrigin;// 18
		this.angle = angle; // 8
		this.flipFlag = flipFlag;
		this.xImagePos = xImagePos;// 12
		this.yImagePos = yImagePos;// 13
		this.imageWidth = imageWidth;// 14
		this.imageHeight = imageHeight;// 15
		this.layer = layer;// 16
		this.imageIndex = 0;
		this.ssl = ssl;

	}

	public Bone(ArrayList<Float> valueList, ArrayList<SpriteSheet> ssl)
	{
		this.xTranslation = valueList.get(0); // 1
		this.yTranslation = valueList.get(1); // 2
		this.xScale = valueList.get(2); // 6
		this.yScale = valueList.get(3); // 7
		this.xOrigin = valueList.get(4).intValue();// 17
		this.yOrigin = valueList.get(5).intValue();// 18
		this.angle = valueList.get(6); // 8
		this.flipFlag = valueList.get(7).intValue();
		this.xImagePos = valueList.get(8).intValue();// 12
		this.yImagePos = valueList.get(9).intValue();// 13
		this.imageWidth = valueList.get(10).intValue();// 14
		this.imageHeight = valueList.get(11).intValue();// 15
		this.layer = valueList.get(12).intValue();// ITS THIRTEEN BUBBY
		this.imageIndex = valueList.get(13).intValue(); //imageindex
		this.ssl = ssl;
	}


	public int getLayer()
	{
		return layer;
	}

	public ArrayList<Float> getBoneValues()
	{
		ArrayList<Float> valueList = new ArrayList<Float>();
		valueList.add(xTranslation);
		valueList.add(yTranslation);
		valueList.add(xScale);
		valueList.add(yScale);
		valueList.add((float) xOrigin);
		valueList.add((float) yOrigin);
		valueList.add(angle);
		valueList.add((float) flipFlag);
		valueList.add((float) xImagePos);
		valueList.add((float) yImagePos);
		valueList.add((float) imageWidth);
		valueList.add((float) imageHeight);
		valueList.add((float) layer);
		valueList.add((float)imageIndex);
		return valueList;
	}
	
	public float getAngle()
	{
		return angle;
	}
	
	public void setAngle(float angle)
	{
		this.angle = angle;
	}
	
	public ArrayList<SpriteSheet> getSpriteSheetList()
	{
		return ssl;
	}

	public void render(int x, int y, Graphics g)
	{
		ssl.get(imageIndex).drawSprite(x + xTranslation, y +  yTranslation, xScale, yScale, xImagePos, yImagePos, imageWidth, imageHeight, xOrigin, yOrigin, angle, flipFlag, g);
	}
	
	public void render(int x, int y, Graphics g, int direction)
	{
		ssl.get(imageIndex).drawSprite(x + xTranslation, y +  yTranslation, xScale, yScale, xImagePos, yImagePos, imageWidth, imageHeight, xOrigin, yOrigin, angle, flipFlag, g, direction);
	}

	public String toString()
	{
		return xTranslation + "," + yTranslation + "," + xScale + "," + yScale + "," + xOrigin + "," + yOrigin + "," + angle + "," + flipFlag + "," + xImagePos + "," + yImagePos + "," + imageWidth + "," + imageHeight + "," + layer + "," + ssl;
	}

	public int compareTo(Object b)
	{
		if (this.getLayer() > ((Bone) b).getLayer())
		{
			return -1;
		}
		else return 1;
	}


}
