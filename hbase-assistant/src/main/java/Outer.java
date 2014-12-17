import java.lang.reflect.Field;

public class Outer {
    public class Inner {
    }

    public static void main(String[] args) throws Exception {

        // Create the inner instance
        Inner inner = new Outer().new Inner();

        // Get the implicit reference from the inner to the outer instance
        // ... make it accessible, as it has default visibility
        Field field = Inner.class.getDeclaredField("this$0");
        field.setAccessible(true);

        // Dereference and cast it
        Outer outer = (Outer) field.get(inner);
        System.out.println(outer);
    }
}