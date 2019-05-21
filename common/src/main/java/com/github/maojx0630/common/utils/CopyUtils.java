package com.github.maojx0630.common.utils;

import java.io.*;

/**
 * @author MaoJiaXing
 */
@SuppressWarnings("all")
public class CopyUtils {

	public static <T extends Serializable> T copy(T t) {
		try (ByteArrayOutputStream bo = new ByteArrayOutputStream();
		     ObjectOutputStream oo = new ObjectOutputStream(bo)) {
			oo.writeObject(t);
			try (ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			     ObjectInputStream oi = new ObjectInputStream(bi)) {
				return (T) oi.readObject();
			} catch (NotSerializableException e) {
				throw new RuntimeException(e.getMessage() + " Not Serializable");
			} catch (Exception e) {
				throw new RuntimeException("Deep copy failure");
			}
		} catch (NotSerializableException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage() + " Not Serializable");
		} catch (Exception e) {
			throw new RuntimeException("Deep copy failure");
		}
	}

}
