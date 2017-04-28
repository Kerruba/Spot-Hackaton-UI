package uk.ac.ebi.biosamples.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SolrResponse {

    @JsonProperty("response")
    InnerResponse response;

    public InnerResponse getResponse() {
        return response;
    }

    public class InnerResponse {
        @JsonProperty("numFound")
        long numFound;

        @JsonProperty("start")
        long start;

        @JsonProperty("docs")
        List<OlsSearchDoc> docs;

        public List<OlsSearchDoc> getDocs() {
            return docs;
        }
    }
}
