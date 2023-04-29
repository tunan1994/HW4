import java.util.ArrayList;
import java.util.List;
/**
 Домашняя работа, задача:
 ========================
 import java.util.ArrayList;
 import java.util.Random;

 /**
 Домашняя работа, задача:
 ========================

 a. Даны классы Fruit1, Apple extends Fruit1, Orange extends Fruit1;+++
 b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
 поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
 c. Для хранения фруктов внутри коробки можно использовать ArrayList;
 d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество:
 вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
 e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую
 подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
 Можно сравнивать коробки с яблоками и апельсинами;
 f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
 Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
 Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
 g. Не забываем про метод добавления фрукта в коробку.
 */
public class Main {
    public static void main(String[] args) {

        Box<Apple> boxApple = new Box<>();
        System.out.println("Коробка №1 с яблоками: ");
        boxApple.addFruit(new Apple(1));
        boxApple.addFruit(new Apple(1));
        boxApple.addFruit(new Apple(1));
        boxApple.addFruit(new Apple(1));
        boxApple.addFruit(new Apple(1));
        System.out.println();
        boxApple.boxINFO();
        System.out.println();
        System.out.println();
        System.out.println("Коробка №2 с апельсинами: ");
        Box<Orange> boxOrange = new Box<>();
        boxOrange.addFruit(new Orange(1.5));
        boxOrange.addFruit(new Orange(1.5));
        boxOrange.addFruit(new Orange(1.5));
        boxOrange.addFruit(new Orange(1.5));
        boxOrange.addFruit(new Orange(1.5));
        boxOrange.boxINFO();
        System.out.println();
        System.out.println();
        System.out.println((boxOrange.getCurrentBoxWeight() > boxApple.getCurrentBoxWeight())? "Коробка c апельсинами тяжелее":
                (boxOrange.getCurrentBoxWeight() < boxApple.getCurrentBoxWeight())? "Коробка с яблоками тяжелее": "Коробки равны");
        System.out.println();
        Box<Apple> newBoxApple = new Box<>();
        newBoxApple.addFruit(new Apple(1));
        newBoxApple.addFruit(new Apple(1));
        newBoxApple.addFruit(new Apple(1));
        newBoxApple.addFruit(new Apple(1));
        boxApple.addBox(newBoxApple);
        System.out.println();
        System.out.println();
        boxApple.boxINFO();
        System.out.println();
        System.out.println();
        System.out.println((boxOrange.getCurrentBoxWeight() > boxApple.getCurrentBoxWeight())? "Коробка c апельсинами тяжелее":
                (boxOrange.getCurrentBoxWeight() < boxApple.getCurrentBoxWeight())? "Коробка с яблоками тяжелее": "Коробки равны");
    }
}


abstract class Fruit{

    private double weight;

    public double getWeight() {
        return weight;
    }

    public Fruit(double weight) {
        this.weight = weight;
    }

}


class Apple extends Fruit {
    public Apple(double weight) {
        super(weight);
    }

    @Override
    public String toString() {
        return String.format("Добавили яблоко весом %.1f кг", getWeight());
    }
}

class Orange extends Fruit {
    public Orange(double weight) {

        super(weight);
    }

    @Override
    public String toString() {
        return String.format("Добавили апельсин весом %.1f кг", getWeight());
    }

}


class Box <T extends Fruit>{
    private final double maxBoxCapacity;
    private double currentBoxWeight;

    public Box() {
        this.maxBoxCapacity = 15;
    }

    public double getCurrentBoxWeight() {
        return currentBoxWeight;
    }

    public void setCurrentBoxWeight(double currentBoxWeight) {
        this.currentBoxWeight = currentBoxWeight;
    }

    List<T> fruits = new ArrayList<>();

    public void addFruit(T fruit) {
        if (currentBoxWeight + fruit.getWeight() <= maxBoxCapacity) {
            fruits.add(fruit);
            currentBoxWeight += fruit.getWeight();
        } else {
            System.out.println("В коробке места нет");
        }
    }


    public void boxINFO() {
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println(fruits.get(i));
        }
        System.out.printf("Текущий вес коробки - %.2f кг", currentBoxWeight);
    }

    public void addBox(Box<T> box) {
        if (currentBoxWeight + box.getCurrentBoxWeight() <= maxBoxCapacity) {
            fruits.addAll(box.fruits);
            currentBoxWeight += box.getCurrentBoxWeight();
            System.out.printf("Персыпали в коробку №1, %d фрукта весом %.2f кг.", box.fruits.size(), box.currentBoxWeight);
        } else {
            System.out.println("В коробке места нет");
        }
    }



}
