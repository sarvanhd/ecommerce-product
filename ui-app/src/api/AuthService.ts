import axios from "axios";
import type { AuthResponse } from "../models/auth";
import { useAuthStore } from "../store";

const apiUrl = import.meta.env.VITE_AUTH_SERVICE_URL;

export const login = async (payload: {
  emailAddress: string;
  password: string;
}) => {
  try {
    const response = await axios.post<AuthResponse>(
      `${apiUrl}/auth/login`,
      payload
    );
    return response.data;
  } catch (error) {
    console.error("Error in auth:", error);
    throw error;
  }
};

export const logout = () => {
  // Implement logout logic if needed
  useAuthStore.getState().clearToken();
};
