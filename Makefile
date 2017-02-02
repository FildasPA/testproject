
BIN     := bin
SOURCES := src

JC      := javac
JFLAGS  := -g

CRDIRS  := $(BIN)

default: makedir compileClient compileServer


client: makedir compileClient
	java -classpath bin Client

server: makedir compileServer
	java -classpath bin Server

compileClient:
	$(JC) -sourcepath $(SOURCES) -classpath $(BIN) -d $(BIN) src/client/Client.java

compileServer:
	$(JC) -sourcepath $(SOURCES) -classpath $(BIN) -d $(BIN) src/server/Server.java


# Créer les dossiers nécessaires
makedir:
	mkdir -p $(foreach dir,$(CRDIRS),$(dir))

clean:
	rm -drf $(BIN)
