

/* First created by JCasGen Wed Nov 20 19:52:24 CET 2013 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Nov 20 19:52:24 CET 2013
 * XML source: D:/div/work/workspace/SocialMediaExplorer/aeDescriptor.xml
 * @generated */
public class Sentiment extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Sentiment.class);
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
  protected Sentiment() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Sentiment(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Sentiment(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Sentiment(JCas jcas, int begin, int end) {
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
  //* Feature: positive

  /** getter for positive - gets 
   * @generated */
  public boolean getPositive() {
    if (Sentiment_Type.featOkTst && ((Sentiment_Type)jcasType).casFeat_positive == null)
      jcasType.jcas.throwFeatMissing("positive", "Sentiment");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Sentiment_Type)jcasType).casFeatCode_positive);}
    
  /** setter for positive - sets  
   * @generated */
  public void setPositive(boolean v) {
    if (Sentiment_Type.featOkTst && ((Sentiment_Type)jcasType).casFeat_positive == null)
      jcasType.jcas.throwFeatMissing("positive", "Sentiment");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Sentiment_Type)jcasType).casFeatCode_positive, v);}    
  }

    