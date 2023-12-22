import { reactive } from "vue";

export function useAsyncWrapper() {
  const state = reactive({
    isLoading: false,
    message: "",
    error: "",
    successful: false,
  });

  async function callAsync(
    asyncFn: () => Promise<void>,
    successMessage?: string,
    errorMessage?: string
  ) {
    state.isLoading = true;
    state.message = "";
    state.error = "";

    try {
      const result = await asyncFn();
      state.isLoading = false;
      state.message = successMessage || "Success!";
      state.successful = true; // Set the successful property to true on success
      return result;
    } catch (error) {
      state.isLoading = false;
      state.error = errorMessage || "An error occurred!";
      state.successful = false; // Set the successful property to false on error
      throw error;
    }
  }

  return {
    isLoading: () => state.isLoading,
    message: () => state.message,
    error: () => state.error,
    successful: () => state.successful, // Add the successful property to the returned object
    callAsync,
  };
}
