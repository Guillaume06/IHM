﻿rem set PATH=../../lib/;../../jre/bin/
rem javac -cp .;../../VocalyzeSIVOX/bin/SI_VOX.jar -Djava.library.path=../ressources/lib/ -d ../bin devintAPI/*.java jeu/*.java

javac -cp .;../../VocalyzeSIVOX/bin/SI_VOX.jar -d ../bin dvt/run/*.java dvt/devint/*.java dvt/jeuquizz/*.java dvt/score/*.java dvt/menu/*.java dvt/jeuchronometre/*.java dvt/menu/*.java dvt/jeumultijoueur/*.java dvt/run/*.java 
pause
