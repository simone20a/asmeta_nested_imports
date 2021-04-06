/**
 * generated by Xtext 2.24.0
 */
package org.asmeta.xt.asmetal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.asmeta.xt.asmetal.RuleDeclaration#getName <em>Name</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.RuleDeclaration#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.RuleDeclaration#getDomain <em>Domain</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.RuleDeclaration#getReturnedDomain <em>Returned Domain</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.RuleDeclaration#getRuleBody <em>Rule Body</em>}</li>
 * </ul>
 *
 * @see org.asmeta.xt.asmetal.AsmetalPackage#getRuleDeclaration()
 * @model
 * @generated
 */
public interface RuleDeclaration extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getRuleDeclaration_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.asmeta.xt.asmetal.RuleDeclaration#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Variables</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variables</em>' attribute list.
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getRuleDeclaration_Variables()
   * @model unique="false"
   * @generated
   */
  EList<String> getVariables();

  /**
   * Returns the value of the '<em><b>Domain</b></em>' containment reference list.
   * The list contents are of type {@link org.asmeta.xt.asmetal.Domain}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Domain</em>' containment reference list.
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getRuleDeclaration_Domain()
   * @model containment="true"
   * @generated
   */
  EList<Domain> getDomain();

  /**
   * Returns the value of the '<em><b>Returned Domain</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Returned Domain</em>' containment reference.
   * @see #setReturnedDomain(Domain)
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getRuleDeclaration_ReturnedDomain()
   * @model containment="true"
   * @generated
   */
  Domain getReturnedDomain();

  /**
   * Sets the value of the '{@link org.asmeta.xt.asmetal.RuleDeclaration#getReturnedDomain <em>Returned Domain</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Returned Domain</em>' containment reference.
   * @see #getReturnedDomain()
   * @generated
   */
  void setReturnedDomain(Domain value);

  /**
   * Returns the value of the '<em><b>Rule Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rule Body</em>' containment reference.
   * @see #setRuleBody(Rule)
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getRuleDeclaration_RuleBody()
   * @model containment="true"
   * @generated
   */
  Rule getRuleBody();

  /**
   * Sets the value of the '{@link org.asmeta.xt.asmetal.RuleDeclaration#getRuleBody <em>Rule Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rule Body</em>' containment reference.
   * @see #getRuleBody()
   * @generated
   */
  void setRuleBody(Rule value);

  org.asmeta.xt.asmetal.Body getAsmBody();
  
  org.eclipse.emf.common.util.EList<org.asmeta.xt.asmetal.VariableTerm> getVariable();
} // RuleDeclaration
