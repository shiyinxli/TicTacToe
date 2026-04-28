# Exercise 3 – TicTacToe Architectural Decisions

# Part 1 \- Primary ADRs

## Programming Language: JavaScript vs. Python

This decision defines the primary technology stack. We are selecting the core language for the TicTacToe game.

## 1\. Context

We are developing a browser-based TicTacToe game that allows two players to play in real time across different devices: desktop and mobile. The system must run without installation and be easily accessible via a web browser. 

The choice of programming language will define how the game is implemented, deployed, and executed in the browser. 

## 1.1. Constraints

### 1.1.1. Organizational

* The project is developed within a limited timeframe.  
* Team members have basic knowledge of both JavaScript and Python.  
* The solution should be simple to implement and maintain.  
* No complex infrastructure or deployment pipeline is expected. 

### 1.1.2. Technological

* The game must run in a web browser.  
* No installation should be required for users.   
* The application should support real-time interaction between players. 

## 1.2. Quality Objectives

* Usability: The game should be easy to access and play instantly.  
* Portability: Must run across different devices and browsers.  
* Performance efficiency: Fast response to user input.   
* Reliability: Stable gameplay without crashes or desynchronization.  
* Functional suitability: Correct game logic and behavior. 

## 2\. Solution Alternatives

**Alternative 1: JavaScript**

* Runs natively in web browsers.  
* Can be used for both fronted and backed (Node.js)  
* Supports real-time communication (e.g., WebSockets)

**Alternative 2: Python**

* Requires a backend framework (e.g., Flask, Django).  
* Cannot run directly in the browser (without additional tools).  
* Needs integration with frontend technologies (HTML/CSS/JS).

## 3\. Evaluation of Alternatives

|  | JavaScript | Python |
| :---- | :---- | :---- |
| Portability | \++ | \- |
| Usability | \++ | \- |
| Compatibility | \+ | \-- |
| Reliability | \++ | \+ |
| Performance Efficiency | \+ | 0 |
| Real-time support | \++ | \- |
| Learning curve | \++ | \++ |

## 4\. Decision

We chose JavaScript as the primary programming language for the TicTacToe Project. 

## 5\. Consequences

**Advantages:**

* The game can run directly in the browser without installation.   
* Faster development and simpler deployment.   
* Better support for real-time interaction.  
* High portability across devices and platforms.

**Disadvantages:**

* Requires careful handling of game state synchronization.   
* Potential security concerns if the backend is added later.   
* JavaScript code can become harder to maintain if not structured well. 

**Risks:**

* JavaScript runs on the client side, which allows users to manipulate the game and potentially cheat.   
* Asynchronous communication in JavaScript can lead to inconsistent game states between players.   
* JavaScript’s dynamic typing may cause runtime errors that are harder to detect during development.   
* Differences between browsers can result in inconsistent behavior or display issues.   
* Performance depends on the user’s device, which may lead to slower gameplay on low-end hardware.   
* JavaScript code can become difficult to maintain if not properly structured.

## Website type: SPA vs. Template

In this architectural decision, we decide the type of website for the TicTacToe game.

## 1\. Context

We are building a browser-based TicTacToe game that allows two players to play by simply sharing a link. The game must run instantly in a browser on both desktop and mobile devices without requiring login or installation.

The system must support real-time interaction between two players using the same shared session.

## 1.1. Constraints

### 1.1.1. Organizational

* Small development scope (single developer or small team)  
* Rapid development and deployment expected  
* No need for complex infrastructure

### 1.1.2. Technological

* Must run in modern browsers (mobile \+ desktop)  
* No login/authentication system  
* All developers know JavaScript.  
* Requires real-time communication between two devices.  
* Lightweight backend or backend-as-a-service is acceptable.

## 1.2. Quality Objectives

The decision will focus on the following quality attributes:

1. Portability: Works across devices and browsers without installation.  
2. Usability: Easy to understand and interact with.  
3. Functional Suitability: Supports all required game features correctly.  
4. Reliability: Stable gameplay without crashes or inconsistent states.  
5. Performance Efficiency: Fast load time and responsive interactions.

## 2\. Solution Alternatives

**Alternative 1: Single Page Application (SPA)**

A client-side application (e.g., React, Vue, or vanilla JavaScript) that loads once and communicates with a backend service (e.g., WebSockets or self-defined API) to synchronize game state between the two players. There are dynamic updates without page reload, and it maintains game state on the client and syncs via the backend.

**Alternative 2: Server-Side Templating**

The server generates HTML pages dynamically for each interaction.

* Each move requires a request to the server  
* Updates are reflected via page reload or polling  
* Real-time interaction is difficult to achieve

## 3\. Evaluation of Alternatives

|  | SPA | Template |
| :---- | :---- | :---- |
| Portability | \+ | \+ |
| Usability | \+ | \- |
| Compatibility | \+ | \++ |
| Reliability | \+ | 0 |
| Performance Efficiency | \+ | \- |
| Real-time Synchronization | \+ | \- |
| Two-device Experience | \+ | \- |
| Implementation Effort | 0 | \+ |

## 4\. Decision

We choose: Single Page Application (SPA) with real-time backend support.

## 5\. Consequences

**Advantage**: 

* Real-time gameplay between two devices  
* Smooth and responsive user experience  
* Easy link-based session sharing  
* Strong alignment with usability and performance goals

**Disadvantages:**

* Requires a backend (or backend-as-a-service) for synchronization  
* Increased frontend complexity (state management, event handling)

**Risks:**

* Synchronization issues if real-time communication is not handled properly  
* Network instability may affect gameplay  
* Dependency on JavaScript and browser capabilities

