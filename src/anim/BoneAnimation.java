package anim;

import graphics.SpriteSheet;

import java.awt.Graphics;
import java.util.*;

public class BoneAnimation implements Comparable
{
	public List<List<Float>> animationValuesY;
	public List<Float> animationValuesX;
	public List<Float> currentAnimationValues;
	public ArrayList<SpriteSheet> ssl;

	public int nextAnimationIndex;
	public int currentFrames = 0;
	public int targetFrames = 5;
	public float lastRotation;
	
	public boolean animationComplete = false;
	public boolean loopable = false;

	public BoneAnimation(List<Bone> boneList)
	{
		ArrayList<List<Float>> animationValues = new ArrayList<List<Float>>();
		this.ssl = boneList.get(0).getSpriteSheetList();
		for (Bone b : boneList)
		{
			animationValues.add(b.getBoneValues());
		}

		animationValuesY = new ArrayList<List<Float>>();
		for (int y = 0; y < animationValues.size(); y++)
		{
			animationValuesX = new ArrayList<Float>();
			for (int x = 0; x < animationValues.get(0).size(); x++)
			{
				animationValuesX.add(animationValues.get(y).get(x).floatValue());
				// System.out.println("yes");
			}
			animationValuesY.add(animationValuesX);
		}

		currentAnimationValues = new ArrayList<Float>();
		for (int x = 0; x < animationValuesY.get(0).size(); x++)
		{
			currentAnimationValues.add(animationValuesY.get(0).get(x).floatValue());
		}
		nextAnimationIndex++;
	}

	public void printAnimationValues()
	{
		for (int y = 0; y < animationValuesY.size(); y++)
		{
			System.out.println();
			for (int x = 0; x < animationValuesX.size(); x++)
			{
				System.out.print(animationValuesY.get(y).get(x) + ", ");
			}
		}

		System.out.println();
		System.out.println("Now print the current values");
		for (int x = 0; x < currentAnimationValues.size(); x++)
		{
			System.out.print(currentAnimationValues.get(x) + ", ");
		}
	}

	public void tick()
	{		
		if (currentFrames == 0 && nextAnimationIndex < animationValuesY.size())
		{
			lastRotation = new Float(animationValuesY.get(nextAnimationIndex - 1).get(6).floatValue());
			float targetRotation = animationValuesY.get(nextAnimationIndex).get(6).floatValue();
			float add360 = lastRotation + 360f;
			float sub360 = lastRotation - 360f;
			
			if (Math.abs(targetRotation - add360) < Math.abs(targetRotation - sub360))
			{
				if (Math.abs(targetRotation - add360) < Math.abs(targetRotation - lastRotation))
				{
					lastRotation = add360;
				}
				else
				{
					//do nothing
				}
			}
			else if (Math.abs(targetRotation - sub360) < Math.abs(targetRotation - lastRotation))
			{
				lastRotation = sub360;
			}
			else
			{
				//do nothing
			}
				
			
		}
		
		if (nextAnimationIndex < animationValuesY.size())
		{
			for (int x = 0; x < animationValuesX.size(); x++)
			{
				// current animation = current x + delta x
				// first one is SMOOTH TWEENING (needs work), 2nd one is REGULAR TWEENING
				//currentAnimationValues.set(x, currentAnimationValues.get(x) + calculateDeltaNextValue(currentAnimationValues.get(x),animationValuesY.get(nextAnimationIndex).get(x).floatValue()));
				if (x == 6)
				{
					currentAnimationValues.set(x, currentAnimationValues.get(x) + calculateDeltaNextValue(lastRotation , animationValuesY.get(nextAnimationIndex).get(x).floatValue()));
				}
				else if (x >= 7 && x <= 11 || x == 4 || x == 5)
				{
					currentAnimationValues.set(x, animationValuesY.get(nextAnimationIndex).get(x).floatValue());
				}
				else currentAnimationValues.set(x, currentAnimationValues.get(x) + calculateDeltaNextValue(animationValuesY.get(nextAnimationIndex - 1).get(x).floatValue(), animationValuesY.get(nextAnimationIndex).get(x).floatValue()));
			}
			currentFrames++;
			// if(Math.abs(currentAnimationValues.get(0) -
			// animationValuesY.get(nextAnimationIndex).get(0)) < 1) //FIX THE
			// RANGE CHRIS
			if (currentFrames == targetFrames)
			{
				for (int i = 0; i < currentAnimationValues.size(); i++)
				{
					currentAnimationValues.set(i, animationValuesY.get(nextAnimationIndex).get(i));
				}
				nextAnimationIndex++;
				currentFrames = 0;
			}
		}
		else
		{
			animationComplete = true;
		}
	}
	
	public void addFrame(List<Float> floatList)
	{
		animationValuesY.add(floatList);
	}

	public float calculateDeltaNextValue(float last, float target)
	{
		return (float)(((double)target - (double)last)*.20);
	}

	public List<Float> getCurrentAnimationValues()
	{
		return currentAnimationValues;
	}

	public void render(Graphics g)
	{
		// g.drawRect(currentAnimationValues.get(0).intValue(),
		// currentAnimationValues.get(1).intValue(), 10, 10);
		currentAnimationValues.set(6,0f);
		new Bone((ArrayList<Float>) currentAnimationValues, ssl).render(0, 0, g);
	}
	
	public void render(int x, int y, Graphics g)
	{
		// g.drawRect(currentAnimationValues.get(0).intValue(),
		// currentAnimationValues.get(1).intValue(), 10, 10);

		new Bone((ArrayList<Float>) currentAnimationValues, ssl).render(x, y, g);
	}
	
	public void render(int x, int y, Graphics g, int direction)
	{
		// g.drawRect(currentAnimationValues.get(0).intValue(),
		// currentAnimationValues.get(1).intValue(), 10, 10);

		new Bone((ArrayList<Float>) currentAnimationValues, ssl).render(x, y, g, direction);
	}
	
	public void setTargetFrames(int x)
	{
		targetFrames = 10;
	}
	
	public int getCurrentFrames()
	{
		return currentFrames;
	}
	
	public int getTargetFrames()
	{
		return targetFrames;
	}
	
	public void setLoopable(boolean b)
	{
		loopable = b;
	}
	
	
	public void reset()
	{
		nextAnimationIndex = 1;
		currentFrames = 0;
		animationComplete = false;
		
		for (int i = 0; i < animationValuesY.get(0).size(); i++)
		{
			currentAnimationValues.set(i, new Float( animationValuesY.get(0).get(i)));
		}
	}
	
	public boolean checkAnimationComplete()
	{
		return animationComplete;
	}
	
	public boolean checkLoopable()
	{
		return loopable;
	}

	@Override
	public int compareTo(Object b)
	{
		if (currentAnimationValues.get(12) > ((BoneAnimation) b).getCurrentAnimationValues().get(12))
			return -1;
		else
			return 1;
	}
}
