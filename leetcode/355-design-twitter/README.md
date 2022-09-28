---
categories: map,priority queue,deque
---

# [Design Twitter](https://leetcode.com/problems/design-twitter/)

The task is to create a class with Twitter-like features, giving users the following abilities:

1. Follow a user
2. Unfollow a user
3. Post a tweet
4. See a news feed of the 10 most recent tweets of self and the users they follow (called _followee_)

The challenge is in **#4**. How we approach it will also dictate how **#1**-**#3** is implemented. There are two high-level approaches to come up with the news feed. Each is described in detail in separate sections:

 1. Traverse all tweets, checking if each is tweeted by a user's followee
 2. Using a priority queue containing tweets of just the user's followees, use it to crank out the 10 most recent.

Don't forge to include the user itself in the set of followees to produce the correct news feed tweets.

## Approach A: Traverse all tweets

In a real-world scenario, this might be the most inefficient of the two approaches. But in LeetCode, ironically, I got better runtime and memory performance out of this. That's because a) the number of tweets is just `10^4` (`10,000`) and b) we only need 10 tweets in the news feed.

This is also the easiest to implement:

1. Use a class `Tweet` with `userId` and `tweetId` attributes.
2. Maintain a collection of all `Tweet`s.
    > In Java, an `ArrayList` or `ArrayDeque` would do.
3. Maintain a set of followees per user.
   > In Java, use a `HashMap` indexed by an the `userId` with `HashSet` values. We use this structure for faster insertion, removal, and read which we'll often do with `follow`, `unfollow` and `getNewsFeed`.
4. To get the news feed of user `x`, traverse the collection of tweets, starting from the most recent `tweet`. If the `tweet.userId` is found in `followees[x]`, then we add that in the news feed. We do so until we've checked all tweets or the news feed already has 10 tweets.

Source code is in [TwitterSearchTweets.java](./TwitterSearchTweets.java)

## Approach B: Priority Queue of most recent tweets from followees

Here, we need to maintain two collections _for each user_:

1. Set of followees including self
2. Collection of tweets

How these two are stored may vary. For the sake of simplicity, let's use hash maps for both. This allows fast lookups for a given user's tweets and followees, which we'll do often.

```java
this.tweetsByUser = new HashMap<>();
this.followeesByUser = new HashMap<>();
```

For the set of followees, we can use a set. A `HashSet` will allow us to add (_follow_) and remove (_unfollow_) users in constant time. This snippet ensures that the user's followees exists in the map before adding new ones, initially adding the user itself so that its tweets will also show up in the news feed.

```java
this.followeesByUser
    .computeIfAbsent(followerId, k -> {
        Set<Integer> s = new HashSet<>();
        s.add(followerId);
        return s;
    })
```

When a user posts a `Tweet`, we add them to a collection indexed in the hash map by the `userId`. What kind of collection can we use so that the order in which the tweets were posted is preserved? Here are some options:

- `ArrayList` - calling `add(x)` would add an item at the back of the list in _O(1)_. So to get the most recent tweets, we have to start from the back of the list.
- `ArrayDeque` - calling `push(x)` would add an item at the front of the list in _O(1)_. So to get the most recent tweets, start from the front of the list. This is an implementation of the `Deque` interface.
- A custom linked list. Each `Tweet` object has a `next` attribute, containing the reference to the next tweet. Each user then has an access to the head of the list, the `latestTweet`.
  > This is the most ideal especially if the news feed size is large. But for this discussion, let's skip this. It introduces needless complexity and doesn't make a difference in performance when the news feed size is 10. 

For readability purposes, I'll use `ArrayDeque` in this example since it's more straightforward to grab the most recent tweets.

```java
this.tweetsByUser
    .computeIfAbsent(userId, k -> new ArrayDeque<>())
    .push(new Tweet(tweetId));
```

Here's what the `Tweet` class would look like:

```java
static class Tweet {
    static int currentTime = 0;
    int id;
    int time;

    Tweet(int id) {
        this.id = id;
        this.time = ++currentTime;
    }
}
```

The `time` field is crucial in distinguishing whether a given tweet `x` is more recent than tweet `y`. We can't rely on the `tweetId` for this. Given tweet `x` with `tweetId=1` and tweet `y` with `tweetId=2`, if `y` was posted before `x`, then `x` is more recent.

Now, time to get the news feed for user number `x`. First we create a priority queue that give us the most recent tweets. It should have a comparator that orders the tweets from most recent to least recent. For this, we'll use `Tweet.time`.

```
PriorityQueue<Tweet> recentTweets = new PriorityQueue<>((t1, t2) -> t2.time - t1.time);
```

Then, for each followee of the user, get 10 most recent tweets and add it into the priority queue.
```
for (int followeeId : this.followeesByUser.get(userId)) {
    Deque<Tweet> tweets = this.tweetsByUser.get(followeeId);
    if (tweets == null || tweets.isEmpty()) continue;

    tweets.stream()
        .limit(FEED_SIZE)
        .collect(Collectors.toCollection(() -> recentTweets));
}
```

By now, the priority queue may contain more than 10 tweets from several followers. We need to get just the 10 most recent. The `poll` function returns the most recent tweet and removes it from the queue. We call it at max 10 times, and push the `Tweet.id` to the result each time.

```java
List<Integer> newsFeed = new ArrayList<>();
while (!recentTweets.isEmpty() && newsFeed.size() < FEED_SIZE) {
    newsFeed.add(recentTweets.poll().id);
}
```

That's it! Full source code is in [Twitter.java](./Twitter.java)


## Gotchas

- Users own tweets can be seen on their news feed
- If the user has no tweets and no followers, news feed should be empty
- A tweet can be posted before a user even has followers
