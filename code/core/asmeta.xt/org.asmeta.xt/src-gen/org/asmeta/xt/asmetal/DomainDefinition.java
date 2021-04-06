/**
 * generated by Xtext 2.24.0
 */
package org.asmeta.xt.asmetal;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Domain Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.asmeta.xt.asmetal.DomainDefinition#getDefinedDomainName <em>Defined Domain Name</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.DomainDefinition#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @see org.asmeta.xt.asmetal.AsmetalPackage#getDomainDefinition()
 * @model
 * @generated
 */
public interface DomainDefinition extends EObject
{
  /**
   * Returns the value of the '<em><b>Defined Domain Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Defined Domain Name</em>' attribute.
   * @see #setDefinedDomainName(String)
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getDomainDefinition_DefinedDomainName()
   * @model
   * @generated
   */
  String getDefinedDomainName();

  /**
   * Sets the value of the '{@link org.asmeta.xt.asmetal.DomainDefinition#getDefinedDomainName <em>Defined Domain Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Defined Domain Name</em>' attribute.
   * @see #getDefinedDomainName()
   * @generated
   */
  void setDefinedDomainName(String value);

  /**
   * Returns the value of the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Body</em>' containment reference.
   * @see #setBody(Term)
   * @see org.asmeta.xt.asmetal.AsmetalPackage#getDomainDefinition_Body()
   * @model containment="true"
   * @generated
   */
  Term getBody();

  /**
   * Sets the value of the '{@link org.asmeta.xt.asmetal.DomainDefinition#getBody <em>Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Body</em>' containment reference.
   * @see #getBody()
   * @generated
   */
  void setBody(Term value);

  public org.asmeta.xt.asmetal.ConcreteDomain getDefinedDomain();
} // DomainDefinition
