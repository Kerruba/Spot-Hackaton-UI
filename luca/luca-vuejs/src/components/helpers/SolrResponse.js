export default function SolrResponse (response) {
  return {
    docs: response.docs,
    total: response.numFound
  }
}

