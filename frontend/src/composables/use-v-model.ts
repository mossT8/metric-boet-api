import { ref, watch } from 'vue';
import { SetupContext } from 'vue';


export default function useVModel(props: any, context: SetupContext): any {
    let innerValue = ref(props.value);
    watch(() => props.value, (newValue) => {
        innerValue.value = newValue;
    })
    watch(innerValue, (value) => {
        context.emit('input', value);
        context.emit('change', value);
    });
    return {
        innerValue,
    }
}
