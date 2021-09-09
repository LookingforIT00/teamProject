package com.sist.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class FileSerialize {

	public String serialize(FileObject test) throws Exception {
		byte[] serializedMember;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(test);
		serializedMember = baos.toByteArray();

		return Base64.getEncoder().encodeToString(serializedMember);
	}

	public FileObject deserialize(String base64Member) throws Exception {
		byte[] serializedMember = Base64.getDecoder().decode(base64Member);
		ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Object objectMember = ois.readObject();

		return (FileObject) objectMember;
	}
}