
/* First created by JCasGen Wed Nov 20 19:52:24 CET 2013 */

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
 * Updated by JCasGen Wed Nov 20 19:52:24 CET 2013
 * @generated */
public class Sentiment_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Sentiment_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Sentiment_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Sentiment(addr, Sentiment_Type.this);
  			   Sentiment_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Sentiment(addr, Sentiment_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Sentiment.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("Sentiment");
 
  /** @generated */
  final Feature casFeat_positive;
  /** @generated */
  final int     casFeatCode_positive;
  /** @generated */ 
  public boolean getPositive(int addr) {
        if (featOkTst && casFeat_positive == null)
      jcas.throwFeatMissing("positive", "Sentiment");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_positive);
  }
  /** @generated */    
  public void setPositive(int addr, boolean v) {
        if (featOkTst && casFeat_positive == null)
      jcas.throwFeatMissing("positive", "Sentiment");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_positive, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Sentiment_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_positive = jcas.getRequiredFeatureDE(casType, "positive", "uima.cas.Boolean", featOkTst);
    casFeatCode_positive  = (null == casFeat_positive) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_positive).getCode();

  }
}



    