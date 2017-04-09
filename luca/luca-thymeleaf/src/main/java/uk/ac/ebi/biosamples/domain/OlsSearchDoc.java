package uk.ac.ebi.biosamples.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OlsSearchDoc {
    String iri;

    @JsonProperty("ontology_name")
    String ontology;
}
