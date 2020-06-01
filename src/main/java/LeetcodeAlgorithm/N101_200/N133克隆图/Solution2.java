package LeetcodeAlgorithm.N101_200.N133克隆图;

import java.util.*;

public class Solution2 {
    public Node cloneGraph(Node node) {
        if (node == null) return node;

        Map<Node, Node> visited = new HashMap<>();
        Node clone = new Node(node.val, new ArrayList<>());
        visited.put(node, clone);

        Deque<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            for (Node neighbor : tmp.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.offer(neighbor);
                }
                visited.get(tmp).neighbors.add(visited.get(neighbor));
            }
        }

        return clone;
    }
}
