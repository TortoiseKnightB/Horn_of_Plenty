<!--  content  -->
<template>
  <a-row class="content" justify="center">
    <!--   侧边导航  -->
    <a-col :span="4">
      <MenuSlideGrid/>
    </a-col>
    <!--   内容展示  -->
    <a-col :span="20">
      <!--    头像    -->
      <AvatarGrid/>
      <!-- 自我介绍卡片 -->
      <ProfileCardGrid/>
      <!-- 图片预览 -->
      <ImagePreviewGrid/>
      <!-- 分页器 -->
      <PaginationHomeGrid/>
    </a-col>
  </a-row>
</template>


<script>
import MenuSlideGrid from "@/components/menu/grid/MenuSlideGrid";
import AvatarGrid from "@/components/avatar/grid/AvatarGrid";
import ProfileCardGrid from "@/components/card/grid/ProfileCardGrid";
import ImagePreviewGrid from "@/components/image/grid/ImagePreviewGrid";
import PaginationHomeGrid from "@/components/pagination/grid/PaginationHomeGrid";

export default {
  name: "Content",
  components: {
    MenuSlideGrid,
    AvatarGrid,
    ProfileCardGrid,
    ImagePreviewGrid,
    PaginationHomeGrid,
  }
}
</script>

<style scoped>

</style>
