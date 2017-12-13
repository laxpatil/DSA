/**
 * 
 */
package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lapatil
 *
 */

// Definition for binary tree with next pointer.
class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}
}

public class NextRightPointers {
	public void connect(TreeLinkNode root) {
		if (root == null) {
			return;
		}

		Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
		q.add(root);
		int curItr = 1;
		int nextItr = 0;
		TreeLinkNode prev = null;

		while (curItr > 0) {
			TreeLinkNode cur = q.poll();
			curItr--;
			if (cur.left != null) {
				q.add(cur.left);
				nextItr++;
				if (prev == null) {
					prev = cur.left;
				} else {
					prev.next = cur.left;
					prev = prev.next;
				}
			}

			if (cur.right != null) {
				q.add(cur.right);
				nextItr++;
				if (prev == null) {
					prev = cur.right;
				} else {
					prev.next = cur.right;
					prev = prev.next;
				}
			}
			if (curItr == 0) {
				curItr = nextItr;
				nextItr = 0;
				if (prev != null) {
					prev.next = null;
				}
				prev = null;
			}

		}

	}
}