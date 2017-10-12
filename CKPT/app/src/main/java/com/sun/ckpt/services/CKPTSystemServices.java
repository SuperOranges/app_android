package com.sun.ckpt.services;

import com.sun.ckpt.bean.taskBean;
import com.sun.ckpt.bean.userBean;

/**
 * Created by Administrator on 2017/10/9.
 */

public interface CKPTSystemServices {
      void  requestData();//请求数据
      taskBean receiveTaskData();//接收Task数据
      userBean receiverUserData();//接收用户数据

}
