package ude.SocialMediaExplorer.client.rmi;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DataHelper implements Serializable{

	private String[] configHashtags;
	
	public DataHelper() {}
	
	private void setConfighashtags(String[] hashtags) {
		this.configHashtags = configHashtags;
	}
	
	private String[] getConfigHastags() {
		return configHashtags;
	}
	
}
