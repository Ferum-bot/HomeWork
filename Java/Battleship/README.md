# Battleship
The battleship game written in java. Supports several modes.
You input game settings(field size, ships count, enabled modes), then program randomly 
generates game field with these settings and then game starts.

## Available modes
### Torpedo mode:
* Allows shooting torpedoes
* If torpedo shoot the ship, it sunk
### Recovery mode:
* The ship recover after missing hit
* User has to hit the same ship with next shot

Two modes do not contradict each other: when ship is sunk by a torpedo 
it can not be recovered, since it is already sunk.

## Requirements:
 1. JDK 17 (better from Oracle)
 2. Computer with JVM
 3. Any code editor

## How to run:
  1. Get absolute path of JAR/Game.jar file
  2. Copy this path
  3. Write this command in terminal `java -jar <your_absolute_path>`
 