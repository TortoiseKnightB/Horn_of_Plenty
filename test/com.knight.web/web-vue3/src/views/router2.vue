<template>
  <ul>
    <li v-for="r in routerList" v-bind:key="r.id">
      <router-link v-bind:to="{
          // 通过 name 寻找路由
          name: r.destination,
          // 向下一个路由页面传入的参数
          query:{
            id:r.id,
            message: r.text
          }
        }">{{ r.text }}
      </router-link>
    </li>
  </ul>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
import {onMounted, onUnmounted, reactive, toRefs} from 'vue'

export default {
  name: "router2",
  setup() {
    let data = reactive({
          routerList: [
            {
              id: '001',
              destination: 'r2_1',
              text: '2_1'
            },
            {
              id: '002',
              destination: 'r2_2',
              text: '2_2'
            }
          ]
        }
    )

    onMounted(() => {
      console.log("router2 mounted")
    })

    onUnmounted(() => {
      console.log("router2 unmounted")
    })

    return {
      // 将data对象里的对象依次摊开
      ...toRefs(data)
    }
  }
}
</script>

<style scoped>

</style>
