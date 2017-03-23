import Vue from 'vue'
import Router from 'vue-router'
import Search from '@/components/templates/Search'
import Home from '@/components/templates/Home'
import Term from '@/components/templates/Term'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/search',
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
