package ch.supsi.connectfour.frontend.contracts.observer;

import java.util.List;
import java.util.Map;

public interface AboutObserver {
    void about(List<String> infos, Map<String, String> developers);
}
