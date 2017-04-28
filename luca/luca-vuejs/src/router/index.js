import Vue from 'vue'
import Router from 'vue-router'
import Search from '@/components/pages/Search'
import Term from '@/components/pages/Term'
import About from '@/components/pages/About'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/search',
      name: 'search',
      component: Search
    },
    {
      path: '/term/:onto/:termid',
      name: 'term',
      component: Term
    },
    {
      path: '/about',
      name: 'about',
      component: About
    }
  ]
})
