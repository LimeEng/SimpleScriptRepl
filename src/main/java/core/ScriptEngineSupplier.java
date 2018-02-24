package core;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ScriptEngineSupplier {

	public static ScriptEngine getEngine(String shortName) {
		return new ScriptEngineManager().getEngineByName(shortName);
	}
	
}
