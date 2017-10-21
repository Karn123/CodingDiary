package cst;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by 11022 on 2017/3/11.
 */
public class TopNPriorityQueue<E> {
    private PriorityQueue<E> queue;
    private int topN;

    public TopNPriorityQueue(int topN) {
        queue = new PriorityQueue<E>();
        this.topN = topN;
    }

    public TopNPriorityQueue(int topN, Comparator<? super E> comparator) {
        this.topN = topN;
        queue = new PriorityQueue<E>(comparator);
    }

    public boolean push(E e) {
        if (!queue.offer(e)) {
            return false;
        }
        while (queue.size() > topN) {
            queue.poll();
        }
        return true;
    }

    public E peek() {
        return queue.peek();
    }

    public E pop() {
        return queue.poll();
    }

    public PriorityQueue<E> getQueue() {
        return queue;
    }

    public void setQueue(PriorityQueue<E> queue) {
        this.queue = queue;
    }

    public int getTopN() {
        return topN;
    }
}
