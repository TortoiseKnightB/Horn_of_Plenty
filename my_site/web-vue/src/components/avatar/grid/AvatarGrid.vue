<!-- 头像栅栏 -->
<template>
  <div>
    <a-row class="grid-demo" justify="center" :wrap="true">
      <a-col :span="24">
        <div>
          <Avatar/>
        </div>
      </a-col>
    </a-row>
  </div>
</template>

<script>
import Avatar from "@/components/avatar/Avatar";

export default {
  name: "AvatarGrid",
  components: {
    Avatar
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
