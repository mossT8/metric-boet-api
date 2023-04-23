export const dateFormatter = new Intl.DateTimeFormat("en-US", {
  year: "numeric",
  month: "long",
  day: "numeric",
});

export function formatDate(dateStr: string) {
  const date = new Date(dateStr);
  return dateFormatter.format(date);
}
