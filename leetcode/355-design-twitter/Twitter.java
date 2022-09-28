import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class Twitter {
    private final int FEED_SIZE = 10;
    private final Map<Integer, Deque<Tweet>> tweetsByUser;
    private final Map<Integer, Set<Integer>> followeesByUser;

    static class Tweet {
        static int currentTime = 0;
        int id;
        int time;

        Tweet(int id) {
            this.id = id;
            this.time = ++currentTime;
        }
    }

    Twitter() {
        this.tweetsByUser = new HashMap<>();
        this.followeesByUser = new HashMap<>();
    }

    public Set<Integer> getOrInitFollowees(int userId) {
        return this.followeesByUser
            .computeIfAbsent(userId, k -> {
                Set<Integer> s = new HashSet<>();
                s.add(userId);
                return s;
            });
    }

    public void postTweet(int userId, int tweetId) {
        this.tweetsByUser
            .computeIfAbsent(userId, k -> new ArrayDeque<>())
            .push(new Tweet(tweetId));
        getOrInitFollowees(userId);
    }

    public List<Integer> getNewsFeed(int userId) {
        if (!this.followeesByUser.containsKey(userId)) return new ArrayList<>();

        PriorityQueue<Tweet> recentTweets = new PriorityQueue<>((t1, t2) -> t2.time - t1.time);
        for (int followeeId : this.followeesByUser.get(userId)) {
            Deque<Tweet> tweets = this.tweetsByUser.get(followeeId);
            if (tweets == null || tweets.isEmpty()) continue;

            tweets.stream()
                .limit(FEED_SIZE)
                .collect(Collectors.toCollection(() -> recentTweets));
        }

        List<Integer> newsFeed = new ArrayList<>();
        while (!recentTweets.isEmpty() && newsFeed.size() < FEED_SIZE) {
            newsFeed.add(recentTweets.poll().id);
        }

        return newsFeed;
    }

    public void follow(int followerId, int followeeId) {
        getOrInitFollowees(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (this.followeesByUser.containsKey(followerId)) {
            this.followeesByUser.get(followerId).remove(followeeId);
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
