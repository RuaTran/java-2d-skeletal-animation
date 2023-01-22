package util;

import enums.MobState;
import graphics.SpriteSheet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import anim.Skeleton;
import anim.SkeletonAnimation;

public class ResourceLoader
{
	public static HashMap<MobState, SkeletonAnimation> m_swordmanAnimations;
	public static HashMap<MobState, SkeletonAnimation> poringAnimations;

	public static synchronized void loadGraphics()
	{
		try
		{
			m_swordmanAnimations = new HashMap<MobState, SkeletonAnimation> ();
			poringAnimations = new HashMap<MobState, SkeletonAnimation> ();
			createPlayerGraphics();
			createPetGraphics();
		}
		catch (Exception e)
		{

		}
	}
	
	public static void createPlayerGraphics()
	{
		ArrayList<SpriteSheet> ssl = new ArrayList<SpriteSheet>();
		ssl.add(new SpriteSheet("sprites/characters/m_swordman/m_swordman1.png"));
		ssl.add(new SpriteSheet("sprites/characters/m_swordman/m_swordman2.png"));
		SkeletonResourceLoader.init("resources/animData/characters/m_swordman/filename.txt", ssl);
		SkeletonAnimation saFull = new SkeletonAnimation(SkeletonResourceLoader.getSkeletonList());
		SkeletonAnimation  standingAnimation = saFull.createSubAnimation(0, 1);
		standingAnimation.addFrame(saFull.getSkeletonList().get(0));
		standingAnimation.addFrame(saFull.getSkeletonList().get(1));
		standingAnimation.addFrame(saFull.getSkeletonList().get(1));
		standingAnimation.addFrame(saFull.getSkeletonList().get(2));
		standingAnimation.addFrame(saFull.getSkeletonList().get(2));
		standingAnimation.addFrame(saFull.getSkeletonList().get(1));
		standingAnimation.addFrame(saFull.getSkeletonList().get(1));
		standingAnimation.addFrame(saFull.getSkeletonList().get(0));
		standingAnimation.setLoopable(true);
		
		SkeletonAnimation walkingAnimation  = saFull.createSubAnimation(3,7);
		walkingAnimation.addFrame(saFull.getSkeletonList().get(3));
		walkingAnimation.setLoopable(true);
		
		SkeletonAnimation duckingAnimation  = saFull.createSubAnimation(16,19);
		
		m_swordmanAnimations.put(MobState.STANDING, standingAnimation);
		m_swordmanAnimations.put(MobState.WALKRIGHT, walkingAnimation);
		m_swordmanAnimations.put(MobState.WALKLEFT, walkingAnimation);
		m_swordmanAnimations.put(MobState.DUCKING, duckingAnimation);
	}
	
	public static void createPetGraphics()
	{
		ArrayList<SpriteSheet> ssl = new ArrayList<SpriteSheet>();
		ssl.add(new SpriteSheet("sprites/characters/poring/poring.png"));
		SkeletonResourceLoader.init("resources/animData/characters/poring/filename.txt", ssl);
		SkeletonAnimation saFull = new SkeletonAnimation(SkeletonResourceLoader.getSkeletonList());
		SkeletonAnimation  standingAnimation = saFull.createSubAnimation(0, 7);
		standingAnimation.addFrame(saFull.getSkeletonList().get(0));
		standingAnimation.setLoopable(true);
		
		SkeletonAnimation walkingAnimation  = saFull.createSubAnimation(7,12);
		walkingAnimation.setLoopable(true);
		
		poringAnimations.put(MobState.STANDING, standingAnimation);
		poringAnimations.put(MobState.WALKRIGHT, walkingAnimation);
		poringAnimations.put(MobState.WALKLEFT, walkingAnimation);
		poringAnimations.put(MobState.DUCKING, saFull.createSubAnimation(0,1));
	}
}
