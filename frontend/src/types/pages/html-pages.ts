import * as yup from "yup";

export const htmpPageSchema = yup.object().shape({
  html: yup.string().required("Html is required!"),
  url: yup
    .string()
    .required("Type is required!")
    .min(3, "Must be at least 3 characters!")
    .max(20, "Must be maximum 20 characters!"),
});

export interface HtmlPage extends yup.InferType<typeof htmpPageSchema> {}

export interface HtmlPageMapped {
  html: string;
  url: string;
  createdAt: Date;
  updatedAt: Date;
}
