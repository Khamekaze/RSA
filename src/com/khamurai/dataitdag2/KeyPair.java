package com.khamurai.dataitdag2;

import java.io.Serializable;
import java.math.BigInteger;

public class KeyPair implements Serializable {
    private static final long serialVersionUID = 4L;
    private BigInteger key;
    private BigInteger n;

    public KeyPair(BigInteger key, BigInteger n) {
        this.setKey(key);
        this.setN(n);
    }

    public BigInteger getKey() {
        return key;
    }

    public BigInteger getN() {
        return n;
    }

    public void setKey(BigInteger key) {
        this.key = key;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }
}
