package y_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import y_rest.models.dto.tweet.TweetPreviewDto;
import y_rest.models.entity.Hashtag;
import y_rest.models.repository.HashtagRepository;

import java.util.List;

@Service
public class HashtagService {

    @Autowired
    private HashtagRepository repo;


    public Hashtag save(Hashtag hashtag) {
        return repo.save(hashtag);
    }

    public List<TweetPreviewDto> searchForHashtags(String label) {
        return repo.findByLabel(label)
                .stream()
                .map(Hashtag::getTweet)
                .map(TweetPreviewDto::create)
                .toList();
    }
}
