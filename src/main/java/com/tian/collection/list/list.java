package com.tian.collection.list;

import java.util.*;

public class list {

    public static void main(String args[])throws Exception{

      /* List aList = new ArrayList<Integer>();
        String[] arrTest = new String[]{};
        aList.add(18);
        aList.add(17);
        aList.add(14);
        aList.add(19);
        aList.add(1);
        aList.add(8);
        aList.add(9);
        aList.add(16);
        Collections.sort(aList);
       Iterator iterator = aList.iterator();
       while(iterator.hasNext()){
           System.out.println(iterator.next());
       }

       ListIterator listIterator = aList.listIterator();
*/

        Person person1 = new Person(11);
        Person person2 = new Person(1);
        Set<Person> people = new TreeSet<>(new Person());
        people.add(person1);
        people.add(person2);
        Iterator<Person> iterator = people.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }


}
