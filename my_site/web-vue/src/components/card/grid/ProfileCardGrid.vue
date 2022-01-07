<!-- 自我介绍卡片栅栏 -->
<template>
  <div>
    <a-row class="grid-demo" justify="center" :wrap="true">
      <a-col :span="16">
        <div>
          <ProfileCard/>
        </div>
      </a-col>
    </a-row>
  </div>
</template>

<script>
import ProfileCard from "@/components/card/ProfileCard";

export default {
  name: "ProfileCardGrid",
  components: {
    ProfileCard
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
