package ude.SocialMediaExplorer.analysis.type;


/* First created by JCasGen Wed Jan 15 11:33:29 CET 2014 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;


/** links or #s
 * Updated by JCasGen Wed Feb 05 12:30:16 CET 2014
 * XML source: C:/Users/Michael/workspace/SocialMediaExplorer/src/main/resources/desc/type/TwitterTypes.xml
 * @generated */
public class ArktweetAnno extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ArktweetAnno.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ArktweetAnno() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ArktweetAnno(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ArktweetAnno(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ArktweetAnno(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: linkOrEmoticon

  /** getter for linkOrEmoticon - gets 
   * @generated */
  public POS getLinkOrEmoticon() {
    if (ArktweetAnno_Type.featOkTst && ((ArktweetAnno_Type)jcasType).casFeat_linkOrEmoticon == null)
      jcasType.jcas.throwFeatMissing("linkOrEmoticon", "ude.SocialMediaExplorer.analysis.type.ArktweetAnno");
    return (POS)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ArktweetAnno_Type)jcasType).casFeatCode_linkOrEmoticon)));}
    
  /** setter for linkOrEmoticon - sets  
   * @generated */
  public void setLinkOrEmoticon(POS v) {
    if (ArktweetAnno_Type.featOkTst && ((ArktweetAnno_Type)jcasType).casFeat_linkOrEmoticon == null)
      jcasType.jcas.throwFeatMissing("linkOrEmoticon", "ude.SocialMediaExplorer.analysis.type.ArktweetAnno");
    jcasType.ll_cas.ll_setRefValue(addr, ((ArktweetAnno_Type)jcasType).casFeatCode_linkOrEmoticon, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    