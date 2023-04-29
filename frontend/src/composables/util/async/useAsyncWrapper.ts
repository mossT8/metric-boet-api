import { reactive } from "vue";

export default function useAsyncWrapper() {
  const state = reactive({
    isLoading: false,
    message: "",
    error: "",
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
      return result;
    } catch (error) {
      state.isLoading = false;
      state.error = errorMessage || "An error occurred!";
      throw error;
    }
  }

  return {
    isLoading: () => state.isLoading,
    message: () => state.message,
    error: () => state.error,
    callAsync,
  };
}
