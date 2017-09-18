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


User Interface:
The game will start with a screen displaying a welcome message, and then four buttons where the user can choose which type of simulation they would like to run. This welcome text will have to be read from a resource file as it can't be hard-coded as stated in the specification. Once one of these buttons is clicked on the screen will change into one asking for user input first of how many rows and then how many columns the user would like for the simulation. We decided that we needed a lower bound for this program so that it could run effectively. Obviously negative numbers would be impossible for the number of rows and number of columns, but we also realized that one row/column wouldn't work as each cell needs to check neighboring cells (one above or one below them) and these wouldn't exist in smaller input values. Therefore, if the input value is 1 or lower, the program will ask the user to input again with a bigger number so that the visualization can show. 

Related to this, we are unsure whether to put an upper bound on the number of rows and columns. Although it should technically be possible to have an infinite number of cells under this model, when actually computing a simulation there will be input values for rows and columns that would simply be too big to implement effectively - the program would not be able to run quickly and it could possibly crash. Therefore, we thought it would be a good idea to cap the number of rows and columns that the user can input, possibly at 100. This will most likely change, however, as we start to code and test with different values. We would like to allow the user to choose as big a number as possible without causing it to affect the program negatively. 

After the user inputs the data, the grid will appear in its initialized state, which will be chosen randomly, with some factors chosen by user input (for example we may let the user decide the number of empty blocks, ratio of one block style to another etc.) The user will then be prompted to press ENTER to start the simulation, which will run automatically. There will be speed + and - buttons to increase and decrease the speed of simulation throughout.  There will also be an option available at all times to press ESC to return to the main menu. At any time during the automated simulation, the user can press P to pause the game (and ENTER to start it again). If the simulation is paused, the user will then be given the option to use the arrow keys to navigate forward and backwards through each state. 

We're unsure at the moment if it would be better to use arrow keys or a slider. We feel that a slider would be easier to move many steps at a time (for example if you want to move 200 steps then you just move the slider to near the end of the program, rather than having to press a key 200 times). However, we realized that the slider will need to know the finishing time of the program in order to have an end point, and this could prove impossible if the program has no end (For example in Segregation if the similarity percentage is too high and the program keeps running indefinitely). For this reason we decided to start with the arrow keys, as it would be something that definitely works, but if we feel that a slider is possible, we would try to implement it as best we could. 

Finally, we need to be sure that the cells will move simultaneously, as described in the prompt. Even though we will be iterating through each cell checking them one at a time, the program should update all the cells at (approximately) the same time after checking all available cells. 


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
The game will start with a screen displaying a welcome message, and then four buttons where the user can choose which type of simulation they would like to run. This welcome text will have to be read from a resource file as it can't be hard-coded as stated in the specification. Once one of these buttons is clicked on the screen will change into one asking for user input first of how many rows and then how many columns the user would like for the simulation. We decided that we needed a lower bound for this program so that it could run effectively. Obviously negative numbers would be impossible for the number of rows and number of columns, but we also realized that one row/column wouldn't work as each cell needs to check neighboring cells (one above or one below them) and these wouldn't exist in smaller input values. Therefore, if the input value is 1 or lower, the program will ask the user to input again with a bigger number so that the visualization can show. 

Related to this, we are unsure whether to put an upper bound on the number of rows and columns. Although it should technically be possible to have an infinite number of cells under this model, when actually computing a simulation there will be input values for rows and columns that would simply be too big to implement effectively - the program would not be able to run quickly and it could possibly crash. Therefore, we thought it would be a good idea to cap the number of rows and columns that the user can input, possibly at 100. This will most likely change, however, as we start to code and test with different values. We would like to allow the user to choose as big a number as possible without causing it to affect the program negatively. 

After the user inputs the data, the grid will appear in its initialized state, which will be chosen randomly, with some factors chosen by user input (for example we may let the user decide the number of empty blocks, ratio of one block style to another etc.) The user will then be prompted to press ENTER to start the simulation, which will run automatically. There will be speed + and - buttons to increase and decrease the speed of simulation throughout.  There will also be an option available at all times to press ESC to return to the main menu. At any time during the automated simulation, the user can press P to pause the game (and ENTER to start it again). If the simulation is paused, the user will then be given the option to use the arrow keys to navigate forward and backwards through each state. 

We're unsure at the moment if it would be better to use arrow keys or a slider. We feel that a slider would be easier to move many steps at a time (for example if you want to move 200 steps then you just move the slider to near the end of the program, rather than having to press a key 200 times). However, we realized that the slider will need to know the finishing time of the program in order to have an end point, and this could prove impossible if the program has no end (For example in Segregation if the similarity percentage is too high and the program keeps running indefinitely). For this reason we decided to start with the arrow keys, as it would be something that definitely works, but if we feel that a slider is possible, we would try to implement it as best we could. 

Finally, we need to be sure that the cells will move simultaneously, as described in the prompt. Even though we will be iterating through each cell checking them one at a time, the program should update all the cells at (approximately) the same time after checking all available cells. 


## Design Details

## Design Consideration

We first considered the degree of freedom that we want to give to users. More precisely, we were thinking about what aspects of the cell society game should be determined by user input. There were several aspects: grid dimension, number of cells, cell dimension, percentage of each initial state. We also thought about whether we should pre-determine the shape and size of the cells, can they be irregular in shape, of variant sizes? 

We also debated about whether it’s possible to have a playback, which let the game store it’s previous state so that user could get back at those later easily and quickly. One of us was concerned about whether it would take up much space.

In addition, we also discussed what kind of user input we would take after the whole process had started. We agreed that we could not let the users to change the dimension of the grid and cells. However, we were considering that we could let the user pause the process, change the speed of process taking place, and move back to any moment that’s passed by.

## Team Responsibilities
Peilin will learn how XML works and how we can use it for the game.

Matt will be the main person working on the user interface, including all buttons, keyboard input. 

We will work on the back-end coding part all together, in which Ramil will take the lead, and assign the other two team members tasks, potentially using “Issue” function on Gitlab.

At this point, we don’t want to assign very specific tasks for each person yet, because none of us is 100% sure what we are going to do, and many aspects of this game still need to be figured out togtehr.