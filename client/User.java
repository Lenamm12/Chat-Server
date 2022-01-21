package client;

import java.io.Serializable;

public class User implements Serializable {

    static String Username;

    public static String getName() {
        return Username;
    }

    public static void setName(String name) {
        Username = name;
    }


}