import axios from "axios";
import type { User } from "../models/user";
const apiUrl = import.meta.env.VITE_USER_SERVICE_URL;

export const getAllUsers = async () => {
  try {
    const response = await axios.get<User[]>(`${apiUrl}/users`);
    return response.data;
  } catch (error) {
    console.error("Error in getAllUsers:", error);
    throw error;
  }
};

export const addUser = async (user: Partial<User>) => {
  try {
    const response = await axios.post<User>(`${apiUrl}/users`, user);
    return response.data;
  } catch (error) {
    console.error("Error in AddUser:", error);
    throw error;
  }
};

export const getUserById = async (id: string) => {
  try {
    const response = await axios.get<User>(`${apiUrl}/users/${id}`);
    return response.data;
  } catch (error) {
    console.error("Error in getUserById:", error);
    throw error;
  }
};

export const updateUser = async (user: User) => {
  try {
    const response = await axios.put<User>(`${apiUrl}/users/${user.id}`, user);
    return response.data;
  } catch (error) {
    console.error("Error in updateUser:", error);
    throw error;
  }
};

export const deleteUser = async (id: number) => {
  try {
    await axios.delete(`${apiUrl}/users/${id}`);
  } catch (error) {
    console.error("Error in deleteUser:", error);
    throw error;
  }
};
