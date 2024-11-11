package ch.supsi.connectfour.frontend.contracts.handler;

import java.util.List;
import java.util.Map;

public interface AboutHandler extends Handler
{
    void about(List<String> load, Map<String, String> stringStringMap);
}
