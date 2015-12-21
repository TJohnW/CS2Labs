/*
 * CarRadio.java
 *
 * Version:
 *  $Id: CarRadio.java,v 1.2 2014/02/12 04:06:35 txw6529 Exp $
 * Revisions:
 *  $Log: CarRadio.java,v $
 *  Revision 1.2  2014/02/12 04:06:35  txw6529
 *  Removed getters/setters in the private enum
 *
 *  Revision 1.1  2014/02/11 02:56:20  txw6529
 *  CarRadio Project
 *
 */

/** 
 * @author Tristan Whitcher
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CarRadio {
	
	/**
	 * Here to unify the current band type(AM/FM) with its value and presets
	 * to simplify a lot of the code (remove testing current band type).
	 * Private variables in here are accessible only to CarRadio.
	 */
	private enum RadioBand {
		
		AM(FreqBand.AM, 520, 5),
		FM(FreqBand.FM, 87900, 5);
		
		private FreqBand band;
		private int freq;
		private int[] presets;
		
		private RadioBand(FreqBand band, int freq, int numPresets) {
			this.band = band;
			this.freq = freq;
			this.presets = new int[numPresets];
			for(int i = 0; i < numPresets; i++)
				this.presets[i] = this.band.minFreq();
		}
		
	}
	
	private static final int MAX_VOLUME = 20;
	private static final int MIN_VOLUME = 0;
	
	private RadioBand currentBand;
	private StationData stationData;
	
	private int volume;
	
	private boolean power;
	private boolean muted;
	private boolean shouldSet;
	
	/**
	 * The constructor for the CarRadio object.
	 * @param sd the StationData to use with the car radio object.
	 */
	public CarRadio(StationData sd) {
		this.currentBand = RadioBand.AM;
		this.stationData = sd;
		this.volume = 0;
		this.power = false;
		this.muted = false;
		this.shouldSet = false;
		
		List<String> myStrings2 = new ArrayList<String>();
		List<String> myStrings4 = new LinkedList<String>();
		
	}
	
	/* Preset buttons pressed */
	public void preset1Btn() { presetBtnHandler(1); }
	public void preset2Btn() { presetBtnHandler(2); }
	public void preset3Btn() { presetBtnHandler(3); }
	public void preset4Btn() { presetBtnHandler(4); }
	public void preset5Btn() { presetBtnHandler(5); }
	
	/* Frequency Handling - Tune and Seek */
	public void tuneUpBtn() { tuneBtnHandler(true); }
	public void tuneDownBtn() { tuneBtnHandler(false); }
	
	public void seekUpBtn() { seekBtnHandler(true); }
	public void seekDownBtn() { seekBtnHandler(false); }
	
	/* Volume Handling */
	public void volumeUpBtn() { volumeBtnHandler(true); }
	public void volumeDownBtn() { volumeBtnHandler(false); }
	
	/**
	 * Toggles the set button.
	 */
	public void setBtn() { 
		if(!power) return;
		shouldSet = !shouldSet;
	}
	
	/**
	 * Toggles the power.
	 */
	public void powerBtn() {
		cancelSet();
		power = !power;
	}
	
	/**
	 * Toggles mute.
	 */
	public void muteBtn() {
		if(!power) return;
		cancelSet();
		muted = !muted;
	}
	
	/**
	 * Toggles AM/FM bands.
	 */
	public void amfmBtn() {
		if(!power) return;
		cancelSet();
		if(currentBand == RadioBand.AM) 
			currentBand = RadioBand.FM;
		else
			currentBand = RadioBand.AM;
	}
	
	/**
	 * Called to cancel the set.
	 */
	private void cancelSet() {
		shouldSet = false;
	}
	
	/**
	 * Here to prevent code duplication.
	 * Acts from any of the tune btns to go up or down.
	 * 
	 * @param up true if tuning up, false if down
	 **/
	private void tuneBtnHandler(boolean up) {
		if(!power) return;
		cancelSet();
		int nextFreq = currentBand.freq;
		//Positive or negative?
		nextFreq += (up) ? currentBand.band.spacing() : currentBand.band.spacing()*-1;
		if(up && nextFreq > currentBand.band.maxFreq()) {
			nextFreq = currentBand.band.minFreq();
		} else if(!up && nextFreq < currentBand.band.minFreq()) {
			nextFreq = currentBand.band.maxFreq();
		}
		currentBand.freq = nextFreq;
	}
	
	/**
	 * Here to prevent code duplication.
	 * Acts upon a seek button up or down.
	 * 
	 * @param up true if tuning up, false if down
	 **/
	private void seekBtnHandler(boolean up) {
		if(!power) return;
		cancelSet();
		int start = currentBand.freq;
		do {
			if(up) tuneUpBtn();
			else tuneDownBtn();
			if(stationData.lookupFreq(currentBand.band, currentBand.freq) != null) {
				return;
			}
		} while(currentBand.freq != start);
	}
	
	/**
	 * Here to prevent code duplication.
	 * Acts upon any of the preset buttons.
	 * 
	 * @param preset the preset to set
	 **/
	private void presetBtnHandler(int preset) {
		if(!power) return;
		if(shouldSet) {
			currentBand.presets[preset-1] = currentBand.freq;
			cancelSet();
		} else {
			currentBand.freq = currentBand.presets[preset-1];
		}
	}
	
	/**
	 * Here for consistency.
	 * 
	 * @param up on true volume up, false down.
	 */
	private void volumeBtnHandler(boolean up) {
		if(up && volume < MAX_VOLUME && power)
			volume++;
		else if(!up && volume > MIN_VOLUME && power)
			volume--;
	}
	
	/**
	 * Last because display
	 * 
	 * @return Returns ArrayList of formatted strings to be outputted by the UI.
	 */
	public ArrayList<String> display() {
		ArrayList<String> out = new ArrayList<String>();
		String body = "---------------------";
		out.add(body);
		/* When power on */
		if(power) {
			String freqOut = String.valueOf(currentBand.freq);
			if(currentBand == RadioBand.FM) {
				short strlen = (short) freqOut.length();
				freqOut = freqOut.substring(0, strlen-3) + "." + freqOut.charAt(strlen-3);
			}
			String currentStation =  stationData.lookupFreq(currentBand.band, currentBand.freq);
			String setOut = "    ";
			String volOut = String.valueOf(volume);
			
			/* Formatting */
			if(currentStation == null) currentStation = "****";
			if(shouldSet) setOut = "SET ";
			if(this.muted) volOut = "--";
			
			out.add(String.format("|  %2s%7s  %s  |", currentBand.toString(), freqOut, currentStation));
			out.add(String.format("|  Vol:%3s    %s  |", volOut, setOut));
		} else {
		/* Power off */
			out.add(String.format("|%19s|", ""));
			out.add(String.format("|%19s|", ""));
		}
		out.add(body);
		return out;
	}

}