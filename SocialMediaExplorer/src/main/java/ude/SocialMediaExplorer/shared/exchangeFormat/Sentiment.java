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

	public Sentiment( double positive, double negative ) {
		this.setPositive( positive );
		this.setNegative( negative );
	}

	/////////////////////////////////

	public double getNegative() {
		return negative;
	}

	public void setNegative( double negative ) {
		if(Double.isNaN( negative ) || Double.isInfinite( negative )) {
			this.negative = 0D;
		}else {
			this.negative = negative;			
		}
	}

	public double getPositive() {
		return positive;
	}

	public void setPositive( double positive ) {
		if(Double.isNaN( positive ) || Double.isInfinite( positive )) {
			this.positive = 0D;
		}else {
			this.positive = positive;			
		}
	}

	public double getNormalized() {
		Double range = getRange();
		if ( range > 0 ) {
			return ( this.positive - ( range / 2D ) );
		}
		else {
			return this.positive;
		}
	}

	public double getRange() {
		return Math.abs( ( this.positive - Math.abs( this.negative ) ) );
	}
}
