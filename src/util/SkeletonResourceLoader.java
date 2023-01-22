package util;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import anim.Bone;
import anim.Skeleton;
import lib.Reference;
import graphics.SpriteSheet;

public class SkeletonResourceLoader
{
	private static BufferedReader br;
	private static FileReader fr;

	private static ArrayList<Float> valueList = new ArrayList<Float>();
	private static ArrayList<Bone> boneList = new ArrayList<Bone>(0);
	private static ArrayList<Skeleton> skeletonList = new ArrayList<Skeleton>();

	public static void init(String filepath, ArrayList<SpriteSheet> ssl)
	{
		reset();
		try
		{
			fr = new FileReader(filepath);
			br = new BufferedReader(fr);
		}
		catch (Exception e)
		{
			System.err.println("Skeleton file " + filepath + " not found!");
		}
		initData(filepath, ssl);
	}

	public static void initData(String filepath, ArrayList<SpriteSheet> ssl)
	{
		String str = "";
		try
		{
			br = new BufferedReader(fr);
			while ((str = br.readLine()) != null)
			{
				if (valueList.size() == Reference.VALUES_PER_BONE)
				{
					valueList.set(6, valueList.get(6) * 360 / 10000);
					boneList.add(new Bone(valueList, ssl));
					if (boneList.size() == Reference.BONES_PER_SKELETON)
					{
						skeletonList.add(new Skeleton(boneList, ssl));
						boneList = new ArrayList<Bone>();
					}
					valueList = new ArrayList<Float>();
				}
				parseLine(str);
			}
			// System.out.println(poseList.size()); // should be 440

			// initialize currentPose

			// SET currentPose THIS IS IMPORTANT
			fr = new FileReader(filepath); // this try is to reset the fileRead on the pose data file to load in some values for this pose because i can't clone Pose because it's not serializable.
			br = new BufferedReader(fr); // i dont want to change the values of poseList so I can't change currentPose without doing this because currentPose is a reference to poseList's objects.
			br.skip(9); // skips first line, which is "Pose: 0"

			// for (int i = 0; i < Reference.BONES_PER_SKELETON; i++) // adds all the values of the first parts to the boneListto be added to our currentPose Pose object
			// {
			// storeLineValues(br.readLine());
			//
			// boneList.add(new Bone(valueList, ss));
			// valueList = new ArrayList<Float>(); // cleaning up
			// }
			boneList = new ArrayList<Bone>(); // cleaning up
			System.gc();
			// printPoseList();
			cleanUp();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void printPoseList()
	{
		for (int i = 0; i < 1; i++)
		{
			skeletonList.get(i).printBoneValues();
			System.out.println();
		}
		;
		System.out.println("Number of Poses: " + skeletonList.size());

		System.out.println(skeletonList.get(0).getBone(0));
	}

	public static void parseLine(String str)
	{
		if (str.equals(""))
		{
			// poseNumber++;
		}
		else if (str.startsWith("Pose"))
		{
			// do nothing
		}
		else
		{
			storeLineValues(str);
		}
	}

	public static void storeLineValues(String str)
	{
		String workString = str;

		while (!(workString.equals("")))
		{
			int index = workString.indexOf(',');

			if (index != -1)
			{
				String temp = workString.substring(0, index);
				if (temp.contains("-"))
				{
					valueList.add((float) -Integer.parseInt(temp.substring(1)));
				}
				else
				{
					valueList.add((float) Integer.parseInt(temp.substring(0)));
				}
				// System.out.println(workString.substring(0, index));
				workString = (workString.substring(index + 1));
			}
			else
			{
				if (workString.contains("-"))
				{
					valueList.add((float) -Integer.parseInt(workString));
				}
				else
				{
					valueList.add((float) Integer.parseInt(workString));
				}
				workString = "";
			}
		}
		// System.out.println(valueList);
	}

	public static void cleanUp()
	{
		try
		{
			valueList = new ArrayList<Float>();
			boneList = new ArrayList<Bone>(0);
			fr.close();
		}
		catch (Exception e)
		{

		}
	}
	
	public static void reset()
	{
		skeletonList = new ArrayList<Skeleton>();
	}

	
	public static ArrayList<Skeleton> getSkeletonList()
	{
		return skeletonList;
	}
}
