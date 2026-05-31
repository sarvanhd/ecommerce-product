import Typography from "@mui/material/Typography";
import AddUser from "../../../components/AddUser";
import { Link } from "react-router";

const Signup = () => {
  return (
    <>
      <div className="text-xl">Signup</div>
      <AddUser isSignup />
      <Typography textAlign={"center"} className="!mt-3 !text-sm">
        Already have an account?
        <Link className="ml-1 text-[#1976d2]" to={"/login"}>
          Login
        </Link>
      </Typography>
    </>
  );
};

export default Signup;
