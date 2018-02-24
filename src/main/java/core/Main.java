package core;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import repl.SimpleRepl;
import repl.status.StatusCode;
import repl.status.StatusMessage;

public class Main {

	private static final String quitCommand = ":exit";
	private static final ScriptEngine engine = ScriptEngineSupplier.getEngine("js");

	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		Scanner scan = new Scanner(System.in);
		Supplier<String> input = () -> {
			System.out.print("> ");
			return scan.nextLine();
		};

		Function<String, StatusMessage> expressionHandler = expression -> {
			if (quitCommand.equals(expression)) {
				System.out.println("Have a nice day!");
				return new StatusMessage(StatusCode.EXIT, "EXIT");
			}
			try {
				System.out.println(engine.eval(expression));
				return new StatusMessage(StatusCode.OK, "OK");
			} catch (ScriptException e) {
				System.out.println(e.getMessage());
			}
			return new StatusMessage(StatusCode.UNDEFINED, "EXCEPTION");
		};

		Predicate<StatusMessage> quitCondition = (message) -> message.getCode() == StatusCode.EXIT;
		
		SimpleRepl repl = new SimpleRepl(input, expressionHandler, quitCondition);
		repl.run();
	}
}
