<template>
  <button v-on:click="updateInfo">插入</button>
  <input type="text" placeholder="请输入数据编号" v-model="insert_id">
  <input type="text" placeholder="请输入数据内容" v-model="insert_message">
  <br/>
  <button v-on:click="getInfo">查询</button>
  <input type="text" placeholder="请输入数据编号" v-model="query_id">
  <p>
    查询结果：
    <br/>
    <span v-if="info == null"></span>
    <span v-else-if="info.flag">Id - {{ info.testId }}，Message - {{ info.message }}</span>
    <span v-else>查询数据错误！！！</span>
  </p>
</template>

<script>
import {reactive, toRefs} from "vue";
import axios from "axios"

export default {
  name: "TestController",
  setup() {
    const data = reactive({
      info: {
        flag: false,
        testId: null,
        message: null
      },
      query_id: null,
      insert_id: null,
      insert_message: null
    })

    const methods = reactive({
      getInfo: () => {
        axios
            .post('http://localhost:8080/test/GetTestDataById', {
              Id: data.query_id,
            })
            .then(response => {
              console.log(response)
              data.info = response.data.Data
              data.info.flag = true
            })
            .catch(error => {
              console.log(error)
              data.info.flag = false
            })
      },
      updateInfo: () => {
        axios
            .post('http://localhost:8080/test/AddTestData', {
              Id: data.insert_id,
              Message: data.insert_message
            })
            .then(response => {
              console.log(response)
              console.log("插入数据成功")
            })
            .catch(error => {
              console.log(error)
              console.log("插入数据失败")
            })
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

</style>
