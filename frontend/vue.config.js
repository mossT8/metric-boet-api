module.exports = {
  // proxy all webpack dev-server requests starting with /api
  // to our Spring Boot backend (localhost:8080) using http-proxy-middleware
  // see https://cli.vuejs.org/config/#devserver-proxy
  devServer: {
    port: 8081,
    proxy: {
      "/api": {
        target: "http://backend:8080",
        changeOrigin: true,
      },
      "/fileUpload": {
        target: "http://backend:8080",
        changeOrigin: true,
      },
    },
  },
  // Change build paths to make them Maven compatible
  // see https://cli.vuejs.org/config/
  outputDir: "target/dist",
  assetsDir: "static",
};
