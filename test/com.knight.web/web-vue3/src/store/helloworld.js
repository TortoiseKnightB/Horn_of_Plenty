export default {
    namespaced: true,

    //准备actions对象——响应组件中用户的动作
    actions: {
        testActions(context, value) {
            console.log("store_actions start")
            context.commit("TEST_MUTATIONS", value);
        }
    },

    //准备mutations对象——修改state中的数据
    mutations: {
        TEST_MUTATIONS(state, value) {
            console.log("store_mutations start");
            state.storeData += value;
        }
    },

    getters : {
        testGetters(state) {
            return state.storeData + "_GETTERS";
        }
    },

    //准备state对象——保存具体的数据
    state: {
        storeData: "hello_world",
        storeSum: 0
    }
}

