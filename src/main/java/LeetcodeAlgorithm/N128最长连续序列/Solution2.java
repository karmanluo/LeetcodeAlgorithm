package LeetcodeAlgorithm.N128最长连续序列;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:KunmingLuo
 * @Date: 2020/5/16 10:52
 */
class Solution2 {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int len = nums.length;
        BCJ bcj = new BCJ(len);

        for(int i = 0; i < len; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i], i);
            }

        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int smaller = entry.getKey() - 1;
            int bigger = entry.getKey() + 1;
            if (map.containsKey(smaller)){
                bcj.Union(entry.getValue(), map.get(smaller));
            }
            if (map.containsKey(bigger)){
                bcj.Union(entry.getValue(), map.get(bigger));
            }
        }

        return bcj.maxSize();

    }

    class BCJ{
        private int[] size;
        private int[] parent;
        private int count;

        public BCJ(int n){
            this.count = n;
            size = new int[n];
            parent = new int[n];

            for(int i = 0; i < n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x){
            if(x == parent[x]){
                return x;
            }

            return parent[x] = find(parent[x]);
        }

        public void Union(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);
            if(rootP == rootQ) return;

            if(size[rootP] > size[rootQ]){
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }else{
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            count--;
        }

        public boolean isConnected(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);

            return rootP == rootQ;
        }

        public int count(){
            return count;
        }

        public int maxSize(){
            int max = 0;
            for(int i = 0; i < size.length; i++){
                if(size[i] > max){
                    max = size[i];
                }
            }

            return max;
        }
    }
}