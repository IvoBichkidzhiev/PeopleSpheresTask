package people.spheres.demo2.constants;

public class Constants {
    private static final String ALLOWED_ORIGIN = "https://localhost";

    private static final String ALLOWED_ORIGIN_IP = "https://127.0.0.1";

    private static final String PORT = "9443";

    public static String LOCALHOST_AND_PORT = String.format("%s:%s", ALLOWED_ORIGIN, PORT);
    public static String LOCALHOST_IP_AND_PORT = String.format("%s:%s", ALLOWED_ORIGIN_IP, PORT);

    public static final String ADD_EMAIL_COMMAND = "addemail:";
}
