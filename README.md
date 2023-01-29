# biltegia-java

cd ../src
javac -d ../bin -g -cp "../lib/jars/jackson/*:." App.java

cd ../bin                                             
java -cp "../lib/jars/jackson/*:." App 


javac -d ../bin -g -cp "../lib/jars/jackson/*:." App.java && cd ../bin && java -cp "../lib/jars/jackson/*:." App && cd ../src

javac -d ../bin -g -cp "../lib/jars/jackson/*:../lib/jars/serialcomm/*:." App.java && cd ../bin && java -cp "../lib/jars/jackson/*:../lib/jars/serialcomm/*:." App && cd ../src

This is my project directory
- MyProject
    - bin
    - lib
        - jars
        - serialcomm
            - jSerialComm-1.3.11.jar
        -jackson
            - jackson-databind-2.14.1.jar
            - jackson-core-2.14.1.jar
            - jackson-annotations-2.14.1.jar

    - src
        - Main.java
        ...


Is not finding the json files or any other file because when compiling the java project, I use this command: javac -d ../bin -g -cp "../lib/jars/jackson/*:../lib/jars/serialcomm/*:." App.java && cd ../bin && java -cp "../lib/jars/jackson/*:../lib/jars/serialcomm/*:." App && cd ../src
Which must be run in the src directory leading to the path to the files be ../lib/files. But when I debug from the debug view of vscode it executes the debug command in the parent directory of src and in these case the path to the files would be lib/files.