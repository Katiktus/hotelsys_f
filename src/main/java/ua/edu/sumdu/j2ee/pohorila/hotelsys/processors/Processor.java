package ua.edu.sumdu.j2ee.pohorila.hotelsys.processors;

import com.sun.deploy.net.HttpRequest;

public abstract class Processor{
    protected String actionToPerfom = null;

    public boolean canProcess(String action){
        return actionToPerfom.equals(action);
    }

    public abstract void process(HttpRequest request);
}