## Web Library / Framework: React vs. No Framework (Vanilla JavaScript)

## 1\. Context

The project is the development of a web-based TicTacToe game. The application is relatively small and includes a limited number of features, such as rendering the game board, handling user interaction, updating game state, checking win conditions, and restarting the game.

The main architectural question is whether the frontend should be implemented using React or without any framework, using only HTML, CSS, and JavaScript.

Because TicTacToe is a simple game with a small state space and limited UI complexity, both alternatives are technically feasible. The decision, therefore, depends mainly on development effort, maintainability, learning benefits, and suitability for the project scope.

## 1.1. Constraints

### 1.1.1. Organizational

* The team size is small.  
* Development resources are limited.  
* The project should be easy to explain and demonstrate.  
* The implementation should remain manageable for all team members.

### 1.1.2. Technological

* The application is browser-based.  
* The game logic is simple and can be handled entirely on the frontend.  
* No backend, database, or distributed infrastructure is required.  
* The UI consists of only a few interactive components.

## 1.2. Quality Objectives

1. Portability: Works across devices and browsers without installation.  
2. Usability: Provides an intuitive and user-friendly interface.  
3. Functional Suitability: Ensures correct implementation of all required game features.  
4. Reliability: Maintains stable and consistent gameplay without failures.  
5. Performance Efficiency: Ensures fast loading and responsive user interactions.

## 2\. Solution Alternatives

**Alternative 1: React**  
React is a JavaScript library for building component-based user interfaces. It supports state-driven rendering and modular UI development.  
Possible use in this project:

* A Board component for the game grid  
* A Square component for each field  
* A state variable for turns and board state  
* Conditional rendering for winner and restart messages

**Alternative 2: No Framework (Vanilla JavaScript)**  
This approach uses only standard web technologies:

* HTML for structure  
* CSS for styling  
* JavaScript for logic and DOM manipulation (DOM-Manipulation)  
* Possible use in this project:  
* Render the game board directly in HTML  
* Handle clicks using event listeners  
* Update the UI manually through DOM operations  
* Store board state in a simple array

## 3\. Evaluation of Alternatives

|  | React | No Framework |
| :---- | :---- | :---- |
| Portability | \+ | \+ |
| Usability | \+ | \- |
| Functional Suitability | \+ | \- |
| Reliability | \+ | 0 |
| Performance Efficiency | \+ | \- |
| Real-time Synchronization | \+ | \- |
| Two-device Experience | \+ | \- |
| Implementation Effort | 0 | \+ |

## 4\. Decision

The project will be implemented without a framework, using HTML, CSS, and Vanilla JavaScript.

## 5\. Consequences

**Advantages:**

* The implementation remains simple and lightweight.  
* Development can proceed quickly with minimal setup effort.  
* The solution matches the small scope of the application.  
* The team can focus on core game logic instead of framework-specific structure.  
* The final code is easy to demonstrate in an academic context.

**Disadvantages:**

* UI updates require more manual DOM handling.  
* The architecture is less modular than a React-based solution.  
* Future extensions may require refactoring if the project becomes larger.  
* The team gains less experience with modern frontend libraries.

**Risks**

* If additional features are added later, the code may become less maintainable.  
* Manual DOM manipulation may lead to repetitive logic.

# Part 2 \- Additional ADRs

## State Management Approach: Frontend State Only vs. Backend \+ Database

## 1\. Context

The TicTacToe game requires managing the game state, including the board, player turns, and game results.  
The main decision is whether the state should be managed entirely on the frontend (client-side) or stored and managed using a backend with a database.

## 1.1. Constraints

### 1.1.1. Organizational

* Small team  
* Focus on a working prototype  
* Easy demonstration required

### 1.1.2. Technological

* Browser-based application  
* No strict requirement for persistent storage  
* No multiplayer or distributed system needed  
* Simple game logic

## 1.2. Quality Objectives

1. Portability: Works on browsers without installation.  
2. Usability: Provides an intuitive and user-friendly interface.  
3. Functional Suitability: Ensures correct implementation of all required game features.  
4. Reliability: Maintains stable and consistent gameplay without failures.  
5. Performance Efficiency: Ensures fast loading and responsive user interactions.

## 2\. Solution Alternatives

**Alternative 1: Frontend State Only (Client-side)**

* Game state stored in browser memory (e.g., JavaScript variables or state)  
* No backend or database

**Alternative 2: Backend \+ Database**

* Game state stored on a server  
* Persistent storage using a database  
* Communication via API

## 3\. Evaluation of Alternatives

|  | Frontend State Only  | Backend \+ Database |
| :---- | :---- | :---- |
| Portability | \+ | \+ |
| Usability | \+ | 0 |
| Functional Suitability | \+ | \+ |
| Reliability | 0 | \+ |
| Performance Efficiency | \+ | \- |
| Real-time Synchronization | \- | \+ |
| Two-device Experience | \- | \+ |
| Persistence | \- | \+ |
| Implementation Effort | \+ | \- |

## 4\. Decision

Due to the requirement that the application must be usable across two devices, the game state will be managed using a backend with a database.

## 5\. Consequences

**Advantages:**

* Enables multi-device interaction   
* Supports real-time synchronization between clients  
* Allows persistent storage of game state  
* Provides a foundation for future extensions (e.g., multiplayer, analytics)

**Disadvantages:**

* Increased implementation complexity  
* Requires backend setup and maintenance  
* Higher latency compared to a pure frontend solution

**Risks:** 

* More development effort for a small team  
* Need to handle synchronization and consistency issues

