import { useEffect, useState } from "react";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

function App() {

  const [stompClient, setStompClient] = useState(null);

  const [roomId, setRoomId] = useState("");

  const [joinRoomId, setJoinRoomId] = useState("");

  const [player, setPlayer] = useState("");

  const [game, setGame] = useState(null);

  useEffect(() => {

    const socket = new SockJS("http://localhost:8080/ws");

    const client = Stomp.over(socket);

    client.connect({}, () => {
      console.log("Connected");
    });

    setStompClient(client);

  }, []);

  const subscribeToRoom = (roomId, client) => {

    client.subscribe(`/topic/game/${roomId}`, (message) => {

      const updatedGame = JSON.parse(message.body);

      setGame(updatedGame);
    });
  };

  const createRoom = async () => {

    const response = await fetch(
      "http://localhost:8080/api/game/create",
      {
        method: "POST",
      }
    );

    const data = await response.json();

    setRoomId(data.roomId);

    setPlayer("X");

    setGame(data);

    subscribeToRoom(data.roomId, stompClient);
  };

  const joinRoom = async () => {

    const response = await fetch(
      `http://localhost:8080/api/game/join/${joinRoomId.toUpperCase()}`,
      {
        method: "POST",
      }
    );

    if (!response.ok){
      alert("Room not found or already full");
      return;
    }

    const data = await response.json();

    setRoomId(data.roomId);
    setPlayer("O");
    setGame(data);
    subscribeToRoom(data.roomId, stompClient);
  };

  const makeMove = (row, col) => {

    if (!game) {
      return;
    }

    stompClient.send(
      "/app/move",
      {},
      JSON.stringify({
        roomId,
        row,
        col,
        player,
      })
    );
  };

  return (
    <div style={styles.container}>

      <h1>Tic Tac Toe</h1>

      {!game && (
        <>
          <button onClick={createRoom} style={styles.button}>
            Create Room
          </button>

          <div style={{ marginTop: "20px" }}>

            <input
              placeholder="Room ID"
              value={joinRoomId}
              onChange={(e) => setJoinRoomId(e.target.value)}
              style={styles.input}
            />

            <button onClick={joinRoom} style={styles.button}>
              Join Room
            </button>

          </div>
        </>
      )}

      {game && (
        <>
          <h2>Room: {roomId}</h2>

          <h3>You are Player {player}</h3>

          <h3>Current Turn: {game.currentPlayer}</h3>

          {game.winner === "DRAW" ?(
            <h2>It's a Draw!</h2>
          ):(game.winner && <h2>Winner: {game.winner}</h2>)}

          <div style={styles.board}>

            {game.board.map((row, rowIndex) =>
              row.map((cell, colIndex) => (

                <button
                  key={`${rowIndex}-${colIndex}`}
                  style={styles.cell}
                  onClick={() => makeMove(rowIndex, colIndex)}
                >
                  {cell}
                </button>
              ))
            )}

          </div>
        </>
      )}

    </div>
  );
}

const styles = {

  container: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    marginTop: "50px",
    fontFamily: "Arial",
  },

  button: {
    padding: "10px 20px",
    fontSize: "16px",
    margin: "5px",
    cursor: "pointer",
  },

  input: {
    padding: "10px",
    fontSize: "16px",
  },

  board: {
    display: "grid",
    gridTemplateColumns: "repeat(3, 100px)",
    gap: "5px",
    marginTop: "20px",
  },

  cell: {
    width: "100px",
    height: "100px",
    fontSize: "36px",
    cursor: "pointer",
  },
};

export default App;