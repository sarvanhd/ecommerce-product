import { useCallback, useEffect, useRef, useState } from "react";
import useWebsocket from "../hooks/useWebsocket";
import SendIcon from "@mui/icons-material/Send";
import IconButton from "@mui/material/IconButton";
import OutlinedInput from "@mui/material/OutlinedInput";
import InputAdornment from "@mui/material/InputAdornment";
import RemoveIcon from "@mui/icons-material/Remove";
import Fab from "@mui/material/Fab";
import QuestionAnswerIcon from "@mui/icons-material/QuestionAnswer";
import Skeleton from "@mui/material/Skeleton";
import DeleteIcon from "@mui/icons-material/Delete";
import Tooltip from "@mui/material/Tooltip";

export default function ChatBot() {
  const [messages, setMessages] = useState<{ type: string; msg: string }[]>([]);
  const [isOpen, setIsOpen] = useState(false);
  const [showLoader, setShowLoader] = useState(false);
  const [input, setInput] = useState("");
  const chatContainerRef = useRef<HTMLDivElement>(null);
  const { client, connected } = useWebsocket();
  const [conversationId] = useState<string | null>(crypto.randomUUID());

  // Scroll to bottom on new message
  useEffect(() => {
    if (chatContainerRef.current) {
      chatContainerRef.current.scrollTop =
        chatContainerRef.current.scrollHeight;
    }
  }, [messages]);

  useEffect(() => {
    if (!client || !connected) return;

    const subscription = client.subscribe("/chat/messages", (msg) => {
      if (msg.body) {
        const newMsg = JSON.parse(msg.body);
        console.log("Received:", newMsg);
        setMessages((prev) => [...prev, newMsg]);
        setShowLoader(false);
      }
    });

    return () => {
      subscription.unsubscribe();
    };
  }, [client, connected]);

  const sendMessage = useCallback(() => {
    if (client && connected && input.trim() !== "") {
      /* const msgs = [
        ...messages.filter((m) => m.type === "you").map((m) => m.msg),
        input.trim(),
      ].join(" "); */
      const req = { type: "you", msg: input.trim(), conversationId };
      client.publish({
        destination: "/app/user",
        body: JSON.stringify(req),
      });
      req.msg = input.trim();
      setInput("");
      setMessages((prev) => [...prev, req]);
      setShowLoader(true);
    }
  }, [client, connected, input, conversationId]);

  return (
    <div className="absolute bottom-4 right-2">
      {!isOpen && (
        <Fab
          color="primary"
          aria-label="add"
          onClick={() => setIsOpen(!isOpen)}
        >
          <QuestionAnswerIcon />
        </Fab>
      )}
      {isOpen && (
        <div
          className="border-blue-500 border-1 shadow-lg bg-white w-full min-w-[450px] max-w-[450px]"
          style={{ borderRadius: "12px" }}
        >
          <div
            className="flex justify-between items-center cursor-pointer p-2 bg-blue-500 text-white"
            style={{ borderRadius: "11px 11px 0 0" }}
            onClick={() => (!isOpen ? setIsOpen(!isOpen) : null)}
          >
            <h4 className="text-lg">Chat</h4>
            {isOpen && (
              <div className="flex gap-1">
                <Tooltip title="Clear Chat">
                  <IconButton
                    onClick={() => setMessages([])}
                    sx={{ color: "white" }}
                  >
                    <DeleteIcon />
                  </IconButton>
                </Tooltip>
                <Tooltip title="Minimize">
                  <IconButton
                    onClick={() => setIsOpen(!isOpen)}
                    sx={{ color: "white" }}
                  >
                    <RemoveIcon />
                  </IconButton>
                </Tooltip>
              </div>
            )}
          </div>

          <div className="pb-2 max-w-lg mx-auto min-w-[300px]">
            <div
              className="p-3 h-75 overflow-y-auto mb-2 bg-gray-50"
              ref={chatContainerRef}
            >
              {messages.map((msg, idx) => (
                <div
                  key={idx}
                  className={`mb-1 flex flex-col ${msg.type === "you" ? "text-right" : "text-left"}`}
                >
                  <p className="font-bold capitalize text-[14px]">{msg.type}</p>
                  <p className="text-gray-600 font-normal text-[14px]">
                    {msg.msg}
                  </p>
                </div>
              ))}
              {showLoader && (
                <div className="mb-1 flex flex-col gap-2">
                  <Skeleton variant="rounded" width={80} height={20} />
                  <Skeleton variant="rounded" width={300} height={40} />
                </div>
              )}
              {messages.length === 0 && !showLoader && (
                <div className="flex flex-col h-full w-full justify-center items-center p-4">
                  <span className="text-gray-600">Start the conversation!</span>
                </div>
              )}
            </div>

            <div className="flex space-x-2 p-1">
              <OutlinedInput
                type="text"
                value={input}
                onChange={(e) => setInput(e.target.value)}
                placeholder="Type a message..."
                sx={{ height: "40px", paddingRight: "0px", flexGrow: 1 }}
                onKeyDown={(e) => (e.key === "Enter" ? sendMessage() : null)}
                endAdornment={
                  <InputAdornment position="end">
                    <IconButton
                      onClick={sendMessage}
                      disabled={!connected}
                      sx={{ color: "oklch(62.3% 0.214 259.815)" }}
                    >
                      <SendIcon />
                    </IconButton>
                  </InputAdornment>
                }
              />
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
