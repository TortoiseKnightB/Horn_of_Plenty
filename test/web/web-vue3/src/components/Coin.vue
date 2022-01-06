<template>
  <h3>比特币信息</h3>
  <section v-if="errored">
    <p>We're sorry, we're not able to retrieve this information at the moment, please try back later</p>
  </section>

  <section v-else>
    <div v-if="loading">Loading...</div>
    <div v-for="(item,index) in info" v-bind:key="index">
      {{ item.description }}:{{ item.rate_float }}
    </div>
  </section>
</template>

<script>
import {onMounted, ref} from "vue";

export default {
  name: "Coin",
  setup() {
    let info = ref(null)
    let loading = ref(null)
    let errored = ref(true)
    // 官方建议：使用计算属性或方法来代替过滤器
    const filter = (value) => value.toFixed(2)

    onMounted(() => {
      // todo axios
      errored.value = false
    })


    return {
      info,
      loading,
      errored,
      filter
    }
  }
}
</script>

<style scoped>

</style>
