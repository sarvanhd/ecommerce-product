import { useNavigate } from "react-router-dom";
import { useAuthStore } from "../../store";
import { useEffect } from "react";
import HomePage from "./HomePage";

const Home = () => {
  const { isValidUser } = useAuthStore();
  const navigate = useNavigate();

  useEffect(() => {
    if (!isValidUser()) {
      // Redirect to dashboard or another authenticated page
      navigate("/login"); // Adjust the path as needed
    }
  }, [isValidUser, navigate]);

  return (
    <>
      <HomePage />
    </>
  );
};

export default Home;
