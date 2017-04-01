import Vue from 'vue'
import Router from 'vue-router'
import Search from '@/components/pages/Search'
// import Home from '@/components/pages/Home'
import Term from '@/components/pages/Term'

Vue.use(Router)

export default new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'Home',
    //   component: Home
    // },
    {
      path: '/',
      name: 'Search',
      component: Search
    },
    {
      path: '/term/:termid',
      name: 'Term',
      component: Term
    }
  ]
})
