# HeroQuest

This project development was part of the MC322 Object-Oriented Programming subject activities.

The proposal was to develop a virtual version of the board game [HeroQuest][1]. For doing so, a reduced number of game
rules has been used.

## Game mechanics

In a nutshell, the developed game consists in a hero who finds herself lost on an unknown place, and needs to kill all
monsters nearby. In order to do so, she might use weapons or spells to attack the enemies, armors to protect herself
and even use cure potions to restore health points.

There are also chests spread all over the map, which contains the items which can be used during the adventure.
Moreover, you can find some hidden rooms, which are accessible by hidden doors, that might contain treasures,
as well as monsters. An extra care must be taken while wandering around, since hidden traps might be found quite often.

Therefore, your final goal is to kill all monsters on the map, even those hidden in secret rooms!

For more information about the original game, see [Hasbro HeroQuest manual][2].

## Game interfaces

Two different interfaces have been developed in this project: a Command Line Interface (CLI) and a Graphical User
Interface (GUI).

### CLI

The command line interface uses only the text input and output to control the game actions.
This was the proposed user interaction method to the OOP subject project.
Therefore, an extra attention has been given to its development.

This game interface can be selected by passing the `--cli` argument when running the game.
More details on how to do this is given in the [running section](#running).

#### Map interpretation

In order to represent the map objects, a codification has been followed.
The map below illustrates most of the characters used in the representation.

```
################
#  C t   # TC  #
#c    E  # TT C#
#  W     # TC  #
###D############
#  F t S    G  #
#  B   Ŝ   C   #
################
``` 

The map uses the following mapping:
- Map structure:
    - `#` - Walls;
    - ` ` - Floor;
    - `C` - Closed chest;
    - `c` - Opened chest;
    - `D` - Closed door;
    - `T` - Armed trap (when detected);
    - `t` - Unarmed trap;
- Heroes:
    - `B` - Barbarian;
    - `F` - Dwarf;
    - `E` - Elf;
    - `W` - Wizard;
- Monsters:
    - `S` - Common skeleton;
    - `Ŝ` - Wizard skeleton;
    - `G` - Goblin;
- View:
    - `?` - Unknown (not visible) objects;
    
Moreover, for object location reference, a coordinate system is displayed on the top and on the left side of the map.
The coordinate follows the web screen coordinate pattern, i.e. x-coordinate is from left to right, and the y-coordinate
is from top to bottom. The (x,y) coordinate is always used when a location reference is shown or asked the player.

### GUI

This is the default interface. It runs in a fix-sized window, which renders the game configuration menu, and the game
itself.

In the current game version, the graphical interface has the same command interface as the command line.
In other words, the turn actions are displayed at the side text panel, and have the same input interface
(numbered choices selected by their index). Some differences still exists, though.
For instance, in order to select a destination for the teleport spell, a mouse click is used to get to the desired
location.

## Installing

In order to run the game, you'll have to first install the [Java Runtime Environment (JRE)][3] for Java 14.
We strongly recommend installing it following the [OpenJDK platform][5] instructions.

The game installation itself is as simple as downloading the latest game version. To do so, use the GitHub release tab
to find the latest released version, and download its JAR attached file.

It is possible to place the file on a system installation folder. In a Linux system, for instance, you may place it on
the `/usr/local/bin` folder. However, this is not required to execute the game.

## Running

After installing the game, to run it using the Graphical Interface, simply double-click the downloaded JAR file,
or execute on the command line with:
```shell script
$ java -jar heroquest.jar
```

In order to run the CLI version, just add the `--cli` argument:
```shell script
$ java -jar heroquest.jar --cli
```

## Modifying the game

Some game modification does not require to change the source code, such as adding new maps.
We'll present this change first, and then introduce to how recompile the source code after any modification.

### Creating your own map

As we have shown previously, a codification has been used to display the map object during the game.
The same codification is used on the pre-defined maps on the disk. However, some differences exists.
First, on the pre-defined maps, the secret object must be represented. In the current game version, only
secret door can be specified on the map. In order to do so, use the `P` character instead of `D`.
Other hidden objects, such as traps, are added in different locations regardless of the map being pre-defined.
The same happens for monsters. Therefore, only the map structure (wall, doors) and fixed objects (closed chests) can be
arranged on the map when creating your own.

In order to include the created file into an existing JAR game file, just extract the JAR file, include the created map
on the `maps` folder, and zip it once again (alternatively, it is possible to include the file to the folder
without extracting the files). This will make the game automatically list the map when running.

### Recompiling

First you will need to have [Maven][4] installed, which is used for compiling the source code,
and generating the JAR file. To do so, please refer to the Maven installation guide.
This step also includes installing the Java Development Kit (JDK). Here we use the Java 14 version,
provided by [OpenJDK][5].

Once Maven is installed, you just need to run the following command:
```shell script
$ mvn clean compile assembly:single
```

Here we first clear eventual previous builds with the `clean` routine, then `compile` the source code,
and finally `assembly` the project into a `jar` file with all dependencies.

#### Troubleshooting

If you get an issue with some of the Maven plugins, you can force them to be updated, which will also install any
missing dependencies listed in the [`pom.xml`](pom.xml) file. For doing so, use the following command:
```shell script
$ mvn clean install -U
```

If this does not work, consider creating an issue.

## Contributing

If you find any improvement you are willing work on, please fork the project,
and create a pull request with the updates.

## Contributors
- [Henrique Simões][10]
- [Matheus Silva][11]
- [Renan Franco][12]

## Acknowledgements

We'd like to thank our OOP teacher [Leonardo Montecchi][13] for our useful discussions on online meetings,
which helped to improve our approaches and offered some insights on how to better organize the project. In addition,
we thank him for the great classes, which made this project possible to be done following mostly good practices.

[1]: https://en.wikipedia.org/wiki/HeroQuest
[2]: https://www.hasbro.com/common/instruct/HeroQuest.PDF
[3]: https://www.oracle.com/java/technologies/javase-downloads.html
[4]: https://maven.apache.org/
[5]: https://openjdk.java.net/

[10]: https://github.com/henriquesimoes
[11]: https://github.com/matheuss1
[12]: https://github.com/renanffernando
[13]: https://github.com/montex
