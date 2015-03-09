package com.dream.messaging.pool;

import java.lang.reflect.Method;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
* <p>连接管理器</p>
* <p>Copyright: Copyright Tarena(c) 2005</p>
* <p>Company: Tarena</p>
* @author Frank
* @version 1.0
*/
public class ConnectionManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
	//测试程序默认的连接池实现类
	  public static final String PROVIDER_CLASS = "com.dream.messaging.server.mina.MyConnectionProvider";
	  //测试程序的默认ip
	  public static final String HOST = "127.0.0.1";
	  //测试程序的默认端口号
	  public static final String PORT = "8002";
	  /**
	   * 注册钩子程序的静态匿名块
	   */
	  static {
	       //增加钩子控制资源的释放周期
	       Runtime runtime = Runtime.getRuntime();
	       Class c = runtime.getClass();
	       try {
	           Method m = c.getMethod("addShutdownHook", new Class[] { Thread.class } );
	           m.invoke(runtime, new Object[] { new ShutdownThread() });
	       }
	       catch (NoSuchMethodException e) {
	         e.printStackTrace();
	           // Ignore -- the user might not be running JDK 1.3 or later.
	       }
	       catch (Exception e) {
	           e.printStackTrace();
	       }
	   }
	   /**
	    * 默认的构造函数
	    */
	   public ConnectionManager() {
	   }
	   /**
	    * 得到并初始化一个连接池
	    * 连接池的实现类通过系统参数来传递进来，通过命令行-DConnectionProvider=YourImplClass
	    * 如果没有指定的实现的话，则采用系统默认的实现类
	    * 通过命令行传入的参数列表如下
	    * 对方主机名-DHost=192.168.0.200
	    * 对方端口号　-DPort=9880
	    * 最小连接数  -DMax=20
	    * 最大连结数　-DMin=10
	    * 以上的值可以改变，但是参数不能改变，
	    * 最大连结数和最小连接数可以省略，默认值分别为20和10
	    * @return ConnectionProvider
	    */
	   public static ConnectionProvider getConnectionProvider()throws Exception{
	     String provider_class=System.getProperty("ConnectionProvider");
	     if(provider_class==null)
	    	 provider_class=PROVIDER_CLASS;

	     String host=System.getProperty("Host");
	     if(host==null)
	    	 host=HOST;

	     String port=System.getProperty("Port");
	     if(port==null)
	    	 port=PORT;

	     String max_size=System.getProperty("Max_Size");
	     String min_size=System.getProperty("Min_Size");

	     Properties pro=new Properties();
	     pro.setProperty(ConnectionProvider.SERVER_IP,host);
	     pro.setProperty(ConnectionProvider.SERVER_PORT,port);
	     if( max_size!=null ){
	    	 pro.setProperty(ConnectionProvider.MAX_SIZE,max_size);
	     }
	     if(min_size!=null){
	    	 pro.setProperty(ConnectionProvider.MIN_SIZE,min_size);
	     }
	     //通过反射得到实现类
	     logger.debug("Provider class is:"+provider_class);
	     
	     Class provider_impl=Class.forName(provider_class);
	     
	     //由于是单子模式，采用静态方法回调
	     Method m = provider_impl.getMethod("newInstance",new Class[]{java.util.Properties.class});
	     ConnectionProvider provider=null;
	     try{
	    	 provider = (ConnectionProvider) m.invoke(provider_impl, new Object[]{pro});
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }

	     return provider;
	   }
	   /**
	    *
	    * <p>一个钩子的线程: 在程序结束的时候调用注销连接池</p>
	    * <p>Description: </p>
	    * <p>Copyright: Copyright Tarena(c) 2005</p>
	    * <p>Company: Tarena</p>
	    * @author Frank
	    * @version 1.0
	    */
	   private static class ShutdownThread extends Thread {
	          public void run() {
	            try{
	              ConnectionProvider provider = ConnectionManager.getConnectionProvider();
	              if (provider != null) {
	                provider.destroy();
	              }
	            }catch(Exception e){
	              e.printStackTrace();
	            }
	          }
	      }
}
