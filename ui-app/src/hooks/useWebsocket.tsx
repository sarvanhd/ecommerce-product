import { useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";

const useWebsocket = () => {
  const [client, setClient] = useState<Client | null>(null);
  const [connected, setConnected] = useState(false);
  useEffect(() => {
    // Create STOMP client
    const stompClient = new Client({
      //webSocketFactory: () => new SockJS("http://localhost:8084/ws"),
      brokerURL: "ws://localhost:8084/ws",
      reconnectDelay: 5000,
      debug: (str) => console.log(str),
    });
    /* const socket = new WebSocket("ws://localhost:8084/ws");
    const stompClient = Stomp.over(socket); */

    stompClient.onConnect = () => {
      console.log("✅ Connected");
      setConnected(true);
    };

    stompClient.onDisconnect = () => {
      console.log("❌ Disconnected");
      setConnected(false);
    };

    stompClient.activate();
    setClient(stompClient);

    return () => {
      stompClient.deactivate();
    };
  }, []);
  return { client, connected };
};

export default useWebsocket;
