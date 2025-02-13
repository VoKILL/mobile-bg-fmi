import ads.*;
import notifications.*;
import notifications.adapters.*;
import search.*;
import vehicles.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Вътрешен клас за съхранение на създадените филтри с описание
    static class FilterOption {
        String description;
        Filter<Ad> filter;

        public FilterOption(String description, Filter<Ad> filter) {
            this.description = description;
            this.filter = filter;
        }
    }

    public static void main(String[] args) {
        // Инициализация на услугите
        ProductService productService = new ProductServiceImpl();
        AdServiceImpl adService = new AdServiceImpl();

        // Създаваме NotificationService и го задаваме на adService
        NotificationService notificationService = new NotificationService();
        adService.setNotificationService(notificationService);

        // Създаваме дефолтни продукти
        Product car1 = new Car(null, "A4", "Audi", 2012, 550);
        Product car2 = new Car(null, "X5", "BMW", 2020, 600);
        Product truck1 = new Truck(null, "Actros", "Mercedes", 2018, 1200);
        Product car3 = new Car(null, "A6", "Audi", 2015, 700);
        Product truck2 = new Truck(null, "FH16", "Volvo", 2016, 1500);
        Product car4 = new Car(null, "Civic", "Honda", 2010, 500);
        Product car7 = new Car(null, "Corolla", "Toyota", 2018, 400);
        Product car8 = new Car(null, "Model S", "Tesla", 2022, 300);
        Product truck3 = new Truck(null, "F-750", "Ford", 2017, 800);
        Product car9 = new Car(null, "Impreza", "Subaru", 2016, 450);
        Product truck4 = new Truck(null, "Actros LT", "Mercedes", 2019, 1300);

        productService.createProduct(car1);
        productService.createProduct(car2);
        productService.createProduct(truck1);
        productService.createProduct(car3);
        productService.createProduct(truck2);
        productService.createProduct(car4);
        productService.createProduct(car7);
        productService.createProduct(car8);
        productService.createProduct(truck3);
        productService.createProduct(car9);
        productService.createProduct(truck4);

        // Създаваме дефолтни обяви
        Ad ad1 = new Ad(null, car1, 25000.0, "Audi A4 - Отлично състояние!");
        Ad ad2 = new Ad(null, car2, 30000.0, "BMW X5 - Луксозен SUV");
        Ad ad3 = new Ad(null, truck1, 50000.0, "Mercedes Actros за транспорт");
        Ad ad4 = new Ad(null, car3, 27000.0, "Audi A6 - Перфектен седан");
        Ad ad5 = new Ad(null, truck2, 60000.0, "Volvo FH16 - Мощен камион");
        Ad ad6 = new Ad(null, car4, 15000.0, "Honda Civic - Икономичен и практичен");
        Ad ad7 = new Ad(null, car7, 18000.0, "Toyota Corolla - Надежден и икономичен!");
        Ad ad8 = new Ad(null, car8, 80000.0, "Tesla Model S - Електрически лукс и иновация!");
        Ad ad9 = new Ad(null, truck3, 45000.0, "Ford F-750 - Идеален за тежки товари!");
        Ad ad10 = new Ad(null, car9, 22000.0, "Subaru Impreza - Спортна и сигурна!");
        Ad ad11 = new Ad(null, truck4, 70000.0, "Mercedes Actros LT - Нов модел с висока ефективност!");

        adService.createAd(ad1);
        adService.createAd(ad2);
        adService.createAd(ad3);
        adService.createAd(ad4);
        adService.createAd(ad5);
        adService.createAd(ad6);
        adService.createAd(ad7);
        adService.createAd(ad8);
        adService.createAd(ad9);
        adService.createAd(ad10);
        adService.createAd(ad11);

        // Списък за съхранение на филтрите, създадени от потребителя
        List<FilterOption> userFilters = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\n==== Главно Меню ====");
            System.out.println("1. Показване на всички обяви");
            System.out.println("2. Създаване на нова обява");
            System.out.println("3. Създаване на нов филтър");
            System.out.println("4. Прилагане на филтър върху обявите");
            System.out.println("5. Създаване на правило за нотификации");
            System.out.println("6. Изход");
            int mainChoice = readInt(scanner, "Вашият избор: ");

            switch (mainChoice) {
                case 1:
                    showAllAds(adService, scanner, userFilters);
                    break;
                case 2:
                    createNewAd(productService, adService, scanner);
                    break;
                case 3:
                    createNewFilter(userFilters, scanner);
                    break;
                case 4:
                    applyFilter(adService, userFilters, scanner);
                    break;
                case 5:
                    createNotificationRule(notificationService, userFilters, scanner);
                    break;
                case 6:
                    exit = true;
                    System.out.println("Изход от програмата.");
                    break;
                default:
                    System.out.println("Невалиден избор. Моля опитайте отново.");
                    break;
            }
        }
        scanner.close();
    }

    // Метод за създаване на правило за нотификации (интерактивно)
    private static void createNotificationRule(NotificationService notificationService, List<FilterOption> userFilters, Scanner scanner) {
        System.out.println("\n=== Създаване на правило за нотификации ===");
        if (userFilters.isEmpty()) {
            System.out.println("Няма създадени филтри. Моля първо създайте филтър, който ще използвате за правило за нотификации.");
            return;
        }

        // Избор на тип нотификатор
        System.out.println("Изберете тип нотификатор:");
        System.out.println("1. SMS нотификатор");
        System.out.println("2. Email нотификатор");
        System.out.println("3. Pigeon нотификатор");
        int notifierType = readInt(scanner, "Вашият избор: ");

        Notifier notifier = null;
        switch (notifierType) {
            case 1:
                String phone = readNonEmptyString(scanner, "Въведете телефонен номер: ");
                notifier = new SmsNotifierAdapter(phone);
                break;
            case 2:
                String email = readNonEmptyString(scanner, "Въведете email адрес: ");
                String title = readNonEmptyString(scanner, "Въведете title: ");
                notifier = new EmailNotifierAdapter(email, title);
                break;
            case 3:
                String address = readNonEmptyString(scanner, "Въведете адрес: ");
                int number = readInt(scanner, "Въведете номер на Pigeon: ");
                notifier = new PigeonNotifierAdapter(address, number);
                break;
            default:
                System.out.println("Невалиден избор за тип нотификатор.");
                return;
        }

        // Избор на филтър, който ще свързва правилото
        System.out.println("Изберете филтър, който ще служи като условие за нотификация:");
        for (int i = 0; i < userFilters.size(); i++) {
            System.out.println((i + 1) + ". " + userFilters.get(i).description);
        }
        int filterIndex = readInt(scanner, "Вашият избор: ") - 1;
        if (filterIndex < 0 || filterIndex >= userFilters.size()) {
            System.out.println("Невалиден избор на филтър.");
            return;
        }

        // Създаваме правилото за нотификации и го добавяме в NotificationService
        NotificationRule rule = new NotificationRule(userFilters.get(filterIndex).filter, notifier);
        notificationService.addRule(rule);
        System.out.println("Правилото за нотификации беше успешно създадено с филтър: "
                + userFilters.get(filterIndex).description);
    }

    // Помощни методи за въвеждане с ерор хендлинг
    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден вход. Моля въведете цяло число.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден вход. Моля въведете число (например 25000 или 30000.0).");
            }
        }
    }

    private static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Входът не може да бъде празен. Моля опитайте отново.");
        }
    }

    // Метод за показване на всички обяви (с обобщена визуализация)
    private static void showAllAds(AdServiceImpl adService, Scanner scanner, List<FilterOption> userFilters) {
        List<Ad> allAds = adService.findAds(ad -> true);
        System.out.println("\n=== Всички обяви ===");
        if (allAds.isEmpty()) {
            System.out.println("Няма обяви за показване.");
        } else {
            for (int i = 0; i < allAds.size(); i++) {
                printAdSummary(allAds.get(i), i + 1);
            }
            // Предлагаме възможност за преглед на детайлите на конкретна обява
            System.out.print("\nВъведете номера на обявата за подробен преглед или натиснете Enter за връщане в менюто: ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    int index = Integer.parseInt(input);
                    if (index >= 1 && index <= allAds.size()) {
                        printAdDetails(allAds.get(index - 1));
                    } else {
                        System.out.println("Невалиден номер на обява.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Невалиден вход.");
                }
            }
        }
        System.out.println("Натиснете Enter за връщане към главното меню...");
        scanner.nextLine();
    }

    // Метод за създаване на нова обява с подобрен ерор хендлинг
    private static void createNewAd(ProductService productService, AdServiceImpl adService, Scanner scanner) {
        System.out.println("\n=== Създаване на нова обява ===");
        int typeChoice = 0;
        while (true) {
            System.out.println("Изберете тип превозно средство:");
            System.out.println("1. Кола");
            System.out.println("2. Камион");
            typeChoice = readInt(scanner, "Вашият избор: ");
            if (typeChoice == 1 || typeChoice == 2) {
                break;
            } else {
                System.out.println("Невалиден избор. Моля изберете 1 или 2.");
            }
        }

        Product product = null;
        // Въведете име (аналог на модел)
        String name = readNonEmptyString(scanner, "Въведете модел: ");
        String brand = readNonEmptyString(scanner, "Въведете марка: ");
        int year = readInt(scanner, "Въведете година: ");
        double range = readDouble(scanner, "Въведете пробег: ");

        if (typeChoice == 1) {
            product = new Car(null, name, brand, year, range);
        } else if (typeChoice == 2) {
            product = new Truck(null, name, brand, year, range);
        }
        productService.createProduct(product);

        double price = readDouble(scanner, "Въведете цена за обявата: ");
        String description = readNonEmptyString(scanner, "Въведете описание за обявата: ");

        Ad newAd = new Ad(null, product, price, description);
        adService.createAd(newAd);
        System.out.println("Обявата беше успешно създадена.");
        System.out.println("Натиснете Enter за връщане към главното меню...");
        scanner.nextLine();
    }

    // Метод за създаване на нов филтър (основен или композитен)
    private static void createNewFilter(List<FilterOption> userFilters, Scanner scanner) {
        System.out.println("\n=== Създаване на нов филтър ===");
        System.out.println("Изберете тип филтър:");
        System.out.println("1. Основен филтър");
        System.out.println("2. Композитен филтър");
        int filterTypeChoice = readInt(scanner, "Вашият избор: ");

        if (filterTypeChoice == 1) {
            createBasicFilter(userFilters, scanner);
        } else if (filterTypeChoice == 2) {
            createCompositeFilter(userFilters, scanner);
        } else {
            System.out.println("Невалиден избор за тип филтър.");
        }
        System.out.println("Натиснете Enter за връщане към главното меню...");
        scanner.nextLine();
    }

    // Метод за създаване на основен филтър
    private static void createBasicFilter(List<FilterOption> userFilters, Scanner scanner) {
        System.out.println("\nИзберете тип основен филтър:");
        System.out.println("1. Филтър по марка");
        System.out.println("2. Филтър по диапазон на година");
        System.out.println("3. Филтър по диапазон на цена");
        int basicChoice = readInt(scanner, "Вашият избор: ");

        Filter<Ad> filter = null;
        String description = "";
        switch (basicChoice) {
            case 1:
                String brand = readNonEmptyString(scanner, "Въведете марка за филтъра: ");
                filter = new CaseInsensitiveFilter<>(ad -> ad.getProduct().getBrand(), brand);
                description = "Филтър по марка: " + brand;
                break;
            case 2:
                int minYear = readInt(scanner, "Въведете минимална година: ");
                int maxYear = readInt(scanner, "Въведете максимална година: ");
                filter = new RangeFilter<>(ad -> ad.getProduct().getYear(), minYear, maxYear);
                description = "Филтър по година: от " + minYear + " до " + maxYear;
                break;
            case 3:
                double minPrice = readDouble(scanner, "Въведете минимална цена: ");
                double maxPrice = readDouble(scanner, "Въведете максимална цена: ");
                filter = new RangeFilter<>(Ad::getPrice, minPrice, maxPrice);
                description = "Филтър по цена: от " + minPrice + " до " + maxPrice;
                break;
            default:
                System.out.println("Невалиден избор за основен филтър.");
                return;
        }
        userFilters.add(new FilterOption(description, filter));
        System.out.println("Филтърът беше успешно създаден: " + description);
    }

    // Метод за създаване на композитен филтър чрез комбиниране на вече създадени филтри
    private static void createCompositeFilter(List<FilterOption> userFilters, Scanner scanner) {
        if (userFilters.isEmpty()) {
            System.out.println("Няма налични основни филтри за комбиниране. Моля, създайте основни филтри първо.");
            return;
        }
        System.out.println("\nИзберете тип композитен филтър:");
        System.out.println("1. AND филтър");
        System.out.println("2. OR филтър");
        System.out.println("3. N от M филтър");
        int compositeChoice = readInt(scanner, "Вашият избор: ");

        // Показваме наличните филтри
        System.out.println("\nНалични филтри:");
        for (int i = 0; i < userFilters.size(); i++) {
            System.out.println((i + 1) + ". " + userFilters.get(i).description);
        }
        int count = readInt(scanner, "Колко филтъра искате да комбинирате? ");
        List<Filter<Ad>> selectedFilters = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int index = readInt(scanner, "Изберете номер на филтър " + (i + 1) + ": ") - 1;
            if (index >= 0 && index < userFilters.size()) {
                selectedFilters.add(userFilters.get(index).filter);
            } else {
                System.out.println("Невалиден номер. Пропускане на този филтър.");
            }
        }
        if (selectedFilters.isEmpty() || selectedFilters.size() < count) {
            System.out.println("Не са избрани валидни филтри. Отказ.");
            return;
        }
        Filter<Ad> compositeFilter = null;
        String description = "";
        switch (compositeChoice) {
            case 1:
                compositeFilter = new AndFilter<>(selectedFilters);
                description = "AND филтър комбиниращ " + selectedFilters.size() + " филтъра";
                break;
            case 2:
                compositeFilter = new OrFilter<>(selectedFilters);
                description = "OR филтър комбиниращ " + selectedFilters.size() + " филтъра";
                break;
            case 3:
                int n = readInt(scanner, "Въведете необходимия брой съвпадения (N): ");
                compositeFilter = new NOfMFilter<>(n, selectedFilters);
                description = "N от M филтър (N=" + n + ") комбиниращ " + selectedFilters.size() + " филтъра";
                break;
            default:
                System.out.println("Невалиден избор за тип композитен филтър.");
                return;
        }
        userFilters.add(new FilterOption(description, compositeFilter));
        System.out.println("Композитният филтър беше успешно създаден: " + description);
    }

    // Метод за прилагане на филтър върху обявите
    private static void applyFilter(AdServiceImpl adService, List<FilterOption> userFilters, Scanner scanner) {
        if (userFilters.isEmpty()) {
            System.out.println("Няма създадени филтри. Моля, създайте филтър първо.");
            return;
        }
        System.out.println("\n=== Прилагане на филтър ===");
        System.out.println("Налични филтри:");
        for (int i = 0; i < userFilters.size(); i++) {
            System.out.println((i + 1) + ". " + userFilters.get(i).description);
        }
        int filterIndex = readInt(scanner, "Изберете номер на филтър за прилагане: ") - 1;
        if (filterIndex < 0 || filterIndex >= userFilters.size()) {
            System.out.println("Невалиден избор на филтър.");
            return;
        }
        Filter<Ad> chosenFilter = userFilters.get(filterIndex).filter;
        List<Ad> filteredAds = adService.findAds(chosenFilter);
        System.out.println("\n=== Обяви след прилагане на филтър: "
                + userFilters.get(filterIndex).description + " ===");
        if (filteredAds.isEmpty()) {
            System.out.println("Няма обяви, които да отговарят на филтъра.");
        } else {
            for (int i = 0; i < filteredAds.size(); i++) {
                printAdSummary(filteredAds.get(i), i + 1);
            }
            // Предлагаме възможност за детайлен преглед
            System.out.print("\nВъведете номера на обявата за подробен преглед или натиснете Enter за връщане към менюто: ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    int index = Integer.parseInt(input);
                    if (index >= 1 && index <= filteredAds.size()) {
                        printAdDetails(filteredAds.get(index - 1));
                    } else {
                        System.out.println("Невалиден номер на обява.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Невалиден вход.");
                }
            }
        }
        System.out.println("Натиснете Enter за връщане към главното меню...");
        scanner.nextLine();
    }

    // Метод за показване на обобщена информация за обява (без описание)
    private static void printAdSummary(Ad ad, int index) {
        Product product = ad.getProduct();
        String type = "Продукт";
        if (product instanceof Car) {
            type = "Кола";
        } else if (product instanceof Truck) {
            type = "Камион";
        }
        // Извеждаме: номер, марка, модел (name), година, цена и пробег (range)
        System.out.printf("%d. %s %s (%d) - Цена: %.2f, Пробег: %.2f\n",
                index,
                product.getBrand(),
                product.getName(),
                product.getYear(),
                ad.getPrice(),
                product.getRange());
    }

    // Метод за показване на подробна информация за конкретна обява (включително описание и всички полета)
    private static void printAdDetails(Ad ad) {
        Product product = ad.getProduct();
        String type = "Продукт";
        if (product instanceof Car) {
            type = "Кола";
        } else if (product instanceof Truck) {
            type = "Камион";
        }
        System.out.println("\n===================================");
        System.out.println("Детайли на обявата:");
        System.out.println("Тип: " + type);
        System.out.println("Марка: " + product.getBrand());
        System.out.println("Модел: " + product.getName());
        System.out.println("Година: " + product.getYear());
        System.out.println("Пробег: " + product.getRange());
        System.out.println("Цена: " + ad.getPrice());
        System.out.println("Описание: " + ad.getDescription());
        System.out.println("===================================");
        System.out.println("Натиснете Enter за връщане към менюто...");
        new Scanner(System.in).nextLine();
    }
}
