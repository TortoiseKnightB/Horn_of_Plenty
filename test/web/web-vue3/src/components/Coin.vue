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
import {onMounted, reactive, toRefs} from "vue";
import axios from "axios"

export default {
  name: "Coin",
  setup() {
    const data = reactive({
      info: null,
      loading: true,
      errored: null
    })

    // 官方建议：使用计算属性或方法来代替过滤器
    const filter = (value) => value.toFixed(2)

    onMounted(() => {
      axios
          .get("https://api.coindesk.com/v1/bpi/currentprice.json")
          .then(response => {
            console.log(response.data.bpi)
            data.info = response.data.bpi
            data.errored = false
          })
          .catch(error => {
            console.log(error)
            data.errored = true
          })
          .finally(() => data.loading = false)
    })

    return {
      ...toRefs(data),
      filter
    }
  }
}
</script>

<style scoped>

</style>
