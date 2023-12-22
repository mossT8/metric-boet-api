import { Directive } from "vue";

const clickOutsideDirective: Directive<HTMLElement> = {
  beforeMount(el: any, binding: any) {
    const handler = (e: any) => {
      if (!el.contains(e.target) && el !== e.target) {
        binding.value();
      }
    };

    document.addEventListener("click", handler);
    el._clickOutsideHandler = handler;
  },
  unmounted(el: any) {
    document.removeEventListener("click", el._clickOutsideHandler);
  },
};

export default clickOutsideDirective;
