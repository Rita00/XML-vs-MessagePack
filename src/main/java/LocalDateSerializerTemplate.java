import org.msgpack.MessageTypeException;
import org.msgpack.packer.Packer;
import org.msgpack.template.AbstractTemplate;
import org.msgpack.unpacker.Unpacker;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateSerializerTemplate extends AbstractTemplate<LocalDate> {


    public void write(Packer pk, LocalDate target, boolean required) throws IOException {
        if (target == null) {
            if (required) {
                throw new MessageTypeException("Attempted to write null");
            }
            pk.writeNil();
            return;
        }
        pk.write(target.toEpochDay());
    }

    public LocalDate read(Unpacker u, LocalDate to, boolean required) throws IOException {
        if (!required && u.trySkipNil()) {
            return null;
        }
        return LocalDate.ofEpochDay(u.readLong());
    }

    static public LocalDateSerializerTemplate getInstance() {
        return instance;
    }

    static final LocalDateSerializerTemplate instance = new LocalDateSerializerTemplate();
}
