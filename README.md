# KingdomHallTimer

* [Description](#description)
* [Features](#features)
* [Installation](#installation)
* [Future features](#future-features)
* [Structure](#structure)

------------

### Description
Program for those who want show left time for speakers. Mainly created for Jehovah’s Witnesses but flexibility allows to use it in other purposes.

### Features

- Built-in voice recorder (with autostart option)
- Fast download of schedule
- Simple and usefull widget
- Rich config
- Ability to send time to a server ex. 7seg display built with raspberry pi
- Portable (doesn&apos;t need any installation)
- Constantly supported

### Installation
1. Install git - https://git-scm.com/book/en/v2/Getting-Started-Installing-Git
2. Open git console in destination folder.
3. Paste into console: `git clone https://github.com/Slupik/KingdomHallTimer.git`
4. Import project file into your IDE:
4.1 For Intellij IDEA (https://www.jetbrains.com/idea/download/)
4.1.1 File -> New -> Project from Existing Sources...
4.1.2 Find and select main pom.xml file in a project
4.1.3 Configure new project (most options should be left default)
5. Download newest verstion of xt-audio - https://sjoerdvankreel.github.io/xt-audio/
6.  Unpack files.
7. Enter into folder java-xt
8. Copy folders: linux-x64, linux-x86, win32-x64, win32-x86
9. Paste it into your main folder of the project. Please remember that those files must be allways with your project and exported jars.

### Future features
- Improved code quality
- Completely new user interface
- Compatibility with MacOS
- Compatibility with Linux
- Unpacking and loading of libraries of xt-audio in runtime
- Config editor with user interface
- Mechanism of plugins

### Structure
```
parent/
+--config/                    Module with code to handle config files
   +----/src
   +----/test
   +----pom.xml
+--custom_view/                    New elements for JavaFx UI
   +----/src
   +----/test
   +----pom.xml
+--logger/                    Simple logger
   +----/src
   +----/test
   +----pom.xml
+--recorder/                    Module provides ability to record voice
   +----/src
   +----/test
   +----pom.xml
+--schedule_downloader/                    Allows to download schedule from web from different source
   +----/src
   +----/test
   +----pom.xml
+--domain/                    Bussiness logic and abstraction
   +----/src
   +----/test
   +----pom.xml
+--data/                    Module provides handler for files (recording, config etc.)
   +----/src
   +----/test
   +----pom.xml
+--device/                    Layer betweeen bussiness logic and hardware + http server communication
   +----/src
   +----/test
   +----pom.xml
+--javafx/                    Visual presentation of the program
   +----/src
   +----/test
   +----pom.xml
+--config_editor/                    Future feature - visual editor of config
   +----/src
   +----/test
   +----pom.xml
+--control_panel/                    Main program with the main class :)
   +----/src
   +----/test
   +----pom.xml
+--pom.xml                   The main maven build script
```