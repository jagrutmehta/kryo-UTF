/**
 * You can use this code as is or modify as per your need. You take full responsibility of testing and using it in your environment. 
 */



import java.nio.charset.Charset;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class KryoCharsetSeralizer {


	/**
	 * Use this method to register java.nio.charset classes. By default it will register UTF-8, UTF-16, UTF-16BE, UTF-16LE, ISO_8859_1 and US_ASCII. You can add more.
	 * @param kryo
	 */
	public static void register(Kryo kryo) {

		Serializer charsetCustomListSerializer = new Serializer() {
			@Override
			public void write(Kryo kryo, Output output, Object object) {
				Charset charset = (Charset) object;
				kryo.writeObject(output, charset.name());
			}

			@Override
			public Charset read(Kryo kryo, Input input, Class type) {
				return Charset.forName(kryo.readObject(input, String.class));
			}

		};
		// Register those Charset classes which are not public.
		registerCharsets(kryo, "UTF-8", charsetCustomListSerializer);
		registerCharsets(kryo, "UTF-16", charsetCustomListSerializer);
		registerCharsets(kryo, "UTF-16BE", charsetCustomListSerializer);
		registerCharsets(kryo, "UTF-16LE", charsetCustomListSerializer);
		registerCharsets(kryo, "ISO_8859_1", charsetCustomListSerializer);
		registerCharsets(kryo,"US_ASCII",charsetCustomListSerializer);


	}

	/**
	 * Safe method to register CharSets. It ignores any exception and moves on.
	 * 
	 * @param kryo
	 * @param name
	 * @param s
	 * @return
	 */
	private static boolean registerCharsets(Kryo kryo, String name, Serializer s) {
		try {
			kryo.register(Charset.forName(name).getClass(), s);
			return true;
		} catch (Throwable e) {
			System.err.println("[WARN] Unable to register charset " + name + " with Kryo. Exception is " + e.getMessage());
			return false;
		}
	}
	
	public static void main(String...argv){
		Kryo kryo = new Kryo();
		KryoCharsetSeralizer.register(kryo);
	}

}
