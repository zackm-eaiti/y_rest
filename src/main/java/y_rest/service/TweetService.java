package y_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import y_rest.models.entity.Tweet;
import y_rest.models.repository.TweetRepository;

import java.util.List;

@Service
public class TweetService {

    @Autowired
    private TweetRepository repo;

    public List<Tweet> listTweets() {
        return repo.findAll();
    }
}
