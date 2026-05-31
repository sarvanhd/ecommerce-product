import Alert from "@mui/material/Alert";
import { useAlertStore } from "../store";
import { useEffect } from "react";

const AlertMessage = () => {
  const { alertMessage, clearAlertMessage } = useAlertStore();
  useEffect(() => {
    if (alertMessage) {
      const timer = setTimeout(() => {
        clearAlertMessage();
      }, 3000); // Clear the alert after 3 seconds
      return () => clearTimeout(timer); // Cleanup the timer on unmount or when alertMessage changes
    }
  }, [alertMessage, clearAlertMessage]);
  return (
    <div className="fixed top-20 left-1/2 transform -translate-x-1/2 z-50 w-11/12 md:w-1/3">
      <Alert severity={alertMessage?.type}>{alertMessage?.message}</Alert>
    </div>
  );
};

export default AlertMessage;
