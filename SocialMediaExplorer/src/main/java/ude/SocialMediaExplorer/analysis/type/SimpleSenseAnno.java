package ude.SocialMediaExplorer.analysis.type;


/* First created by JCasGen Fri Jan 17 12:09:45 CET 2014 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Jan 17 12:09:45 CET 2014
 * XML source: C:/Users/Michael/workspace/SocialMediaExplorer/src/main/resources/desc/type/TwitterTypes.xml
 * @generated */
public class SimpleSenseAnno extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SimpleSenseAnno.class);
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
  protected SimpleSenseAnno() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SimpleSenseAnno(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SimpleSenseAnno(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SimpleSenseAnno(JCas jcas, int begin, int end) {
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
  //* Feature: simpleSense

  /** getter for simpleSense - gets 
   * @generated */
  public String getSimpleSense() {
    if (SimpleSenseAnno_Type.featOkTst && ((SimpleSenseAnno_Type)jcasType).casFeat_simpleSense == null)
      jcasType.jcas.throwFeatMissing("simpleSense", "SimpleSenseAnno");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SimpleSenseAnno_Type)jcasType).casFeatCode_simpleSense);}
    
  /** setter for simpleSense - sets  
   * @generated */
  public void setSimpleSense(String v) {
    if (SimpleSenseAnno_Type.featOkTst && ((SimpleSenseAnno_Type)jcasType).casFeat_simpleSense == null)
      jcasType.jcas.throwFeatMissing("simpleSense", "SimpleSenseAnno");
    jcasType.ll_cas.ll_setStringValue(addr, ((SimpleSenseAnno_Type)jcasType).casFeatCode_simpleSense, v);}    
  }

    