/**
 *
 */
package nl.cybercompany.treinadvies.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * Convenience methods for {@link Collection}s.
 *
 * @author haiko
 *
 */
public class CollectionUtil {

	/**
	 * Returns last element of a Collection. There should be elements in the {@link Collection}.
	 *
	 * @param coll {@link Collection}.
	 * @return T last element.
	 */
	public static <T> T last(Collection<T> coll){
		Iterator<T> it =  coll.iterator();
		for (T t : coll) {
			if(!it.hasNext()) {
				return t;
			}
		}
		throw new IllegalStateException("no last element?");
	}
}
