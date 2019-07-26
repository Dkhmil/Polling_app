package pollingapp.util;

import pollingapp.model.Poll;
import pollingapp.model.User;
import pollingapp.payload.ChoiceResponse;
import pollingapp.payload.PollResponse;
import pollingapp.payload.UserSummary;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelMapper {

    public static PollResponse mapPollToPollResponse(Poll poll, Map<Long, Long> choicesVoteMap, User creator, Long userVote) {
        PollResponse pollResponse = new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setQuestion(poll.getQuestion());
        pollResponse.setCreationdateTime(poll.getCreatedAt());
        pollResponse.setExpirationdatetime(poll.getExpirationDateTime());
        Instant now = Instant.now();
        pollResponse.setEpired(poll.getExpirationDateTime().isBefore(now));

        List<ChoiceResponse> choiceResponses = poll.getChoiсes()
                .stream()
                .map(choice -> {
                    ChoiceResponse choiceResponse = new ChoiceResponse();
                    choiceResponse.setId(choice.getId());
                    choiceResponse.setText(choice.getText());

                    if (choicesVoteMap.containsKey(choice.getId())) {
                        choiceResponse.setVoteCount(choicesVoteMap.get(choice.getId()));
                    } else {
                        choiceResponse.setVoteCount((long) 0);
                    }
                    return choiceResponse;
                }).collect(Collectors.toList());

        pollResponse.setChoises(choiceResponses);
        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
        pollResponse.setCreatedby(creatorSummary);

        if (userVote != null) {
            pollResponse.setSelectedChoiсe(userVote);
        }
        long totalVotes = pollResponse.getChoiсes()
                .stream()
                .mapToLong(ChoiceResponse::getVoteCount)
                .sum();
        pollResponse.setTotalVotes(totalVotes);
        return pollResponse;
    }

}
