* 【前端】预约时提交具体时间的问题

  * 起始时间在16：00，而终止时间在14：30

* 【前端】申请教室时联系人电话格式不正确也可以提交成功

* 【前端】预约时间为过去时间也可以预约成功

  - 预约当天时间时，例如下午的时候进入系统预约，但是还可以预约上午的时间，并能够预约成功。
    改进建议：1.预约过去时间时提示不可预约；2或者，不提供当天预约，只能提前一天预约

* 【前端】日期和星期几对应问题以及预约详情问题

  - 1.预约页面的日期和星期几不对应
    2.点击首页已预约的教室和时间的单元格报错，错误如下：
    type Exception report

    message javax.el.ELException: Error reading 'cname' on type 

* 【前端】Message Illegal

  - Here are a few small problems. When I use the system, I find the message people write could be illegal. For example, 
  - I can select the appointment time surpass the given, but there are no warning, 
  - and the phone number not be checked at all, which should be a 11 Numbers.
  
* 【前端】关于管理员维护

  * 在新增管理员时，密码可以为空。出于安全考虑，应该加个验证，防止空密码的出现。
   ​

* 【后端】对已过期的预约进行“取消”操作不合理

  * 历史预约记录中，对于已过期的预约显示“取消”操作，不合理，显示“已通过”或“已取消”感觉比较合理。

* 【后端】查询预约详情时报错 

  * javax.el.ELException: Error reading 'cname' on type 

* 【后端】可直接通过访问url进入后台，无需以管理员身份登录【真的可以】

  * http://118.89.142.57:8080/reservation/admin.do?flag=showClass

* 【后端】同一个教室同一预约时间用同一个班级账号可以预约多次

  * 查看历史预约消息发现E506在2016-12-10的8:30-11:30用1603班的账号预约了3次

* 【后端】管理员界面的班级维护问题

  * 管理员界面的班级组织管理页面中，不能对已有班级信息进行修改，会显示如下信息：

    错误页面  failed to lazily initialize a collection of role: edu.zju.reservation.domain.ResStudent.resReservations, could not initialize proxy - no Session

* 【后端】关于管理员维护

  * 现在的管理员维护界面，只能增加管理员。可以给admin，也就是活动室预约系统默认管理员增加一个功能，让他能够对其他管理员进行删除操作。
    
* 【已修复】【后端】登入总次数逻辑错误

  * 最近一次登入是显示未更新的最近一次登入，而登入总次数应该显示更新后的。而系统还是用的更新前的。即历史登入总次数为1，登入后应该显示2，后台更新为2，UI应该取更新后的值2而非1.

* 【已修复】【后端】query did not return a unique result: 2 : getReservationByDateAndTimequantumAndClass
