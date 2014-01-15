package ude.SocialMediaExplorer.analysis.type;


/* First created by JCasGen Wed Jan 15 11:33:29 CET 2014 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** links or #s 
 * Updated by JCasGen Wed Jan 15 11:33:29 CET 2014
 * XML source: C:/Users/Michael/workspace/SocialMediaExplorer/src/main/java/ude/SocialMediaExplorer/analysis/type/typeSystemDescriptor2.xml
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
  //* Feature: linkOrHash

  /** getter for linkOrHash - gets 
   * @generated */
  public String getLinkOrHash() {
    if (ArktweetAnno_Type.featOkTst && ((ArktweetAnno_Type)jcasType).casFeat_linkOrHash == null)
      jcasType.jcas.throwFeatMissing("linkOrHash", "ArktweetAnno");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ArktweetAnno_Type)jcasType).casFeatCode_linkOrHash);}
    
  /** setter for linkOrHash - sets  
   * @generated */
  public void setLinkOrHash(String v) {
    if (ArktweetAnno_Type.featOkTst && ((ArktweetAnno_Type)jcasType).casFeat_linkOrHash == null)
      jcasType.jcas.throwFeatMissing("linkOrHash", "ArktweetAnno");
    jcasType.ll_cas.ll_setStringValue(addr, ((ArktweetAnno_Type)jcasType).casFeatCode_linkOrHash, v);}    
  }

    