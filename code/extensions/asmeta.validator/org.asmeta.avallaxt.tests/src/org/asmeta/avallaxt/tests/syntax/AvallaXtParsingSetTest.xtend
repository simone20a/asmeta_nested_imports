/*
 * generated by Xtext 2.14.0
 */
package org.asmeta.avallaxt.tests.syntax

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.asmeta.avallaxt.tests.AvallaInjectorProvider
import org.asmeta.avallaxt.avalla.Scenario
import org.asmeta.avallaxt.avalla.ExecBlock
import org.asmeta.avallaxt.avalla.Step
import org.asmeta.avallaxt.avalla.Block
import org.asmeta.avallaxt.avalla.Set

@RunWith(XtextRunner)
@InjectWith(AvallaInjectorProvider)
class AvallaXtParsingSetTest {
	@Inject
	ParseHelper<Scenario> parseHelper
	
	@Test
	def void set() {
		test("x", "10"); // questo passa che ha lo spazio
		test("x ", "10"); // questo passa che ha lo spazio
		test("x(yy)", "z+3");
	}

	@Test
	def void setWithStrings() {
		test("x(8)", "\"good\""); // x = "jjkk" a string 
		test("x(\"good\")", "\"good\""); // or f("mmm") = ,k,,,
	}
	
	def void test(String location,String value){
		println("testing with " + location + " := " + value)
		val scenario = "scenario scen load spec set "+ location  + ":=" + value + ";";
		val result = parseHelper.parse(scenario)
		Assert.assertNotNull(result)
		val errors = result.eResource.errors
		Assert.assertTrue('''Unexpected errors: �errors.join(", ")�''', errors.isEmpty)
		Assert.assertEquals(((result as Scenario).elements.get(0) as Set).location,location.trim);
		Assert.assertEquals(((result as Scenario).elements.get(0) as Set).value,value);		
	}
}
