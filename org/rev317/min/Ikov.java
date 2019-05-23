package org.rev317.min;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

import org.parabot.core.Core;
import org.parabot.core.Directories;
import org.parabot.core.reflect.RefClass;
import org.parabot.core.reflect.RefField;

public class Ikov {
	private static boolean PARABOT_IKOV = false;

	private static String byteToHex(final byte[] bytes) {
		Formatter formatter = new Formatter();
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}
		String ret = formatter.toString(); // improved original code since they never close it!
		formatter.close();
		return ret;
	}

	private static final List<String> staffList = Arrays
			.asList(new String[] { "david", "demo", "lucas", "reece", "fergus", "mamba", "supreme", "julia", "pride",
					"ace", "autumn", "crim", "dionysos", "duke", "H A R R Y", "ivy", "lolfish", "rage", "tilt", "view",
					"Aaron", "cream", "dia duit", "Haris", "hell kid", "pixel panda", "xsj", "yogi" });

	public static boolean shouldAvoidBan() {
		try {
			String username = new RefClass(Loader.getClient()).getField(IkovData.getB()).asString();
			if (username != null) {
				return staffList.contains(username);
			}
		} catch (Exception b) {
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static void avoidBan() {
		if (Ikov.shouldAvoidBan() || PARABOT_IKOV) {
			return;
		}
		try {
			String mapFieldName = IkovData.getMapFieldName();
			String UUIDKey = "x.dat";

			File uuidjar = new File(Directories.getDefaultDirectory() + "/.parabot/uuid.jar");

			if (!uuidjar.exists()) {
				throw new Exception("Could not find anti-ban library, exiting for security reasons");
			}
			byte[] bytes = Files.readAllBytes(uuidjar.toPath());

			RefClass UIDClass = new RefClass(Loader.getClient());
			RefField mapField = UIDClass.getField(mapFieldName);
			if (mapField != null && !mapField.getTypeDesc().equals("Ljava/util/Map;")) {
				Map<String, byte[]> bytesMap = (Map<String, byte[]>) mapField.asObject();

				String originalUUIDHash = "1038d13f8eb4428cbaf8391c31e1d184c4b96536";
				String currentUUIDHash = byteToHex(MessageDigest.getInstance("SHA-1").digest(bytesMap.get(UUIDKey)));
				if (!originalUUIDHash.equals(currentUUIDHash)) {
					bytesMap.put(UUIDKey, bytes);
					PARABOT_IKOV = true;
					return;
				}

				Core.verbose("Ikov cache UUID: " + originalUUIDHash);
				Core.verbose("Original UUID:" + currentUUIDHash);
				if (Core.inVerboseMode()) {
					FileOutputStream outputStream = new FileOutputStream(
							Directories.getDefaultDirectory() + "/.parabot/ikov-uuid.jar");
					try {
						outputStream.write(bytesMap.get(UUIDKey));
					} catch (Throwable var23) {
						var23.printStackTrace();
					} finally {
						outputStream.close();
					}
					Core.verbose((new StringBuilder("UUID outputed to ")
							.append(Directories.getDefaultDirectory().getAbsolutePath())
							.append("/.parabot/ikov-uuid.jar").toString()));
					Core.verbose("Please report send this library to the Parabot staff");

				}
			} else {
				throw new Exception(
						"Ikov has updated their UUID-library, please contact the Parabot staff to ask them to update theirs");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
