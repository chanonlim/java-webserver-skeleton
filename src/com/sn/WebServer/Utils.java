package com.sn.WebServer;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import freemarker.template.*;

public class Utils {
    public static byte[] readFile(String fileName) throws IOException {
        byte[] array = Files.readAllBytes(Paths.get(fileName));
        return array;
    }
    static public String genKey(int length) {
        Random random = new Random();

        String generated = random.ints(48, 123)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generated;
    }
    static public Map<String, String> parseCookie(HttpExchange t) {
        if (t.getRequestHeaders().get("Cookie") == null) {
            return new HashMap<String, String>();
        }
        List<String> cookies = t.getRequestHeaders().get("Cookie");
        Map<String, String> cookieMap = new HashMap<String, String>();
        for (String cookie: cookies) {
            List<String> cookieSplit = Arrays.asList(cookie.split("="));
            cookieMap.put(cookieSplit.get(0), cookieSplit.get(1));
        }
        return cookieMap;
    }
    static public void setCookie(HttpExchange t, Map<String, String> cookies) {
        StringBuilder setC = new StringBuilder();

        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            setC.append(key);
            setC.append("=");
            setC.append(value);
            setC.append("; ");
        }

        t.getResponseHeaders().set("Set-Cookie", setC.toString());
    }
    static String convertStreamToString(java.io.InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
    static void renderTemplateAndWrite(Configuration cfg, Map<String, Object> dataModel, String template, OutputStream os) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(template);
        Writer out = new OutputStreamWriter(os);
        temp.process(dataModel, out);

    }
}