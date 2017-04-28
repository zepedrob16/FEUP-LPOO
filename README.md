# Ad Hunter

### Mock-ups
**Main Menu**  
<img src=/screenshots/mockup-mainmenu.png height=300 />  

**Settings**  
<img src=/screenshots/mockup-settings.png height=300 />  

**Tutorial**  
<img src=/screenshots/mockup-tutorial.png height=300 />  

**About**  
<img src=/screenshots/mockup-about.png height=300 />  

**Pre-game**  
<img src=/screenshots/mockup-pregame.png height=300 />  

**Game**  
<img src=/screenshots/mockup-game.png height=300 />  

### Package and Class Diagram (UML)
<img src=/screenshots/uml-diagram.png height=300 />

### UML Sequence
<img src=/screenshots/uml-sequence.png height=300 />

### Design Patterns
* Decorator  
There is a need to generate a massive amount of buttons in an efficient way, given that we planned for about 150 unique combinations. Therefore, we've stripped down the button to four main components: the background, the outline, the icon and the shape. Adding decorators for these details allow us to add these functionalities without altering the structure.
* Singleton  
Ensuring that only one single game state is created and having easy access to it in every class is a top priority.

### Tests to implement

- Settings menu opens when the respective button is pressed
- About menu opens when the respective button is pressed
- Help menu opens when the respective button is pressed
- High scores are updated correctly
- Game starts when tapped in the right place
- Language is changed correctly in the settings menu
- Song is changed correctly in the settings menu
- Player loses a life when he taps in the wrong button
- The correct button disappears from the screen when tapped
- Player advances a stage when all the correct buttons are tapped
- Player advances a level when all stages are complete
- Player loses the game when the time runs out
- Player loses the game when he runs out of lives
- Time stops running when the player hits the pause button
- A certain achievement is unlocked when completed
