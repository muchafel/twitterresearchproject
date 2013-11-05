package main.analysis.interfaces;

import twitter4j.Status;
import main.analysis.components.language.Language;

/**
 * an Interface for detecting languages 
 * @author henrikdetjen
 *
 */
public interface LanguageClassification {
	
	public Language getLanguage(Status status); //TODO: Handle exceptions
	
}
