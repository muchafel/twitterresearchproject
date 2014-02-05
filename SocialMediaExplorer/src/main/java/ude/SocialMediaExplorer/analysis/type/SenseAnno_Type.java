package ude.SocialMediaExplorer.analysis.type;

/* First created by JCasGen Mon Dec 02 18:09:31 CET 2013 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Wed Feb 05 12:30:16 CET 2014
 * @generated */
public class SenseAnno_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SenseAnno_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SenseAnno_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SenseAnno(addr, SenseAnno_Type.this);
  			   SenseAnno_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SenseAnno(addr, SenseAnno_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = SenseAnno.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ude.SocialMediaExplorer.analysis.type.SenseAnno");

  /** @generated */
  final Feature casFeat_senseValue;
  /** @generated */
  final int     casFeatCode_senseValue;
  /** @generated */ 
  public String getSenseValue(int addr) {
        if (featOkTst && casFeat_senseValue == null)
      jcas.throwFeatMissing("senseValue", "ude.SocialMediaExplorer.analysis.type.SenseAnno");
    return ll_cas.ll_getStringValue(addr, casFeatCode_senseValue);
  }
  /** @generated */    
  public void setSenseValue(int addr, String v) {
        if (featOkTst && casFeat_senseValue == null)
      jcas.throwFeatMissing("senseValue", "ude.SocialMediaExplorer.analysis.type.SenseAnno");
    ll_cas.ll_setStringValue(addr, casFeatCode_senseValue, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SenseAnno_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_senseValue = jcas.getRequiredFeatureDE(casType, "senseValue", "uima.cas.String", featOkTst);
    casFeatCode_senseValue  = (null == casFeat_senseValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_senseValue).getCode();

  }
}



    