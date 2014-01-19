package ude.SocialMediaExplorer.analysis.type;

/* First created by JCasGen Fri Jan 17 12:09:45 CET 2014 */

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
 * Updated by JCasGen Fri Jan 17 12:09:45 CET 2014
 * @generated */
public class SimpleSenseAnno_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SimpleSenseAnno_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SimpleSenseAnno_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SimpleSenseAnno(addr, SimpleSenseAnno_Type.this);
  			   SimpleSenseAnno_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SimpleSenseAnno(addr, SimpleSenseAnno_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = SimpleSenseAnno.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("SimpleSenseAnno");
 
  /** @generated */
  final Feature casFeat_simpleSense;
  /** @generated */
  final int     casFeatCode_simpleSense;
  /** @generated */ 
  public String getSimpleSense(int addr) {
        if (featOkTst && casFeat_simpleSense == null)
      jcas.throwFeatMissing("simpleSense", "SimpleSenseAnno");
    return ll_cas.ll_getStringValue(addr, casFeatCode_simpleSense);
  }
  /** @generated */    
  public void setSimpleSense(int addr, String v) {
        if (featOkTst && casFeat_simpleSense == null)
      jcas.throwFeatMissing("simpleSense", "SimpleSenseAnno");
    ll_cas.ll_setStringValue(addr, casFeatCode_simpleSense, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SimpleSenseAnno_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_simpleSense = jcas.getRequiredFeatureDE(casType, "simpleSense", "uima.cas.String", featOkTst);
    casFeatCode_simpleSense  = (null == casFeat_simpleSense) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_simpleSense).getCode();

  }
}



    