import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("entities.Entity");
        Method[] methods = clazz.getDeclaredMethods();

        for (Method m : methods){
            System.out.print("Метод: " + m + ". ");
            if(m.getDeclaredAnnotations().length>0){
               for(Annotation a :m.getDeclaredAnnotations()){
                   System.out.print("Аннотация метода: " + a + " ");
               }
            }
            System.out.println();
        }
    }
}
