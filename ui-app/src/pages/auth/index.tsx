import { Outlet } from "react-router";
import CardContent from "@mui/material/CardContent";
import { useAuthStore } from "../../store";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Paper from "@mui/material/Paper";

const AuthLayout = () => {
  const { isValidUser } = useAuthStore();
  const navigate = useNavigate();

  useEffect(() => {
    if (isValidUser()) {
      // Redirect to dashboard or another authenticated page
      navigate("/"); // Adjust the path as needed
    }
  }, [isValidUser, navigate]);
  return (
    <div className="w-full m-auto flex justify-center items-center h-screen bg-gray-400">
      <Paper sx={{ minWidth: 275 }} elevation={7}>
        <CardContent>
          <Outlet />
        </CardContent>
      </Paper>
    </div>
  );
};

export default AuthLayout;
