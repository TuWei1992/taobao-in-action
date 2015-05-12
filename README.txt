1.Version Control：GIT
1.1
http://www.bootcss.com/p/git-guide/

2.Java Denpendency Management：Maven
3.CI:Hudson
4.Javascript Denpendency Management：Bower,Grunt
5.Java IDE：Eclipse

6.Javascript IDE：Sublime Text2
6.1
http://www.cnblogs.com/leecanz/archive/2012/03/04/2379446.html
http://www.cnblogs.com/dolphin0520/archive/2013/04/29/3046237.html
http://blog.yadgen.com/?p=934

7.Java Unit Test：JUnit
8.Javascript Unit Test：


9.Review
mvn findbugs:help       查看findbugs插件的帮助
mvn findbugs:check      检查代码是否通过findbugs检查，如果没有通过检查，检查会失败，但检查不会生成结果报表
mvn findbugs:findbugs   检查代码是否通过findbugs检查，如果没有通过检查，检查不会失败，会生成结果报表保存在target/findbugsXml.xml文件中
mvn findbugs:gui        检查代码并启动gui界面来查看结果


10.J2EE
$TOMCAT_HOME\Catalina\localhost

11.DB
H2:com.dream.rapid.base.H2DatabaseStarter


12.URL:
taobao-open-recommend:http://localhost:8080/taobao-open-recommend/t
taobao-sandbox:http://www.tbsandbox.com/doc/
系统默认帐号密码统一为：taobao1234 ， 帐号如下：
商城卖家账号 -> sandbox_c_1 & sandbox_b_00~sandbox_b_29 -> 例如：sandbox_c_1 ， sandbox_b_01
集市卖家账号 -> sandbox_c_2~sandbox_c_20 -> 例如：sandbox_c_2

13.Redis
Path：D:\Work\Tools\redis-2.4.5-win32-win64
CMD：D:\Work\Tools\redis-2.4.5-win32-win64\64bit\redis-server.exe
PORT：
http://www.cnblogs.com/lxx/archive/2013/06/04/3116985.html
http://www.cnblogs.com/kkgreen/archive/2011/11/09/2243554.html


14.Zookeeper
Path：D:\Work\Frameworks\Java\zookeeper-3.4.6
CMD：D:\Work\Frameworks\Java\zookeeper-3.4.6\zkServer.cmd
PORT：2181
http://www.cnblogs.com/snake-hand/archive/2013/06/09/3129882.html
http://www.cnblogs.com/shanyou/archive/2013/07/28/3221990.html


15.Filter&Servlet&Interceptor&Controller Sequence 
Filter：
1.CharacterEncodingFilter
2.SiteMeshFilter
3.URLRewriteFilter

Servlet：
1.DispatcherServlet

Interceptor：


16.Cobar Client

17.Dubbo

18.包名说明:
rapid,快速框架
rop:rest open platform
AbstractXXXX:抽象基类
BaseXXX：实现类基类
DefaultXXX：默认基类


19.定时任务：taobao-open-schedule
19.1默认配置文件：classpath:///myschedule/web/myschedule-settings.properties
19.2客户化配置文件：基于JVM系统参数的配置文件，通过JVM参数myschedule.settings，如果未取得JVM系统参数，则通过系统环境变量获取MYSCHEDULE_SETTINGS，如果系统环境变量无法取得则使用默认配置。


20.在Eclipse中使用JUnit时，先执行mvn test-compile，再执行响应方法。
