package anim;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import graphics.SpriteSheet;

public class Skeleton
{

	ArrayList<Bone> boneList;
	ArrayList<SpriteSheet> ssl;

	public Skeleton(ArrayList<Bone> boneList, ArrayList<SpriteSheet> ssl)
	{
		this.boneList = boneList;
		this.ssl = ssl;
	}


	public void printBoneValues()
	{
		for (Bone b : boneList)
		{
			System.out.println(b.toString());
		}
	}

	public Bone getBone(int i)
	{
		return boneList.get(i);
	}

	public List<Bone> getBoneList()
	{
		return boneList;
	}
}
