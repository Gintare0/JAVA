import java.util.Random;

public class LuckyTicket {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Paleidimas: java LuckyTicket <6-skaitmenu-bilietas>");
            return;
        }

        String ticket = args[0];

        if (!isCorrect(ticket)) {
            System.out.println("Bilietas neteisingas. Reikia 6 skirtingu skaitmenu.");
        } else {
            if (isLucky(ticket)) {
                System.out.println("Bilietas " + ticket + " yra laimingas.");
            } else {
                System.out.println("Bilietas " + ticket + " nera laimingas.");
            }
        }

        Random random = new Random();
        int count = 0;
        String generated;

        while (true) {
            int number = random.nextInt(1000000);
            generated = String.format("%06d", number);
            count++;

            if (isCorrect(generated) && isLucky(generated)) {
                break;
            }
        }

        System.out.println("Pirmas rastas laimingas bilietas: " + generated);
        System.out.println("Bandymu skaicius: " + count);
    }

    private static boolean isCorrect(String ticket) {
        if (ticket.length() != 6) {
            return false;
        }

        boolean[] used = new boolean[10];

        for (int i = 0; i < 6; i++) {
            char c = ticket.charAt(i);

            if (c < '0' || c > '9') {
                return false;
            }

            int digit = c - '0';
            if (used[digit]) {
                return false;
            }
            used[digit] = true;
        }

        return true;
    }

    private static boolean isLucky(String ticket) {
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < 3; i++) {
            sum1 += ticket.charAt(i) - '0';
            sum2 += ticket.charAt(i + 3) - '0';
        }

        return sum1 == sum2;
    }
}
