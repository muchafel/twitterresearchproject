package ude.SocialMediaExplorer.analysis.type;


/* First created by JCasGen Sun Jan 05 15:25:00 CET 2014 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.AnnotationBase;
import org.apache.uima.jcas.tcas.Annotation;


/** includes the orthographically cleaned senses and their frequenzy in the whole corpus
 * Updated by JCasGen Wed Feb 05 12:30:16 CET 2014
 * XML source: C:/Users/Michael/workspace/SocialMediaExplorer/src/main/resources/desc/type/TwitterTypes.xml
 * @generated */
public class CleanedSenseAnno extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CleanedSenseAnno.class);
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
  protected CleanedSenseAnno() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CleanedSenseAnno(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CleanedSenseAnno(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public CleanedSenseAnno(JCas jcas, int begin, int end) {
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
  //* Feature: cleanedSense

  /** getter for cleanedSense - gets 
   * @generated */
  public String getCleanedSense() {
    if (CleanedSenseAnno_Type.featOkTst && ((CleanedSenseAnno_Type)jcasType).casFeat_cleanedSense == null)
      jcasType.jcas.throwFeatMissing("cleanedSense", "ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CleanedSenseAnno_Type)jcasType).casFeatCode_cleanedSense);}
    
  /** setter for cleanedSense - sets  
   * @generated */
  public void setCleanedSense(String v) {
    if (CleanedSenseAnno_Type.featOkTst && ((CleanedSenseAnno_Type)jcasType).casFeat_cleanedSense == null)
      jcasType.jcas.throwFeatMissing("cleanedSense", "ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno");
    jcasType.ll_cas.ll_setStringValue(addr, ((CleanedSenseAnno_Type)jcasType).casFeatCode_cleanedSense, v);}    
   
    
  //*--------------*
  //* Feature: orderByFreqenzy

  /** getter for orderByFreqenzy - gets 
   * @generated */
  public int getOrderByFreqenzy() {
    if (CleanedSenseAnno_Type.featOkTst && ((CleanedSenseAnno_Type)jcasType).casFeat_orderByFreqenzy == null)
      jcasType.jcas.throwFeatMissing("orderByFreqenzy", "ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CleanedSenseAnno_Type)jcasType).casFeatCode_orderByFreqenzy);}
    
  /** setter for orderByFreqenzy - sets  
   * @generated */
  public void setOrderByFreqenzy(int v) {
    if (CleanedSenseAnno_Type.featOkTst && ((CleanedSenseAnno_Type)jcasType).casFeat_orderByFreqenzy == null)
      jcasType.jcas.throwFeatMissing("orderByFreqenzy", "ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno");
    jcasType.ll_cas.ll_setIntValue(addr, ((CleanedSenseAnno_Type)jcasType).casFeatCode_orderByFreqenzy, v);}    
   
    
  //*--------------*
  //* Feature: totalFrequenzy

  /** getter for totalFrequenzy - gets 
   * @generated */
  public int getTotalFrequenzy() {
    if (CleanedSenseAnno_Type.featOkTst && ((CleanedSenseAnno_Type)jcasType).casFeat_totalFrequenzy == null)
      jcasType.jcas.throwFeatMissing("totalFrequenzy", "ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CleanedSenseAnno_Type)jcasType).casFeatCode_totalFrequenzy);}
    
  /** setter for totalFrequenzy - sets  
   * @generated */
  public void setTotalFrequenzy(int v) {
    if (CleanedSenseAnno_Type.featOkTst && ((CleanedSenseAnno_Type)jcasType).casFeat_totalFrequenzy == null)
      jcasType.jcas.throwFeatMissing("totalFrequenzy", "ude.SocialMediaExplorer.analysis.type.CleanedSenseAnno");
    jcasType.ll_cas.ll_setIntValue(addr, ((CleanedSenseAnno_Type)jcasType).casFeatCode_totalFrequenzy, v);}    
  }

    