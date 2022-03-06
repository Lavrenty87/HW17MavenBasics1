package ru.LearnUp;

import org.apache.log4j.helpers.Loader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.LearnUp.Exception.NotPhoneException;
import java.util.List;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.debug("Start Ap....");
        ContactsBook myContactsBook = new ContactsBook(); //создал и заполнил книгу контактов
        long firstNamber = 9879571000l;// для удобства
        String strRu = "7-";
        String strBel = "375-";
        String strUk = "380-";
        for (int i = 9; i >= 0; i--) { //в цикле заполняю книгу 10 контактами
            if(i < 4 ) {
                myContactsBook.add(new Contact("Name - " + i, strRu + (firstNamber + i)));
            } else if (i < 7) {
                myContactsBook.add(new Contact("Name - " + i, strBel + (firstNamber + i)));
            } else {
                myContactsBook.add(new Contact("Name - " + i, strUk + (firstNamber + i)));
            }
        }
        myContactsBook.add(new Contact("Сергей", strBel + (firstNamber + 55)));
        myContactsBook.add(new Contact("Алексей", strRu + (firstNamber + 56)));
        myContactsBook.add(new Contact("Андрей", strUk + (firstNamber + 57)));
        myContactsBook.add(new Contact("Тимофей", strBel + (firstNamber + 58)));

        System.out.println("Filled book: " + myContactsBook.toString()); //для контроля заполненная книга контактов

        System.out.println(myContactsBook.getByPhone(firstNamber + 5));// ищу существующий
        System.out.println(myContactsBook.getByPhone(firstNamber + 25)); // шщу не существующий вывод null

        try {
            myContactsBook.removeByPhone(firstNamber + 9); // удаляю существующий
            myContactsBook.removeByPhone(firstNamber + 15); // удаляю не существующий исключение
        } catch (NotPhoneException e) {
            e.printStackTrace();
        }
        System.out.println("After changes: " + myContactsBook.toString()); // печатаю книгу после манипуляций
        List<Contact> listByName = myContactsBook.getListSortedByName(); // получаю список.
        System.out.println("Sort list: " + listByName.toString()); // печатаю список.
        List<Contact> contactList = myContactsBook.searchBy(contact -> contact.getName().contains("2")); // searchBy с predicate
        System.out.println(contactList);
      //  List<Contact> contactList1 = myContactsBook.searchBy("*ей"); // searchBy с String
        List<Contact> contactList1 = myContactsBook.searchBy("А*ей"); // searchBy с String

        System.out.println("Str----------------- \n" + contactList1);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Коды стран в телефонной книге: " + myContactsBook.getCountries());
        LOG.debug("End Ap....");
    }
}
