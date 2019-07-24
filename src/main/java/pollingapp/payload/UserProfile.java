package pollingapp.payload;

import java.time.Instant;

public class UserProfile {
    private Long id;
    private String name;
    private String username;
    private Instant joinAt;
    private Long pollCount;
    private Long voteCount;

    public UserProfile(Long id, String name, String username, Instant joinAt, Long pollCount, Long voteCount) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.joinAt = joinAt;
        this.pollCount = pollCount;
        this.voteCount = voteCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getJoinAt() {
        return joinAt;
    }

    public void setJoinAt(Instant joinAt) {
        this.joinAt = joinAt;
    }

    public Long getPollCount() {
        return pollCount;
    }

    public void setPollCount(Long pollCount) {
        this.pollCount = pollCount;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }
}
