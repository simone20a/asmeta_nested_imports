/**
 * generated by Xtext 2.28.0
 */
package org.asmeta.avallaxt.avalla.impl;

import org.asmeta.avallaxt.avalla.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AvallaFactoryImpl extends EFactoryImpl implements AvallaFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static AvallaFactory init()
  {
    try
    {
      AvallaFactory theAvallaFactory = (AvallaFactory)EPackage.Registry.INSTANCE.getEFactory(AvallaPackage.eNS_URI);
      if (theAvallaFactory != null)
      {
        return theAvallaFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new AvallaFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AvallaFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case AvallaPackage.SCENARIO: return createScenario();
      case AvallaPackage.INVARIANT: return createInvariant();
      case AvallaPackage.ELEMENT: return createElement();
      case AvallaPackage.COMMAND: return createCommand();
      case AvallaPackage.CHECK: return createCheck();
      case AvallaPackage.SET: return createSet();
      case AvallaPackage.STEP_UNTIL: return createStepUntil();
      case AvallaPackage.EXEC: return createExec();
      case AvallaPackage.BLOCK: return createBlock();
      case AvallaPackage.EXEC_BLOCK: return createExecBlock();
      case AvallaPackage.STEP: return createStep();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Scenario createScenario()
  {
    ScenarioImpl scenario = new ScenarioImpl();
    return scenario;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Invariant createInvariant()
  {
    InvariantImpl invariant = new InvariantImpl();
    return invariant;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Element createElement()
  {
    ElementImpl element = new ElementImpl();
    return element;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Command createCommand()
  {
    CommandImpl command = new CommandImpl();
    return command;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Check createCheck()
  {
    CheckImpl check = new CheckImpl();
    return check;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Set createSet()
  {
    SetImpl set = new SetImpl();
    return set;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public StepUntil createStepUntil()
  {
    StepUntilImpl stepUntil = new StepUntilImpl();
    return stepUntil;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Exec createExec()
  {
    ExecImpl exec = new ExecImpl();
    return exec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Block createBlock()
  {
    BlockImpl block = new BlockImpl();
    return block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ExecBlock createExecBlock()
  {
    ExecBlockImpl execBlock = new ExecBlockImpl();
    return execBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Step createStep()
  {
    StepImpl step = new StepImpl();
    return step;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public AvallaPackage getAvallaPackage()
  {
    return (AvallaPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static AvallaPackage getPackage()
  {
    return AvallaPackage.eINSTANCE;
  }

} //AvallaFactoryImpl
