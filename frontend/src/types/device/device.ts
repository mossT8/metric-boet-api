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

export interface Device extends yup.InferType<typeof deviceShema> {
 
}

export interface DeviceMapped {
    name: string,
    type: string,
    location: string,
    status: boolean,
    token: string,
    createdAt: Date,
    updatedAt: Date,
}
