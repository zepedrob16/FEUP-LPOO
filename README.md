# Ad Hunter 

### Mock-ups
![Main Menu](/screenshots/mockup-mainmenu.png)  
![Settings](/screenshots/mockup-settings.png)  
![Tutorial](/screenshots/mockup-tutorial.png)  
![About](/screenshots/mockup-about.png)  
![Pre-game](/screenshots/mockup-pregame.png)  
![Game](/screenshots/mockup-game.png)

### Design Patterns
* Decorator  
There is a need to generate a massive amount of buttons in an efficient way, given that we planned for about 150 unique combinations. Therefore, we've stripped down the button to four main components: the background, the outline, the icon and the shape. Adding decorators for these details allow us to add these functionalities without altering the structure.

* Singleton  
Ensuring that only one single game state is created and having easy access to it in every class is a top priority.
