package ude.SocialMediaExplorer.shared.exchangeFormat;

import java.io.Serializable;

public class Sentiment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1547415218581887161L;

	/////////////////////////////////

	private double positive = 0;
	private double negative = 0;

	/////////////////////////////////

	public Sentiment() {

	}
	
	public Sentiment(double positive, double negative) {
		this.setPositive(positive);
		this.setNegative(negative);
	}

	/////////////////////////////////

	public double getNegative() {
		return negative;
	}

	public void setNegative( double negative ) {
		this.negative = negative;
	}

	public double getPositive() {
		return positive;
	}

	public void setPositive( double positive ) {
		this.positive = positive;
	}

	public double getNormalized() {
		return ( this.positive - ( getRange() / 2 ) );
				
	}

	public double getRange() {
		if (this.negative >= 0) {
			return (this.positive - this.negative);			
		}else {
			return (this.positive + this.negative);			
		}
			
	}
}
