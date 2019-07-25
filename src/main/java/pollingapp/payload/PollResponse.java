package pollingapp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;

public class PollResponse {
    private Long id;
    private String question;
    private List<ChoiceResponse> choises;
    private UserSummary createdby;
    private Instant creationdateTime;
    private Instant expirationdatetime;
    private Boolean isEpired;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long selectedChoise;
    private Long totalVotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<ChoiceResponse> getChoises() {
        return choises;
    }

    public void setChoises(List<ChoiceResponse> choises) {
        this.choises = choises;
    }

    public UserSummary getCreatedBy() {

        return createdby;
    }

    public void setCreatedby(UserSummary createdby) {
        this.createdby = createdby;
    }

    public Instant getCreationdateTime() {
        return creationdateTime;
    }

    public void setCreationdateTime(Instant creationdateTime) {
        this.creationdateTime = creationdateTime;
    }

    public Instant getExpirationdatetime() {
        return expirationdatetime;
    }

    public void setExpirationdatetime(Instant expirationdatetime) {
        this.expirationdatetime = expirationdatetime;
    }

    public Boolean getEpired() {
        return isEpired;
    }

    public void setEpired(Boolean epired) {
        isEpired = epired;
    }

    public Long getSelectedChoise() {
        return selectedChoise;
    }

    public void setSelectedChoise(Long selectedChoise) {
        this.selectedChoise = selectedChoise;
    }

    public Long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Long totalVotes) {
        this.totalVotes = totalVotes;
    }
}
