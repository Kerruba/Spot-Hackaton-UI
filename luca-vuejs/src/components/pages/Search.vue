<template>
  <div>
    <h2>Search OLS terms</h2>
    <search-box></search-box>
  </div>
</template>

<script>
  import Search from '@/components/parts/LocalSearchBox'
  export default {
    data () {
      return {
        'term': 'Hello world'
      }
    },
    components: {
      'search-box': Search
    }
  }
</script>
