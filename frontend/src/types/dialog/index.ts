export function showError(self: any, error: string, title: string = 'An error occurred'): void {
  self.$bvToast.toast(error, {
    title,
    variant: 'danger',
  });
}

export function showMessage(self: any, message: string, title: string): void {
  self.$bvModal.msgBoxOk(message, {
    title,
    size: 'lg',
    buttonSize: 'sm',
    okVariant: 'primary',
    titleClass: 'p-2 border-bottom-0',
    footerClass: 'p-2 border-top-0',
    centered: true,
  });
}