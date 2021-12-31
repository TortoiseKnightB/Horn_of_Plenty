export default {
    namespaced: true,
    //准备actions对象——响应组件中用户的动作
    actions: {
        addHero(context, value) {
            console.log("store_actions start");
            const hero = {
                id: context.state.heroList.length + 1,
                name: value
            };
            context.commit("ADD_PERSON", hero);
        }
    },

    //准备mutations对象——修改state中的数据
    mutations: {
        ADD_PERSON(state, value) {
            console.log("store_mutations start");
            state.heroList.unshift(value);
        }
    },

    getters: {},

    //准备state对象——保存具体的数据
    state: {
        heroList: [
            {id: "001", name: "秃头披风侠"},
            {id: "002", name: "背心尊者"},
            {id: "003", name: "King"}
        ]
    }
}