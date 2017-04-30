package org.darebeat.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Executors {

	private ActionQueueImpl defaultActionQueue;
	private ThreadPoolExecutor pool;

	/**
	 * 执行action队列的线程池
	 *
	 * @param corePoolSize
	 *            最小线程数，包括空闲线程
	 * @param maximumPoolSize
	 *            最大线程数
	 * @param keepAliveTime
	 *            当线程数大于核心时，终止多余的空闲线程等待新任务的最长时间
	 * @param prefix
	 *            线程池前缀名称
	 */
	public Executors(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, String prefix) {
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
		RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
		if (prefix == null) {
			prefix = "";
		}
		ThreadFactory threadFactory = new MyThreadFactory(prefix);
		pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
		defaultActionQueue = new ActionQueueImpl(this);
	}

	public void execute(Action action) {
		pool.execute(action);
	}

	public ActionQueueImpl getDefaultActionQueue() {
		return defaultActionQueue;
	}

	public static Executors create() {
		int corePoolSize = 5;
		int maximumPoolSize = 64;
		long keepAliveTime = 1;
		TimeUnit unit = TimeUnit.MINUTES;
		String prefix = "";
		return new Executors(corePoolSize, maximumPoolSize, keepAliveTime, unit, prefix);
	}

}
