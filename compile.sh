#bin/bash

javac -d bin -cp "lib/*" src/project/dbLover/*.java
javac -d WebContent/WEB-INF/classes -cp "lib/*"src/project/dbLover/*.java
javac -d bin -cp "lib/*" src/project/simplechat/*.java
javac -d WebContent/WEB-INF/classes -cp "lib/*" src/example/lesson04/*.java
javac -d bin -cp "lib/*" src/server/*.java