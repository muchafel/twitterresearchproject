

/* First created by JCasGen Fri Nov 29 09:50:45 CET 2013 */
package ude.SocialMediaExplorer.analysis.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Feb 05 12:30:16 CET 2014
 * XML source: C:/Users/Michael/workspace/SocialMediaExplorer/src/main/resources/desc/type/TwitterTypes.xml
 * @generated */
public class SentimentAnno extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SentimentAnno.class);
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
  protected SentimentAnno() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SentimentAnno(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SentimentAnno(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SentimentAnno(JCas jcas, int begin, int end) {
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
  //* Feature: sentimentValue

  /** getter for sentimentValue - gets 
   * @generated */
  public double getSentimentValue() {
    if (SentimentAnno_Type.featOkTst && ((SentimentAnno_Type)jcasType).casFeat_sentimentValue == null)
      jcasType.jcas.throwFeatMissing("sentimentValue", "ude.SocialMediaExplorer.analysis.type.SentimentAnno");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((SentimentAnno_Type)jcasType).casFeatCode_sentimentValue);}
    
  /** setter for sentimentValue - sets  
   * @generated */
  public void setSentimentValue(double v) {
    if (SentimentAnno_Type.featOkTst && ((SentimentAnno_Type)jcasType).casFeat_sentimentValue == null)
      jcasType.jcas.throwFeatMissing("sentimentValue", "ude.SocialMediaExplorer.analysis.type.SentimentAnno");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((SentimentAnno_Type)jcasType).casFeatCode_sentimentValue, v);}    
  }

    