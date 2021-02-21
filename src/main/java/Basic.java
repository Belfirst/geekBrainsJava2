public class Basic {
    public static void main(String[] args) {

        String[][] arr = {{"1","1","1","1"},{"1","1","1","1"},{"1","1","1","1"},{"1","1","1","1"}};
        try {
            System.out.println(myException(arr));
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }


    }

    static int myException(String[][] arr) throws MyArraySizeException, MyArrayDataException{
        if(arr.length != 4 || arr[0].length != 4 || arr[1].length != 4 || arr[2].length != 4 || arr[3].length != 4) throw new MyArraySizeException();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int num;
                try {
                    num = Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    String result = "arr" +"["+ i +"]"+"["+ j +"]" + " = " + arr[i][j] + " - is not a number";
                    throw new MyArrayDataException (result);
                }
                sum += num;
            }
        }
        return sum;

    }

    static class MyArraySizeException extends Exception{
        MyArraySizeException(){
            super("Wrong array size!");
        }
    }

    static class MyArrayDataException extends Exception{
        MyArrayDataException(String massage){
            super(massage);
        }
    }

}
