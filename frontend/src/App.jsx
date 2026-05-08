import { useState } from "react";

function App() {

  const [roomId, setRoomId] = useState("");
  const [joinRoomId, setJoinRoomId] = useState("");
  const [message, setMessage] = useState("");

  const createRoom = async () => {

    const response = await fetch(
      "http://localhost:8080/api/game/create",
      {
        method: "POST",
      }
    );

    const data = await response.json();

    setRoomId(data.roomId);
    setMessage("Room created!");
  };

  const joinRoom = async () => {

    const response = await fetch(
      `http://localhost:8080/api/game/join/${joinRoomId}`,
      {
        method: "POST",
      }
    );

    if (!response.ok) {
      setMessage("Failed to join room");
      return;
    }

    const data = await response.json();

    if (!data) {
      setMessage("Room not found or full");
      return;
    }

    setRoomId(data.roomId);
    setMessage("Joined room!");
  };

  return (
    <div style={styles.container}>

      <h1>Tic Tac Toe</h1>

      <button onClick={createRoom} style={styles.button}>
        Create Room
      </button>

      <div style={{ marginTop: "20px" }}>

        <input
          type="text"
          placeholder="Enter Room ID"
          value={joinRoomId}
          onChange={(e) => setJoinRoomId(e.target.value)}
          style={styles.input}
        />

        <button onClick={joinRoom} style={styles.button}>
          Join Room
        </button>

      </div>

      {roomId && (
        <h2>
          Room ID: {roomId}
        </h2>
      )}

      <p>{message}</p>

    </div>
  );
}

const styles = {

  container: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    marginTop: "100px",
    fontFamily: "Arial",
  },

  button: {
    padding: "10px 20px",
    fontSize: "16px",
    marginLeft: "10px",
    cursor: "pointer",
  },

  input: {
    padding: "10px",
    fontSize: "16px",
  },
};

export default App;