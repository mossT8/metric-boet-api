import { createStore } from "vuex";
import { auth } from "@/store/module/auth/auth.module";

const store = createStore({
  modules: {
    auth,
  },
});

export default store;
