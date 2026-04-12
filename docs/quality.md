# Part 1 – Quality Attributes 

1. **List and rank the 5 most important ISO 25010 quality attributes for your TicTacToe project.**   
1. Portability  
2. Usability  
3. Functional suitability  
4. Reliability  
5. Performance efficiency  
2. **For each quality attribute: Provide a short description, why this attribute is important for your TicTacToe project.**   
1. Portability: Portability is important because the game must run on different devices and platforms, including desktop browsers and mobile browsers. Ensuring portability allows both players to access the game without installation and configuration, regardless of their device or operating system.  
2. Usability:  Usability is crucial because both users have different technical skills and devices. The game must be simple, intuitive, and easy to use so that players can play without confusion and can quickly start a game during their short lunch break.  
3. Functional suitability: Functional suitability ensures that the game behaves correctly (e.g., valid moves, correct handling, and accurate game results). If the game does not fulfill players’ expectations, users might experience frustration and loss of trust in the game.  
4. Reliability: Making sure the game runs consistently without interruptions, allowing both players to interact smoothly in real time despite being in different locations.  
5. Performance efficiency: Performance efficiency is important because the game requires an immediate response to user actions. In a browser-based TicTacToe, moves must be displayed instantly to ensure smooth gameplay and a good user experience.   
3. **For each quality attribute: Name or create a use case for which this quality attribute is the most important. Describe how this specific quality attribute affects this specific use-case.** 

| UC-001 | Start and share the game |  |
| :---- | :---- | :---- |
| **Description** | Player 1 starts a TicTacToe game on a desktop browser, and Player 2 joins the same game using a smartphone browser. |  |
| **Preconditions** | Both players have access to a web browser Internet connection is available The game is hosted and accessible via URL |  |
| **Ordinary Sequence** | **Step** | **Action** |
|  | 1 | Player 1 opens the game in a desktop browser |
|  | 2 | Player 1  creates a new game session |
|  | 3 | The system generates a join link/code |
|  | 4 | Player 1  shares the link/code with Player 2 |
|  | 5 | Player 2 opens the link on a smartphone browser |
|  | 6 | Player 2 joins the game session |
|  | 7 | The game starts and synchronizes on both devices |
| **Postcondition** | Both players are connected and can play the game on their respective devices |  |
| **Exceptions** | Device/browser not supported \-\> game does not display correctly Connection issues \-\> players cannot join the session |  |

| UC-002 | Player does the game flow |  |
| :---- | :---- | :---- |
| **Description** | The player plays a full TicTacToe game on a smartphone without needing instructions. |  |
| **Preconditions** | Player has access to the game link The game is already running or can be joined The interface is loaded on a smartphone |  |
| **Ordinary Sequence** | **Step** | **Action** |
|  | 1 | Player opens the game link on a smartphone |
|  | 2 | The game interface loads and displays the grid clearly |
|  | 3 | Player recognizes it is his or her turn |
|  | 4 | Player taps on a cell to place his or her mark |
|  | 5 | The system immediately shows his or her amove |
|  | 6 | The game indicates the next player’s turn |
|  | 7 | The game ends and displays the result (win/loss/draw) |
| **Postcondition** | Player successfully completes a game and understands the outcome |  |
| **Exceptions** | Interface is confusing \-\> Player does not know what to do Buttons or grid are too small \-\> incorrect or failed input No clear feedback \-\> Player cannot follow the game progress |  |

| UC-003 | Make a move in TicTacToe |  |
| :---- | :---- | :---- |
| **Description** | A player places their symbol (X or O) on the TicTacToe board during their turn. |  |
| **Preconditions** | Both players are connected to the game. The game session has started. It is the player’s turn. |  |
| **Ordinary Sequence** | **Step** | **Action** |
|  | 1\. | The system displays the current game board. |
|  | 2\. | The system indicates whose turn it is. |
|  | 3\. | The player selects an empty cell. |
|  | 4\.  | The system places the player’s symbol (X or O) in the selected cell. |
|  | 5\.  | The system updates the board for both players. |
|  | 6\.  | The system switches the turn to the other player. |
| **Postcondition** | The move is recorded correctly. The game state is updated. The next player can make a move. |  |
| **Exceptions** | If the player selects an occupied cell, the system rejects the move and shows an error message. If it is not the player’s turn, the system ignores input and notifies the player.  |  |

| UC-004 | Maintain game state during connection issues |  |
| :---- | :---- | :---- |
| **Description** | The system ensures the TicTacToe game continues correctly even if there is a temporary connection loss. |  |
| **Preconditions** | A game session is in progress. At least one move has been made.  |  |
| **Ordinary Sequence** | **Step** | **Action** |
|  | 1\. | The system continuously synchronizes the game state between both players. |
|  | 2\. | A temporary network interruption occurs. |
|  | 3\. | The system attempts automatic reconnection. |
|  | 4\.  | The player reconnects to the game session. |
|  | 5\. | The system restores the latest game state. |
|  | 6\. | The game continues from the last valid move. |
| **Postcondition** | The game state is preserved. Players can continue without restarting the game. |  |
| **Exceptions** | If reconnection fails, the system displays an error and provides an option to restart the game. If the game session expires, the system informs players and ends the session. |  |

