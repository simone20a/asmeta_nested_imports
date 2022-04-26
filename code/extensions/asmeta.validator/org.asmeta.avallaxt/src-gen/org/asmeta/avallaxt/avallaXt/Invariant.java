/**
 * generated by Xtext 2.26.0
 */
package org.asmeta.avallaxt.avallaXt;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Invariant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.asmeta.avallaxt.avallaXt.Invariant#getName <em>Name</em>}</li>
 *   <li>{@link org.asmeta.avallaxt.avallaXt.Invariant#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see org.asmeta.avallaxt.avallaXt.AvallaXtPackage#getInvariant()
 * @model
 * @generated
 */
public interface Invariant extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.asmeta.avallaxt.avallaXt.AvallaXtPackage#getInvariant_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.asmeta.avallaxt.avallaXt.Invariant#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Expression</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' attribute.
   * @see #setExpression(String)
   * @see org.asmeta.avallaxt.avallaXt.AvallaXtPackage#getInvariant_Expression()
   * @model
   * @generated
   */
  String getExpression();

  /**
   * Sets the value of the '{@link org.asmeta.avallaxt.avallaXt.Invariant#getExpression <em>Expression</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' attribute.
   * @see #getExpression()
   * @generated
   */
  void setExpression(String value);

} // Invariant
