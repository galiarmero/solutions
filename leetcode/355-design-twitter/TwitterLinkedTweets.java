import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class TwitterLinkedTweets {

    private final Map<Integer, User> users;

    static class Tweet {
        static int currentTimestamp = 0;
        int tweetId;
        int timestamp;
        Tweet prev;

        Tweet(int tweetId) {
            this.tweetId = tweetId;
            this.timestamp = ++currentTimestamp;
        }
    }

    class User {
        Tweet latestTweet;
        Set<User> followees;
        int userId;

        User(int userId) {
            this.latestTweet = null;
            this.followees = new HashSet<>();
            this.followees.add(this);
            this.userId = userId;
        }

        void addTweet(Tweet t) {
            t.prev = latestTweet;
            latestTweet = t;
        }

        void follow(User user) {
            followees.add(user);
        }

        void unfollow(User user) {
            followees.remove(user);
        }
    }

    public TwitterLinkedTweets() {
        this.users = new HashMap<>();
    }

    private User getUser(int userId) {
        return this.users.computeIfAbsent(userId, k -> new User(userId));
    }
    
    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(tweetId);
        this.getUser(userId).addTweet(tweet);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeed = new ArrayList<>();
        if (!this.users.containsKey(userId)) return newsFeed;

        PriorityQueue<Tweet> recentTweets = new PriorityQueue<>((t1, t2) -> t2.timestamp - t1.timestamp);
        for (User followee : this.getUser(userId).followees) {
            if (followee.latestTweet != null) {
                // System.out.printf("Latest tweet#%d: %d on %d\n", followee.userId, followee.latestTweet.tweetId, followee.latestTweet.timestamp);
                recentTweets.add(followee.latestTweet);
            }
        }

        int count = 0;
        while (!recentTweets.isEmpty() && count < 10) {
            Tweet recentTweet = recentTweets.poll();
            newsFeed.add(recentTweet.tweetId);
            count++;

            if (recentTweet.prev != null) {
                recentTweets.add(recentTweet.prev);
            }
        }

        return newsFeed;
    }
    
    public void follow(int followerId, int followeeId) {
        User userToFollow = this.getUser(followeeId);
        this.getUser(followerId).follow(userToFollow);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if (this.users.containsKey(followerId)) {
            User followedUser = this.getUser(followeeId);
            this.getUser(followerId).unfollow(followedUser);
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
