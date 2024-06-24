package diagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PortUtil {

    /**
     * Проверяет, занят ли указанный порт.
     *
     * @param port Номер порта для проверки.
     * @return true, если порт занят, иначе false.
     */
    public static boolean isPortInUse(int port) {
        try (ServerSocket socket = new ServerSocket(port)) {
            socket.setReuseAddress(true);
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    /**
     * Освобождает указанный порт, если он занят.
     *
     * @param port Номер порта для освобождения.
     * @return true, если порт успешно освобожден, иначе false.
     */
    public static boolean freePort(int port) {
        String os = System.getProperty("os.name").toLowerCase();
        Process process = null;
        try {
            if (os.contains("win")) {
                // Команда для Windows
                process = new ProcessBuilder("cmd", "/c", "netstat -ano | findstr :" + port).start();
            } else {
                // Команда для Unix-подобных систем
                process = new ProcessBuilder("sh", "-c", "lsof -i :" + port).start();
            }

            // Чтение вывода команды
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Process using port: " + line);
                    // Получение PID и завершение процесса
                    int pid = getPidFromLine(line);
                    if (pid != -1) {
                        killProcess(pid);
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

    // Получение PID из строки вывода команды
    private static int getPidFromLine(String line) {
        Pattern pattern = Pattern.compile("\\s*(\\d+)\\s*$");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return -1;
    }

    // Убийство процесса по PID
    private static void killProcess(int pid) {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                new ProcessBuilder("taskkill", "/PID", String.valueOf(pid), "/F").start();
            } else {
                new ProcessBuilder("kill", "-9", String.valueOf(pid)).start();
            }
            System.out.println("Process with PID " + pid + " has been terminated.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        freePort(8080);
    }
}
