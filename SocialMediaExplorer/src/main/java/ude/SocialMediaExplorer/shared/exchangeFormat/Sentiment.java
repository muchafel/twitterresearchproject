package ude.SocialMediaExplorer.shared.exchangeFormat;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Sentiment implements Serializable{
	
	/////////////////////////////////
	
	private double positive = 0;
	private double negative = 0;
	
	/////////////////////////////////
	
	public Sentiment() {
		
	}
	
	/////////////////////////////////

	public double getNegative() {
		return negative;
	}

	public void setNegative(double negative) {
		this.negative = negative;
	}

	public double getPositive() {
		return positive;
	}

	public void setPositive(double positive) {
		this.positive = positive;
	}
	
	public double getNormalized(){
		return  ( this.getPositive() + this.getNegative() ) / 2;
	}
}
