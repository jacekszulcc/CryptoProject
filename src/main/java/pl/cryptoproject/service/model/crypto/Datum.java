
package pl.cryptoproject.service.model.crypto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("num_market_pairs")
    @Expose
    private Integer numMarketPairs;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("max_supply")
    @Expose
    private Object maxSupply;
    @SerializedName("circulating_supply")
    @Expose
    private Double circulatingSupply;
    @SerializedName("total_supply")
    @Expose
    private Double totalSupply;
    @SerializedName("platform")
    @Expose
    private Object platform;
    @SerializedName("cmc_rank")
    @Expose
    private Integer cmcRank;
    @SerializedName("self_reported_circulating_supply")
    @Expose
    private Double selfReportedCirculatingSupply;
    @SerializedName("self_reported_market_cap")
    @Expose
    private Double selfReportedMarketCap;
    @SerializedName("tvl_ratio")
    @Expose
    private Object tvlRatio;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @SerializedName("quote")
    @Expose
    private Quote quote;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getNumMarketPairs() {
        return numMarketPairs;
    }

    public void setNumMarketPairs(Integer numMarketPairs) {
        this.numMarketPairs = numMarketPairs;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Object getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(Object maxSupply) {
        this.maxSupply = maxSupply;
    }

    public Double getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(Double circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public Double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Object getPlatform() {
        return platform;
    }

    public void setPlatform(Object platform) {
        this.platform = platform;
    }

    public Integer getCmcRank() {
        return cmcRank;
    }

    public void setCmcRank(Integer cmcRank) {
        this.cmcRank = cmcRank;
    }

    public Double getSelfReportedCirculatingSupply() {
        return selfReportedCirculatingSupply;
    }

    public void setSelfReportedCirculatingSupply(Double selfReportedCirculatingSupply) {
        this.selfReportedCirculatingSupply = selfReportedCirculatingSupply;
    }

    public Double getSelfReportedMarketCap() {
        return selfReportedMarketCap;
    }

    public void setSelfReportedMarketCap(Double selfReportedMarketCap) {
        this.selfReportedMarketCap = selfReportedMarketCap;
    }

    public Object getTvlRatio() {
        return tvlRatio;
    }

    public void setTvlRatio(Object tvlRatio) {
        this.tvlRatio = tvlRatio;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Datum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", slug='" + slug + '\'' +
                ", numMarketPairs=" + numMarketPairs +
                ", dateAdded='" + dateAdded + '\'' +
                ", tags=" + tags +
                ", maxSupply=" + maxSupply +
                ", circulatingSupply=" + circulatingSupply +
                ", totalSupply=" + totalSupply +
                ", platform=" + platform +
                ", cmcRank=" + cmcRank +
                ", selfReportedCirculatingSupply=" + selfReportedCirculatingSupply +
                ", selfReportedMarketCap=" + selfReportedMarketCap +
                ", tvlRatio=" + tvlRatio +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", quote=" + quote +
                '}';
    }
}
