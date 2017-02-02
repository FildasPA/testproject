
BIN     := bin
SOURCES := src

JC      := javac
JFLAGS  := -g


default: compileClient compileServer

compileClient:
	$(JC) -sourcepath $(SOURCES) -classpath $(BIN) -d $(BIN) src/client/Client.java

compileServer:
	$(JC) -sourcepath $(SOURCES) -classpath $(BIN) -d $(BIN) src/server/Server.java

client:
	compileClient
	java -classpath bin Client

server:
	compileServer
	java -classpath bin Server


clean:
	$(RM) *.class
