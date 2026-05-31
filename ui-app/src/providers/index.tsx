import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { RouterProvider } from "react-router-dom";
import { router } from "../routes/router";
import { muiTheme } from "../themes";
import { ThemeProvider } from "@mui/material/styles";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { useAlertStore } from "../store";
import AlertMessage from "../components/AlertMessage";

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false, // avoid refetch on tab switch
      refetchOnReconnect: false,
      refetchOnMount: false,
    },
  },
});

const AllProviders = () => {
  const { alertMessage } = useAlertStore();
  return (
    <QueryClientProvider client={queryClient}>
      <ThemeProvider theme={muiTheme}>
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <RouterProvider router={router} />
          {alertMessage && <AlertMessage />}
        </LocalizationProvider>
      </ThemeProvider>
    </QueryClientProvider>
  );
};

export default AllProviders;
