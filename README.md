# Project
Regular Automata is an application that allows the use of finite automata to express regular languages by checking if certain "words" belong to them. (More information about this area can be found [here](https://en.wikipedia.org/wiki/Automata_theory))

It was developed as the final project for a college class on formal languages at UFRGS (Universidade Federal do Rio Grande do Sul) and is currently being expanded to encompass all the most basic functionality one would expect from these formalisms. It is not meant to be the most comprehensive nor the most efficient program to do that, but to be an example of a possible implementation of these concepts in a straightforward and hopefully understandable manner, using a language that allows the code to be very expressive - as well as a playground to implement certain non-trivial algorithms by hand. As such, the source code is left here in case it can help anyone who is studying this subject to have a better grasp of it, in an educational context.

## How to build
Since it is a [Maven](https://maven.apache.org/) project, assuming you have Maven installed it should be as simple as running `mvn clean install` on the root directory.

## How to run
After it has been built, a .jar with everything needed to run inside of it will be placed on the "target" directory. Just double clicking it or going to the root directory and running `java -jar ./target/RegularAutomata.jar` (on Linux, or its equivalent on Windows) will open the application.

## What is currently present
As it stands now, the application works with DFAs and NFAs that can be loaded from correctly formatted files. It will always load the automaton as an NFA, which needs to be explicitly converted to a DFA before running words through it. The user can either enter words manually or load a list of them from a file.

## What is being developed
Support for NFA with epsilon transition.

Running words through any kind of automaton without the need to convert to a specific type.

Display of more information about the automata.


## What is not currently under consideration but would be cool to have
Display of properties about the automata, such as if its recognized language is finite or infinite, if it is a minimal automaton, so on.
