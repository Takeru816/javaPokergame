package kouka3.poker2;
import java.util.*;;

public class Human {
    protected String name;
    protected Human() {
        setName();
    }

    public void setName() {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("名前を入力してください：");     String name = stdIn.next();
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
