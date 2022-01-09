<template>
  <a-layout class="layout-demo">
    <a-layout-sider collapsible breakpoint="xl">
      <div class="logo"/>
      <a-menu
          :default-open-keys="['1']"
          :default-selected-keys="['0_3']"
          :style="{ width: '100%' }"
          @menu-item-click="onClickMenuItem"
      >
        <a-menu-item key="0_1" disabled>
          <IconHome></IconHome>
          Menu 1
        </a-menu-item>
        <a-menu-item key="0_2">
          <IconCalendar></IconCalendar>
          Menu 2
        </a-menu-item>
        <a-menu-item key="0_3">
          <IconCalendar></IconCalendar>
          Menu 3
        </a-menu-item>
        <a-sub-menu key="1">
          <template #title>
            <IconCalendar></IconCalendar>
            Navigation 1
          </template>
          <a-menu-item key="1_1">Menu 1</a-menu-item>
          <a-menu-item key="1_2">Menu 2</a-menu-item>
          <a-sub-menu key="2" title="Navigation 2">
            <a-menu-item key="2_1">Menu 1</a-menu-item>
            <a-menu-item key="2_2">Menu 2</a-menu-item>
          </a-sub-menu>
          <a-sub-menu key="3" title="Navigation 3">
            <a-menu-item key="3_1">Menu 1</a-menu-item>
            <a-menu-item key="3_2">Menu 2</a-menu-item>
            <a-menu-item key="3_3">Menu 3</a-menu-item>
          </a-sub-menu>
        </a-sub-menu>
        <a-sub-menu key="4">
          <template #title>
            <IconCalendar></IconCalendar>
            Navigation 4
          </template>
          <a-menu-item key="4_1">Menu 1</a-menu-item>
          <a-menu-item key="4_2">Menu 2</a-menu-item>
          <a-menu-item key="4_3">Menu 3</a-menu-item>
        </a-sub-menu>
      </a-menu>
      <!-- trigger -->
      <template #trigger="{ collapsed }">
        <IconCaretRight v-if="collapsed"></IconCaretRight>
        <IconCaretLeft v-else></IconCaretLeft>
      </template>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="padding-left: 0px;">
        <!--    顶部菜单    -->
        <MenuTopGrid/>
      </a-layout-header>
      <a-layout style="padding: 0 24px;">
        <a-layout-content>
          <!--    头像    -->
          <AvatarGrid/>
          <!-- 自我介绍卡片 -->
          <ProfileCardGrid/>
          <!-- 图片预览 -->
          <ImagePreviewGrid/>
          <!-- 分页器 -->
          <PaginationHomeGrid/>
        </a-layout-content>
        <a-layout-footer>
          <!-- 页脚卡片 -->
          <FooterCardGrid/>
        </a-layout-footer>
      </a-layout>
    </a-layout>
  </a-layout>
</template>


<script>
import {defineComponent} from 'vue';
import {Message} from '@arco-design/web-vue';
import {
  IconCaretRight,
  IconCaretLeft,
  IconHome,
  IconCalendar,
} from '@arco-design/web-vue/es/icon';
// 自定义
import MenuTopGrid from "@/components/menu/grid/MenuTopGrid";
import AvatarGrid from "@/components/avatar/grid/AvatarGrid";
import ProfileCardGrid from "@/components/card/grid/ProfileCardGrid";
import ImagePreviewGrid from "@/components/image/grid/ImagePreviewGrid";
import PaginationHomeGrid from "@/components/pagination/grid/PaginationHomeGrid";
import FooterCardGrid from "@/components/card/grid/FooterCardGrid";

export default defineComponent({
  components: {
    IconCaretRight,
    IconCaretLeft,
    IconHome,
    IconCalendar,
    MenuTopGrid,
    AvatarGrid,
    ProfileCardGrid,
    ImagePreviewGrid,
    PaginationHomeGrid,
    FooterCardGrid
  },
  methods: {
    onClickMenuItem(key) {
      Message.info({content: `You select ${key}`, showIcon: true});
    }
  }
});
</script>


<style scoped>
.layout-demo {
  /* 固定底部位置，使页面自适应显示屏窗口大小，并且不会被内容撑出 */
  height: 100%;
  position: fixed;
  bottom: 0;
  background: var(--color-fill-2);
  border: 1px solid var(--color-border);
}

.layout-demo :deep(.arco-layout-sider) .logo {
  height: 32px;
  margin: 12px 8px;
  background: rgba(255, 255, 255, 0.2);
}

.layout-demo :deep(.arco-layout-sider-light) .logo {
  background: var(--color-fill-2);
}

.layout-demo :deep(.arco-layout-header) {
  height: 64px;
  line-height: 64px;
  background: var(--color-bg-3);
}

.layout-demo :deep(.arco-layout-footer) {
  height: 48px;
  color: var(--color-text-2);
  font-weight: 400;
  font-size: 14px;
  line-height: 48px;
}

.layout-demo :deep(.arco-layout-content) {
  color: var(--color-text-2);
  font-weight: 400;
  font-size: 14px;
  background: var(--color-bg-3);
}

.layout-demo :deep(.arco-layout-footer) {
  display: flex;
  flex-direction: column;
  justify-content: center;
  color: var(--color-white);
  font-size: 16px;
  font-stretch: condensed;
  text-align: center;
  max-height: 40px;
}

.layout-demo :deep(.arco-layout-content) {
  /*display: flex;*/
  /*flex-direction: column;*/
  justify-content: center;
  color: var(--color-white);
  font-size: 16px;
  font-stretch: condensed;
  text-align: center;
}

/*!* 自定义添加样式 *!*/
/*.layout-demo :deep(.arco-layout-content) {*/
/*  margin-bottom: 15px;*/
/*}*/


</style>
