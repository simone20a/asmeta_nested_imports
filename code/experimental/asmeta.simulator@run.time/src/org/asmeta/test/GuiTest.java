package org.asmeta.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.asmeta.assertion_catalog.InvariantGraphicsInterface;
import org.asmeta.runtime_container.ContainerRT;

public class GUITest {

	public static void main(String[] args) {
		ContainerRT imp = ContainerRT.getInstance();
		imp.init(20);
		//String model = "examples/ferrymanSimulator_raff1.asm";
		//String model = "examples/LGS_GM.asm";
		String model = "examples/lift3.asm";
		try {
			Files.copy(Paths.get(model+".original"), Paths.get(model), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("java files good");
		}
		imp.startExecution(model);
		imp.startExecution("examples/LGS_GM.asm");
		imp.startExecution("examples/ferrymanSimulator_raff1.asm");
		imp.startExecution("examples/Lavatrice.asm");

		//imp.stopExecution(2);
		InvariantGraphicsInterface.main(imp);
		/*Map<String,String> m=new HashMap<String, String>(); //prova per invalid invariant su aggiunta
		m.put("carry", "CABBAGE");
		imp.runStep(1,m, model);*/
		/*try { //prova per simulazione stoppata nella gui
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		imp.stopExecution(3);*/
	}

}
