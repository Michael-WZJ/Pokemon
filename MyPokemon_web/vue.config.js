const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  configureWebpack: {
    devServer: {
      client: {
        // 报错时不会全屏显示
        // overlay: false
      }
    }
  }
})
