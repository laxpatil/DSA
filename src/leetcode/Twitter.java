/**
 * 
 */
package leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author lapatil
 *
 */
public class Twitter {

	private static int timestamp = 0;

	private Map<Integer, User> userMap;

	private class User {
		public int userid;
		public Set<Integer> followed;
		public Tweet tweetHead;

		public User(int id) {
			this.userid = id;
			this.followed = new HashSet<Integer>();
			follow(id);
			this.tweetHead = null;
		}

		public void follow(int id) {
			this.followed.add(id);
		}

		public void unfollow(int id) {
			this.followed.remove(id);
		}

		public void post(int tweetId) {
			Tweet t = new Tweet(tweetId);
			t.next = this.tweetHead;
			this.tweetHead = t;
		}

	}

	private class Tweet {
		public int tweetId;
		public int time;
		public Tweet next;

		public Tweet(int id) {
			this.tweetId = id;
			this.time = timestamp++;
			this.next = null;
		}
	}

	/** Initialize your data structure here. */
	public Twitter() {
		userMap = new HashMap<Integer, User>();
	}

	/** Post a new Tweet **/
	public void postTweet(int userId, int tweetId) {
		if (!userMap.containsKey(userId)) {
			User u = new User(userId);
			userMap.put(userId, u);
		}
		userMap.get(userId).post(tweetId);
	}

	/**
	 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in
	 * the news feed must be posted by users who the user followed or by the user
	 * herself. Tweets must be ordered from most recent to least recent.
	 */
	public List<Integer> getNewsFeed(int userId) {
		List<Integer> result = new LinkedList<Integer>();

		if (!userMap.containsKey(userId)) {
			return result;
		}

		Set<Integer> follweees = userMap.get(userId).followed;
		PriorityQueue<Tweet> pq = new PriorityQueue<Tweet>(follweees.size(), new Comparator<Tweet>() {
			public int compare(Tweet a, Tweet b) {
				return b.time - a.time;
			}
		});
		
		for(int user : follweees) {
			Tweet t = userMap.get(user).tweetHead;
			if(t!=null) {
				pq.add(t);
			}
		}

		int n = 0;
		while (!pq.isEmpty() && n < 10) {
			Tweet t = pq.remove();
			result.add(t.tweetId);
			n++;
			if (t.next != null) {
				pq.add(t.next);
			}
		}
		return result;
	}

	/**
	 * Follower follows a followee. If the operation is invalid, it should be a
	 * no-op.
	 */
	public void follow(int followerId, int followeeId) {
		if (!userMap.containsKey(followeeId)) {
			User u = new User(followeeId);
			userMap.put(followeeId, u);
		}
		if (!userMap.containsKey(followerId)) {
			User u = new User(followerId);
			userMap.put(followerId, u);
		}
		userMap.get(followerId).follow(followeeId);

	}

	/**
	 * Follower unfollows a followee. If the operation is invalid, it should be a
	 * no-op.
	 */
	public void unfollow(int followerId, int followeeId) {
		if (!userMap.containsKey(followerId) && followerId == followeeId) {
			return;
		}
		userMap.get(followerId).unfollow(followeeId);

	}
}

/**
 * Your Twitter object will be instantiated and called as such: Twitter obj =
 * new Twitter(); obj.postTweet(userId,tweetId); List<Integer> param_2 =
 * obj.getNewsFeed(userId); obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
