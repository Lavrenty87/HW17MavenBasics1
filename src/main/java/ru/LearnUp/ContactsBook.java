package ru.LearnUp;

import ru.LearnUp.Exception.NotPhoneException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ContactsBook {
    private Map<String, Contact> contacts = new HashMap<>();

    public void add(Contact contact) {
        contacts.put(contact.getPhone(), contact);
    }

    public <T> Contact getByPhone(T phone) {
        Contact contRez = null;
        for (String number : contacts.keySet()) {
            if (number.equals(phone)) {
                contRez = contacts.get(number);
            }
        }
        return contRez;
    }

    public <T> void removeByPhone(T phone) throws NotPhoneException {
        if (!contacts.containsKey(phone)) {
            throw new NotPhoneException("Нет такого номера");
        } else {
            contacts.remove(phone);
        }
    }

    public ArrayList<Contact> getListSortedByName() {
        ArrayList<Contact> list = new ArrayList<>();
        for (String l : contacts.keySet()) {//.entrySet()) {
           /* Long key = map.getKey();
            Contact value = map.getValue();
            list.add(value);*/
            list.add(contacts.get(l));
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public String toString() {
        return "ContactsBook{" +
                "contacts=" + contacts +
                '}';
    }

    // HW11
    public List<Contact> searchBy(Predicate<Contact> predicate) {
        List<Contact> list = new ArrayList();
        for (Contact c : contacts.values()) {
            if (predicate.test(c)) {
                list.add(c);
            }
        }
        Collections.sort(list);
        return list;
    }

    /*HW11*/
    public List<Contact> searchBy(String str) {
        List<Contact> list = null;
        String[] strArr = str.split("\\*");

        if (strArr.length == 2) {
            list = new ArrayList<>(searchBy(contact -> contact.getName().startsWith(strArr[0]) && contact.getName().endsWith(strArr[1])));
        } else {
            list = new ArrayList<>(searchBy(contact -> contact.getName().endsWith(strArr[0])));
        }

        return list;
    }

    //HW12
    /*Возьмите решение предыдущей задачи.

Вы решили узнать с кодами каких стран есть номера в вашей телефонной книге. Будем считать, что номера записываются
в виде “кодстраны-другиецифры”. Вам нужно добавить метод Set<Integer> getCountries(), который соберёт множество из
 кодов стран добавленных номеров контактов. При этом реализацию метода нужно сделать полностью через стримы.*/

    public Set<Integer> getCountries(){
        return contacts.values().stream()
                .map(contact -> Integer.parseInt(contact.getPhone().split("-")[0]))
                .collect(Collectors.toSet());
    }
}