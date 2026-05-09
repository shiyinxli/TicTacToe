# Exercise 3 – TicTacToe Architectural Decisions

# Part 1 \- Primary ADRs

## Backend Programming Language: Java vs. JavaScript

In this architectural decision, we decide the core backend programming language of the TicTacToe application.

### **1\. Context**

We are building a multiplayer TicTacToe web application.

Two players should be able to play against each other from different devices through the internet.

One player can start a game simply by opening the website, without authentication, and share the generated room number with another player.

The application supports real-time communication between players and should work reliably with multiple concurrent game rooms.

The backend is responsible for:

* Room creation and management  
* Game state synchronization  
* WebSocket communication  
* Move validation  
* Reliability and performance  
* REST API endpoints

The frontend is implemented as a web application communicating with the backend through REST and WebSocket APIs.

#### 1.1 Constraints

##### 1.1.1 Organizational

The development team already has experience with Java from university projects and backend development courses.

The project should follow clean software architecture and support automated testing.

The backend should be maintainable and easy to extend in the future.

##### 1.1.2 Technological

The application will run as a web service and support real-time multiplayer communication.

The backend framework should provide:

* REST API support  
* WebSocket support  
* Good testing capabilities  
* Scalability for concurrent users  
* Stable deployment support

The frontend communicates with the backend using HTTP and WebSocket protocols.

#### 1.2 Quality Objectives

1. Portability  
2. Usability  
3. Compatibility  
4. Reliability  
5. Performance efficiency

### **2\. Solution Alternatives**

1. Alternative 1: Java Backend

    The backend is implemented using Java with Spring Boot.

    REST APIs are implemented using Spring MVC and real-time communication is implemented using WebSocket support from Spring.

2. Alternative 2: JavaScript Backend

    The backend is implemented using JavaScript with Node.js and Express.

    REST APIs are implemented with Express and WebSocket communication is implemented using Socket.IO or ws.

### **3\. Evaluation of Alternatives**

|  | Java | JavaScript |
| ----- | ----- | ----- |
| Portability | \+ | \+ |
| Usability | \+ | 0 |
| Compatibility | \+ | \+ |
| Reliability | \+ | 0 |
| Performance efficiency | \+ | 0 |
| Concurrency handling | \+ | 0 |
| Maintainability | \+ | 0 |
| Development speed | 0 | \+ |

### **4\. Decision**

Java is selected as the backend programming language because it provides higher reliability, maintainability, and performance efficiency for a real-time multiplayer web application.

Spring Boot also offers strong support for REST APIs, WebSocket communication, automated testing, and scalable backend architecture.

### **5\. Consequences**

**Advantages**

* Strong type safety and compile-time checking  
* Better reliability for multiplayer synchronization  
* Mature backend ecosystem with Spring Boot  
* Good support for WebSocket communication  
* Easier maintenance and testing  
* Better scalability for concurrent users

**Disadvantages**

* More verbose syntax compared to JavaScript  
* Slower initial development speed  
* Higher learning curve for backend configuration

**Risks**

* More complex backend setup  
* Higher memory consumption compared to lightweight Node.js servers

## Website type: SPA vs. Template

In this architectural decision, we decide the type of website for the TicTacToe game.

### **1\. Context**

We are building a browser-based TicTacToe game that allows two players to play by simply sharing a link. The game must run instantly in a browser on both desktop and mobile devices without requiring login or installation.

The system must support real-time interaction between two players using the same shared session.

#### 1.1. Constraints

##### 1.1.1. Organizational

* Small development scope (single developer or small team)  
* Rapid development and deployment expected  
* No need for complex infrastructure

##### 1.1.2. Technological

* Must run in modern browsers (mobile \+ desktop)  
* No login/authentication system  
* All developers know JavaScript.  
* Requires real-time communication between two devices.  
* Lightweight backend or backend-as-a-service is acceptable.

#### 1.2. Quality Objectives

The decision will focus on the following quality attributes:

1. Portability: Works across devices and browsers without installation.  
2. Usability: Easy to understand and interact with.  
3. Functional Suitability: Supports all required game features correctly.  
4. Reliability: Stable gameplay without crashes or inconsistent states.  
5. Performance Efficiency: Fast load time and responsive interactions.

### **2\. Solution Alternatives**

**Alternative 1: Single Page Application (SPA)**

A client-side application (e.g., React, Vue, or vanilla JavaScript) that loads once and communicates with a backend service (e.g., WebSockets or self-defined API) to synchronize game state between the two players. There are dynamic updates without page reload, and it maintains game state on the client and syncs via the backend.

**Alternative 2: Server-Side Templating**

The server generates HTML pages dynamically for each interaction.

* Each move requires a request to the server  
* Updates are reflected via page reload or polling  
* Real-time interaction is difficult to achieve

### **3\. Evaluation of Alternatives**

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

### **4\. Decision**

We choose: Single Page Application (SPA) with real-time backend support.

### **5\. Consequences**

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

## Frontend Build Framework: React (Create React App) vs. Vite

In this architectural decision, we decide the frontend development framework and build tool of the TicTacToe application.

### **1\. Context**

We are building a multiplayer TicTacToe web application that allows two players on different devices to play together through the internet.

The frontend must provide:

* Real-time game updates  
* Responsive user interface  
* Room creation and joining  
* Communication with the backend through REST APIs and WebSocket  
* Fast loading and smooth user experience

