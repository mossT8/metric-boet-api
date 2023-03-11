import * as yup from "yup";

export const deviceShema = yup.object().shape({
    name: yup
    .string()
    .required("Name is required!")
    .min(3, "Must be at least 3 characters!")
    .max(50, "Must be maximum 50 characters!"),
    type: yup
    .string()
    .required("Type is required!")
    .min(3, "Must be at least 3 characters!")
    .max(50, "Must be maximum 50 characters!"),
    location: yup
    .string()
    .required("Location is required!")
    .min(3, "Must be at least 3 characters!")
    .max(225, "Must be maximum 15 characters!"),
    status: yup
    .boolean()
    .required("status is required!"),
    token: yup
    .string()
    .required("Token is required!")
    .max(225, "Must be maximum 225 characters!"),
});


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
    .max(50, "Must be maximum 50 characters!"),
    username: yup
    .string()
    .required("Username is required!")
    .min(3, "Must be at least 3 characters!")
    .max(50, "Must be maximum 50 characters!"),
    email: yup
    .string()
    .email()
    .required("Email is required!")
    .min(3, "Must be at least 3 characters!")
    .max(50, "Must be maximum 50 characters!"),    
    password: yup
    .string()
    .required("Password is required!")
    .min(3, "Must be at least 3 characters!")
    .max(50, "Must be maximum 50 characters!"),
});