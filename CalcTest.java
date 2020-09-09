
public class CalcTest {
    public static void main(String[] args) {
        Calc calc = new ReversePolishNotationCalculator();
        String example = "clear";
        System.out.println("---------------Example 1--------------");
        example = "5 2";
        calc.input(example);

        calc.clear();
        System.out.println("---------------Example 2--------------");
        example = "2 sqrt";
        calc.input(example);

        calc.clear();
        System.out.println("---------------Example 3--------------");
        example = "5 2 -";
        calc.input(example);
        example = "3 -";
        calc.input(example);
        example = "clear";
        calc.input(example);





        calc.clear();
        System.out.println("---------------Example 4--------------");
        example = "5 4 3 2";
        calc.input(example);
        example = "undo undo *";
        calc.input(example);
        example = "5 *";
        calc.input(example);
        example = "undo";
        calc.input(example);



        calc.clear();
        System.out.println("---------------Example 5--------------");
        example = "7 12 2 /";
        calc.input(example);
        example = "*";
        calc.input(example);
        example = "4 /";
        calc.input(example);



        calc.clear();
        System.out.println("---------------Example 6--------------");
        example = "1 2 3 4 5";
        calc.input(example);
        example = "*";
        calc.input(example);
        example = "clear 3 4 -";
        calc.input(example);




        calc.clear();
        System.out.println("---------------Example 7--------------");
        example = "1 2 3 4 5";
        calc.input(example);
        example = "* * * *";
        calc.input(example);



        calc.clear();
        System.out.println("---------------Example 8--------------");
        example = "1 2 3 * 5 + * * 6 5";
        calc.input(example);




    }
}
