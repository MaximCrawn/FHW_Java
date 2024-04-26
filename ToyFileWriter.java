import java.io.*;
import java.util.*;

public class ToyFileWriter {
    private static final String TOYS_FILE = "toys.txt";
    private static final String PRIZES_FILE = "prizes.txt";

    // Запись одной игрушки в файл
    public static void writeToyToFile(Toy toy) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TOYS_FILE, true))) {
            bw.write(toy.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Toy> readToysFromFile() {
        List<Toy> toys = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(TOYS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                int id = Integer.parseInt(parts[0].split(": ")[1]);
                String name = parts[1].split(": ")[1];
                int quantity = Integer.parseInt(parts[2].split(": ")[1]);
                double weight = Double.parseDouble(parts[3].split(": ")[1]);
                if (quantity > 0) { 
                    toys.add(new Toy(id, name, quantity, weight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toys;
    }

    public static void writeToysToFile(List<Toy> toys) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TOYS_FILE))) {
            for (Toy toy : toys) {
                if (toy.getQuantity() > 0) { 
                    bw.write("ID: " + toy.getId() + ", Название: " + toy.getName() + ", Количество: " + toy.getQuantity() + ", Вес: " + toy.getWeight());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    public static void updateToyWeight(List<Toy> toys, int toyId, double newWeight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(newWeight);
                updateToyFile(toys);
                break;
            }
        }
    }

    // Обновление файла с игрушками
    public static void updateToyFile(List<Toy> toys) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TOYS_FILE, false))) {
            for (Toy toy : toys) {
                bw.write(toy.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Обновление файла с призами
    public static void updatePrizeFile(List<Toy> prizes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PRIZES_FILE, false))) {
            for (Toy prize : prizes) {
                bw.write("ID: " + prize.getId() + ", Название: " + prize.getName() + ", Количество: " + prize.getQuantity());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Чтение призов из файла
    public static List<Toy> readPrizesFromFile() {
        List<Toy> prizes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PRIZES_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",\\s+");
                int id = Integer.parseInt(parts[0].replace("ID: ", ""));
                String name = parts[1].replace("Название: ", "");
                int quantity = Integer.parseInt(parts[2].replace("Количество: ", ""));
                prizes.add(new Toy(id, name, quantity, 0.0)); 
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return prizes;
    }
}
