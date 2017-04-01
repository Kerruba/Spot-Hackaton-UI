<template>
<div id="app">
  <ebi-skip-to></ebi-skip-to>
  <div data-sticky-container>
    <div id="local-masthead" data-sticky data-sticky-on="large" data-top-anchor="180" data-btm-anchor="300000">
      <header>
        <ebi-global-nav></ebi-global-nav>
        <ebi-local-nav></ebi-local-nav>
      </header>
    </div>
  </div>
  <div id="content" role="main" class="row">
    <router-view></router-view>
  </div>
  <ebi-footer></ebi-footer>
</div>
</template>

<script>
    import EbiSkipTo from '@/components/pages/fragments/EbiSkipTo'
    import EbiGlobalNav from '@/components/pages/fragments/EbiGlobalNav'
    import EbiFooter from '@/components/pages/fragments/EbiFooter'
    import EbiLocalNav from '@/components/pages/fragments/EbiLocalNav'

    export default {
      name: 'app',
      components: {
        'ebi-skip-to': EbiSkipTo,
        'ebi-global-nav': EbiGlobalNav,
        'ebi-local-nav': EbiLocalNav,
        'ebi-footer': EbiFooter
      }
    }
</script>

<style>

</style>
