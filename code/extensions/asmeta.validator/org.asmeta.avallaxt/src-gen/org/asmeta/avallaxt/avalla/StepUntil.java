/**
 * generated by Xtext 2.28.0
 */
package org.asmeta.avallaxt.avalla;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Step Until</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.asmeta.avallaxt.avalla.StepUntil#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see org.asmeta.avallaxt.avalla.AvallaPackage#getStepUntil()
 * @model
 * @generated
 */
public interface StepUntil extends Command
{
  /**
   * Returns the value of the '<em><b>Expression</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' attribute.
   * @see #setExpression(String)
   * @see org.asmeta.avallaxt.avalla.AvallaPackage#getStepUntil_Expression()
   * @model
   * @generated
   */
  String getExpression();

  /**
   * Sets the value of the '{@link org.asmeta.avallaxt.avalla.StepUntil#getExpression <em>Expression</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' attribute.
   * @see #getExpression()
   * @generated
   */
  void setExpression(String value);

} // StepUntil
