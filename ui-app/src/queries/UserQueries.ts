import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { addUser, getAllUsers, updateUser } from "../api/UserService";
import { QUERY_KEYS } from "./querykeys";
import { useAlertStore } from "../store";

// Queries
export const useGetUsers = () =>
  useQuery({ queryKey: [QUERY_KEYS.GET_ALL_USERS], queryFn: getAllUsers });

// Mutations
export const useAddUser = () => {
  const queryClient = useQueryClient();
  const { setAlertMessage } = useAlertStore();
  return useMutation({
    mutationFn: addUser,
    onSuccess: () => {
      // Invalidate and refetch
      queryClient.invalidateQueries({ queryKey: [QUERY_KEYS.GET_ALL_USERS] });
      setAlertMessage({
        message: "User created successfully",
        type: "success",
      });
    },
  });
};
export const useUpdateUser = () => {
  const queryClient = useQueryClient();
  const { setAlertMessage } = useAlertStore();
  return useMutation({
    mutationFn: updateUser,
    onSuccess: () => {
      // Invalidate and refetch
      queryClient.invalidateQueries({ queryKey: [QUERY_KEYS.GET_ALL_USERS] });
      setAlertMessage({
        message: "User updated successfully",
        type: "success",
      });
    },
  });
};
