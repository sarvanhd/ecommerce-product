import { createTheme } from "@mui/material/styles";

export const muiTheme = createTheme({
  components: {
    /* MuiOutlinedInput: {
      styleOverrides: {
        root: {
          height: "40px",
          "& .MuiOutlinedInput-input": {
            padding: "4px 4px", // adjust vertical padding
          },
        },
        input: {
          height: "40px",
        },
      },
    }, */
    MuiButton: {
      styleOverrides: {
        root: {
          textTransform: "none", // Prevent uppercase transformation
        },
      },
    },
  },
});
