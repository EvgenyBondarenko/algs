import java.util.PriorityQueue;

class MedianFinder {

    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        // 12],[],[10],[],[13],[],[11],[],[5
        obj.addNum(12);
        obj.addNum(10);
        obj.addNum(13);
        obj.addNum(11);
        obj.addNum(5);
        double param_2 = obj.findMedian();
        System.out.println(param_2);
    }

    private PriorityQueue<Integer> small = new PriorityQueue<>((a,b) -> b - a);
    private PriorityQueue<Integer> large = new PriorityQueue<>((a,b) -> a - b);

    public MedianFinder() {

    }

    public void addNum(int num) {
        if (small.size() <= large.size()) {
            small.add(num);
        } else {
            large.add(num);
        }
        if (large.isEmpty()) return;
        int maxFromSmall = small.peek();
        int minFromLarge = large.peek();
        if (maxFromSmall > minFromLarge) {
            int temp = large.remove();
            large.add(small.remove());
            small.add(temp);
        }
    }

    public double findMedian() {
        if (small.size() > large.size()) {
            return small.peek();
        } else if (large.size() > small.size()) {
            return large.peek();
        } else {
            return (small.peek() + large.peek()) / 2.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */