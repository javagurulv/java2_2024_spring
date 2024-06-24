package diagram;

import java.io.File;
import java.io.IOException;

public class StructurizrStarter {
    public static void main(String[] args) throws IOException {

        int port = 8080;
        if (PortUtil.isPortInUse(port)) {
            System.out.println("Port " + port + " is already in use. Attempting to free the port...");
            if (!PortUtil.freePort(port)) {
                System.err.println("Failed to free port " + port + ". Exiting.");
                return;
            }
        }

        File liteWar = new File("teacher/structurizr/structurizr-lite.war");
        if (!liteWar.exists()) {
            throw new IOException("Structurizr Lite WAR file not found: " + liteWar.getAbsolutePath());
        }
        System.out.println("Structurizr Lite WAR file was found: " + liteWar.getAbsolutePath());

        File configPackage = new File("teacher/step_60/src/main/java/diagram");

        if (!configPackage.exists()) {
            throw new IOException("workspace.dsl not found: " + configPackage.getAbsolutePath());
        }
        System.out.println("workspace.dsl was found: " + configPackage.getAbsolutePath());


        launchStructurizrLite(liteWar.getAbsolutePath(), configPackage.getAbsolutePath());

    }

    private static void launchStructurizrLite(String warPath, String directory) throws IOException {

        ProcessBuilder builder = new ProcessBuilder(
                "java", "\"-Djdk.util.jar.enableMultiRelease=false\"", "-jar", warPath, directory
        );
        builder.redirectErrorStream(true);
        builder.start();
        System.out.println("Structurizr Lite is running at http://localhost:8080");
    }

}
