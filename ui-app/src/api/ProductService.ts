import axios from "axios";
import type { Product } from "../models/prodcuts";
const apiUrl = import.meta.env.VITE_PRODUCT_SERVICE_URL;

export const getAllProducts = async () => {
  try {
    const response = await axios.get<Product[]>(`${apiUrl}/products`);
    return response.data;
  } catch (error) {
    console.error("Error in getAllUsers:", error);
    throw error;
  }
};
