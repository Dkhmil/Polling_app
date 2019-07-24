package pollingapp.payload;

import javax.validation.constraints.NotNull;

public class VoteRequest {
    @NotNull
    private Long choiseId;

    public Long getChoiseId() {
        return choiseId;
    }

    public void setChoiseId(Long choiseId) {
        this.choiseId = choiseId;
    }
}
