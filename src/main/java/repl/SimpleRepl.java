package repl;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import repl.status.StatusMessage;

public class SimpleRepl {

	private Supplier<String> input;
	private Function<String, StatusMessage> expressionHandler;
	private Predicate<StatusMessage> quitCondition;

	public SimpleRepl(Supplier<String> input, Function<String, StatusMessage> expressionHandler,
			Predicate<StatusMessage> quitCondition) {
		this.input = input;
		this.expressionHandler = expressionHandler;
		this.quitCondition = quitCondition;
	}

	public void run() {
		Stream.generate(input)
				.map(expressionHandler)
				.noneMatch(quitCondition);
	}
}
