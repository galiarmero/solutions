import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class TwitterSearchTweets {
    private static final int NEWS_FEED_SIZE = 10;
    private final Deque<Tweet> tweets;
    private final Map<Integer, Set<Integer>> followees;

    class Tweet {
        final int userId;
        final int tweetId;

        Tweet(int userId, int tweetId) {
            this.userId = userId;
            this.tweetId = tweetId;
        }

        boolean isBy(int userId) {
            return this.userId == userId;
        }
    }

    public TwitterSearchTweets() {
        tweets = new ArrayDeque<>();
        followees = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        tweets.push(new Tweet(userId, tweetId));
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeed = new ArrayList<>(NEWS_FEED_SIZE);
        int count = 0;

        Set<Integer> userFollowees = this.followees.get(userId);
        for (Tweet tweet : tweets) {
            if (tweet.isBy(userId) ||
                (userFollowees != null && userFollowees.contains(tweet.userId))
            ) {
                newsFeed.add(tweet.tweetId);
                count++;
            }
            if (count == NEWS_FEED_SIZE) break;
        }

        return newsFeed;
    }
    
    public void follow(int followerId, int followeeId) {
        this.followees.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if (followees.containsKey(followerId)) {
            followees.get(followerId).remove(followeeId);
        }
    }

    /*
     * Test cases
     */
    public static void main(String[] args) {
        // Scenario 1
        Twitter twitter = new Twitter();
        twitter.postTweet(3, 300);
        twitter.postTweet(1, 100);
        twitter.postTweet(1, 105);
        twitter.postTweet(1, 110);
        twitter.postTweet(1, 115);
        twitter.postTweet(1, 120);
        twitter.postTweet(2, 200);
        twitter.postTweet(2, 205);
        twitter.postTweet(2, 210);
        twitter.postTweet(2, 215);
        twitter.postTweet(2, 220);
        twitter.postTweet(4, 400);

        List<Integer> tweets = twitter.getNewsFeed(3);
        print(tweets);
        assert tweets.equals(Arrays.asList(300));
        
        twitter.follow(3, 1);
        twitter.follow(3, 2);
        tweets = twitter.getNewsFeed(3);
        print(tweets);
        assert tweets.equals(Arrays.asList(220, 215, 210, 205, 200, 120, 115, 110, 105, 100));

        twitter.unfollow(3, 2);
        tweets = twitter.getNewsFeed(3);
        print(tweets);
        assert tweets.equals(Arrays.asList(120, 115, 110, 105, 100, 300));

        twitter.unfollow(3, 4);

        // Scenario 2
        twitter = new Twitter();
        twitter.postTweet(2, 5);
        twitter.postTweet(1, 3);
        twitter.postTweet(1, 101);
        twitter.postTweet(2, 13);
        twitter.postTweet(2, 10);
        twitter.postTweet(1, 2);
        twitter.postTweet(2, 94);
        twitter.postTweet(2, 505);
        twitter.postTweet(1, 333);
        twitter.postTweet(1, 22);

        tweets = twitter.getNewsFeed(2);
        print(tweets);
        assert tweets.equals(Arrays.asList(505, 94, 10, 13, 5));

        twitter.follow(2, 1);
        tweets = twitter.getNewsFeed(2);
        print(tweets);
        assert tweets.equals(Arrays.asList(22, 333, 505, 94, 2, 10, 13, 101, 3, 5));
    }

    public static void print(List<Integer> nums) {
        for (int num : nums) {
            System.out.printf("%d, ", num);
        }
        System.out.println();
    }
}
