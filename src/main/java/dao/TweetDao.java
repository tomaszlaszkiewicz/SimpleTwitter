package dao;

import model.Tweet;
import model.User;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class TweetDao  extends AbstractDao {

    UserDao userDao = new UserDao();

    public void addTweet(String userLogin, String message){
        User author = userDao.getUserByLogin(userLogin);
        Tweet tweet = new Tweet();
        tweet.setPublishedAt(new Date());
        tweet.setAuthor(author);
        tweet.setMessage(message);
        hibernateUtil.save(tweet);
    }

    public void deleteTweet(String userLogin, long tweetId){
        User currentUser = userDao.getUserByLogin(userLogin);
        Tweet tweet = entityManager.find(Tweet.class, tweetId);
        if (tweet.getAuthor().equals(currentUser)) {
            hibernateUtil.delete(Tweet.class, tweetId);
        }
    }

    public List<Tweet> getFollowedTweets(String userLogin){
        User currentUser = userDao.getUserByLogin(userLogin);
        Set<User> followedUsers = currentUser.getFollows();
        List<User> followedWithCurrent = new ArrayList<>(followedUsers);
        followedWithCurrent.add(currentUser);
        Query query = entityManager.createQuery("select t from Tweet t where t.author in :list");
        query.setParameter("list", followedWithCurrent);
        return query.getResultList();
    }




}
