package be.technifutur.poupier.utils;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ServiceLocator implements Closeable {

    private final Map<Key<?>, Object> services = new HashMap<>();

    public ServiceLocator() {
    }

    public void register(Key key, Object service){
        services.put(key, service);
    }

    public <T> T get(Key<T> key){
        return (T) services.get( key );
    }


    public static <T> Key<T> keyFor(String name, Class<T> clazz){
        return new Key<T>(name, clazz);
    }

    @Override
    public void close() throws IOException {
        services.values().forEach((s -> {
            if (s instanceof Closeable) {
                try {
                    ((Closeable)s).close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
    }

    private static class Key<T>{

        private final String name;
        private final Class<T> clazz;

        public Key(String name, Class<T> clazz) {
            this.name = name;
            this.clazz = clazz;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (!Objects.equals(name, key.name)) return false;
            return Objects.equals(clazz, key.clazz);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
            return result;
        }
    }

}
