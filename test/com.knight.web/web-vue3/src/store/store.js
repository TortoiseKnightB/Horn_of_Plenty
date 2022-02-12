import {createStore} from 'vuex'
import helloworld from "./helloworld"
import hero from "./hero"

export default createStore({
    modules: {
        helloworldStore: helloworld,
        heroStore: hero
    }
})
