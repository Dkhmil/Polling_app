package pollingapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pollingapp.model.Poll;
import pollingapp.payload.*;
import pollingapp.repository.*;
import pollingapp.security.CurrentUser;
import pollingapp.security.UserPrinsipal;
import pollingapp.service.PollService;

import javax.validation.Valid;

import java.net.URI;

import static pollingapp.util.AppConstants.DEFAULT_PAGE_NUMBER;
import static pollingapp.util.AppConstants.DEFAULT_PAGE_SIZE;


@RestController
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PollService pollService;

    private static final Logger logger = LoggerFactory.getLogger(PollController.class);


    @GetMapping
    public PagedResponse<PollResponse> getPolls(@CurrentUser UserPrinsipal currentUser,
                                                @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) int size) {
        return pollService.getAllPolls(currentUser, page, size);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createPoll(@Valid @RequestBody PollRequest pollRequest) {

        Poll poll = pollService.createPoll(pollRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{pollId}")
                .buildAndExpand(poll.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Poll Created Successfully"));
    }

    @GetMapping("/{pollId}")
    public PollResponse getPollById(@PathVariable Long pollId, @CurrentUser UserPrinsipal currentUser) {
        return pollService.getPollById(pollId, currentUser);

    }

    @PostMapping("/{pollId}/votes")
    @PreAuthorize("hasRole('USER')")
    public PollResponse castVote(@CurrentUser UserPrinsipal currentUser,
                                 @PathVariable Long pollingId,
                                 @Valid @RequestBody VoteRequest voteRequest) {
        return pollService.castVoteAndGetUpdatePoll(pollingId, voteRequest, currentUser);
    }


}
