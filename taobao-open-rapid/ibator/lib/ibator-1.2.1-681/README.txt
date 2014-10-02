===============================================================================
Overview
--------
Ibator is a code generator for the iBATIS SQL mapping framefork.
Ibator will introspect database tables (through JDBC DatabaseMetaData) and
generate SQL Map XML files, Java model object (POJOs) that match the table,
and (optionally) DAO classes that use the other generated objects.

For full documentation, please refer to the user's manual at doc/index.html
in this distribution.

Dependencies
------------
Ibator has no dependencies beyond the JRE.  Ibator does require JRE 5.0 or
above.  Ibator also requires that the JDBC driver implements the
DatabaseMetaData interface, especially the "getColumns" and "getPrimaryKeys"
methods.

Support
-------
Support for ibator is provided through the iBATIS user mailing list.  Mail
questions or bug reports to:

  user-java@ibatis.apache.org

  
==================================================
abator适合用来对新建表的Java、XML文件的生成。
==================================================
1.修改abatorConfig.properties的变量:jdbc.classpath为本地oracle JDBC的驱动，java.model.target.project，sqlmap.target.project，dao.target.project改为本地i2shopping-abator所在目录，
2.修改需要生成的表名：ibatorConfiguration.ibatorContext.table
3.在Eclipse中的Run中执行org.apache.ibatis.ibator.api.IbatorRunner -configfile D:\Projects\I2Shoppingv3.0\trunk\3.Development\i2shopping-abator\src\abatorConfig.xml -overwrite
4.或者在命令行执行i2shopping-abator.bat