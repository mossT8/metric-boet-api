import { Ref, ref } from 'vue';
import { showError } from '@/core/dialog';

export interface AsyncWrapperComposition {
  doAsync: (
    action: () => any,
    self?: any,
    loadBackground?: boolean,
    onError?: () => void
  ) => Promise<any>;
  error: Ref<string | null>;
  loading: Ref<boolean>;
}

export default function useAsyncWrapper(): AsyncWrapperComposition {
  let error: Ref<string | null> = ref(null);
  let loading: Ref<boolean> = ref(false);

  const doAsync = async (
    action: () => any,
    self: any = null,
    loadBackground: boolean = false,
    onError?: () => void
  ): Promise<any> => {
    loading.value = !loadBackground;
    try {
      return await action();
    } catch (err) {
      const error: any = err || {};
      const message: string =
        error.has('message') ? error.message : err;
      error.value = message.trim();
      if (self) {
        showError(self, error.value);
      }
      error.value = message;
      if (onError) {
        onError();
      }
    } finally {
      loading.value = false;
    }
  };

  return {
    doAsync,
    error,
    loading,
  };
}
