package zero.zucc.com.elp.Httptask;

import java.util.List;


/**
 * Created by SunFly on 2016/12/4.
 */

public interface HttpCallBack {
    void success(List result);
    void error(Exception e);
}
