java -cp javacc-7.0.12.jar javacc -OUTPUT_DIRECTORY=src sql.jj
pause

javac -d classes src\*.java
pause