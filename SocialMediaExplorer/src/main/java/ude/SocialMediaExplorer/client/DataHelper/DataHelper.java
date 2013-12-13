package ude.SocialMediaExplorer.client.DataHelper;

import java.io.Serializable;

public class DataHelper implements Serializable{

	private String[] configHashtags;
	
	public void DataHelper() {}
	
	private void setConfighashtags(String[] hashtags) {
		this.configHashtags = configHashtags;
	}
	
	private String[] getConfigHastags() {
		return configHashtags;
	}
	
}
