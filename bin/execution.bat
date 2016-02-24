rem set PATH=../../lib/;../../jre/bin/
rem javac -cp .;../../VocalyzeSIVOX/bin/SI_VOX.jar -Djava.library.path=../ressources/lib/ -d ../bin devintAPI/*.java jeu/*.java

java -cp .;../../VocalyzeSIVOX/bin/SI_VOX.jar dvt.run.Main
pause
