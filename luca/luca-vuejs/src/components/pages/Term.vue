<template>
  <div>
    <h2>{{term.label}} <small>{{term.ontology_iri}}</small></h2>
    <section v-if="hasDescription">
      <p v-for="entry in term.description">
        {{entry}}  
      </p>
    </section>
    <p v-else>No description provided</p>
  </div>
</template>

<script>
  function doubleEncode (value) {
    return encodeURIComponent(encodeURIComponent(value))
  }
  export default {
    data () {
      return {
        term: {}
      }
    },
    computed: {
      hasDescription () {
        return !!(this.term.description)
      }
    },
    mounted () {
      this.$nextTick(this.queryApi)
    },
    methods: {
      queryApi () {
        let ontology = this.$route.params.onto.toLowerCase()
        let iri = doubleEncode(this.$route.params.termid)
        fetch(`http://www.ebi.ac.uk/ols/api/ontologies/${ontology}/terms/${iri}`)
          .then(response => {
            return response.json()
          })
          .then(json => {
            this.term = json
          })
      }
    }
  }
</script>
