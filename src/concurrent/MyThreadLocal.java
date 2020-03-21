package concurrent;

public class MyThreadLocal {
	public static ThreadLocal<String> value = new ThreadLocal<>();
	
	public void threadLocalTest() throws Exception {
		//set to "123"
		value.set("123");
		System.out.println("before thread1 exec, main thread threadLocal value:"+value.get());
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				String v = value.get();
				System.out.println("thread1 threadLocal value: "+ v);
				//set threadLocal in thread1
				value.set("456");
				System.out.println("thread1 value after reset:"+value.get());
				System.out.println("thread1 finishes");
			}
		}).start();
		
		Thread.sleep(5000L);
		
		System.out.println("after thread1, main thread thread value:"+value.get());
		
	}
	
	public static void main(String[] args) {
		MyThreadLocal local = new MyThreadLocal();
		try {
			local.threadLocalTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
