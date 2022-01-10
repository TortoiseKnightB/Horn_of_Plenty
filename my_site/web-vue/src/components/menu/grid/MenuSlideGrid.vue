<template>
  <div>
    <a-row class="grid-demo" justify="space-around">
      <a-col :span="16">
        <div>
          <MenuSlide/>
        </div>
      </a-col>
    </a-row>
  </div>
</template>

<script>
import MenuSlide from "@/components/menu/MenuSlide";

export default {
  name: "MenuSlideGrid",
  components: {
    MenuSlide
  }
}
</script>

<style scoped>
.grid-demo {
  background-color: var(--color-fill-2);
  margin-bottom: 40px;
}

.grid-demo:last-child {
  margin-bottom: 0px;
}

.grid-demo .arco-col {
  height: 100%;
  line-height: 48px;
  color: var(--color-white);
  text-align: center;
}

.grid-demo .arco-col:nth-child(2n) {
  background-color: rgba(var(--arcoblue-6), 0.9);
}

.grid-demo .arco-col:nth-child(2n + 1) {
  background-color: var(--color-primary-light-4);
}
</style>
