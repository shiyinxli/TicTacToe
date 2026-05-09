# Real-Time Multiplayer TicTacToe

A full-stack real-time multiplayer TicTacToe web application built with React, Spring Boot, REST APIs, and WebSockets.

The project allows two players on different devices to join the same game room and play simultaneously with live board synchronization.

---

# Features

- Real-time multiplayer gameplay
- Room creation and joining system
- WebSocket-based live synchronization
- Turn validation and game state management
- Winner and draw detection
- Responsive Single Page Application (SPA)
- REST API integration
- Unit and integration testing
- CI pipeline with GitHub Actions

---

# Architecture

## Frontend
- React
- Vite
- JavaScript
- SockJS + STOMP

## Backend
- Java Spring Boot
- Spring Web
- Spring WebSocket
- Maven

## Communication
- REST APIs for room management
- WebSockets for real-time gameplay updates

---

# Project Structure

```text
tic-tac-toe/
├── backend/
│   ├── src/
│   ├── pom.xml
│   └── ...
│
├── frontend/
│   ├── src/
│   ├── package.json
│   └── ...
│
└── .github/
    └── workflows/
```

---

# Screenshots

## Home Screen

- Create room
- Join existing room

## Multiplayer Gameplay

- Live synchronized board
- Turn switching
- Winner detection

---

# Getting Started

## Prerequisites

- Java 21
- Node.js 20+
- npm

---

# Backend Setup

```bash
cd backend
./mvnw spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

---

# Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on:

```text
http://localhost:5173
```

---

# Running on Multiple Devices

1. Connect devices to the same WiFi network
2. Start backend and frontend
3. Run frontend with:

```bash
npm run dev -- --host
```

4. Open:

```text
http://YOUR_LOCAL_IP:5173
```

on another device.

---

# Testing

## Backend Tests

```bash
cd backend
./mvnw test
```

Tests include:

- unit tests
- REST controller integration tests
- WebSocket tests
- multiplayer flow tests

---

# Quality Attributes

The architecture was designed with the following quality objectives:

- Reliability
- Compatibility
- Performance Efficiency
- Portability
- Usability

---

# CI/CD

GitHub Actions pipeline automatically:

- runs backend tests
- builds frontend
- validates pull requests

---

# Future Improvements

- Authentication system
- Persistent database storage
- Matchmaking
- Spectator mode
- Reconnect support
- Docker deployment
- Cloud deployment
- Online multiplayer over internet

---

# Author

Shiyin Li

Techinische Hochschule Aschaffenburg