The frontend should be easy to develop, maintain, and deploy.

The application is intended to run in modern web browsers on desktop and mobile devices.

#### 1.1 Constraints

##### 1.1.1 Organizational

The development team already has basic knowledge of JavaScript and React.

The frontend should support rapid development and easy debugging.

The project timeline is limited, therefore developer productivity is important.

##### 1.1.2 Technological

The frontend communicates with a Java backend through HTTP and WebSocket.

The frontend technology should support:

* Component-based UI development  
* Fast hot reloading during development  
* Efficient production builds  
* Easy dependency management  
* Cross-platform browser support

#### 1.2 Quality Objectives

1. Portability  
2. Usability  
3. Compatibility  
4. Reliability  
5. Performance efficiency

### **2\. Solution Alternatives**

1. Alternative 1: React with Create React App (CRA)

    The frontend is developed using React initialized with Create React App.

    CRA provides a traditional React project setup with webpack-based configuration.

2. Alternative 2: React with Vite

    The frontend is developed using React initialized with Vite.

    Vite provides a modern frontend tooling system with faster development server startup and optimized builds.

### **3\. Evaluation of Alternatives**

|  | CRA | Vite |
| ----- | ----- | ----- |
| Portability | \+ | \+ |
| Usability | 0 | \+ |
| Compatibility | \+ | \+ |
| Reliability | \+ | \+ |
| Performance efficiency | \- | \+ |
| Development speed | \- | \+ |
| Hot reload performance | \- | \+ |
| Build speed | \- | \+ |
| Community adoption | \+ | \+ |

### **4\. Decision**

Vite is selected as the frontend build framework because it provides better development performance, faster hot reload, and improved build efficiency for the TicTacToe web application.

Vite also simplifies frontend setup and improves the overall developer experience while maintaining compatibility with React.

### **5\. Consequences**

**Advantages**

* Very fast development server startup  
* Faster hot module replacement  
* Better frontend build performance  
* Improved developer productivity  
* Modern and lightweight tooling  
* Easy integration with React and WebSocket communication

**Disadvantages**

* Smaller ecosystem compared to older CRA tooling  
* Some older tutorials and plugins are mainly designed for CRA

**Risks**

* Developers unfamiliar with Vite may require initial learning  
* Some legacy webpack-based configurations may require adaptation

# Part 2 \- Additional ADRs

## Testing Strategy and Amount of Testing

In this architectural decision, we determine the appropriate  
 amount and focus of testing for the Browser-Based TicTacToe system.

### **1\. Context**

The project is a browser-based multiplayer TicTacToe game  
 implemented using React, Spring Boot, REST APIs, and WebSockets.

The system supports:

* creation of multiplayer rooms  
* real-time synchronization between players  
* move validation  
* winner and draw detection

The application is intentionally lightweight and does not  
 contain persistent storage, authentication, or complex UI workflows.

#### **1.1 Constraints**

##### **1.1.1 Organizational**

The project is developed within limited time constraints  
 as part of a university software architecture exercise.

The testing effort must therefore focus on the most critical  
 architectural risks instead of maximizing total test quantity.

##### **1.1.2 Technological**

The frontend is implemented as a React Single Page Application.

The backend uses Java Spring Boot with WebSocket communication.

The application stores active games in-memory using ConcurrentHashMap.

#### **1.2 Quality Objectives**

1. Reliability  
2. Compatibility  
3. Performance Efficiency  
4. Portability  
5. Usability

### **2\. Solution Alternatives**

#### **2.1 Alternative 1: Extensive Full-Stack Testing**

Apply extensive testing across:

* backend services  
* REST APIs  
* WebSocket communication  
* frontend UI  
* end-to-end browser automation

This approach maximizes coverage but significantly increases  
 implementation complexity and development effort.

#### **2.2 Alternative 2: Backend-Focused Testing**

Prioritize testing of:

* backend business logic  
* move validation  
* winner detection  
* draw handling  
* room management  
* REST endpoint integration

Frontend testing remains limited to manual UI verification  
 because the frontend mainly renders backend state and contains  
 limited business logic.

### **3\. Evaluation of Alternatives**

|  | Full-Stack Testing | Backend-Focused Testing |
| ----- | ----- | ----- |
| Reliability | \+ | \+ |
| Compatibility | \+ | \+ |
| Performance Efficiency | \- | \+ |
| Development Complexity | \- | \+ |
| Maintainability | \- | \+ |
| Testing Effort | \- | \+ |
| UI Validation | \+ | 0 |

### **4\. Decision**

Alternative 2 was selected.

The project prioritizes backend-focused testing because the  
 backend contains the system’s critical architectural logic,  
 including:

* multiplayer synchronization  
* move validation  
* turn enforcement  
* winner detection  
* draw handling

These components directly affect the reliability and  
 consistency of the system.

Integration tests are additionally used for REST endpoints  
 to ensure compatibility between frontend and backend  
 communication.

Frontend UI testing remains primarily manual because the UI  
 contains limited business logic and low architectural risk.

### **5\. Consequences**

Advantages:

* Strong reliability testing for critical game logic  
* Reduced implementation complexity  
* Faster development iteration  
* Good balance between quality and development effort

Disadvantages:

* Limited automated frontend UI validation  
* Some visual UI issues may require manual detection

Risks:

* UI regressions may not be automatically detected  
* WebSocket behavior is only partially covered by automated tests

