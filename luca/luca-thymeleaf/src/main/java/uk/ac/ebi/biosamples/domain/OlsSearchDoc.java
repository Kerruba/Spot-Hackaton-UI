package uk.ac.ebi.biosamples.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OlsSearchDoc {

    @JsonProperty("iri")
    String iri;
    @JsonProperty("ontology_name")
    String ontology;
    @JsonProperty("label")
    String label;

    public String getIri() {
        return iri;
    }

    public void setIri(String iri) {
        this.iri = iri;
    }

    public String getOntology() {
        return ontology;
    }

    public void setOntology(String ontology) {
        this.ontology = ontology;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return String.format("%s: %s (%s)", this.ontology, this.label, this.iri);
    }
}
