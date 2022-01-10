<!--  footer  -->
<template>
  <a-row class="footer" justify="center">
    <a-col :span="24">
      <div>
        <!-- 页脚卡片 -->
        <FooterCardGrid/>
      </div>
    </a-col>
  </a-row>
</template>

<script>
import FooterCardGrid from "@/components/card/grid/FooterCardGrid";

export default {
  name: "Footer",
  components: {
    FooterCardGrid,
  }
}
</script>

<style scoped>

</style>
