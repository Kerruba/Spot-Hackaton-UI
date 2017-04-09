package uk.ac.ebi.biosamples.domain;

import java.util.List;

public class SolrResponse {
    long numFound;
    long start;
    List<OlsSearchDoc> docs;
}
