import { ref, watch, SetupContext } from "vue";

export default function useVModel(props: any, context: SetupContext): any {
  let innerValue = ref(props.value);
  watch(
    () => props.value,
    (newValue, oldValue) => {
      innerValue.value = newValue;
    }
  );
  watch(innerValue, (value, oldValue) => {
    context.emit("input", value);
    context.emit("change", value);
  });
  return {
    innerValue,
  };
}
