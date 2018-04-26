##1. login中专门的session表:jedis.hset(JsonKeyword.SESSION, username, sessionid);

##2. regist中将把用户的基本信息传入到redis中.格式为 jedis.hmset(userLoginInfo.getUname(), userLoginInfoMap);
    将整个对象的信息都存到redis中.

## 3. tankHandler tankCodeHander mapHandler 向redis传输哪些数据

###    公共部分:
    username        redis可根据这两个数据进行用户的定义
    userSession

###    不同部分:
        tankHandler:(包含了坦克的基本信息  eg:攻击力...)
            "userTankInfoId":"1"
            "equpmentArmourId":"1",
            "equpmentTurretId":"2",
            "equpmentWhellId":"3",
            "equpmentEnginId":"4"
        tankCodeHander
            "code": "qqqqqqq"
        mapHandler
            1)mapinfoid

## 4. 


