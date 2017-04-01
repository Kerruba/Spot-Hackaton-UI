<template>
  <div>
    <h2>Search OLS terms</h2>
    <search-box v-model="searchTerm" :onSubmit="queryApi"></search-box>
    <spinner v-if="isLoading" style="margin: 0 auto; text-align: center"></spinner>
    <div v-if="hasResults">
    <table>
      <thead>
        <tr>
          <td>Ontology</td>
          <td>Label</td>
          <td>Iri</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="term in terms">
          <td>{{term.ontology_prefix}}</td>
          <td>{{term.label}}</td>
          <td>{{term.iri}}</td>
        </tr>
      </tbody>
    </table>
    </div>
  </div>
</template>

<script>
  import Search from '@/components/LocalSearchBox'
  import Spinner from '@/components/Spinner'

  export default {
    data () {
      return {
        searchTerm: 'Hello world',
        terms: [],
        isLoading: false
      }
    },
    computed: {
      hasResults () {
        return this.terms.length > 0
      }
    },
    components: {
      'search-box': Search,
      'spinner': Spinner
    },
    methods: {
      queryApi (e) {
        this.isLoading = true
        fetch(`http://www.ebi.ac.uk/ols/api/search?q=${this.searchTerm}`)
          .then(response => response.json())
          .then(json => {
            this.terms = json.response.docs
          })
          .then(() => {
            this.isLoading = false
          })
      }
    }
  }
</script>
