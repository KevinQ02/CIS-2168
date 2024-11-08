import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ArrayListEx {

    // #1 Uniqueness
    public static <E> boolean unique(List<E> list) {
        return new HashSet<>(list).size() == list.size();
        //hashset allows for no dups to be seen inside the list
    }

    // #2 All Multiples
    public static List<Integer> allMultiples(List<Integer> list, int n) {
        return list.stream().filter(x -> x % n == 0).collect(Collectors.toList());
    }

    // #3 All Strings of Size
    public static List<String> allStringsOfSize(List<String> list, int length) {
        return list.stream().filter(s -> s.length() == length).collect(Collectors.toList());
    }

    //#4 isPermutation
    public static <E> boolean isPermutation(List<E> listA, List<E> listB) {
        if(listA.size() != listB.size()) {
            return false;
        }

        for(E item : listA){
            int countA = 0;
            int countB = 0;

            for (int i = 0; i < listA.size(); i++) {
                E other = listA.get(i);
                if(item.equals(other)){
                    countA++;
                }
            }

            for (int i = 0; i < listB.size(); i++) {
                E other = listB.get(i);
                if(item.equals(other)){
                    countB++;
                }
            }

            if(countA != countB) {
                return false;
            }
        }

        return true;
    }

    //#5 String To List of Words
    public static List<String> StringToListofWords(String str){
        return new ArrayList<>(List.of(str.split("\\s+")));
    }

    //#6 Remove All Instances
    public static <E> void RemoveAllInstances(List<E> list, E item){
        list.removeIf(e -> e.equals(item));
        // removes all instance e when e is seen in the list
    }

    //test 1-6
    public static void main(String[] args) {
        // 1 Uniqueness Test
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        System.out.println("Unique: " + unique(numbers));

        // #2 All Multiples Test
        List<Integer> num = List.of(1, 25, 2, 5, 30, 19, 57, 2, 25);
        List<Integer> multiples = allMultiples(num, 5);
        System.out.println("Multiples: " + multiples);

        // #3 All Strings of Size Test
        List<String> words = List.of("I", "like", "to", "eat","eat","eat", "apples", "and", "bananas");
        List<String> sizeWords = allStringsOfSize(words, 3);
        System.out.println("3 letter words: " + sizeWords);

        // #4 isPermutation Test
        List<Integer> perm1 = List.of(1, 2, 4);
        List<Integer> perm2 = List.of(2, 1, 4);
        System.out.println("permutation: " + isPermutation(perm1, perm2));

        // #5 String To List of Words Test
        String sentence = "Hello world!";
        List<String> wordList = StringToListofWords(sentence);
        System.out.println("List of words: " + wordList);

        // #6 Remove All Instances Test
        List<Integer> numList = new ArrayList<>(List.of(1, 2, 3, 4, 5, 3, 3));
        RemoveAllInstances(numList, 5);
        System.out.println("5s removed: " + numList);
    }
}