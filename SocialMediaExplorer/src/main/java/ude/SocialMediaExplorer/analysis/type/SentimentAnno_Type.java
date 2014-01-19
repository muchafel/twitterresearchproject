
/* First created by JCasGen Fri Nov 29 09:50:45 CET 2013 */
package ude.SocialMediaExplorer.analysis.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Fri Jan 17 12:10:54 CET 2014
 * @generated */
public class SentimentAnno_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SentimentAnno_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SentimentAnno_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SentimentAnno(addr, SentimentAnno_Type.this);
  			   SentimentAnno_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SentimentAnno(addr, SentimentAnno_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = SentimentAnno.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ude.SocialMediaExplorer.analysis.type.SentimentAnno");
 
  /** @generated */
  final Feature casFeat_sentimentValue;
  /** @generated */
  final int     casFeatCode_sentimentValue;
  /** @generated */ 
  public String getSentimentValue(int addr) {
        if (featOkTst && casFeat_sentimentValue == null)
      jcas.throwFeatMissing("sentimentValue", "ude.SocialMediaExplorer.analysis.type.SentimentAnno");
    return ll_cas.ll_getStringValue(addr, casFeatCode_sentimentValue);
  }
  /** @generated */    
  public void setSentimentValue(int addr, String v) {
        if (featOkTst && casFeat_sentimentValue == null)
      jcas.throwFeatMissing("sentimentValue", "ude.SocialMediaExplorer.analysis.type.SentimentAnno");
    ll_cas.ll_setStringValue(addr, casFeatCode_sentimentValue, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SentimentAnno_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_sentimentValue = jcas.getRequiredFeatureDE(casType, "sentimentValue", "uima.cas.String", featOkTst);
    casFeatCode_sentimentValue  = (null == casFeat_sentimentValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentimentValue).getCode();

  }
}



    