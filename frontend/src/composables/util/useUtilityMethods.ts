import { SetupContext } from "vue";
import useVModel from "@/composables/util/v-model/useVModel";
import useAsyncWrapper from "@/composables/util/async/useAsyncWrapper";


export default function useUtiltyMethods(props: any, context: SetupContext) {
  return {
    ...useVModel(props, context),
    ...useAsyncWrapper(),
  };
}
