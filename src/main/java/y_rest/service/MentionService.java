package y_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import y_rest.models.dto.tweet.TweetPreviewDto;
import y_rest.models.entity.Mention;
import y_rest.models.repository.MentionRepository;

import java.util.List;

@Service
public class MentionService {

    @Autowired
    private MentionRepository repo;


    public Mention save(Mention mention) {
        return repo.save(mention);
    }

    public List<TweetPreviewDto> searchForMentions(String label) {
        return repo.findByLabel(label)
                .stream()
                .map(Mention::getTweet)
                .map(TweetPreviewDto::create)
                .toList();
    }
}
