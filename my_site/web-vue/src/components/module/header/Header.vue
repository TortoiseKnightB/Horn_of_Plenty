<!--  header  -->
<template>
  <a-row class="header" justify="center">
    <a-col :span="24">
      <div>
        <!--    顶部菜单    -->
        <MenuTopGrid/>
      </div>
    </a-col>
  </a-row>
</template>

<script>
import MenuTopGrid from "@/components/menu/grid/MenuTopGrid";

export default {
  name: "Header",
  components: {
    MenuTopGrid
  }
}
</script>

<style scoped>

</style>
