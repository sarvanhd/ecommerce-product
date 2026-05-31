import { create } from "zustand";
import { persist } from "zustand/middleware";

interface AuthState {
  token: string | null;
  setToken: (token: string) => void;
  clearToken: () => void;
  isValidUser: () => boolean;
}

export const useAuthStore = create<AuthState>()(
  persist(
    (set, get) => ({
      token: null,
      setToken: (token) => set({ token }),
      clearToken: () => set({ token: null }),
      isValidUser: () => {
        const token = get().token;
        return token !== null && token !== undefined && token !== "";
      },
    }),
    {
      name: "auth-storage", // key in localStorage
    }
  )
);

type AlertMessage = {
  message: string;
  type: "success" | "error" | "info" | "warning";
};

interface AlertState {
  alertMessage: AlertMessage | null;
  setAlertMessage: (message: AlertMessage) => void;
  clearAlertMessage: () => void;
}

export const useAlertStore = create<AlertState>()(
  persist(
    (set) => ({
      alertMessage: null,
      setAlertMessage: (message) => set({ alertMessage: message }),
      clearAlertMessage: () => set({ alertMessage: null }),
    }),
    {
      name: "alert-storage",
    }
  )
);
