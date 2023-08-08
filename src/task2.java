import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Product {
    String name;
    int price;
    int stock;

    public Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}

class InventoryManagement {

    public static List<Product> sortProducts(List<Product> products, String sortKey, boolean ascending) {
        Comparator<Product> comparator;
        switch (sortKey) {
            case "name":
                comparator = Comparator.comparing(product -> product.name);
                break;
            case "price":
                comparator = Comparator.comparingInt(product -> product.price);
                break;
            case "stock":
                comparator = Comparator.comparingInt(product -> product.stock);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort key");
        }

        if (!ascending) {
            comparator = comparator.reversed();
        }

        List<Product> sortedProducts = new ArrayList<>(products);
        Collections.sort(sortedProducts, comparator);
        return sortedProducts;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Product> products = new ArrayList<>();

        boolean addingMoreProducts = true;
        while (addingMoreProducts) {
            System.out.print("Enter name of the product: ");
            String name = scanner.nextLine();
            System.out.print("Enter price of the product: ");
            int price = scanner.nextInt();
            System.out.print("Enter stock of the product: ");
            int stock = scanner.nextInt();
            scanner.nextLine();

            products.add(new Product(name, price, stock));

            System.out.print("Do you want to add more products? (y/n): ");
            String continueInput = scanner.nextLine();
            addingMoreProducts = continueInput.equalsIgnoreCase("y");
        }

        System.out.print("Enter sort key (name, price, stock): ");
        String sortKey = scanner.nextLine();

        System.out.print("Sort in ascending order? (true/false): ");
        boolean ascending = scanner.nextBoolean();
        scanner.nextLine();

        List<Product> sortedProducts = sortProducts(products, sortKey, ascending);

        System.out.println("Sorted Products:");
        for (Product product : sortedProducts) {
            System.out.println(product);
        }

        scanner.close();
    }
}