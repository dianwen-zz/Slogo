slogo
=====

Part 1 Design:

Design Goals

Our project will utilize the model-view paradigm. The view operates indepenently of the model, and contains one setter to issue commands to the engine. The rest of the view only acts as a getter.

For the GUI, we will have a main window class. This class will hold the evaluation console, command line, reset button, command history, and the SLogo graphic window (using JGEngine). The evaluation console only prints the results of the user’s commands and prints errors if the user’s commands don’t correspond to an existing command. The SLogo Graphic Window holds instances of the Turtle Class and the Turtle Path Class. It also holds a variable, color, that is used to set the background color for the turtle’s display area and toggles the reference grid based off of the user’s input.  Because of the functionality of the evaluation Console and the SLogo graphic window, they will only use getters since they do not need to change anything in the Engine.

The command line, unlike the other two classes, will use setters. It responds to user input and therefore modifies the Engine. It evaluates the inputs of the user line by line when the 'enter' button is pressed. Each command will be sent to the parser part of the engine to verify its validity. The commands will only be acted on if the engine returns true for its validity (checking syntax and if the end of a loop has been reached). It also prints the user’s input. The final aspect of the GUI is the reset button, which is just an action listener that triggers a reset. A rough diagram is shown below that displays what we want our GUI to look like.

For the model/engine portion, we'd like extensibility in adding new advanced commands. Advanced commands includes any sort of looping, evaluation for continuation, etc... Therefore, we will create advanced commands objects that can be instantiated. These advanced command objects will break the commands down into simple commands for the turtle to execute. This can be done by passing parts of the advanced command back into the parser to evaluate. For instance, in a while loop, the test statement while(....) must be evaluated in each iteration. This statement may contain variables that change and logic statements that must be evaluated. The parser will recursively evaluate these portions by passing parts of the statements into the engine and back into the parser if they are not decomposed into the most basic commands.

The parser's main role is to create command objects using a commandFactory. (The commandFactory is responsible for creating advanced commands. Simple commands such as move forward and change angle are directly related to the view, so they will be passed onto and handled by the turtle object itself.)

[ “-” : uses/contains, “+” : subclass of]

Class Hierarchy for the view

Main Window

	- Evaluation console

		- Engine

	- Command line

		- Parser

	- Command history

		- Parser

	- SLogo graphic window

		- Turtle

		- Turtle Path

		- Color

		- Reference Grid

	- Reset

UI Mockup:
![UI mockup sketch] (https://raw2.github.com/duke-compsci308-spring2014/slogo_team12/master/design/UISketch.png?token=5981803__eyJzY29wZSI6IlJhd0Jsb2I6ZHVrZS1jb21wc2NpMzA4LXNwcmluZzIwMTQvc2xvZ29fdGVhbTEyL21hc3Rlci9kZXNpZ24vVUlTa2V0Y2gucG5nIiwiZXhwaXJlcyI6MTM5MzU3MDk5Mn0%3D--331bf95ec9d01ee49cb79d27409b9d3381ec7d01)

Example Code (Pseudo-Code):
```
//User Input: “fd 50”
//GUI:
i
f(parser.parseCommand(userInput)){ //rejection of command here due to improper syntax will throw an exception in the console and not execute
	clearTextBox();
	addCommandToHistory(userInput);
}

//Parser:
boolean parseCommand(String s){
	if(simpleCommand){
		return parseSimple(String s);
	}
	if(complexCommand){
//		...
	}
}

boolean parseSimpleCommand(String s){
	obtain command
engine.obey(turtleChange change) //Overload this method and let it be part of the turtle’s role. Simple commands can be issued directly to turtle and handled by GUI
}
engine.obey{
	turtle.obey(command)
}
turtle.obey{
	offsets()
}
```
