
BIN     := bin
SOURCES := src

JC      := javac
JFLAGS  := -g

default:
	$(JC) -sourcepath $(SOURCES) -classpath $(BIN) -d $(BIN) src/server/Server.java
	$(JC) -sourcepath $(SOURCES) -classpath $(BIN) -d $(BIN) src/client/Client.java

clean:
	$(RM) *.class
