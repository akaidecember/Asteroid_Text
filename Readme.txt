Asteroids (Text version) by Anshul Kumar Shandilya
___________________________________________________

In addition to the classes mentioned in the assignment specification, I have added a few extra classes for easier understanding of the code. The extra classes are GameCanvas and ClosingApp.

The class GameCanvas simply houses the attributes for the resolution of the screen of the game. This is done because it will me much easier to modify the resolution later on. Also, the class GameCanvas houses some static methods that 
enable us to check if the coordinates used in the game for the objects lie within the screen resolution or not.

The class ClosingApp has been created to encapsulate the methods and objects necessary to correctly close the application upon inputting the command 'q'.

Furthermore, I have created a new SteerableMissileLauncher that extends the regular MissileLauncher and also implements the interface ISteerable. This is because the MissileLauncher for the Player Ship is supposed to be 
steerable.

Extra methods have also been added to the GameWorld class. They are gameOver() that is called once the number of lives for the player becomes zero (0). This method then exits the program by using Systen.exit(0).