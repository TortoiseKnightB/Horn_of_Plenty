<template>
  <div class="menu-demo">
    <a-menu mode="pop" showCollapseButton default-collapsed="true">

      <a-menu-item key="1">
        <template #icon>
          <icon-apps></icon-apps>
        </template>
        Navigation 1
      </a-menu-item>

      <a-sub-menu key="2">
        <template #icon>
          <icon-bug></icon-bug>
        </template>
        <template #title>Navigation 2</template>
        <a-menu-item key="2_0">Beijing</a-menu-item>
        <a-menu-item key="2_1">Shanghai</a-menu-item>
        <a-menu-item key="2_2">Guangzhou</a-menu-item>
      </a-sub-menu>

    </a-menu>
  </div>
</template>

<script>
import {IconApps, IconBug, IconBulb} from "@arco-design/web-vue/es/icon";

export default {
  name: "MenuSlide",
  components: {
    IconApps,
    IconBug,
    IconBulb,
  },
}
</script>

<style scoped>
.menu-demo {
  width: 100%;
  height: 600px;
  padding: 40px;
  box-sizing: border-box;
  background-color: var(--color-neutral-2);
}

.menu-demo .arco-menu {
  width: 200px;
  height: 100%;
  box-shadow: 0 0 1px rgba(0, 0, 0, 0.3);
}

.menu-demo .arco-menu :deep(.arco-menu-collapse-button) {
  width: 32px;
  height: 32px;
  border-radius: 50%;
}

.menu-demo .arco-menu:not(.arco-menu-collapsed) :deep(.arco-menu-collapse-button) {
  right: 0;
  bottom: 8px;
  transform: translateX(50%);
}

.menu-demo .arco-menu:not(.arco-menu-collapsed)::before {
  content: '';
  position: absolute;
  right: 0;
  bottom: 0;
  width: 48px;
  height: 48px;
  background-color: inherit;
  border-radius: 50%;
  box-shadow: -4px 0 2px var(--color-bg-2), 0 0 1px rgba(0, 0, 0, 0.3);
  transform: translateX(50%);
}

.menu-demo .arco-menu.arco-menu-collapsed {
  width: 48px;
  height: auto;
  padding-top: 24px;
  padding-bottom: 138px;
  border-radius: 22px;
}

.menu-demo .arco-menu.arco-menu-collapsed :deep(.arco-menu-collapse-button) {
  right: 8px;
  bottom: 8px;
}

/* 侧边菜单至于图层最顶层 */
.arco-menu-pop {
  z-index: 9999;
}
</style>
