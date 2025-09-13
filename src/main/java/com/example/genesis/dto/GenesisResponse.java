package com.example.genesis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GenesisResponse {
    @JsonProperty("Genesis")
    private List<ChapterDto> genesis;

    public GenesisResponse() {}
    public GenesisResponse(List<ChapterDto> genesis) { this.genesis = genesis; }

    public List<ChapterDto> getGenesis() { return genesis; }
    public void setGenesis(List<ChapterDto> genesis) { this.genesis = genesis; }
}
