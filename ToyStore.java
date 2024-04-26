import java.util.*;

public class ToyStore {
    private List<Toy> toys;
    private List<Toy> prizes;

    public ToyStore() {
        this.toys = ToyFileWriter.readToysFromFile();
        this.prizes = ToyFileWriter.readPrizesFromFile();
    }

    public List<Toy> getToys() {
        return this.toys;
    }

    public List<Toy> getPrizes() {
        return this.prizes;
    }

    public void decreaseToyQuantity(int toyId) {
        Toy toRemove = null;
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                int currentQuantity = toy.getQuantity();
                if (currentQuantity > 0) {
                    toy.setQuantity(currentQuantity - 1);
                    if (toy.getQuantity() == 0) {
                        toRemove = toy; // Если количество 0, помечаем для удаления
                    }
                    break;
                }
            }
        }
        
        if (toRemove != null) {
            toys.remove(toRemove); // Удаляем игрушку с нулевым количеством
        }
        
        ToyFileWriter.updateToyFile(toys); // Обновляем файл с игрушками
    }

    public Toy drawPrize() {
        // Отфильтровываем игрушки с нулевым количеством
        List<Toy> availableToys = new ArrayList<>();
        for (Toy toy : toys) {
            if (toy.getQuantity() > 0) {
                availableToys.add(toy);
            }
        }

        if (availableToys.isEmpty()) {
            if (prizes.isEmpty()) {
                System.out.println("Нет игрушек для розыгрыша. Добавьте игрушки для продолжения розыгрыша.");
            } else {
                System.out.println("Нет игрушек для розыгрыша. Получите выигранные призы.");
            }
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(availableToys.size());
        Toy prize = availableToys.get(randomIndex);
        
        decreaseToyQuantity(prize.getId());

        // Добавляем в призы или увеличиваем количество, если приз уже есть
        boolean found = false;
        for (Toy existingPrize : prizes) {
            if (existingPrize.getId() == prize.getId()) {
                existingPrize.setQuantity(existingPrize.getQuantity() + 1);
                found = true;
                break;
            }
        }

        if (!found) {
            prizes.add(new Toy(prize.getId(), prize.getName(), 1, 0.0)); // Вес не важен для призов
        }

        ToyFileWriter.updatePrizeFile(prizes); // Обновляем файл с призами
        return prize;
    }
}
