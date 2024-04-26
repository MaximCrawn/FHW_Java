import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ToyRaffleApp {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить игрушку");
            System.out.println("2. Изменить вес игрушки");
            System.out.println("3. Разыграть приз");
            System.out.println("4. Получить приз");
            System.out.println("5. Выйти");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (choice) {
                    case 1:
                        System.out.println("Введите ID:");
                        int id = scanner.nextInt();
                        scanner.nextLine(); 
                        
                        System.out.println("Введите название:");
                        String name = scanner.nextLine();
                        
                        System.out.println("Введите количество:");
                        int quantity = scanner.nextInt();
                        
                        System.out.println("Введите вес:");
                        double weight = scanner.nextDouble();
                        
                        Toy newToy = new Toy(id, name, quantity, weight);
                        ToyFileWriter.writeToyToFile(newToy);
                        toyStore.getToys().add(newToy);
                        break;

                    case 2:
                        // Изменение веса
                        System.out.println("Введите ID игрушки, вес которой вы хотите изменить:");
                        int toyId = scanner.nextInt();
                        scanner.nextLine(); 
                        
                        System.out.println("Введите новый вес:");
                        double newWeight = scanner.nextDouble();
                        
                        ToyFileWriter.updateToyWeight(toyStore.getToys(), toyId, newWeight);
                        break;

                    case 3:
                        // Розыгрыш приза
                        Toy prize = toyStore.drawPrize();
                        
                        if (prize == null) {
                            continue; 
                        }
                        
                        System.out.println("Приз разыгран: " + prize.getName());
                        break;

                    case 4:
                        // Получение приза
                        System.out.println("Вы получили приз: ");
                        List<Toy> prizes = toyStore.getPrizes();
                        
                        if (prizes.isEmpty()) {
                            System.out.println("Нет выигранных призов.");
                        } else {
                            for (Toy p : prizes) {
                                System.out.println(p);
                            }
                            prizes.clear(); 
                            ToyFileWriter.updatePrizeFile(prizes); 
                        }
                        break;

                    case 5:
                        System.out.println("Выход из программы.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Неправильный выбор. Пожалуйста, введите число от 1 до 5.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Введен некорректный символ. Пожалуйста, введите число от 1 до 5.");
                scanner.nextLine(); // Очищаем ошибочный ввод
            }
        }
            
    }
}
