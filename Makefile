all: jar

run:
	java -jar Main.jar

jar:
	javac *.java -d .
	jar -cfe Main.jar Main *.class

clean:
	rm *.class Main.jar