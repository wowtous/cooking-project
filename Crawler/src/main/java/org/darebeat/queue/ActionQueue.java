package org.darebeat.queue;

import java.util.Queue;

/**
 * Created by darebeat on 4/30/17.
 */
public interface ActionQueue {
    public ActionQueue getActionQueue();

    public void enqueue(Action action);

    public void dequeue(Action action);

    public void clear();

    public Queue<Action> getQueue();
}
