public class BinarySearch {
    public static int[] search (Entry[] entries, String searchableName) {
        
        int low = 0;
        int high = entries.length - 1;
        int[] result = {-1, -1};
        
        
        while (low <= high) {

            int mid = (low + high) / 2;
            int cmp = entries[mid].getName().compareTo(searchableName);

            if (cmp < 0) {
                low = mid + 1;

            } else if (cmp > 0) {
                high = mid - 1;

            } else {
                result[0]= findStartIndex(entries, searchableName, low, mid); 
                result[1] = findEndIndex(entries, searchableName, mid, high); 
                break;
            }
        }    
           
        return result;
    }


    private static int findStartIndex(Entry[] entries, String searchableName, int low, int high) {  

        while (low < high) {
            int mid = (low + high) / 2;

            if (entries[mid].getName().compareTo(searchableName) < 0) {
                low = mid + 1;

            }else{
               high = mid;}
        }

        return low;
    }

    private static int findEndIndex (Entry[] entries, String searchableName, int low, int high) {

        while (low < high) {
            int mid = (low + high + 1) / 2;

            if (entries[mid].getName().compareTo(searchableName) > 0) {
                high = mid - 1;

            } else {
                low = mid;
            }
        }

        return high;
    }
}