package ude.SocialMediaExplorer.analysis.type;

/* First created by JCasGen Sun Jan 05 15:25:00 CET 2014 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.cas.AnnotationBase_Type;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** includes the orthographically cleaned senses and their frequenzy in the whole corpus
 * Updated by JCasGen Fri Jan 17 12:10:54 CET 2014
 * @generated */
public class CleanedSenseAnno_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CleanedSenseAnno_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CleanedSenseAnno_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CleanedSenseAnno(addr, CleanedSenseAnno_Type.this);
  			   CleanedSenseAnno_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CleanedSenseAnno(addr, CleanedSenseAnno_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CleanedSenseAnno.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno");
 
  /** @generated */
  final Feature casFeat_cleanedSense;
  /** @generated */
  final int     casFeatCode_cleanedSense;
  /** @generated */ 
  public String getCleanedSense(int addr) {
        if (featOkTst && casFeat_cleanedSense == null)
      jcas.throwFeatMissing("cleanedSense", "ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno");
    return ll_cas.ll_getStringValue(addr, casFeatCode_cleanedSense);
  }
  /** @generated */    
  public void setCleanedSense(int addr, String v) {
        if (featOkTst && casFeat_cleanedSense == null)
      jcas.throwFeatMissing("cleanedSense", "ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno");
    ll_cas.ll_setStringValue(addr, casFeatCode_cleanedSense, v);}
    
  
 
  /** @generated */
  final Feature casFeat_orderByFreqenzy;
  /** @generated */
  final int     casFeatCode_orderByFreqenzy;
  /** @generated */ 
  public int getOrderByFreqenzy(int addr) {
        if (featOkTst && casFeat_orderByFreqenzy == null)
      jcas.throwFeatMissing("orderByFreqenzy", "ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno");
    return ll_cas.ll_getIntValue(addr, casFeatCode_orderByFreqenzy);
  }
  /** @generated */    
  public void setOrderByFreqenzy(int addr, int v) {
        if (featOkTst && casFeat_orderByFreqenzy == null)
      jcas.throwFeatMissing("orderByFreqenzy", "ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno");
    ll_cas.ll_setIntValue(addr, casFeatCode_orderByFreqenzy, v);}
    
  
 
  /** @generated */
  final Feature casFeat_totalFrequenzy;
  /** @generated */
  final int     casFeatCode_totalFrequenzy;
  /** @generated */ 
  public int getTotalFrequenzy(int addr) {
        if (featOkTst && casFeat_totalFrequenzy == null)
      jcas.throwFeatMissing("totalFrequenzy", "ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno");
    return ll_cas.ll_getIntValue(addr, casFeatCode_totalFrequenzy);
  }
  /** @generated */    
  public void setTotalFrequenzy(int addr, int v) {
        if (featOkTst && casFeat_totalFrequenzy == null)
      jcas.throwFeatMissing("totalFrequenzy", "ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno");
    ll_cas.ll_setIntValue(addr, casFeatCode_totalFrequenzy, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CleanedSenseAnno_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_cleanedSense = jcas.getRequiredFeatureDE(casType, "cleanedSense", "uima.cas.String", featOkTst);
    casFeatCode_cleanedSense  = (null == casFeat_cleanedSense) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cleanedSense).getCode();

 
    casFeat_orderByFreqenzy = jcas.getRequiredFeatureDE(casType, "orderByFreqenzy", "uima.cas.Integer", featOkTst);
    casFeatCode_orderByFreqenzy  = (null == casFeat_orderByFreqenzy) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_orderByFreqenzy).getCode();

 
    casFeat_totalFrequenzy = jcas.getRequiredFeatureDE(casType, "totalFrequenzy", "uima.cas.Integer", featOkTst);
    casFeatCode_totalFrequenzy  = (null == casFeat_totalFrequenzy) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_totalFrequenzy).getCode();

  }
}



    