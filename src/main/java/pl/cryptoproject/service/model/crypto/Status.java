
package pl.cryptoproject.service.model.crypto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Status {

    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("error_message")
    @Expose
    private Object errorMessage;
    @SerializedName("elapsed")
    @Expose
    private Integer elapsed;
    @SerializedName("credit_count")
    @Expose
    private Integer creditCount;
    @SerializedName("notice")
    @Expose
    private Object notice;
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getElapsed() {
        return elapsed;
    }

    public void setElapsed(Integer elapsed) {
        this.elapsed = elapsed;
    }

    public Integer getCreditCount() {
        return creditCount;
    }

    public void setCreditCount(Integer creditCount) {
        this.creditCount = creditCount;
    }

    public Object getNotice() {
        return notice;
    }

    public void setNotice(Object notice) {
        this.notice = notice;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "Status{" +
                "timestamp='" + timestamp + '\'' +
                ", errorCode=" + errorCode +
                ", errorMessage=" + errorMessage +
                ", elapsed=" + elapsed +
                ", creditCount=" + creditCount +
                ", notice=" + notice +
                ", totalCount=" + totalCount +
                '}';
    }
}
