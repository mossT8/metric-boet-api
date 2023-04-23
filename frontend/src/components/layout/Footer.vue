<template>
  <footer class="footer">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-sm-12 col-md-6 text-center">
          <div class="copyright">
            © {{ year }} made with <i class="custom-icons icon-heart-2"></i> by
            <a href="https://www.linkedin.com/in/tannar-moss-558a651b0/" target="_blank" rel="noopener">Tananr Moss</a>
            for Demo of Skills.
          </div>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  data() {
    return {
      year: new Date().getFullYear()
    }
  }
};
</script>