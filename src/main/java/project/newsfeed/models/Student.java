//package project.newsfeed.models;
//
//import java.util.*;
//
//class Student {
//    long date;
//    String name, address;
//
//    public Student(long date, String name, String address) {
//        this.date = date;
//        this.name = name;
//        this.address = address;
//    }
//
//    public String toString() {
//        return this.date + " " + this.name +
//                " " + this.address;
//    }
//}
//
//// Driver class
//class Main {
//    public static void main(String[] args) {
//        List<Student> list = new ArrayList<>();
//        list.add(new Student(111, "bbbb", "london"));
//        list.add(new Student(131, "aaaa", "nyc"));
//        list.add(new Student(121, "cccc", "jaipur"));
//        System.out.println(list);
//        System.out.println("Unsorted");
//        for (Student student : list) System.out.println(student);
//
//        Collections.sort(list, (a,b) -> (int) (a.date - b.date));
//
//        System.out.println("\nSorted by date");
//        for (Student student : list) System.out.println(student);
//
//
//
////        Log N
////        PriorityQueue<Student> heap = new PriorityQueue<>((a,b) -> (int) (a.date - b.date));
//////
//////        heap.addAll(yahooNews);
//////        heap.addAll(cnbcNews);
////
////        List<Student> newList = new ArrayList(heap);
////
////        for (Student s : newList) {
////            System.out.println(s.date);
////        }
//    }
//
//}
