package anim;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import lib.Reference;

public class SkeletonAnimation
{
	public ArrayList<BoneAnimation> boneAnimationList;
	public ArrayList<Skeleton> skeletonList;

	public SkeletonAnimation()
	{

	}

	public SkeletonAnimation(ArrayList<Skeleton> skeletonList)
	{
		boneAnimationList = new ArrayList<BoneAnimation>();
		this.skeletonList = skeletonList;
		createBoneAnimations();
	}

	public SkeletonAnimation createSubAnimation(int x, int y)
	{
		ArrayList<Skeleton> sL = new ArrayList<Skeleton>();
		for (int i = x; i < y; i++)
		{
			sL.add(skeletonList.get(i));
		}
		return new SkeletonAnimation(sL);
	}

	public void createBoneAnimations()
	{
		for (int x = 0; x < skeletonList.get(0).getBoneList().size(); x++) // if you get an error, it could be because of this '0' here.
		{
			ArrayList<Bone> boneList = new ArrayList<Bone>();
			for (int y = 0; y < skeletonList.size(); y++)
			{ // ideally should be Reference.BONES_PER_SKELETON
				boneList.add(skeletonList.get(y).getBone(x));
			}
			boneAnimationList.add(new BoneAnimation(boneList));
		}
	}

	public ArrayList<Skeleton> getSkeletonList()
	{
		return skeletonList;
	}

	public void addFrame(Skeleton s)
	{
		for (int x = 0; x < s.getBoneList().size(); x++) // if you get an error, it could be because of this '0' here.
		{
			ArrayList<Bone> boneList = new ArrayList<Bone>();
			boneList.add(s.getBone(x));
			boneAnimationList.get(x).addFrame(s.getBone(x).getBoneValues());
		}
	}

	public void tick()
	{
		Collections.sort(boneAnimationList);
		for (BoneAnimation b : boneAnimationList)
		{
			if (b.getCurrentFrames() != b.getTargetFrames())
			{
				b.tick();
			}
			if (b.checkAnimationComplete() && b.checkLoopable())
			{
				b.reset();
			}
		}
	}

	public void setLoopable(boolean b)
	{
		for (BoneAnimation bA : boneAnimationList)
		{
			bA.setLoopable(b);
		}
	}

	public void play()
	{
		for (BoneAnimation bA : boneAnimationList)
		{
			bA.reset();
		}
	}

	public void render(Graphics g)
	{
		for (BoneAnimation b : boneAnimationList)
		{
			b.render(g);
		}
	}

	public void render(int x, int y, Graphics g)
	{
		for (BoneAnimation b : boneAnimationList)
		{
			b.render(x, y, g);
		}
	}

	public void render(int x, int y, Graphics g, int direction)
	{
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = new AffineTransform();
		if (direction == 0)
		{
			at.scale(-1, 1);
			g2.setTransform(at);
			
			for (BoneAnimation b : boneAnimationList)
			{
				b.render(-x, y, g2);
			}
		}
		else
		for (BoneAnimation b : boneAnimationList)
		{
			b.render(x, y, g2);
		}
		
		g2.setTransform(new AffineTransform());
	}
}
