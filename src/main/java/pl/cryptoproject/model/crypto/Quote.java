
package pl.cryptoproject.model.crypto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Quote {

    @SerializedName("USD")
    @Expose
    private Usd usd;

    public Usd getUsd() {
        return usd;
    }

    public void setUsd(Usd usd) {
        this.usd = usd;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "usd=" + usd +
                '}';
    }
}
