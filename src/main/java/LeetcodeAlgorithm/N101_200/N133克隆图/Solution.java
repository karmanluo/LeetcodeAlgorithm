package LeetcodeAlgorithm.N101_200.N133克隆图;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Solution {
    public Node cloneGraph(Node node) {
        Map<Node, Node> visited = new HashMap<>();
        return dfs(node, visited);
    }

    //DFS 遍历图
    private Node dfs(Node node, Map<Node, Node> visited) {
        if (node == null) return node;

        if (visited.containsKey(node)) return visited.get(node);

        // Create a clone for the given node.
        // Note that we don't have cloned neighbors as of now, hence [].
        Node clone = new Node(node.val, new ArrayList<>());

        // The key is original node and value being the clone node.
        visited.put(node, clone);

        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.
        for (Node n : node.neighbors) {
            clone.neighbors.add(dfs(n, visited));
        }

        return clone;
    }
}
