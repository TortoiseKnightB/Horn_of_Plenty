<template>
  <div>
    <h3>比特币信息</h3>
    <section v-if="errored">
      <p>We're sorry, we're not able to retrieve this information at the moment, please try back later</p>
    </section>

    <section v-else>
      <div v-if="loading">Loading...</div>
      <div v-for="(item,index) in info" v-bind:key="index">
        {{ item.description }}:{{ item.rate_float | filt }}
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "coin",
  data() {
    return {
      info: null,
      loading: true,
      errored: false
    }
  },
  filters: {
    filt(value) {
      return value.toFixed(2)
    }
  },
  mounted() {
    this.axios
        .get('https://api.coindesk.com/v1/bpi/currentprice.json')
        .then(response => {
          console.log(response.data.bpi)
          this.info = response.data.bpi
        })
        .catch(error => {
          console.log(error)
          this.errored = true
        })
        .finally(() => this.loading = false)
  }
}
</script>

<style scoped>

</style>