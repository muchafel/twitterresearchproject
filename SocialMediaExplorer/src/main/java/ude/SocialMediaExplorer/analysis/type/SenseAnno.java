package ude.SocialMediaExplorer.analysis.type;


/* First created by JCasGen Mon Dec 02 18:09:31 CET 2013 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Feb 05 12:30:16 CET 2014
 * XML source: C:/Users/Michael/workspace/SocialMediaExplorer/src/main/resources/desc/type/TwitterTypes.xml
 * @generated */
public class SenseAnno extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SenseAnno.class);
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
  protected SenseAnno() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SenseAnno(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SenseAnno(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SenseAnno(JCas jcas, int begin, int end) {
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
  //* Feature: senseValue

  /** getter for senseValue - gets 
   * @generated */
  public String getSenseValue() {
    if (SenseAnno_Type.featOkTst && ((SenseAnno_Type)jcasType).casFeat_senseValue == null)
      jcasType.jcas.throwFeatMissing("senseValue", "ude.SocialMediaExplorer.analysis.type.SenseAnno");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SenseAnno_Type)jcasType).casFeatCode_senseValue);}
    
  /** setter for senseValue - sets  
   * @generated */
  public void setSenseValue(String v) {
    if (SenseAnno_Type.featOkTst && ((SenseAnno_Type)jcasType).casFeat_senseValue == null)
      jcasType.jcas.throwFeatMissing("senseValue", "ude.SocialMediaExplorer.analysis.type.SenseAnno");
    jcasType.ll_cas.ll_setStringValue(addr, ((SenseAnno_Type)jcasType).casFeatCode_senseValue, v);}    
  }

    