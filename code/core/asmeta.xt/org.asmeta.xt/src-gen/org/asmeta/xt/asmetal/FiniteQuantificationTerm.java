/**
 * generated by Xtext 2.28.0
 */
package org.asmeta.xt.asmetal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Finite Quantification Term</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.asmeta.xt.asmetal.FiniteQuantificationTerm#getRanges <em>Ranges</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.FiniteQuantificationTerm#getGuard <em>Guard</em>}</li>
 * </ul>
 *
 * @see org.asmeta.xt.asmetal.AsmetalPackage#getFiniteQuantificationTerm()
 * @model
 * @generated
 */
public interface FiniteQuantificationTerm extends basicExpr, VariableBindingTerm
{
  /**
   * Returns the value of the '<em><b>Ranges</b></em>' containment reference list.
   * The list contents are of type {@link org.asmeta.xt.asmetal.Term}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ranges</em>' containment reference list.
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getFiniteQuantificationTerm_Ranges()
   * @model containment="true"
   * @generated
   */
  EList<Term> getRanges();

  /**
   * Returns the value of the '<em><b>Guard</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Guard</em>' containment reference.
   * @see #setGuard(Term)
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getFiniteQuantificationTerm_Guard()
   * @model containment="true"
   * @generated
   */
  Term getGuard();

  /**
   * Sets the value of the '{@link org.asmeta.xt.asmetal.FiniteQuantificationTerm#getGuard <em>Guard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Guard</em>' containment reference.
   * @see #getGuard()
   * @generated
   */
  void setGuard(Term value);

  public void setRanges(EList<Term> list);
} // FiniteQuantificationTerm
