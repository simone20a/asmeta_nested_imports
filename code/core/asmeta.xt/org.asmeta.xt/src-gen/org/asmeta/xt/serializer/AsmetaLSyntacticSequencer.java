/*
 * generated by Xtext 2.24.0
 */
package org.asmeta.xt.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.asmeta.xt.services.AsmetaLGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public class AsmetaLSyntacticSequencer extends AbstractSyntacticSequencer {

	protected AsmetaLGrammarAccess grammarAccess;
	protected AbstractElementAlias match_CtlSpec_CTLKeyword_0_0_or_CTLSPECKeyword_0_1;
	protected AbstractElementAlias match_EnumTD_CommaKeyword_6_0_1_or_VerticalLineKeyword_6_0_0;
	protected AbstractElementAlias match_InvariantElement___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q;
	protected AbstractElementAlias match_LtlSpec_LTLKeyword_0_0_or_LTLSPECKeyword_0_1;
	protected AbstractElementAlias match_MacroDeclaration_MacroKeyword_0_q;
	protected AbstractElementAlias match_ParenthesizedExpression_LeftParenthesisKeyword_0_a;
	protected AbstractElementAlias match_ParenthesizedExpression_LeftParenthesisKeyword_0_p;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (AsmetaLGrammarAccess) access;
		match_CtlSpec_CTLKeyword_0_0_or_CTLSPECKeyword_0_1 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getCtlSpecAccess().getCTLKeyword_0_0()), new TokenAlias(false, false, grammarAccess.getCtlSpecAccess().getCTLSPECKeyword_0_1()));
		match_EnumTD_CommaKeyword_6_0_1_or_VerticalLineKeyword_6_0_0 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getEnumTDAccess().getCommaKeyword_6_0_1()), new TokenAlias(false, false, grammarAccess.getEnumTDAccess().getVerticalLineKeyword_6_0_0()));
		match_InvariantElement___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getInvariantElementAccess().getLeftParenthesisKeyword_1_0()), new TokenAlias(false, false, grammarAccess.getInvariantElementAccess().getRightParenthesisKeyword_1_2()));
		match_LtlSpec_LTLKeyword_0_0_or_LTLSPECKeyword_0_1 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getLtlSpecAccess().getLTLKeyword_0_0()), new TokenAlias(false, false, grammarAccess.getLtlSpecAccess().getLTLSPECKeyword_0_1()));
		match_MacroDeclaration_MacroKeyword_0_q = new TokenAlias(false, true, grammarAccess.getMacroDeclarationAccess().getMacroKeyword_0());
		match_ParenthesizedExpression_LeftParenthesisKeyword_0_a = new TokenAlias(true, true, grammarAccess.getParenthesizedExpressionAccess().getLeftParenthesisKeyword_0());
		match_ParenthesizedExpression_LeftParenthesisKeyword_0_p = new TokenAlias(true, false, grammarAccess.getParenthesizedExpressionAccess().getLeftParenthesisKeyword_0());
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		return "";
	}
	
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if (match_CtlSpec_CTLKeyword_0_0_or_CTLSPECKeyword_0_1.equals(syntax))
				emit_CtlSpec_CTLKeyword_0_0_or_CTLSPECKeyword_0_1(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_EnumTD_CommaKeyword_6_0_1_or_VerticalLineKeyword_6_0_0.equals(syntax))
				emit_EnumTD_CommaKeyword_6_0_1_or_VerticalLineKeyword_6_0_0(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_InvariantElement___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q.equals(syntax))
				emit_InvariantElement___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_LtlSpec_LTLKeyword_0_0_or_LTLSPECKeyword_0_1.equals(syntax))
				emit_LtlSpec_LTLKeyword_0_0_or_LTLSPECKeyword_0_1(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_MacroDeclaration_MacroKeyword_0_q.equals(syntax))
				emit_MacroDeclaration_MacroKeyword_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ParenthesizedExpression_LeftParenthesisKeyword_0_a.equals(syntax))
				emit_ParenthesizedExpression_LeftParenthesisKeyword_0_a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_ParenthesizedExpression_LeftParenthesisKeyword_0_p.equals(syntax))
				emit_ParenthesizedExpression_LeftParenthesisKeyword_0_p(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Ambiguous syntax:
	 *     'CTL' | 'CTLSPEC'
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) body=Term
	 *     (rule start) (ambiguity) name=ID
	 */
	protected void emit_CtlSpec_CTLKeyword_0_0_or_CTLSPECKeyword_0_1(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     '|' | ','
	 *
	 * This ambiguous syntax occurs at:
	 *     element+=EnumElement (ambiguity) element+=EnumElement
	 */
	protected void emit_EnumTD_CommaKeyword_6_0_1_or_VerticalLineKeyword_6_0_0(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('(' ')')?
	 *
	 * This ambiguous syntax occurs at:
	 *     constrainedName=ID (ambiguity) (rule end)
	 */
	protected void emit_InvariantElement___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     'LTL' | 'LTLSPEC'
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) body=Term
	 *     (rule start) (ambiguity) name=ID
	 */
	protected void emit_LtlSpec_LTLKeyword_0_0_or_LTLSPECKeyword_0_1(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     'macro'?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) 'rule' name=RULE_ID
	 */
	protected void emit_MacroDeclaration_MacroKeyword_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     '('*
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) '(' 'exist' 'unique' variable+=VariableTerm
	 *     (rule start) (ambiguity) '(' 'exist' variable+=VariableTerm
	 *     (rule start) (ambiguity) '(' 'forall' variable+=VariableTerm
	 *     (rule start) (ambiguity) agent=extendedName
	 *     (rule start) (ambiguity) functionName=extendedNameForFunction
	 *     (rule start) (ambiguity) name=ID_VARIABLE
	 *     (rule start) (ambiguity) op='+'
	 *     (rule start) (ambiguity) op='-'
	 *     (rule start) (ambiguity) op='not'
	 *     (rule start) (ambiguity) symbol='false'
	 *     (rule start) (ambiguity) symbol='true'
	 *     (rule start) (ambiguity) symbol='undef'
	 *     (rule start) (ambiguity) symbol=CHAR_LITERAL
	 *     (rule start) (ambiguity) symbol=COMPLEX_NUMBER
	 *     (rule start) (ambiguity) symbol=ENUM_ID
	 *     (rule start) (ambiguity) symbol=NATNUMBER
	 *     (rule start) (ambiguity) symbol=NUMBER_TOKEN
	 *     (rule start) (ambiguity) symbol=NumberWithSign
	 *     (rule start) (ambiguity) symbol=RealNumberWithSign
	 *     (rule start) (ambiguity) symbol=STRING_LITERAL
	 *     (rule start) (ambiguity) {BinaryOperation.left=}
	 */
	protected void emit_ParenthesizedExpression_LeftParenthesisKeyword_0_a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     '('+
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) '(' 'exist' 'unique' variable+=VariableTerm
	 *     (rule start) (ambiguity) '(' 'exist' variable+=VariableTerm
	 *     (rule start) (ambiguity) '(' 'forall' variable+=VariableTerm
	 *     (rule start) (ambiguity) agent=extendedName
	 *     (rule start) (ambiguity) functionName=extendedNameForFunction
	 *     (rule start) (ambiguity) name=ID_VARIABLE
	 *     (rule start) (ambiguity) op='+'
	 *     (rule start) (ambiguity) op='-'
	 *     (rule start) (ambiguity) op='not'
	 *     (rule start) (ambiguity) symbol='false'
	 *     (rule start) (ambiguity) symbol='true'
	 *     (rule start) (ambiguity) symbol='undef'
	 *     (rule start) (ambiguity) symbol=CHAR_LITERAL
	 *     (rule start) (ambiguity) symbol=COMPLEX_NUMBER
	 *     (rule start) (ambiguity) symbol=ENUM_ID
	 *     (rule start) (ambiguity) symbol=NATNUMBER
	 *     (rule start) (ambiguity) symbol=NUMBER_TOKEN
	 *     (rule start) (ambiguity) symbol=NumberWithSign
	 *     (rule start) (ambiguity) symbol=RealNumberWithSign
	 *     (rule start) (ambiguity) symbol=STRING_LITERAL
	 *     (rule start) (ambiguity) {BinaryOperation.left=}
	 */
	protected void emit_ParenthesizedExpression_LeftParenthesisKeyword_0_p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
