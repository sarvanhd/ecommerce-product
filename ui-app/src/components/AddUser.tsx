import Button from "@mui/material/Button";
import Divider from "@mui/material/Divider";
import TextField from "@mui/material/TextField";
import { useFormik } from "formik";
import * as Yup from "yup";
import dayjs, { Dayjs } from "dayjs";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { useAddUser, useUpdateUser } from "../queries/UserQueries";
import { useNavigate } from "react-router-dom";
import type { User } from "../models/user";
import { useState } from "react";

const AddUser = ({
  isSignup,
  closePopup,
  userData,
  isEdit,
}: {
  isSignup?: boolean;
  closePopup?: () => void;
  userData?: Partial<User> | null;
  isEdit?: boolean;
}) => {
  const addUserMutation = useAddUser();
  const updateUserMutation = useUpdateUser();
  const navigate = useNavigate();
  const [error, setError] = useState<string | null>(null);
  const {
    handleSubmit,
    errors,
    touched,
    values,
    handleBlur,
    handleChange,
    isSubmitting,
    setFieldValue,
  } = useFormik({
    initialValues: {
      email: userData?.email || "",
      password: "",
      firstName: userData?.firstName || "",
      lastName: userData?.lastName || "",
      dob: userData?.dob
        ? dayjs(userData.dob).format("YYYY-MM-DD")
        : dayjs().format("YYYY-MM-DD"),
      address: {
        street: userData?.address?.street || "",
        city: userData?.address?.city || "",
        state: userData?.address?.state || "",
        postalCode: userData?.address?.postalCode || "",
        country: userData?.address?.country || "",
      },
    },
    validationSchema,
    onSubmit: async (values) => {
      console.log(values);
      setError(null);
      if (isEdit && userData?.id) {
        updateUserMutation
          .mutateAsync({ ...userData, id: userData.id, ...values })
          .then((response) => {
            console.log("User updated:", response);
            closePopup?.();
          })
          .catch((err) => {
            setError(err.message || "Failed to update user");
          });
      } else {
        addUserMutation.mutateAsync(values).then((response) => {
          console.log("User added:", response);
          if (isSignup) navigate("/login");
          else closePopup?.();
        });
      }
    },
  });
  return (
    <form onSubmit={handleSubmit} className="flex flex-col gap-4 mt-4">
      <div className="grid grid-cols-2 md:grid-cols-2 gap-4">
        <TextField
          name="email"
          label="Email"
          size="small"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.email}
          error={touched.email && Boolean(errors.email)}
          helperText={touched.email && errors.email}
          type="email"
        />
        <TextField
          name="password"
          label="Password"
          size="small"
          type="password"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.password}
          error={touched.password && Boolean(errors.password)}
          helperText={touched.password && errors.password}
        />
        <TextField
          name="firstName"
          label="First Name"
          size="small"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.firstName}
          error={touched.firstName && Boolean(errors.firstName)}
          helperText={touched.firstName && errors.firstName}
        />
        <TextField
          name="lastName"
          label="Last Name"
          size="small"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.lastName}
          error={touched.lastName && Boolean(errors.lastName)}
          helperText={touched.lastName && errors.lastName}
        />
        <DatePicker
          label="Date of Birth"
          value={dayjs(values.dob)}
          onChange={(newValue) =>
            setFieldValue("dob", (newValue as Dayjs).format("YYYY-MM-DD"))
          }
          slotProps={{ textField: { size: "small" } }}
        />
      </div>
      <Divider />
      <div className="grid grid-cols-2 md:grid-cols-2 gap-4">
        <TextField
          name="address.street"
          label="Street"
          size="small"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.address.street}
          error={touched.address?.street && Boolean(errors.address?.street)}
          helperText={touched.address?.street && errors.address?.street}
        />
        <TextField
          name="address.city"
          label="City"
          size="small"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.address.city}
          error={touched.address?.city && Boolean(errors.address?.city)}
          helperText={touched.address?.city && errors.address?.city}
        />
        <TextField
          name="address.state"
          label="State"
          size="small"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.address.state}
          error={touched.address?.state && Boolean(errors.address?.state)}
          helperText={touched.address?.state && errors.address?.state}
        />
        <TextField
          name="address.country"
          label="Country"
          size="small"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.address.country}
          error={touched.address?.country && Boolean(errors.address?.country)}
          helperText={touched.address?.country && errors.address?.country}
        />
        <TextField
          name="address.postalCode"
          label="Postal Code"
          size="small"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.address.postalCode}
          error={
            touched.address?.postalCode && Boolean(errors.address?.postalCode)
          }
          helperText={touched.address?.postalCode && errors.address?.postalCode}
        />
      </div>
      <Button
        type="submit"
        variant="contained"
        loading={isSubmitting}
        loadingIndicator="Login…"
        disabled={isSubmitting}
      >
        Submit
      </Button>
      {error && <div className="text-red-500">{error}</div>}
    </form>
  );
};
export default AddUser;

const validationSchema = Yup.object({
  email: Yup.string()
    .email("Invalid email format")
    .required("Email is required"),
  password: Yup.string()
    .min(6, "Password must be at least 6 characters")
    .required("Password is required"),
  firstName: Yup.string().required("Firstname is required"),
  lastName: Yup.string().required("Lastname is required"),
  dob: Yup.date().required("Date of Birth is required"),
  address: Yup.object({
    //street: Yup.string().required("Street is required"),
  }),
});
