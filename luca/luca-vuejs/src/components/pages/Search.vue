<template>
  <div>
    <h2>Search OLS terms</h2>
    <search-box v-model="searchTerm" :onSubmit="queryApi"></search-box>
    <spinner v-if="isLoading"></spinner>
    <div v-if="hasResults">
    <table>
      <thead>
        <tr>
          <td>Ontology</td>
          <td>Label</td>
          <td>Iri</td>
          <td>Details</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="term in terms">
          <td>{{term.ontology_prefix}}</td>
          <td>{{term.label}}</td>
          <td>{{term.iri}}</td>
          <td><router-link :to="term.routelink">Link</router-link></td>
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
        searchTerm: this.$route.query.searchTerm,
        terms: [],
        isLoading: false
      }
    },
    computed: {
      hasResults () {
        return this.terms.length > 0
      },
      hasSearchTerm () {
        return !!this.searchTerm
      }
    },
    components: {
      'search-box': Search,
      'spinner': Spinner
    },
    mounted () {
      this.$nextTick(() => {
        if (this.hasSearchTerm) {
          this.queryApi()
        }
      })
    },
    methods: {
      queryApi (e) {
        this.isLoading = true
        this.$router.push({path: '/search', query: { searchTerm: this.searchTerm }})
        fetch(`http://www.ebi.ac.uk/ols/api/search?q=${this.searchTerm}`)
          .then(response => response.json())
          .then(json => {
            var docs = json.response.docs.map(doc => {
              doc.routelink = {
                name: 'term',
                params: {
                  onto: doc.ontology_prefix,
                  termid: doc.iri
                }
              }
              return doc
            })
            this.terms = docs
          })
          .then(() => {
            this.isLoading = false
          })
      }
    }
  }
</script>
