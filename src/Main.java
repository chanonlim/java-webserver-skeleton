import com.sn.WebServer.Handlers.*;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpServer;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        Map<String, Map<String, String>> sessionData = new HashMap<String, Map<String, String>>();
        List<String> validPaths = new ArrayList<String>();
        // insert valid paths into validPaths here

        // initialize database/anything you need here

        // create contexts here

        server.setExecutor(null); // process the requests one-by-one
        server.start();
        System.out.println("Server is running on port 8000!");
    }
}