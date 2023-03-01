import { Ref, ref } from 'vue';
import { showError } from '@/core/dialog';

// export interface AsyncWrapperComposition extends ErrorComposition, LoadingComposition {
//     doAsync: (action: () => any, key?: string) => Promise<any>;
//     error
// }
export interface AsyncWrapperComposition{
    doAsync: (action: () => any, self?: any, loadBackground?: boolean, onError?: () => void) => Promise<any>;
    error: Ref<string | null>;
    loading: Ref<boolean>;
}

export default function useAsyncWrapper(): AsyncWrapperComposition {

    // const error = useError();
    // const loader = useLoading();

    let error: Ref<string | null> = ref(null)
    let loading: Ref<boolean> = ref(false);

    const doAsync = async (action: () => any, self: any = null, loadBackground: boolean = false, onError?: () => void): Promise<any> => {
        // error.clearError(key);
        // loader.startLoading(key);
        loading.value = !loadBackground;
        try {
            return await action();
        } catch (err) {
            const message: string = (err as any).hasOwnProperty('message') ? (err as any).message : err;
            // error.setError(message, key);
            error.value = message.trim();
            if (self) {
                showError(self, error.value);
            }
            error.value = message;
            if (onError) {
                onError();
            }
        } finally {
            // loader.stopLoading(key);
            loading.value = false;
        }
    }

    return {
        // ...error,
        // ...loader,
        doAsync,
        error,
        loading,
    }
}