| UC-005 | Update Game Board After Player Move |  |
| :---- | :---- | :---- |
| **Description** | The system updates the game board after a player makes a move. |  |
| **Preconditions** | The game is running. Both players are playing on the same device. The game board is visible. |  |
| **Ordinary Sequence** | **Step** | **Action** |
|  | 1 | The player clicks on an empty cell. |
|  | 2 | The system immediately displays the player’s symbol. |
|  | 3 | The system updates the internal game state. |
|  | 4 | The system checks for a win, a loss, or a draw. |
| **Postcondition** | The move is shown instantly on the board. The game state is updated correctly. The next turn is ready, or the game ends. |  |
| **Exceptions** | The symbol appears with a delay. The browser becomes unresponsive. The board update is not smooth. |  |

## 

4. **Name one quality attribute you think is specifically not so important. Provide a short description on why.**  
* Maintainability: Maintainability is not so important because the TicTacToe application is small and simple, with limited functionality. The codebase is easy to understand and modify, so long-term maintenance is not a major concern


  

# Part 2 – Quality Scenarios For each quality attribute: Create at least 2 quality scenarios.

**1-1 Portability \- Scenario 1**

| Source | Stimulus | Artifact | Environment | Response | Measure |
| ----- | ----- | ----- | ----- | ----- | ----- |
| Player | Opens the game link on the smartphone | Web Application | Mobile browser (small screen, touch input) | Game adapts layout and remains fully playable | Fully usable within 2 seconds without layout errors |

**1-2 Portability \- Scenario 2**

| Source | Stimulus | Artifact | Environment | Response | Measure |
| ----- | ----- | ----- | ----- | ----- | ----- |
| Player | Opens the game on desktop browser | Web Application | Desktop browser (large screen, mouse input) | Game renders correctly and supports all interactions | All features are available without errors across major browsers (e.g., Chrome, Firefox) |

**2-1 Usability \- Scenario 1**

| Source | Stimulus | Artifact | Environment | Response | Measure |
| ----- | ----- | ----- | ----- | ----- | ----- |
| Player | Wants to make a move | Game Interface | During an active game session on desktop browser or an application | User taps a cell and receives immediate visual feedback (X/O appears) | Response time \< 1 second, no input errors |

**2-2 Usability \- Scenario 2**

| Source | Stimulus | Artifact | Environment | Response | Measure |
| ----- | ----- | ----- | ----- | ----- | ----- |
| Player | Starts a new game during break | Web Application | Desktop browser, limited time | The user can start and understand the game without instructions | The game started within 10 seconds, no help needed |

**3-1 Functional suitability \- Scenario 1**

| Source | Stimulus | Artifact | Environment | Response | Measure |
| ----- | ----- | ----- | ----- | ----- | ----- |
| Player | A player completes three symbols (O or X) in a row | TicTacToe game logic | During an active game session on desktop browser or an application | The system detects the winning condition immediately | The result is displayed within \< 1 second after the move |

**3-2 Functional suitability \- Scenario 2**

| Source | Stimulus | Artifact | Environment | Response | Measure |
| ----- | ----- | ----- | ----- | ----- | ----- |
| Player  | Place a symbol (O or X) on an already occupied cell | TicTacToe game logic | During an active game session on desktop browser or an application | The system rejects the move | Error message shown within \< 1 second |

**4-1 Reliability \- Scenario 1**

| Source | Stimulus | Artifact | Environment | Response | Measure |
| ----- | ----- | ----- | ----- | ----- | ----- |
| Player | Synchronization movement | Network communication and game state synchronization module | During gameplay over Wi-Fi connection | The system sends the move to the server | Synchronization success rate ≥ 99% |

**4-2 Reliability \- Scenario 2**

| Source | Stimulus | Artifact | Environment | Response | Measure |
| ----- | ----- | ----- | ----- | ----- | ----- |
| Player | Temporary network disconnection occurs during a game | Session management and reconnection mechanism | Unstable internet connection | The system detects the connection loss | Reconnection attempt starts within ≤ 3 seconds |

**5-1 Performance efficiency \- Scenario 1**

| Source | Stimulus | Artifact | Environment | Response | Measure |
| ----- | ----- | ----- | ----- | ----- | ----- |
| Player  | Player taps or clicks on a cell  | User interface and input handling module  | Face-to-face game on the same device  | The system immediately registers the input and displays the symbol (X or O) | Input response time ≤ 100 ms, no noticeable delay  |

**5-2 Performance efficiency \- Scenario 2**

| Source | Stimulus | Artifact | Environment | Response | Measure |
| ----- | ----- | ----- | ----- | ----- | ----- |
| Player  | Players play multiple consecutive games without restarting the application | Game state management and memory handling module  | gameplay on a single device over an extended period of time  | The system maintains consistent performance without slowing down or freezing  | No increase in response time (\>10%), no memory-related lag after 10+ games  |

