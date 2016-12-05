package survive;

import javax.sound.sampled.*;
import javax.swing.*;

public class Sound {
	private Clip clip;

	public static Sound theme = new Sound("doom2.wav");
	public static Sound powerup = new Sound("powerup.wav");
	public static Sound broken = new Sound("broken.wav");
	public static Sound fall = new Sound("fall.wav");
	public static Sound hurt = new Sound("hurt.wav");
	public static Sound jump = new Sound("jump.wav");
	public static Sound splat = new Sound("splat.wav");
//	public static Sound sound4 = new Sound("doom theme 4.wav");
	
	public Sound (String fileName) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(Sound.class.getResource(fileName));
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setGain(float val)
	{
		if(clip!=null)
		{
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(val);
		}
	}
	
	public void play() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop(){
		if(clip == null) return;
		clip.stop();
	}
	
	public void loop() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isActive(){
		return clip.isActive();
}
}
