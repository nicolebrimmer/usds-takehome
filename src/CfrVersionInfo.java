import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CfrVersionInfo {
    Meta meta;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Meta {
        @JsonProperty("latest_issue_date")
        Date latestIssueDate;

        public Date getLatestIssueDate() {
            return latestIssueDate;
        }

        public void setLatestIssueDate(Date latestIssueDate) {
            this.latestIssueDate = latestIssueDate;
        }
    }
}
