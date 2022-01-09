<!-- 主页分页器 -->
<template>
  <!--          biaoji-->
  <a-pagination class="pagination-home" :total="200" @change="changePage" v-model:current="currentPage"/>
</template>

<script>
import {reactive, toRefs} from "vue";

export default {
  name: "PaginationHome",
  setup() {
    const data = reactive({
      // 当前页面，用来接收 arco-design 组件内的 current 属性
      currentPage: 0
    })

    const methods = reactive({
      changePage: () => {
        console.log("@@@", data.currentPage)
      }
    })

    return {
      ...toRefs(data),
      ...toRefs(methods)
    }
  }
}
</script>

<style scoped>
.pagination-home {
  justify-content: center;
}
</style>