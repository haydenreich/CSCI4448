//
//import javax.sound.sampled.*;
//import javax.swing.*;
//
//public class Sound {
//	private Clip clip;
//
//	public static Sound sound2 = new Sound("doom theme 1.wav");
////	public static Sound sound2 = new Sound("doom theme 2.wav");
////	public static Sound sound3 = new Sound("doom theme 3.wav");
////	public static Sound sound4 = new Sound("doom theme 4.wav");
//	
//	public Sound (String fileName) {
//		try {
//			AudioInputStream ais = AudioSystem.getAudioInputStream(Sound.class.getResource(fileName));
//			clip = AudioSystem.getClip();
//			clip.open(ais);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void play() {
//		try {
//			if (clip != null) {
//				new Thread() {
//					public void run() {
//						synchronized (clip) {
//							clip.stop();
//							clip.setFramePosition(0);
//							clip.start();
//						}
//					}
//				}.start();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void stop(){
//		if(clip == null) return;
//		clip.stop();
//	}
//	
//	public void loop() {
//		try {
//			if (clip != null) {
//				new Thread() {
//					public void run() {
//						synchronized (clip) {
//							clip.stop();
//							clip.setFramePosition(0);
//							clip.loop(Clip.LOOP_CONTINUOUSLY);
//						}
//					}
//				}.start();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public boolean isActive(){
//		return clip.isActive();
//}
//}
