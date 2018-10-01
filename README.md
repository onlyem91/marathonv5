# MARATHON - Develop
This is were I keep some custom additions to the Jalian Marathon software. 
Currently this project is based on the Marathon 5.2.2.0 version with the following additions:
- Addition: process clicks on TableColumnHeaders 
- Fixed the limitation on the length of the installation path / VM arguments. This used to result in the application not starting due to missing VM args and an error message "move marathon to shorter installation path". 


# MARATHON - Test Automation for Java/Swing and Java/FX applications

Marathon is a tool for recording, replaying, refactoring test cases for Java GUI programs developed using Swing or FX components. Marathon consists of an editor, a recorder and a player. Marathon records the test cases in an easy to read and maintainable format using JRuby. The test cases can be run either through the UI or in batch mode.

## Building Marathon

Marathon is built using [gradle](http://gradle.org). Just clone this repository and use the gradle wrapper in the toplevel folder to build marathon.

```
$ ./gradlew build
```

```
C:\> .\gradlew build
```

## Using Eclipse

Use the eclipse target to create eclipse projects for Marathon. Import the projects into a new workspace.

```
$ ./gradlew eclipse
```

```
C:\> .\gradlew eclipse
```

## More Information

You can get more information about Marathon and documentation/support from:

[http://www.marathontesting.com](http://marathontesting.com)
