/**
 * generated by Xtext 2.24.0
 */
package org.asmeta.xt.asmetal.impl;

import org.asmeta.xt.asmetal.AsmetalPackage;
import org.asmeta.xt.asmetal.Domain;
import org.asmeta.xt.asmetal.MapDomain;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Map Domain</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.asmeta.xt.asmetal.impl.MapDomainImpl#getSourceDomain <em>Source Domain</em>}</li>
 *   <li>{@link org.asmeta.xt.asmetal.impl.MapDomainImpl#getTargetDomain <em>Target Domain</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MapDomainImpl extends StructuredTDImpl implements MapDomain
{
  /**
   * The cached value of the '{@link #getSourceDomain() <em>Source Domain</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSourceDomain()
   * @generated
   * @ordered
   */
  protected Domain sourceDomain;

  /**
   * The cached value of the '{@link #getTargetDomain() <em>Target Domain</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetDomain()
   * @generated
   * @ordered
   */
  protected Domain targetDomain;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MapDomainImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return AsmetalPackage.Literals.MAP_DOMAIN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Domain getSourceDomain()
  {
    return sourceDomain;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSourceDomain(Domain newSourceDomain, NotificationChain msgs)
  {
    Domain oldSourceDomain = sourceDomain;
    sourceDomain = newSourceDomain;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AsmetalPackage.MAP_DOMAIN__SOURCE_DOMAIN, oldSourceDomain, newSourceDomain);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setSourceDomain(Domain newSourceDomain)
  {
    if (newSourceDomain != sourceDomain)
    {
      NotificationChain msgs = null;
      if (sourceDomain != null)
        msgs = ((InternalEObject)sourceDomain).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AsmetalPackage.MAP_DOMAIN__SOURCE_DOMAIN, null, msgs);
      if (newSourceDomain != null)
        msgs = ((InternalEObject)newSourceDomain).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AsmetalPackage.MAP_DOMAIN__SOURCE_DOMAIN, null, msgs);
      msgs = basicSetSourceDomain(newSourceDomain, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AsmetalPackage.MAP_DOMAIN__SOURCE_DOMAIN, newSourceDomain, newSourceDomain));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Domain getTargetDomain()
  {
    return targetDomain;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTargetDomain(Domain newTargetDomain, NotificationChain msgs)
  {
    Domain oldTargetDomain = targetDomain;
    targetDomain = newTargetDomain;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AsmetalPackage.MAP_DOMAIN__TARGET_DOMAIN, oldTargetDomain, newTargetDomain);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setTargetDomain(Domain newTargetDomain)
  {
    if (newTargetDomain != targetDomain)
    {
      NotificationChain msgs = null;
      if (targetDomain != null)
        msgs = ((InternalEObject)targetDomain).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AsmetalPackage.MAP_DOMAIN__TARGET_DOMAIN, null, msgs);
      if (newTargetDomain != null)
        msgs = ((InternalEObject)newTargetDomain).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AsmetalPackage.MAP_DOMAIN__TARGET_DOMAIN, null, msgs);
      msgs = basicSetTargetDomain(newTargetDomain, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AsmetalPackage.MAP_DOMAIN__TARGET_DOMAIN, newTargetDomain, newTargetDomain));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case AsmetalPackage.MAP_DOMAIN__SOURCE_DOMAIN:
        return basicSetSourceDomain(null, msgs);
      case AsmetalPackage.MAP_DOMAIN__TARGET_DOMAIN:
        return basicSetTargetDomain(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case AsmetalPackage.MAP_DOMAIN__SOURCE_DOMAIN:
        return getSourceDomain();
      case AsmetalPackage.MAP_DOMAIN__TARGET_DOMAIN:
        return getTargetDomain();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case AsmetalPackage.MAP_DOMAIN__SOURCE_DOMAIN:
        setSourceDomain((Domain)newValue);
        return;
      case AsmetalPackage.MAP_DOMAIN__TARGET_DOMAIN:
        setTargetDomain((Domain)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case AsmetalPackage.MAP_DOMAIN__SOURCE_DOMAIN:
        setSourceDomain((Domain)null);
        return;
      case AsmetalPackage.MAP_DOMAIN__TARGET_DOMAIN:
        setTargetDomain((Domain)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case AsmetalPackage.MAP_DOMAIN__SOURCE_DOMAIN:
        return sourceDomain != null;
      case AsmetalPackage.MAP_DOMAIN__TARGET_DOMAIN:
        return targetDomain != null;
    }
    return super.eIsSet(featureID);
  }

} //MapDomainImpl
