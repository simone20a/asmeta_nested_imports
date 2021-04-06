/**
 * generated by Xtext 2.24.0
 */
package org.asmeta.xt.asmetal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Body</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.asmeta.xt.asmetal.Body#getDomainDefinition <em>Domain Definition</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.Body#getFunctionDefinition <em>Function Definition</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.Body#getRuleDeclaration <em>Rule Declaration</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.Body#getInvariantConstraint <em>Invariant Constraint</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.Body#getFairnessConstraint <em>Fairness Constraint</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.Body#getProperty <em>Property</em>}</li>
 * </ul>
 *
 * @see org.asmeta.xt.asmetal.AsmetalPackage#getBody()
 * @model
 * @generated
 */
public interface Body extends EObject
{
  /**
   * Returns the value of the '<em><b>Domain Definition</b></em>' containment reference list.
   * The list contents are of type {@link org.asmeta.xt.asmetal.DomainDefinition}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Domain Definition</em>' containment reference list.
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getBody_DomainDefinition()
   * @model containment="true"
   * @generated
   */
  EList<DomainDefinition> getDomainDefinition();

  /**
   * Returns the value of the '<em><b>Function Definition</b></em>' containment reference list.
   * The list contents are of type {@link org.asmeta.xt.asmetal.FunctionDefinition}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function Definition</em>' containment reference list.
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getBody_FunctionDefinition()
   * @model containment="true"
   * @generated
   */
  EList<FunctionDefinition> getFunctionDefinition();

  /**
   * Returns the value of the '<em><b>Rule Declaration</b></em>' containment reference list.
   * The list contents are of type {@link org.asmeta.xt.asmetal.RuleDeclaration}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rule Declaration</em>' containment reference list.
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getBody_RuleDeclaration()
   * @model containment="true"
   * @generated
   */
  EList<RuleDeclaration> getRuleDeclaration();

  /**
   * Returns the value of the '<em><b>Invariant Constraint</b></em>' containment reference list.
   * The list contents are of type {@link org.asmeta.xt.asmetal.InvariantConstraint}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Invariant Constraint</em>' containment reference list.
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getBody_InvariantConstraint()
   * @model containment="true"
   * @generated
   */
  EList<InvariantConstraint> getInvariantConstraint();

  /**
   * Returns the value of the '<em><b>Fairness Constraint</b></em>' containment reference list.
   * The list contents are of type {@link org.asmeta.xt.asmetal.FairnessConstraint}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fairness Constraint</em>' containment reference list.
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getBody_FairnessConstraint()
   * @model containment="true"
   * @generated
   */
  EList<FairnessConstraint> getFairnessConstraint();

  /**
   * Returns the value of the '<em><b>Property</b></em>' containment reference list.
   * The list contents are of type {@link org.asmeta.xt.asmetal.Property}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property</em>' containment reference list.
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getBody_Property()
   * @model containment="true"
   * @generated
   */
  EList<Property> getProperty();

  org.asmeta.xt.asmetal.Asm getAsm();
} // Body
