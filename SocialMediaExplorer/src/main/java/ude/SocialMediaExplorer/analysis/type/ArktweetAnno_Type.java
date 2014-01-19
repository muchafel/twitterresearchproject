package ude.SocialMediaExplorer.analysis.type;

/* First created by JCasGen Wed Jan 15 11:33:29 CET 2014 */

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

/** links or #s
 * Updated by JCasGen Fri Jan 17 12:10:54 CET 2014
 * @generated */
public class ArktweetAnno_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ArktweetAnno_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ArktweetAnno_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ArktweetAnno(addr, ArktweetAnno_Type.this);
  			   ArktweetAnno_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ArktweetAnno(addr, ArktweetAnno_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ArktweetAnno.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ude.SocialMediaExplorer.analysis.type.ArktweetAnno");
 
  /** @generated */
  final Feature casFeat_linkOrEmoticon;
  /** @generated */
  final int     casFeatCode_linkOrEmoticon;
  /** @generated */ 
  public int getLinkOrEmoticon(int addr) {
        if (featOkTst && casFeat_linkOrEmoticon == null)
      jcas.throwFeatMissing("linkOrEmoticon", "ude.SocialMediaExplorer.analysis.type.ArktweetAnno");
    return ll_cas.ll_getRefValue(addr, casFeatCode_linkOrEmoticon);
  }
  /** @generated */    
  public void setLinkOrEmoticon(int addr, int v) {
        if (featOkTst && casFeat_linkOrEmoticon == null)
      jcas.throwFeatMissing("linkOrEmoticon", "ude.SocialMediaExplorer.analysis.type.ArktweetAnno");
    ll_cas.ll_setRefValue(addr, casFeatCode_linkOrEmoticon, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ArktweetAnno_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_linkOrEmoticon = jcas.getRequiredFeatureDE(casType, "linkOrEmoticon", "de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS", featOkTst);
    casFeatCode_linkOrEmoticon  = (null == casFeat_linkOrEmoticon) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_linkOrEmoticon).getCode();

  }
}



    