public class FindParentsClasses {
    @SuppressWarnings("rawtypes")
    public static Class[] Find(Class child) {
        if (child.getInterfaces().length != 0) {
            return child.getInterfaces();
        } else {
            return new Class[] { child.getSuperclass() };
        }
    }
}
