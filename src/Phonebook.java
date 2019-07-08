import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Phonebook
{
    public static void main(String[] args)
    {
        System.out.println("Список поддерживаемых команд:");
        System.out.println("LIST                      - вывод списка имеющихся телефонных номеров;");
        System.out.println("номер_телефона            - поиск абонента по номеру телефона;");
        System.out.println("Фамилия Имя Отчество      - поиск абонента по ФИО телефона;");
        System.out.println("EXIT                      - завершение работы программы;");

        boolean on = true; //флаг для выхода из бесконечного цикла
        Scanner in = new Scanner(System.in);
        TreeMap<String, String> subscriber = new TreeMap<>(); // K - ФИО, V - номер телефона
        while (on)
        {
            System.out.println("");
            System.out.println("Введите команду!");
            String input = in.nextLine().trim();
            if (input.length() == 0)
            {
                System.out.println("Введено пустое значение");
                continue;
            }
            String phoneParsed = input.replaceAll("[^0-9]", "");
            if(phoneParsed.length() > 0) //если выполняется, то введен телефон. Работаем с Value
            {
                phoneParsed = phoneParsed.replaceFirst("^8", "7"); // заменяем в начале 8 на 7;
                if (subscriber.containsValue(phoneParsed))
                {
                    System.out.println("Введенный телефон уже есть в базе!");
                    printbyKey (subscriber,phoneParsed); //поиск по Value
                    continue;
                }
                else {
                    System.out.println("Введенный телефон в базе отсутствует!");
                    System.out.println("Введите ФИО нового абонента:");
                    String fio = in.nextLine().trim();
                    while (fio.length() == 0)
                    {
                        System.out.println("Введено пустое значение!");
                        System.out.println("Введите ФИО нового абонента:");
                        fio = in.nextLine().trim();
                    }
                    fio = fio.replaceAll("\\s+", " "); //убираем лишние пробелы
                    subscriber.put(fio, phoneParsed); //добавляем абонента в базу
                    System.out.println("Абонент успешно добавлен в базу!");
                    continue;
                }
            }
            if (input.equals("LIST"))
            {
                if(subscriber.size() == 0)
                {
                    System.out.println("База абонентов пуста!");
                    continue;
                }
                printPhonebook(subscriber);
                continue;
            }
            if (input.equals("EXIT"))
            {
                on = false;
                continue;
            }
            String fio = input.replaceAll("\\s+", " ");
            if(subscriber.containsKey(fio))
            {
                System.out.println("Данный абонент уже есть в базе!");
                System.out.println("тел.:" + subscriber.get(fio));
            }
            else
            {
                System.out.println("Указанный абоненет в базе отсутствует!");
                System.out.println("Введите номер телефона нового абонента:");
                phoneParsed = in.nextLine().trim();
                while(phoneParsed.length() == 0)
                {
                    System.out.println("Введено пустое значение!");
                    System.out.println("Введите номер телефона нового абонента:");
                    phoneParsed = in.nextLine().trim();
                }
                phoneParsed = phoneParsed.replaceAll("[^0-9]", "");
                phoneParsed = phoneParsed.replaceFirst("^8", "7");
                subscriber.put(fio,phoneParsed);
                System.out.println("Абонент успешно добавлен в базу!");
            }
        }
    }
    public static void printPhonebook(Map<String, String> map)
    {
        for(String fio : map.keySet())
        {
            System.out.println("ФИО абонента: " + fio + " " + "тел.:" + map.get(fio));
        }
    }
    public static void printbyKey(Map<String, String> map, String phoneNumber)
    {
        for(String fio : map.keySet())
        {
            if(map.get(fio).equals(phoneNumber))
            {
                System.out.println("ФИО абонента: " + fio + " " + "тел.:" + map.get(fio));
            }
        }
    }
}
