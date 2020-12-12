import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

class SortingStudentsByGPA implements Comparator<Student> {
    @Override
    public int compare(Student a, Student b) {
        if (a.getResults() > b.getResults()) {
            return -1;
        } else if (a.getResults() < b.getResults()) {
            return 1;
        } else {
            return 0;
        }
    }

}


class Student {
    private int idNumber;
    private int results;

    Student() {
        Random random = new Random();
        idNumber = random.nextInt(60);
        results = random.nextInt(6);
    }

    int getIdNumber() {
        return idNumber;
    }
    void setIdNumber(int newIdNumber){
        idNumber = newIdNumber;
    }

    int getResults() {
        return results;
    }
}

public class Solution {
    static int partition(Student[] array, int begin, int end) {     //Quick Sort
        int pivot = end;

        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (array[i].getIdNumber() < array[pivot].getIdNumber()) {
                int temp = array[counter].getIdNumber();
                array[counter].setIdNumber(array[i].getIdNumber());
                array[i].setIdNumber(temp);
                counter++;
            }
        }
        int temp = array[pivot].getIdNumber();
        array[pivot].setIdNumber(array[counter].getIdNumber());
        array[counter].setIdNumber(temp);

        return counter;
    }

    public static void quickSort(Student[] array, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot-1);
        quickSort(array, pivot+1, end);
    }

    static Student [] SortingByIdNumber(Student[] array){        //Quick Sort call
        quickSort(array, 0, array.length-1);
        return array;
    }
    public static void main(String[] args) {
        Student[] students1 = new Student[15];
        Student[] students2 = new Student[15];
        for (int i = 0; i < 15; i++) {
            students1[i] = new Student();
            students2[i] = new Student();
        }

        SortingByIdNumber(students1);                          //Quick Sort call for idNumber
        SortingByIdNumber(students2);

        for (int i=1; i < 15; i++){
            if (students1[i].getIdNumber() == students1[i-1].getIdNumber()){
                students1[i].setIdNumber(students1[i].getIdNumber() + 60 + i);
            }
            if (students2[i].getIdNumber() == students2[i-1].getIdNumber()){
                students2[i].setIdNumber(students2[i].getIdNumber() + 60 + i);
            }
        }

        SortingByIdNumber(students1);
        SortingByIdNumber(students2);

        for (int i=0; i < 15; i++){                       //Output for sort array
            System.out.println(students1[i].getIdNumber() + "        " + students2[i].getIdNumber());
        }

        Arrays.sort(students1, new SortingStudentsByGPA()); //Sort array by average score
        Arrays.sort(students2, new SortingStudentsByGPA());

        Student[] students = new Student[30];               //Merge sort arrays
        int i = 0, j = 0;
        while (i < 15 || j < 15) {
            if (i == 15) {
                students[i + j] = students2[j];
                j++;
            } else if (j == 15) {
                students[i + j] = students1[i];
                i++;
            } else if (students1[i].getResults() > students2[j].getResults()) {
                students[i + j] = students1[i];
                i++;
            } else {
                students[i + j] = students2[j];
                j++;
            }
        }

        for (int k = 0; k < 30; k++) {                 //Output for sort array by average score
            System.out.println(students[k].getIdNumber() + "             " + students[k].getResults());
        }
    }
}