package 笔试.练习.day0622;

import java.lang.annotation.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        UserService target = new UserSeviceImpl();
        UserService proxy = (UserService)PermissionProxy.getInstance(target);
        proxy.deleteUser();
    }

    class PermissionProxy implements InvocationHandler {
        private Object target;
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }


    private boolean doCheck(String permissionCode) throws Exception{
        List<String> premissions = getUserPermission();

        if (premissions != null && premissions.equals(premissions)) {
            System.out.println("成");
            return true;
        }else {
            return false;
        }
    }

    private List<String> getUserPermission() {
        String[] permissions = {"ADD", "DELETE", "UPDATE"};
        System.out.println(String.format("当前用户初始化权限%s", Arrays.toString(permissions)));
        return Arrays.asList(permissions);
    }

    class UserServiceImpl implements UserService {
        @Override
        public void deleteUser() {
            System.out.println("-------");
        }
    }

    interface  UserService {
        @Permission (permissonCode = "DELETE")
        void deleteUser();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface Permission {
        public String permissionCode() default "";
    }
}
