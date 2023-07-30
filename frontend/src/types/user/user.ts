import * as yup from "yup";

export const userSchema = yup.object().shape({
  firstName: yup
    .string()
    .required("First Name is required!")
    .min(3, "Must be at least 3 characters!")
    .max(50, "Must be maximum 50 characters!"),
  lastName: yup
    .string()
    .required("Last Name is required!")
    .min(3, "Must be at least 3 characters!")
    .max(50, "Must be maximum 50 characters!"),
  phone: yup
    .string()
    .required("Phone is required!")
    .min(3, "Must be at least 3 characters!")
    .max(15, "Must be maximum 15 characters!"),
  username: yup
    .string()
    .required("Username is required!")
    .min(3, "Must be at least 3 characters!")
    .max(20, "Must be maximum 20 characters!"),
  email: yup
    .string()
    .required("Email is required!")
    .email("Email is invalid!")
    .max(50, "Must be maximum 50 characters!"),
  password: yup
    .string()
    .required("Password is required!")
    .min(6, "Must be at least 6 characters!")
    .max(40, "Must be maximum 40 characters!"),
});

export interface User extends yup.InferType<typeof userSchema> {
 
}

export interface UserMapped {
  firstName: string,
  lastName: string,
  email: string,
  phone: string,
  createdAt: string,
  updatedAt: string,
}