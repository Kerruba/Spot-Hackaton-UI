package uk.ac.ebi.biosamples.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OntologySummary {

    @JsonProperty("response")
    public void setDocs(Map<String, Object> response) {
        @SuppressWarnings("unchecked") List<Map<String, String>> _docs = (List<Map<String, String>>) response.get("docs");
        this.docs = _docs.stream().map(doc -> {
            OlsSearchDoc olsDoc = new OlsSearchDoc();
            olsDoc.setIri(doc.get("iri"));
            olsDoc.setOntology(doc.get("ontology_name"));
            olsDoc.setLabel(doc.get("label"));
            return olsDoc;
        }).collect(Collectors.toList());
    }

    public List<OlsSearchDoc> docs;

}
