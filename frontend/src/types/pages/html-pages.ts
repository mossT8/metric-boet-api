import * as yup from "yup";

export const htmpPageSchema = yup.object().shape({
  html: yup.string().required("Html is required!"),
  url: yup
    .string()
    .required("Type is required!")
    .min(3, "Must be at least 3 characters!")
    .max(20, "Must be maximum 20 characters!"),
  visibleForUsers: yup
    .boolean()
    .required("Visible for users flag is required!"),
  visibleForModerators: yup
    .boolean()
    .required("Visible for admins flag is required!"),
  visibleOnNav: yup
    .boolean()
    .required("Visible on nav flag is required!"),
});

export interface HtmlPage extends yup.InferType<typeof htmpPageSchema> {}

export interface HtmlPageMapped {
  html: string;
  url: string;
  createdAt: string;
  updatedAt: string;
  isVisibleForUsers: boolean;
  isVisibleForModerators: boolean;
  isVisibleOnNav: boolean;
}
