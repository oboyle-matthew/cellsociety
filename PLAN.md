# Team 15

Methods:

- createGrid
- initializeGrid
- checkNeighbors
- start
- stop
- adjustSpeed (for increasing and decreasing)
- readFile
- displayText
- newConfiguration

Classes:
Grid
Cell (updateCell, getinfo methods, changeSize)
Class for each type of simulation
1. Segregation
2. Predator-prey
3. Fire
4. Game of life
Main class


Freedom - don't just allow anyone to change number of cells once it's initialized 
Controls class that handles parameters Eg. type of cell, number of empty cells, and similar percentage BUT shouldn't be able to change any of these within each step.

Cell could be many different things - could be color, ImageView or symbol.

Width and height: In the grid function you create the different cells. Each cell has its own defined height and width, then total width of the screen is width*no. 

Define cells - we have action type and content. 

Added in portions to the XML file, so that the user can decide the percentage of bricks that are a certain color. 

Label of a timeframe. Also have it so that you can input.

KeyCode 

Timeline function. First checks if the function actually ends, if so then do nothing and if not 

Having something that can go back. Create a reverse action. Store every step in memory, and you can move back to each step to study how the program developed 


Any cell moving is an action which can only happen after all other cells have gone through the information and are ready to move. 
Restriction: Don't let the user change the cells mid-step. 

Within action: Take in an arbitrary action and look at all the neighbours in all spaces then move position.

State will be its own class. Then can use methods such as color or no. blue or something find out info

Define to start with initial state. Initializer accepts state and initializes itself with this state.


Specification

In a markdown formatted file, doc/DESIGN.md, describe the primary classes (in packages if possible) you envision are needed to read in and represent a CA model and its simulation. Focus your design on how to represent a model in a general way and specifically on what behavior (i.e., methods) your classes would have. If describing an inheritance hierarchy, clearly identify what behaviors the classes have in common, the superclass, and what are different, the subclasses, rather than the instance variables.

In your design document, include the following sections:

    Introduction
    This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). This section should discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).
    Overview
    This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. As such, it should describe specific components you intend to create, their purpose with regards to the program's functionality, and how they collaborate with each other. It should also include a picture of how the components are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). This section should discuss specific classes, methods, and data structures, but not individual lines of code.
    User Interface
    This section describes how the user will interact with your program (keep it very simple to start). It should describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). It should also include one or more pictures of the user interface (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a dummy program that serves as a exemplar). Finally, it should describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.). This section should go into as much detail as necessary to cover all your team wants to say.
    Design Details
    This section describes each component introduced in the Overview in detail (as well as any other sub-components that may be needed but are not significant to include in a high-level description of the program). It should describe how each component handles specific features given in the assignment specification, what resources it might use, how it collaborates with other components, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Include the steps needed to complete the Use Cases below to help make your descriptions more concrete. Finally, justify the decision to create each component with respect to the design's key goals, principles, and abstractions. This section should go into as much detail as necessary to cover all your team wants to say.
    Design Considerations
    This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. It should include any design decisions that the group discussed at length (include pros and cons from all sides of the discussion) as well as any assumptions or dependencies regarding the program that impact the overall design. This section should go into as much detail as necessary to cover all your team wants to say.
    Team Responsibilities
    This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.
## Introduction

## Overview

## User Interface
