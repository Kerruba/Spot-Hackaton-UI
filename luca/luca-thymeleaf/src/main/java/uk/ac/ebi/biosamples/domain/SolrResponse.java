package uk.ac.ebi.biosamples.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SolrResponse {
    @JsonProperty("numFound")
    long numFound;

    @JsonProperty("start")
    long start;

    @JsonProperty("docs")
    List<OlsSearchDoc> docs;

    public long getNumFound() {
        return numFound;
    }

    public long getStart() {
        return start;
    }

    public List<OlsSearchDoc> getDocs() {
        return docs;
    }


}
