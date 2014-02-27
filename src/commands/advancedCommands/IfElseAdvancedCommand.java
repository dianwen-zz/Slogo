package commands.advancedCommands;

import backend.Interpreter;

import commands.AdvancedCommand;

import exceptions.InvalidCommandException;
import exceptions.InvalidCommandStringException;
import exceptions.InvalidSyntaxException;
import exceptions.InvalidWordException;
import exceptions.NotEnoughParametersException;
import exceptions.VariableNotFoundException;

public class IfElseAdvancedCommand extends AdvancedCommand {

	public IfElseAdvancedCommand() {
		super(1);
	}

	@Override
	public double execute(Object o) throws InvalidSyntaxException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, InvalidCommandStringException,
			InvalidWordException, NotEnoughParametersException,
			InvalidCommandException, VariableNotFoundException {
		Interpreter interpreter = (Interpreter) o;
		Double ret = 0.0;
		for (int i = 0; i < 2; i++) {
			interpreter.engine.saveTurtleState();
			ret = interpreter.readBrackets();
			if ((parameters.get(0) == 0) ^ (i == 1)) {
				interpreter.engine.restoreTurtleState();
			}
		}

		return ret;
	}

}
