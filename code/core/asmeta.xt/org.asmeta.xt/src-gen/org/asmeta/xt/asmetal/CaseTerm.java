/**
 * generated by Xtext 2.28.0
 */
package org.asmeta.xt.asmetal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Case Term</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.asmeta.xt.asmetal.CaseTerm#getComparedTerm <em>Compared Term</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.CaseTerm#getComparingTerm <em>Comparing Term</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.CaseTerm#getResultTerms <em>Result Terms</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.CaseTerm#getOtherwiseTerm <em>Otherwise Term</em>}</li>
 * </ul>
 *
 * @see org.asmeta.xt.asmetal.AsmetalPackage#getCaseTerm()
 * @model
 * @generated
 */
public interface CaseTerm extends ExtendedTerm
{
  /**
   * Returns the value of the '<em><b>Compared Term</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Compared Term</em>' containment reference.
   * @see #setComparedTerm(Term)
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getCaseTerm_ComparedTerm()
   * @model containment="true"
   * @generated
   */
  Term getComparedTerm();

  /**
   * Sets the value of the '{@link org.asmeta.xt.asmetal.CaseTerm#getComparedTerm <em>Compared Term</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Compared Term</em>' containment reference.
   * @see #getComparedTerm()
   * @generated
   */
  void setComparedTerm(Term value);

  /**
   * Returns the value of the '<em><b>Comparing Term</b></em>' containment reference list.
   * The list contents are of type {@link org.asmeta.xt.asmetal.Term}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comparing Term</em>' containment reference list.
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getCaseTerm_ComparingTerm()
   * @model containment="true"
   * @generated
   */
  EList<Term> getComparingTerm();

  /**
   * Returns the value of the '<em><b>Result Terms</b></em>' containment reference list.
   * The list contents are of type {@link org.asmeta.xt.asmetal.Term}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Result Terms</em>' containment reference list.
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getCaseTerm_ResultTerms()
   * @model containment="true"
   * @generated
   */
  EList<Term> getResultTerms();

  /**
   * Returns the value of the '<em><b>Otherwise Term</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Otherwise Term</em>' containment reference.
   * @see #setOtherwiseTerm(Term)
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getCaseTerm_OtherwiseTerm()
   * @model containment="true"
   * @generated
   */
  Term getOtherwiseTerm();

  /**
   * Sets the value of the '{@link org.asmeta.xt.asmetal.CaseTerm#getOtherwiseTerm <em>Otherwise Term</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Otherwise Term</em>' containment reference.
   * @see #getOtherwiseTerm()
   * @generated
   */
  void setOtherwiseTerm(Term value);

} // CaseTerm
