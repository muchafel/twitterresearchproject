package ude.SocialMediaExplorer.data.result;

import ude.SocialMediaExplorer.data.utils.time.TimeSpan;
import ude.SocialMediaExplorer.shared.exchangeFormat.ClusterElement;

public interface IResultPooling {
	
	ClusterElement getClusters(String hashtag) throws Exception;
	
	ClusterElement getClusters(String hashtag, TimeSpan timespan) throws Exception;
}
