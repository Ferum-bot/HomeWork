# Jigsaw

## Features:
* Multi module structure.
* 

## Structure:

Project contains 4 modules:
* Core - contains core models and service. [Read more.](Core/README.md)
* Jigsaw-Client - game client with main logic. [Read more.](Jigsaw-Client/README.md)
* Jigsaw-UI - game GUI client. Adapts base client. [Read more.](Jigsaw-UI/README.md)
* Main - main module. Launch application. [Read more.](Main/README.md)

## How to run:

You can run using idea:
 1. Open Maven
 2. Open main module
 3. Open Plugins
 4. Open javafx and run `javafx:run`
 
<img src="screenshots/how_to_install.png" width="250px" height="300px" alt="How To Install">

Or you can run next command:

`mvn clean install -pl Main javafx:run`

#### Created and powered by Matvey Popov.
#### Test coverage 77%