package org.rev317.min.redirect;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.parabot.core.Core;

public class MessageDigestRedirect {
    public void update(MessageDigest messageDigest, byte[] arrby, int n, int n2) {
        Core.verbose("Requested MessageDigest#update(byte[], int, int)");
        messageDigest.update(arrby, n, n2);
    }

    public byte[] digest(MessageDigest messageDigest) {
        Core.verbose("Requested MessageDigest#digest()");
        return messageDigest.digest();
    }

    public byte[] digest(MessageDigest messageDigest, byte[] arrby) {
        Core.verbose("Requested MessageDigest#digest(input)");
        return messageDigest.digest(arrby);
    }

    public static MessageDigest getInstance(String string) throws NoSuchAlgorithmException {
        Core.verbose("Requested MessageDigest#getInstance");
        return MessageDigest.getInstance(string);
    }

    public void update(MessageDigest messageDigest, byte by) {
        Core.verbose("Requested MessageDigest#update(byte)");
        messageDigest.update(by);
    }

    public void update(MessageDigest messageDigest, byte[] arrby) {
        Core.verbose("Requested MessageDigest#update(byte[])");
        System.out.println("Hi3");
        messageDigest.update(arrby);
    }

    public int digest(MessageDigest messageDigest, byte[] arrby, int n, int n2) throws DigestException {
        Core.verbose("Requested MessageDigest#digest(buf, offset, len)");
        return messageDigest.digest(arrby, n, n2);
    }
}
