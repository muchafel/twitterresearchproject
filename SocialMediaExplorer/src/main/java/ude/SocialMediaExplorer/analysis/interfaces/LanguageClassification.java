package ude.SocialMediaExplorer.analysis.interfaces;

import ude.SocialMediaExplorer.analysis.components.language.Language;

/**
 * an Interface for detecting languages 
 * @author henrikdetjen
 *
 */
public interface LanguageClassification {
	
	public Language getLanguage(String msg); //TODO: Handle exceptions
	
}
