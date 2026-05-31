import { useQuery } from "@tanstack/react-query";
import { getAllProducts } from "../api/ProductService";

export const useGetAllProducts = () =>
  useQuery({ queryKey: ["getAllProducts"], queryFn: getAllProducts });
