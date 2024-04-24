<template>
  <div class="poke-setting grid">
    <prop-color></prop-color>
  </div>
</template>

<script>
import PropColor from "@/views/setting/components/PropColor.vue";

export default {
  name: "Setting",
  components: { PropColor }
}
</script>

<style scoped lang="scss">
.poke-setting {
  margin: 16px 16px 0 16px;
}

// grid布局
.grid {
  display: grid;
  grid-column-gap: 16px;
  grid-template-columns: repeat(3, 1fr);

  .span2 {
    grid-column-start: span 2;
  }

  .span3 {
    grid-column-start: span 3;
  }

  .span4 {
    grid-column-start: span 4;
  }
}
</style>