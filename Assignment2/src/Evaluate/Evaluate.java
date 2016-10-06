package Evaluate;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

//j
public class Evaluate {
	private static ScriptEngineManager manager;
	private ScriptEngine engine;

	public Evaluate() {
		manager = new ScriptEngineManager();
		engine = manager.getEngineByName("nashorn");

	}

	public boolean eval(String condition) {
		System.out.println("condition: " + condition);
		boolean is = false;
		try {
			
			is = (boolean) engine.eval(condition) ;
		} catch (ScriptException e) {}
		System.out.println(is+" is" + "eval-eval");
		return is;
	}
}
